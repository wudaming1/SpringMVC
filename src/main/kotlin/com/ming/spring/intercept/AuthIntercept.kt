package com.ming.spring.intercept

import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.InvalidClaimException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.ming.spring.bean.ErrorBean
import com.ming.spring.bean.ErrorCode
import com.ming.spring.bean.ResponseBean
import com.ming.spring.bean.ResultCode
import com.ming.spring.jwt.JWTHelper
import com.ming.spring.utils.JsonUtil
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthIntercept : HandlerInterceptor {

    /**
     *  由于不能主动调用传递链，所以Request包装类没有意义，userId可以放到Response里面。后面删除保留都可以。
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val token = request.getHeader("token")
        var errMessage = ""
        try {
            when {
                token?.startsWith("debugToken_") == true -> {
                    val userId = token.split("_").last()
//                    chain.doFilter(ModifyServletRequestWrapper(userId, request), response)
                    response.addHeader("userId", userId)
                }
                JWTHelper.verifyToken(token) -> {
                    //校验通过
                    val userId = JWTHelper.parserIdformToken(token)
//                    chain.doFilter(ModifyServletRequestWrapper(userId, request), response)
                    response.addHeader("userId", userId)
                }
                else -> errMessage = "token验证失败！token：$token"
            }
        } catch (e: AlgorithmMismatchException) {
            errMessage += e.message ?: ""
        } catch (e: SignatureVerificationException) {
            errMessage += e.message ?: ""
        } catch (e: TokenExpiredException) {
            errMessage += e.message ?: ""
        } catch (e: InvalidClaimException) {
            errMessage += e.message ?: ""
        }

        if (!errMessage.isEmpty()) {
            val error = ErrorBean(ErrorCode.TOKEN_INVALID, "", errMessage)
            val result = ResponseBean(ResultCode.FAIL, "", error)
            response.writer.println(JsonUtil.writeValueAsString(result))
            return false
        }
        return true
    }

    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
    }

    override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
    }

}