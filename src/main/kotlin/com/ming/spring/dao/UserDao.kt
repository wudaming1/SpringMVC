package com.ming.spring.dao

import com.ming.spring.bean.UserBean
import org.hibernate.SessionFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.orm.hibernate5.support.HibernateDaoSupport


/**
 *  DAO：Data Access Object-数据访问对象
 */
class UserDao:HibernateDaoSupport(){

//    private var template: HibernateTemplate = hibernateTemplate!!

    fun findAll(): List<UserBean> {
        return hibernateTemplate!!.find("FROM UserBean") as List<UserBean>
    }

    fun find(queryString: String,value:Any): List<UserBean> {
        return hibernateTemplate!!.find(queryString,value) as List<UserBean>
    }



    fun save(userBean: UserBean): Int {
        return hibernateTemplate!!.save(userBean) as Int
    }

    fun queryExist(name: String):Boolean {
        val result = hibernateTemplate!!.find("FROM UserBean as E where E.userName = '$name'")
        return !(result as List<*>).isNotEmpty()
    }

//    fun findByProperty(key:String,value:Any){
//        val queryString = "FROM UserBean as E where E.$key = ?"
//        hibernateTemplate!!.findByNamedQuery()
//        val result = hibernateTemplate!!.find("FROM UserBean as E where E.userName = '$name'")
//    }



}