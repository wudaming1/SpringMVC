package com.ming.spring.utils

import com.ming.spring.bean.ErrorBean
import com.ming.spring.bean.ResponseBean
import com.ming.spring.bean.ResultCode

object Util{

    fun generateSuccessMessage(bean:Any?):String{
        val result = ResponseBean()
        result.data = if (bean == null)"" else JsonUtil.writeValueAsString(bean)
        return JsonUtil.writeValueAsString(result)
    }

    fun generateMessageBean(bean:Any? = null,errorBean: ErrorBean? = null): ResponseBean {
        val result = ResponseBean()
        if (errorBean != null){
            result.status = ResultCode.FAIL
            result.error = errorBean
        }
        result.data = if (bean == null)"" else JsonUtil.writeValueAsString(bean)
        return result
    }

    /**
     * @param errorBean 一定不能为空，需要根据errorBean处理提示。
     */
    fun generateErrorMessage(errorBean: ErrorBean):String{
        val result = ResponseBean(status = ResultCode.FAIL)
        result.error = errorBean
        return JsonUtil.writeValueAsString(result)
    }

}