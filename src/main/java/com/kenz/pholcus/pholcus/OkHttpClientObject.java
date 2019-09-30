package com.kenz.pholcus.pholcus;

//import feign.okhttp.OkHttpClient;
//import okhttp3.OkHttpClient;

import okhttp3.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;


/**
 * Description:
 *
 * @author kenzhao
 * @date 2019/9/30 17:32
 */
public enum  OkHttpClientObject{
    CLIENT;
    private OkHttpClient clientInstance;

    private Integer connectTimeout_time = 10;
    private Integer writeTimeout_time = 10;
    private Integer readTimeout_time = 30;

    OkHttpClientObject() {

        Proxy proxyTest = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 1080));

        clientInstance = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout_time, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout_time, TimeUnit.SECONDS)
                .readTimeout(readTimeout_time, TimeUnit.SECONDS)
                .proxy(proxyTest)
                .retryOnConnectionFailure(true)
                .build();
    }

    public OkHttpClient getClientInstance() {
        return clientInstance;
    }
}
