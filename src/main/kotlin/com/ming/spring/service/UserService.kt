package com.ming.spring.service

import com.ming.spring.bean.UserBean
import com.ming.spring.bean.UserInfoBean
import com.ming.spring.dao.UserDao
import com.ming.spring.dao.UserInfoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 容纳业务层逻辑
 * 将controller的逻辑分类，保证了controller的逻辑更加清晰。
 * 一个controller的业务逻辑可能对应多个service，一个Service逻辑可能对应多个DAO逻辑。
 * 分层增强复用，细化逻辑，便于单元测试。
 */
@Service
class UserService {

    @Autowired
    lateinit var userDao: UserDao

    @Autowired
    lateinit var infoDao: UserInfoDao

    /**
     * @param userName 用户名
     *
     * @return null:不存在该用户，UserBean查询到的用户实例
     */
    fun findUserByName(userName: String): UserBean? {
        val list = userDao.queryUser(userName)
        return if (list.isNotEmpty()) {
            list[0] as UserBean
        } else {
            null
        }
    }

    fun getUser(id: Int) = userDao.getByPrimaryKey(id)


    fun save(userBean: UserBean) = userDao.save(userBean)


    /**
     * @param userName 用户名
     *
     * @return null:不存在该用户信息，UserInfoBean查询到的用户实例
     */
    fun getUserInfo(userName: String): UserInfoBean? {
        val list = infoDao.getByName(userName)
        return if (list.isNotEmpty()) {
            list[0] as UserInfoBean
        } else {
            null
        }
    }

    /**
     * @param id 用户id
     *
     * @return null:不存在该用户信息，UserInfoBean查询到的用户实例
     */
    fun getUserInfo(id: Int) = infoDao.getByPrimaryKey(id)

    fun save(userInfoBean: UserInfoBean) = infoDao.save(userInfoBean)


}