package com.lhz.estest.eneity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lhzlhz
 * @create 2021/5/22
 */
@Data
@TableName(value = "student")
public class Student {
	@TableId(value = "id",type = IdType.AUTO)//指定自增策略
	Integer id;
	String userNo;
	String name;
	String password;
}
