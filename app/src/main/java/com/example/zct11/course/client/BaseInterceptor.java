package com.example.zct11.course.client;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ting on 2017/9/17.
 */

public class BaseInterceptor implements Interceptor {
    private Map<String,String> headers;
    public BaseInterceptor(Map<String,String> headers){
        this.headers=headers;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder=chain.request().newBuilder();
        if(headers!=null&&headers.size()>0){
            Set<String> keys=headers.keySet();
            for (String headerKey:keys) {
                builder.addHeader(headerKey,headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }
}
