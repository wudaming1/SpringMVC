package com.ming.spring.controller

import com.ming.spring.bean.ErrorBean
import com.ming.spring.bean.ResponseBean
import com.ming.spring.bean.UserBean
import com.ming.spring.bean.UserInfoBean
import com.ming.spring.jwt.JWTHelper
import com.ming.spring.service.UserService
import com.ming.spring.utils.SpringContextUtil
import com.ming.spring.utils.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.servlet.http.HttpServletResponse


@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @ResponseBody
    @RequestMapping(method = [(RequestMethod.POST)], value = ["/login"])
    fun login(@RequestParam("name") name: String, @RequestParam("password") password: String): ResponseBean {

        val localBean = userService.findUserByName(name)
        return if (localBean != null) {
            if (localBean.password != password) {
                Util.generateMessageBean(null, ErrorBean(message = "密码错误"))
            } else {
                val token = JWTHelper.generateJWT(localBean.id)
                Util.generateMessageBean(token, null)
            }
        } else {
            Util.generateMessageBean(null, ErrorBean(message = "不存在此用户"))
        }
    }

    @ResponseBody
    @RequestMapping(method = [(RequestMethod.POST)], value = ["/register"])
    fun register(@RequestParam("name") name: String, @RequestParam("password") password: String): ResponseBean {
        val localBean = userService.findUserByName(name)
        return if (localBean != null) {
            Util.generateMessageBean(null, ErrorBean(message = "用户名已存在，请登录！"))
        } else {
            val id = userService.saveUser(name, password)
            val token = JWTHelper.generateJWT(id)
            Util.generateMessageBean(token, null)
        }
    }

    @ResponseBody
    @RequestMapping(method = [(RequestMethod.GET)], value = ["/auth/userInfo"])
    fun getUserInfo(response: HttpServletResponse): ResponseBean {
        val userId = response.getHeader("userId").toInt()
        var userInfo = userService.getUserInfo(userId)

        if (userInfo == null) {
            userInfo = SpringContextUtil.getBean("userInfoBean") as UserInfoBean
            val user = userService.getUser(userId) as UserBean
            userInfo.id = userId
            userInfo.userName = user.userName
            userService.save(userInfo)
        }
        return Util.generateMessageBean(userInfo, null)
    }

    @ResponseBody
    @RequestMapping(method = [(RequestMethod.PUT)], value = ["/auth/userInfo"])
    fun modifyUserInfo(@RequestParam("name") userName: String,
                       @RequestParam("imgUrl") imgUrl: String,
                       @RequestParam("sex") sex: String,
                       @RequestParam("birthday") birthday: Long,
                       response: HttpServletResponse): ResponseBean {
        val userId = response.getHeader("userId").toInt()
        val userInfo = userService.getUserInfo(userId)
        userInfo?.apply {
            this.userName = userName
            this.birthday = birthday
            this.imgUrl = imgUrl
            this.sex = sex
            userService.update(userInfo)
        }

        return Util.generateMessageBean(null
                , if (userInfo == null) ErrorBean(message = "不存在这个用户id:$userId") else null)
    }

    @RequestMapping(method = [(RequestMethod.POST)], value = ["/auth/headImg"])
    fun uploadHeadImg(@RequestParam("file") file: MultipartFile,
                      response: HttpServletResponse): String {
        val userId = response.getHeader("userId").toInt()
        if (!file.isEmpty) {
            val fileName = file.originalFilename
            val path = SpringContextUtil.headImgPath
            val filePath = File(path, fileName)
            if (!filePath.parentFile.exists()) {
                filePath.parentFile.mkdirs()
            }

            file.transferTo(filePath)
            return if (userService.modifyHeadImg(userId, filePath.absolutePath))
                 Util.generateSuccessMessage(null)
            else Util.generateErrorMessage(ErrorBean(message = "更新数据库失败!"))

        }

        return Util.generateErrorMessage(ErrorBean(message = "未获取到文件!"))

    }


}