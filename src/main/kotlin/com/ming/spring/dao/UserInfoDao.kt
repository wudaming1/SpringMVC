package com.ming.spring.dao

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.orm.hibernate5.support.HibernateDaoSupport
import org.springframework.stereotype.Repository

@Repository
open class UserInfoDao : HibernateDaoSupport() {


    private lateinit var template: HibernateTemplate

    @Autowired
    open fun init(sessionFactory: SessionFactory) {
        super.setSessionFactory(sessionFactory)
        template = hibernateTemplate!!
    }

    open fun getByName(name:String):List<*>{
        return template.find("FROM UserInfoBean as E where E.userName = '$name'")
    }
}