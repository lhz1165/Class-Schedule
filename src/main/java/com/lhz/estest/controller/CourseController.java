package com.lhz.estest.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.lhz.estest.commom.Result;
import com.lhz.estest.course_enum.CourseEnum;
import com.lhz.estest.eneity.Course;
import com.lhz.estest.eneity.Student;
import com.lhz.estest.service.ICourseService;
import com.lhz.estest.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: hz.lai
 * Date: 2021/6/16
 * Description:
 */
@Api(tags = "管理课程")
@RestController
@RequestMapping("/")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IStudentService studentService;

    @ApiOperation("删除课程")
    @RequestMapping(path = "/delCourse",method = RequestMethod.GET)
    public Result delCourse(@RequestParam Integer day, @RequestParam String timePeriod, HttpSession session) {
        String studentNo = (String) session.getAttribute("studentNo");
        Student student = studentService.lambdaQuery().eq(Student::getUserNo, studentNo).one();
        Map<String, Object> params = new HashMap<>();
        params.put("stu_id",student.getId());
        params.put("time_period",timePeriod);
        params.put("course_day",day);
        courseService.removeByMap(params);
        return Result.success();

    }
    @ApiOperation("添加或修改课程")
    @RequestMapping(path ="/addOrUpdateCourse",method = RequestMethod.GET)
    public Result addCourse(@RequestParam Integer day, @RequestParam String timePeriod, HttpSession session, @RequestParam String courseName, @RequestParam String courseAddress) {
        String studentNo = (String) session.getAttribute("studentNo");
        Student student = studentService.lambdaQuery().eq(Student::getUserNo, studentNo).one();
        LambdaQueryChainWrapper<Course> wrapper = courseService.lambdaQuery()
                .eq(Course::getStuId, student.getId())
                .eq(Course::getTimePeriod, timePeriod)
                .eq(Course::getCourseDay, day);
        Course course = wrapper.one();
        if (course == null) {
            course = new Course();
            course.setStuId(student.getId());
        }
        course.setCourseDay(day);
        course.setCourseDay(day);
        course.setTimePeriod(timePeriod);
        course.setCourseName(courseName);
        course.setCourseAddress(courseAddress);
        course.setCourseNo(CourseEnum.getCourseEnumByTimePeriod(timePeriod).getCourseNo());
        course.setCourseIndex((day-1)*13+CourseEnum.getCourseEnumByTimePeriod(timePeriod).getCourseNo());
        courseService.saveOrUpdate(course);
        return Result.success();
    }
}
