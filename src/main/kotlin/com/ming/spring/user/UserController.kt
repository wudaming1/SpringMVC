package com.ming.spring.user

import com.ming.spring.bean.*
import com.ming.spring.jwt.JWTHelper
import com.ming.spring.utils.JsonUtil
import org.hibernate.cfg.Configuration
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
            val factory = Configuration().configure().buildSessionFactory()
            val session = factory.currentSession
            val tx = session.beginTransaction()
            val user = UserBean(name, password)
            val hql = "FROM UserBean as E where E.userName = '$name'"
            val query = session.createQuery(hql)
            val list = query.list()

            error.message = if (list.size > 0) {
                val resultBean = list[0] as UserBean
                if (user.password == resultBean.password) {
                    result.status = ResultCode.SUCCESS
                    val token = JWTHelper.generateJWT(resultBean.id)
                    result.data = token
                    "status"
                } else {
                    result.status = ResultCode.FAIL
                    error.code = ErrorCode.UNKNOWN
                    "密码错误"
                }
            } else {
                result.status = ResultCode.FAIL
                error.code = ErrorCode.UNKNOWN
                "不存在此用户"
            }

            tx.commit()
            session.close()

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
            val factory = Configuration().configure().buildSessionFactory()
            val session = factory.currentSession
            val tx = session.beginTransaction()
            val user = UserBean(name, password)
            val hql = "FROM UserBean as E where E.userName = '$name'"
            val query = session.createQuery(hql)
            val list = query.list()

            error.message = if (list.size > 0) {
                result.status = ResultCode.FAIL
                "用户名已存在，请登录！"
            } else {
                val id = session.save(user) as Int
                val token = JWTHelper.generateJWT(id)
                result.data = token
                result.status = ResultCode.SUCCESS
                "Success"
            }
            tx.commit()
            session.close()

        }

        if (result.status == ResultCode.FAIL) {
            result.error = error
        }

        response.writer.write(JsonUtil.writeValueAsString(result))
//        return JsonUtil.writeValueAsString(result)
    }
}