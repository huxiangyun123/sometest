package com.dj.sometest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author leifengyang
 * @email lfy@atguigu.com
 * @date 2020-07-29 20:26:06
 */
@ApiModel
@Data
@TableName("user")
public class UserEntity implements Serializable {
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
	@ApiModelProperty(name = "password",value = "")
	private String password;
	/**
	 * 
	 */
	@ApiModelProperty(name = "createDate",value = "")
	private Date createDate;

}
