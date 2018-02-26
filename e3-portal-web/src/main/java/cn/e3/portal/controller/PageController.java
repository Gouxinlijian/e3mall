package cn.e3.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3.content.service.ContentService;
import cn.e3.utils.AdItem;
import cn.e3.utils.JsonUtils;

/**
 * 门户页面访问
 * @author 轮廓
 *
 */
@Controller
public class PageController {
	
	//注入大广告的categoryId常量
	@Value("${BIG_AD_CATEGORY_ID}")
	private Long BIG_AD_CATEGORY_ID;
	
	//注入ContentService
	@Autowired
	private ContentService contentService;
	/*
	 * 访问门户首页
	 */
	@RequestMapping("index")
	private String showPage(Model model){
		//通过contentService查询获取大广告信息集合
		List<AdItem> BIG_list = contentService.findContentByCategoryId(BIG_AD_CATEGORY_ID);
		//将集合对象转成json数据
		String BIG_Json = JsonUtils.objectToJson(BIG_list);
		//将json数据放入到model域中
		model.addAttribute("ad1",BIG_Json);
		return "index";
	}
}
