package com.lhz.estest.commom;

/**
 * @author lhzlhz
 * @create 2021/5/19
 */
public class Result {
	Object data;
	String code;
	String message;


	public static Result success(Object data) {
		Result result = new Result();
		result.setData(data);
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	public static Result success() {
		Result result = new Result();
		result.setData(null);
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	public static Result failed() {
		Result result = new Result();
		result.setData(null);
		result.setCode("500");
		result.setMessage("success");
		return result;
	}
	public static Result failed(String msg) {
		Result result = new Result();
		result.setData(null);
		result.setCode("500");
		result.setMessage(msg);
		return result;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
