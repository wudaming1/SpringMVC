package com.ming.spring.dao

import com.ming.spring.bean.UserBean
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.orm.hibernate5.support.HibernateDaoSupport
import org.springframework.stereotype.Repository
import java.util.*


/**
 *  DAO：Data Access Object-数据访问对象
 *  方法和类一定不能是final类型的，所有必须声明open，应为这个Dao没有使用接口继承的方式。
 *  在生成代理的时候采用的是CGLib库，需要使用继承特性。
 */
@Repository
open class UserDao : BaseDao() {


    open fun findAll(): List<UserBean> {
        return template.find("FROM UserBean") as List<UserBean>
    }

    open fun find(queryString: String, value: Any): List<UserBean> {
        return template.find(queryString, value) as List<UserBean>
    }

    open fun getByPrimaryKey(id: Int): UserBean? {
//        template.load(UserBean::class.java,id)
        return template.get(UserBean::class.java, id)
    }

    open fun save(userBean: UserBean): Int {
        return template.save(userBean) as Int
    }

    open fun queryUser(name: String): List<*> {
        val result = template.find("FROM UserBean as E where E.userName = '$name'")
        return (result as List<*>)
    }

    fun findByProperty(key: String, value: Any) {
        val queryString = "FROM UserBean as E where E.$key = ?"
        template.findByNamedQuery("byName")
    }


}