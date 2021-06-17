package com.lhz.estest.eneity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by: hz.lai
 * Date: 2021/6/16
 * Description:
 */
@Data
@TableName(value = "course")
public class Course {
   @TableId(value = "id",type = IdType.AUTO)//指定自增策略
   Integer id;

   String courseName;
   /*在第几节*/
   Integer courseNo;

   String courseAddress;

   Integer stuId;
   /*星期几的课*/
   Integer courseDay;

   String startTime;

   String endTime;
   /*几节课那么长*/
   Integer courseLen;

   String courseColor;


}
