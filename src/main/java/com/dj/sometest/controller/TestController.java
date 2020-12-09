package com.dj.sometest.controller;

import com.alibaba.excel.EasyExcel;
import com.dj.sometest.entity.User;
import com.dj.sometest.mapper.UserMapper;
import com.dj.sometest.util.ExcelListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: Chris
 * @Date: 2020/5/22 23:28
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserMapper userMapper;


    /**
     * 导出
     */
    @GetMapping("/write")
    public void test(){
        String fileNmae = "D:\\write.xlsx";
        List<User> list = userMapper.selectList(null);
        EasyExcel.write(fileNmae, User.class).sheet("用户表").doWrite(list);
    }


    /**
     * 导入
     * @param file
     * @throws IOException
     */
    @PostMapping("/read")
    public void test2(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelListener listener = new ExcelListener(userMapper);
        EasyExcel.read(inputStream, User.class,listener).sheet().doRead();
        List<User> list = listener.getList();
        System.out.println(list);
    }

    @GetMapping("/test3")
    public void test3(HttpServletResponse response) throws IOException {
        String filename = "标准版运单导入模板.xlsx";
        String headerValue = "attachment;";
        headerValue += " filename=\"" + encodeURIComponent(filename) + "\";";
        headerValue += " filename*=utf-8''" + encodeURIComponent(filename);
        response.setHeader("Content-Disposition", headerValue);
        List<User> list = userMapper.selectList(null);
        EasyExcel.write(response.getOutputStream(), User.class).sheet("用户表").doWrite(list);
    }

    public static String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
