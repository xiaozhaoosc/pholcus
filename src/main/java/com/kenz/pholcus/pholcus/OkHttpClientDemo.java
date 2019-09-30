package com.kenz.pholcus.pholcus;

import okhttp3.*;

import java.io.IOException;

/**
 * Description:
 *
 * @author kenzhao
 * @date 2019/9/30 17:36
 */
public class OkHttpClientDemo {
    //json传输方式
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //获取okHttpClient对象
    private final OkHttpClient client = (OkHttpClient) OkHttpClientObject.CLIENT.getClientInstance();

    private String result;

    /**
     * get形式,同步执行
     */
    public String get(String url) throws IOException {
        //创建请求
        Request request = new Request.Builder()
                .header("User-Agent", "*****")
                .addHeader("Accept", "*****")
                .url(url)
                .build();
        //同步执行请求，将响应结果存放到response中
        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            //处理response的响应消息
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * post形式
     */
    public String post(String url, String message) throws IOException {
        //请求体传输json格式的数据
        RequestBody requestBody = RequestBody.create(JSON, message);
        //创建请求
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "*****")
                .addHeader("Accept", "*****")
                .post(requestBody)
                .build();

        //同步请求

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步发起请求
     */
    public void pool(String url) {
        //创建请求
        Request request = new Request.Builder()
                .url(url)
                .build();

        //异步请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }
    public static void main(String[] args) {
        OkHttpClientDemo okHttpClientDemo = new OkHttpClientDemo();
        okHttpClientDemo.pool("https://api.tumblr.com/v2/blog/idolsgeneration/posts/photo?api_key=nXcMfImiJuDIhaO7qNT1VF234UhRID8yab3f5tvUoOhCMDUk3y&limit=1&offset=1");
    }
}
