package com.ming.spring

import com.ming.spring.bean.UserInfoBean
import org.hibernate.cfg.Configuration
import org.junit.Test

class Hibernate {

    @Test
    fun testSave() {
        val name = "abc"
        val password = "123456"
        val userInfo = UserInfoBean()
        userInfo.userName = name
        userInfo.birthday = System.currentTimeMillis() - 10 * 365 * 24 * 60 * 60 * 1000L
        userInfo.sex = "男"
        val factory = Configuration().configure().buildSessionFactory()
        val session = factory.currentSession
        val tx = session.beginTransaction()
        session.save(userInfo)
        tx.commit()
        session.close()

    }


    @Test
    fun testQuery() {
        val name = "abc"
        val password = "123456"
        val userInfo = UserInfoBean()
        userInfo.userName = name
        userInfo.birthday = System.currentTimeMillis() - 10 * 365 * 24 * 60 * 60 * 1000L
        userInfo.sex = "男"
        val factory = Configuration().configure().buildSessionFactory()
        val session = factory.currentSession
        val tx = session.beginTransaction()
        val query = session.createQuery("FROM UserInfoBean")
        val list = query.list()
        list?.apply { print(size) }
        tx.commit()
        session.close()
    }

    @Test
    fun testWhereQuery() {
        val name = "wudaming"
        val password = "123456"
        val userInfo = UserInfoBean()
        userInfo.userName = name
        userInfo.birthday = System.currentTimeMillis() - 10 * 365 * 24 * 60 * 60 * 1000L
        userInfo.sex = "男"
        val factory = Configuration().configure().buildSessionFactory()
        val session = factory.currentSession
        val tx = session.beginTransaction()
        val query = session.createQuery("FROM UserInfoBean as E where E.userName = 'wudaming'")
        val list = query.list()
        list?.apply { print(size) }
        tx.commit()
        session.close()
    }
}