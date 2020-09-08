package com.dj.sometest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createDate",value = "")
	private Date createDate;

}
