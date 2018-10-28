package com.wp.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类，将请求的数据按json的格式返回给前台请求，并且可以增加自己想要返回的数据
 * 这个类是自己封装的，可以直接向前台返回json格式的对象，不用再经过视图解析器了。
 * @author Administrator
 *
 */
public class Msg {
	
	//状态码100 表示成功   200表示失败
	private int code;
	
	//提示信息
	private String msg;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	//要返回给浏览器的数据,把要返回给请求客户端的数据封装到map中返回
	private Map<String, Object> extend = new HashMap<>();
	
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}
	
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}
	
	public Msg add(String key,Object value) {
		//向调用对象的map集合中添加要返回给浏览器的内容
		this.getExtend().put(key, value);
		return this;		//返回调用的对象
	}

}
