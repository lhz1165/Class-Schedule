package com.lhz.estest.controller;

import com.lhz.estest.commom.Result;
import com.lhz.estest.eneity.User;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2021/5/22
 */
@RestController
public class ListController {

	@GetMapping("userList")
	public Result getUserList(@RequestParam Integer page,@RequestParam Integer limit) {
		List<User> users = new ArrayList<>();
		for (int i = 1; i < 150; i++) {
			User user = new User();
			user.setId(i);
			user.setUsername("aaa" + i);
			user.setPassword("1234567");
			users.add(user);
		}
		int i = users.size() / limit;
		List<User> users1 = users.subList((page - 1) * i,(page - 1)* i + limit);
		return Result.success(new Page<User>(page,users.size(),users1));
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
