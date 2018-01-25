package com.ming.spring.service

import com.ming.spring.bean.UserBean
import com.ming.spring.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 容纳业务层逻辑
 * 将controller的逻辑分类，保证了controller的逻辑更加清晰。
 */
@Service
class UserService {

    @Autowired
    lateinit var dao: UserDao

    /**
     * @param userName 用户名
     *
     * @return null:不存在该用户，UserBean查询到的用户实例
     */
    fun findUserByName(userName:String):UserBean?{
        val list = dao.queryUser(userName)
        return if (list.isNotEmpty()){
            list[0] as UserBean
        }else{
            null
        }
    }

    fun save(userBean: UserBean)= dao.save(userBean)


}