package com.dj.sometest.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dj.sometest.util.HttpUtils;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * @Author: Chris
 * @Date: 2020/11/12 10:04
 */
public class Test2 {

    public static void main(String[] args){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://cpdc.chinapost.com.cn/web/index.php?m=postsearch&c=index&a=ajax_addr&searchkey=安徽省安庆市宿松县";
        Map<String, Object> map = HttpUtils.doGet(url);

        String result = restTemplate.getForObject(url, String.class);
        System.out.println(map);
        System.out.println(result);


        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.getJSONArray("rs").getJSONObject(1).getString("POSTCODE");
        System.out.println(code);


        /*return Optional.ofNullable(jsonResp)
                .map(jsonObject -> jsonObject.getJSONArray("rs"))
                .filter(jsonArray -> jsonArray.size() > 0)
                // 地址不精确导致找到多个默认取第一个
                .map(jsonArray -> jsonArray.getJSONObject(0))
                .map(jsonObject -> jsonObject.getString("POSTCODE"))
                .orElse(StringUtils.EMPTY);*/

    }
}
