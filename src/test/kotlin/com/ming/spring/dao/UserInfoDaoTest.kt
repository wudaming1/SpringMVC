package com.ming.spring.dao

import com.ming.spring.utils.SpringContextUtil
import org.junit.Test
import org.springframework.context.support.FileSystemXmlApplicationContext


class UserInfoDaoTest{
    @Test
    fun testQueryByName() {
        val name = "abc"
        val context = FileSystemXmlApplicationContext("//Users/wudaming/IdeaProjects/SpringMVC/src/main/webapp/WEB-INF/dispatcher-servlet.xml")
        SpringContextUtil.setApplicationContext(context)
        val userInfoDao = SpringContextUtil.getBean("userInfoDao") as UserInfoDao
        val userInfoBean = userInfoDao.getByName(name)
        print(userInfoBean[0].toString())

    }
}
