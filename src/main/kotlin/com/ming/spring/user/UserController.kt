package com.ming.spring.user

import com.ming.spring.bean.*
import com.ming.spring.dao.UserDao
import com.ming.spring.jwt.JWTHelper
import com.ming.spring.utils.JsonUtil
import com.ming.spring.utils.SpringContextUtil
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse


@RestController
class UserController {

    @RequestMapping(method = [(RequestMethod.POST)], value = ["/login"])
    fun login(@RequestParam("name") name: String, @RequestParam("password") password: String,response: HttpServletResponse) {
        val result = ResponseBean()
        val error = ErrorBean()
        if (name.isEmpty()) {
            result.status = ResultCode.FAIL
            error.message = "用户名为空！"
            error.code = ErrorCode.PARAM_ERR
        }

        if (password.isEmpty()) {
            result.status = ResultCode.FAIL
            error.message += "密码为空！"
            error.code = ErrorCode.PARAM_ERR
        }

        if (result.status != ResultCode.FAIL) {
            val dao = SpringContextUtil.getBean("userDao") as UserDao
            val queryResult = dao.queryExist(name)
            if (queryResult.isNotEmpty()){
                val localBean = queryResult[0] as UserBean
                if (localBean.password != password){
                    error.message = "密码错误"
                    result.status = ResultCode.FAIL
                    error.code = ErrorCode.UNKNOWN
                }else{
                    result.status = ResultCode.SUCCESS
                    val token = JWTHelper.generateJWT(localBean.id)
                    result.data = token
                }
            }else{
                result.status = ResultCode.FAIL
                error.code = ErrorCode.UNKNOWN
                error.message = "不存在此用户"
            }
        }

        if (result.status == ResultCode.FAIL) {
            result.error = error
        }
         response.writer.write(JsonUtil.writeValueAsString(result))
    }

    @RequestMapping(method = [(RequestMethod.POST)], value = ["/register"])
    fun register(@RequestParam("name") name: String, @RequestParam("password") password: String,response: HttpServletResponse) {
        val result = ResponseBean()
        val error = ErrorBean()
        if (name.isEmpty()) {
            result.status = ResultCode.FAIL
            error.message = "用户名为空！"
            error.code = ErrorCode.PARAM_ERR
        }

        if (password.isEmpty()) {
            result.status = ResultCode.FAIL
            error.message += "密码为空！"
            error.code = ErrorCode.PARAM_ERR
        }

        if (result.status != ResultCode.FAIL) {
            val dao = SpringContextUtil.getBean("userDao") as UserDao
            val list = dao.queryExist(name)

            error.message = if (list.isNotEmpty()) {
                result.status = ResultCode.FAIL
                "用户名已存在，请登录！"
            } else {
                val id = dao.save(UserBean(name,password))
                val token = JWTHelper.generateJWT(id)
                result.data = token
                result.status = ResultCode.SUCCESS
                "Success"
            }
        }

        if (result.status == ResultCode.FAIL) {
            result.error = error
        }

        response.writer.write(JsonUtil.writeValueAsString(result))
    }
}