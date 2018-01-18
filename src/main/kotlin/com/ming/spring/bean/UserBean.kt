package com.ming.spring.bean


class UserBean{
    var id: Int = 0
    var userName: String = ""
    var password: String = ""
    constructor()
    constructor(userName: String, password: String) {
        this.userName = userName
        this.password = password
    }


}