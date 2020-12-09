package com.dj.sometest.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Author: Chris
 * @Date: 2020/8/19 20:18
 */
@Slf4j
public class JsonUtil {

    //封装创建json文件的方法
    public static InputStream createJSONFile(Object obj){

        try {

            String jsonString = JSON.toJSONString(obj);

            if(jsonString.indexOf("'")!=-1){
                //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("'", "\\'");
            }
            if(jsonString.indexOf("\"")!=-1){
                //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                jsonString = jsonString.replaceAll("\"", "\\\"");
            }

            if(jsonString.indexOf("\r\n")!=-1){
                //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
                jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");
            }
            if(jsonString.indexOf("\n")!=-1){
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                jsonString = jsonString.replaceAll("\n", "\\u000a");
            }
            // 将格式化后的字符串写入文件
            /*Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            log.info("文件创建成功！");
            write.flush();
            write.close();*/

            ByteArrayInputStream inputStream = new ByteArrayInputStream(jsonString.getBytes());

            return inputStream;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
