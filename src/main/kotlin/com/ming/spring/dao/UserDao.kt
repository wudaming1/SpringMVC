package com.ming.spring.dao

import com.ming.spring.bean.UserBean
import org.springframework.orm.hibernate5.support.HibernateDaoSupport


/**
 *  DAO：Data Access Object-数据访问对象
 */
open class UserDao:HibernateDaoSupport(){

//    private var template: HibernateTemplate = hibernateTemplate!!

    open fun findAll(): List<UserBean> {
        return hibernateTemplate!!.find("FROM UserBean") as List<UserBean>
    }

    open fun find(queryString: String,value:Any): List<UserBean> {
        return hibernateTemplate!!.find(queryString,value) as List<UserBean>
    }

    open fun save(userBean: UserBean): Int {
        return hibernateTemplate!!.save(userBean) as Int
    }

    open fun queryExist(name: String): List<*> {
        val result = hibernateTemplate!!.find("FROM UserBean as E where E.userName = '$name'")
        return (result as List<*>)
    }

    fun findByProperty(key:String,value:Any){
        val queryString = "FROM UserBean as E where E.$key = ?"
        hibernateTemplate!!.findByNamedQuery("byName")
    }



}