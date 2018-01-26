package com.ming.spring.bean

import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "user")
class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    @Column
    var userName: String = ""
    @Column
    var password: String = ""
    constructor()
    constructor(userName: String, password: String) {
        this.userName = userName
        this.password = password
    }

    override fun toString(): String {
        return "UserBean(id=$id, userName='$userName', password='$password')"
    }


}