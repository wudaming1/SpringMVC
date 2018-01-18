package com.ming.spring.bean


class UserInfoBean {
    var userName: String = ""
    var imgUrl: String = ""
    var sex: String = ""
    var birthday: Long = 0
    var id:Int = 0

    constructor()
    constructor(userName: String, imgUrl: String, sex: String, birthday: Long) {
        this.userName = userName
        this.imgUrl = imgUrl
        this.sex = sex
        this.birthday = birthday
    }


}