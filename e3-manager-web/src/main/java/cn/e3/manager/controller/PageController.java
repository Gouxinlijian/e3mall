package cn.e3.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	
	/**
	 * 需求:页面跳转
	 * 特点:
	 * 首页:localhost:8081/index
	 * 商品添加:localhost:8081/item-add
	 * 商品列表:localhost:8081/item-list
	 * 根据以上规律:
	 * 请求名称其实等于页面名称
	 * 因此: 可以把请求当成参数接受,返回参数(页面名称),可以匹配所有页面跳转请求
	 */
	@RequestMapping("{page}")
	public String showPage(@PathVariable String page){
		return page;
	}

}
