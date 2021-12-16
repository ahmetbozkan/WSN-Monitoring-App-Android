package com.intalalab.wsnmonitoring.data.remote.util

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class NullResponseConverter : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(
            this,
            type,
            annotations
        )

        return object : Converter<ResponseBody, Any> {
            override fun convert(value: ResponseBody): Any? {
                if (value.contentLength().toInt() == 0)
                    return null

                return delegate.convert(value)
            }

        }
    }
}