package com.ming.spring.dao

import com.ming.spring.bean.UserInfoBean
import org.springframework.stereotype.Repository

@Repository
open class UserInfoDao : BaseDao() {


    open fun getByName(name: String): List<*> {
        return template.find("FROM UserInfoBean as E where E.userName = '$name'")
    }

    open fun getByPrimaryKey(id: Int): UserInfoBean? {
        return template.get(UserInfoBean::class.java, id)
    }


    open fun save(userInfoBean: UserInfoBean, id: Int = -1): Int {
        if (id >= 0)
            userInfoBean.id = id
        return template.save(userInfoBean) as Int
    }

    open fun update(userInfoBean: UserInfoBean, id: Int = -1){
        if (id >= 0)
            userInfoBean.id = id
        template.update(userInfoBean)
    }


}