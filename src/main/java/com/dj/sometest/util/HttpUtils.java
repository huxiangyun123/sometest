package com.dj.sometest.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装http get post
 */
@Slf4j
public class HttpUtils {

    /**
     * get方法
     */
    public static Map<String, Object> doGet(String url) {

        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 连接超时
                .setConnectionRequestTimeout(5000)// 请求超时
                .setSocketTimeout(5000).setRedirectsEnabled(true) // 允许自动重定向
                .build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String jsonResult = EntityUtils.toString(httpResponse.getEntity());
                map = JSON.parseObject(jsonResult, map.getClass());
            }
        } catch (Exception e) {
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            log.error("doGet-" + stackTraceElement.getMethodName() + "--" + stackTraceElement.getLineNumber());
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
            }
        }
        return map;
    }

    /**
     * 封装post
     */
    public static String doPost(String url, String data, int timeout) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout) // 连接超时
                .setConnectionRequestTimeout(timeout)// 请求超时
                .setSocketTimeout(timeout).setRedirectsEnabled(true) // 允许自动重定向
                .build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "text/html; chartset=UTF-8");

        if (data != null && data instanceof String) { // 使用字符串传参
            StringEntity stringEntity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(stringEntity);
        }

        try {

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(httpEntity);
                return result;
            }

        } catch (Exception e) {
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            log.error(
                    "doPost-" + stackTraceElement.getMethodName() + "--" + stackTraceElement.getLineNumber());

        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }


    public static String httpPostWithJson(String json,String url){

        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");

            // 构建消息实体
            StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);


            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                //成功
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
