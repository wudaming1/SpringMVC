package com.ming.spring

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController{
    @RequestMapping("/greeting/{name}")
    fun greeting(@PathVariable("name") name:String):String{

        return "hello, "+ name
    }

}