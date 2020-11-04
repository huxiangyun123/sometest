package com.dj.sometest.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.web.client.RestTemplate;
import net.sf.json.JSONObject;
import java.util.Optional;

/**
 * @Author: Chris
 * @Date: 2020/10/30 10:03
 */
public class YouBian {

    private static final String URL_PREFIX = "http://cpdc.chinapost.com.cn/web/index.php?m=postsearch&c=index&a=ajax_addr&searchkey=";

    /**
     * 通过地址获取邮编信息
     * @param addr -> 地址
     * @return postcode -> 邮编
     */
    public static String getPostCodeByAddr(String addr) {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(URL_PREFIX + addr, String.class);
        JSONObject jsonResp = JSONObject.fromObject(response);
        //JSONObject jsonResp = JSON.parseObject(response);


        //String code = jsonResp.getJSONObject("rs").getString("POSTCODE");
        String code = jsonResp.getJSONArray("rs").getJSONObject(0).getString("POSTCODE");
        System.out.println(code);
        return code;
       /* return Optional.ofNullable(jsonResp)
                .map(jsonObject -> jsonObject.getJSONArray("rs"))
                .filter(jsonArray -> jsonArray.size() > 0)
                // 地址不精确导致找到多个默认取第一个
                .map(jsonArray -> jsonArray.getJSONObject(0))
                .map(jsonObject -> jsonObject.getString("POSTCODE"))
                .orElse(StringUtils.EMPTY);*/
    }

    public static void main(String[] args) {
        System.out.println(getPostCodeByAddr("安徽省安庆市宿松县"));
    }
}
