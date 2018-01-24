import com.ming.spring.bean.UserBean
import com.ming.spring.bean.UserInfoBean
import com.ming.spring.dao.UserDao
import org.hibernate.cfg.Configuration
import org.junit.Test
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.context.support.FileSystemXmlApplicationContext


class UserDaoTest{

    @Test
    fun testSave() {
        val name = "abc"
        val password = "123456"
        val context = FileSystemXmlApplicationContext("//Users/wudaming/IdeaProjects/SpringMVC/src/main/webapp/WEB-INF/dispatcher-servlet.xml")
        val user = context.getBean("user") as UserBean
        user.userName = name
        user.password = password

        val dao = context.getBean("userDao") as UserDao
        dao.save(user)
//        dao.findAll().listIterator().forEach { print(it.userName + "\n") }

    }
}