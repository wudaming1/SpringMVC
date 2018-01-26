package com.ming.spring.bean

import org.hibernate.annotations.GenericGenerator
import org.springframework.stereotype.Component
import javax.persistence.*


@Component
@Entity
@Table(name="user_info")
class UserInfoBean {
    @Column
    var userName: String = ""
    @Column
    var imgUrl: String = ""
    @Column
    var sex: String = ""
    @Column
    var birthday: Long = 0
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    var id:Int = 0

    constructor()
    constructor(userName: String, imgUrl: String, sex: String, birthday: Long) {
        this.userName = userName
        this.imgUrl = imgUrl
        this.sex = sex
        this.birthday = birthday
    }

    override fun toString(): String {
        return "UserInfoBean(userName='$userName', imgUrl='$imgUrl', sex='$sex', birthday=$birthday, id=$id)"
    }


}