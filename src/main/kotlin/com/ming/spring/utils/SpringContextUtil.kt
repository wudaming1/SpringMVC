package com.ming.spring.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

object SpringContextUtil : ApplicationContextAware {

    private lateinit var context: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    fun getContext() = context

    fun getBean(name: String) = context.getBean(name)


}
