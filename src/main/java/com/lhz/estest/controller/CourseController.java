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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


	@ApiOperation("我的课程")
	@RequestMapping(path = "/myCourse", method = RequestMethod.GET)
	public Result myClourse(HttpSession session) {
		String studentNo = (String) session.getAttribute("studentNo");
		Student student = studentService.lambdaQuery().eq(Student::getUserNo, studentNo).one();
		List<Course> courses = courseService.lambdaQuery().eq(Course::getStuId, student.getId()).list();
		Map<Integer, List<Course>> row2Courses = new HashMap<>();
		for (int i = 1; i <= 13; i++) {
			row2Courses.put(i, initAll());
		}
		Map<Integer, List<Course>> mycourse = courses.stream().collect(Collectors.groupingBy(Course::getCourseNo));
		//表格里面每个格子都填好了 7*13的表格
		fillCourse(row2Courses, mycourse);
		return Result.success(row2Courses);
	}

	public List<Course> initAll() {
		List<Course> rowAllCourse = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			Course course = new Course();
			course.setCourseName("white");
			rowAllCourse.add(course);
		}
		return rowAllCourse;
	}

	public void fillCourse(Map<Integer, List<Course>> row2Courses, Map<Integer, List<Course>> mycourse) {
		for (int i = 1; i <= 13; i++) {
			//这行（no）有课程的的列表
			List<Course> myCourse = mycourse.get(i);
			if (myCourse != null) {
				for (Course course : myCourse) {
					int len = course.getCourseLen();
					for (int j = course.getCourseNo(); j < course.getCourseNo() + len; j++) {
						List<Course> rowCourse = row2Courses.get(j);
						rowCourse.set(course.getCourseDay() - 1, course);
					}
				}
			}


		}
	}


	@ApiOperation("删除课程")
	@RequestMapping(path = "/delCourse", method = RequestMethod.GET)
	public Result delCourse(@RequestParam Integer day, @RequestParam String startTime, HttpSession session) {
		String studentNo = (String) session.getAttribute("studentNo");
		Student student = studentService.lambdaQuery().eq(Student::getUserNo, studentNo).one();
		Map<String, Object> params = new HashMap<>();
		params.put("stu_id", student.getId());
		params.put("start_time", startTime);
		params.put("course_day", day);
		courseService.removeByMap(params);
		return Result.success();

	}

	@ApiOperation("添加或修改课程")
	@RequestMapping(path = "/addOrUpdateCourse", method = RequestMethod.GET)
	public Result addCourse(@RequestParam Integer day,
							@RequestParam String startTime,
							@RequestParam String endTime,
							HttpSession session,
							@RequestParam String courseName,
							@RequestParam String courseAddress,
							@RequestParam Integer courseStartNo,
							@RequestParam Integer courseEndNo) {
		String studentNo = (String) session.getAttribute("studentNo");
		Student student = studentService.lambdaQuery().eq(Student::getUserNo, studentNo).one();
		LambdaQueryChainWrapper<Course> wrapper = courseService.lambdaQuery()
				.eq(Course::getStuId, student.getId())
				.eq(Course::getStartTime, startTime)
				.eq(Course::getEndTime, endTime)
				.eq(Course::getCourseDay, day);
		Course course = wrapper.one();
		boolean isAdd = false;
		if (course == null) {
			course = new Course();
			course.setStuId(student.getId());
			isAdd = true;
		}
		course.setCourseLen(courseEndNo - courseStartNo);
		course.setCourseDay(day);
		course.setCourseDay(day);
		course.setStartTime(startTime);
		course.setEndTime(endTime);
		course.setCourseNo(courseStartNo);
		course.setCourseName(courseName);
		course.setCourseAddress(courseAddress);
		courseService.saveOrUpdate(course);
		if (isAdd) {
			Result.success("新增成功");
		}
		return Result.success("修改成功");
	}
}
