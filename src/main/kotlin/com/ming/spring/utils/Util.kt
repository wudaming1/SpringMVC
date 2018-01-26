package com.ming.spring.utils

import com.ming.spring.bean.ResponseBean

object Util{

    fun generateSuccessMessage(bean:Any):String{
        val result = ResponseBean()
        result.data = bean
        return JsonUtil.writeValueAsString(result)
    }

}