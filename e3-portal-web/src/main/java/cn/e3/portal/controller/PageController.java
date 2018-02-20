package cn.e3.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 门户页面访问
 * @author 轮廓
 *
 */
@Controller
public class PageController {
	
	/*
	 * 访问门户首页
	 */
	@RequestMapping("index")
	private String showPage(){
		return "index";
	}

}
