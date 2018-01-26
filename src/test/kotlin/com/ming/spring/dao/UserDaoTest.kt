package com.ming.spring.dao

import com.ming.spring.bean.UserBean
import com.ming.spring.dao.UserDao
import com.ming.spring.utils.SpringContextUtil
import org.junit.Test
import org.springframework.context.support.FileSystemXmlApplicationContext


class UserDaoTest{

    init {
        val context = FileSystemXmlApplicationContext("//Users/wudaming/IdeaProjects/SpringMVC/src/main/webapp/WEB-INF/dispatcher-servlet.xml")
        SpringContextUtil.setApplicationContext(context)
    }

    @Test
    fun testSave() {
        val name = "abcd"
        val password = "123456"
        val context = FileSystemXmlApplicationContext("//Users/wudaming/IdeaProjects/SpringMVC/src/main/webapp/WEB-INF/dispatcher-servlet.xml")
        val user = context.getBean("userBean") as UserBean
        user.userName = name
        user.password = password

        val dao = context.getBean("userDao") as UserDao
        dao.save(user)
//        userDao.findAll().listIterator().forEach { print(it.userName + "\n") }

    }

    @Test
    fun testQueryByPrimeryKey(){
        val id = 1
        val dao = SpringContextUtil.getBean("userDao") as UserDao
        val user = dao.getByPrimaryKey(id)
        print(user.toString())

    }
}