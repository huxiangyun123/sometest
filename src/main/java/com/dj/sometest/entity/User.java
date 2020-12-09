package com.dj.sometest.entity;

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

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "")
	private Integer id;
	/**
	 * 
	 */
	@ApiModelProperty(name = "username",value = "")
	private String username;
	/**
	 * 
	 */
	@ApiModelProperty(name = "age",value = "")
	private Integer age;
	/**
	 * 
	 */
	@ApiModelProperty(name = "createtime",value = "")
	private String createtime;

	public User(Integer id, String username, Integer age) {
		this.id = id;
		this.username = username;
		this.age = age;
	}
}
