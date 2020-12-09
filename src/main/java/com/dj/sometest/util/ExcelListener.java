package com.dj.sometest.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dj.sometest.entity.User;
import com.dj.sometest.mapper.UserMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris
 * @Date: 2020/7/4 12:21
 */
@Data
public class ExcelListener extends AnalysisEventListener<User> {

    private UserMapper userMapper;

    private List<User> list = new ArrayList();

    //一行一行读取数据，不会读取表头
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        list.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    public ExcelListener(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
