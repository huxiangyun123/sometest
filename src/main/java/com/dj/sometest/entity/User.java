package com.dj.sometest.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Chris
 * @date 2020-09-25 18:23:35
 */
@ApiModel
@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;


	@ExcelProperty("用户id")
	private Integer id;

	@ExcelProperty("用户名称")
	private String username;

	@ExcelProperty("密码")
	private String password;

	@ExcelProperty("创建时间")
	private Date createTime;

	private Integer age;

	public User(Integer id, String username) {
		this.id = id;
		this.username = username;
	}
}
