package com.ming.spring.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

object SpringContextUtil : ApplicationContextAware {

    private lateinit var context: ApplicationContext

    val resourcePath = "/Users/wudaming/IdeaProjects/SpringMVC/resources"

    val imagePath = resourcePath + "/images"

    val headImgPath = imagePath + "/headImg"

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    fun getContext() = context

    fun getBean(name: String) = context.getBean(name)

    fun getResources(location: String) = context.getResource(location)
}
