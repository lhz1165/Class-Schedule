package com.lhz.estest.controller;

import com.lhz.estest.commom.Result;
import com.lhz.estest.eneity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2021/5/22
 */
@RestController
public class ListController {

	@GetMapping("userList")
	public Result getUserList(@RequestParam Integer page,@RequestParam Integer limit) {
		List<Student> students = new ArrayList<>();
		for (int i = 1; i < 150; i++) {
			Student student = new Student();
			student.setId(i);
			student.setName("aaa" + i);
			student.setPassword("1234567");
			students.add(student);
		}
		int i = students.size() / limit;
		List<Student> users1 = students.subList((page - 1) * i,(page - 1)* i + limit);
		return Result.success(new Page<Student>(page, students.size(),users1));
	}
	static class  Page<T>{
		int page;
		int total;
		List<T> item;

		public Page(int page, int total, List<T> item) {
			this.page = page;
			this.total = total;
			this.item = item;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public List<T> getItem() {
			return item;
		}

		public void setItem(List<T> item) {
			this.item = item;
		}
	}
}
