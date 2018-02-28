package cn.e3.search.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.e3.search.pojo.SolrPage;
import cn.e3.search.service.SearchItemService;

@Controller
public class SearchItemController {
	
	//注入搜索服务对象
	@Autowired
	private SearchItemService searchItemService;
	
	/**
	 * 需求:根据查询参数,查询索引库
	 * 请求:http://localhost:8085/search.html?q=
	 * 参数:String qName,Integer page,Integer rows
	 * 返回值:search.jsp
	 */
	@RequestMapping("search")
	public String search(@RequestParam(value="q") String qName,
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows,
			Model model){
		
		//乱码解决
		//解码,再编码
		try {
			qName = new String(qName.getBytes("ISO8859-1"), "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//调用远程service服务方法
		SolrPage spage = searchItemService.findSolrIndex(qName, page, rows);
		//页面回显
		//回显查询条件
		model.addAttribute("query", qName);
		//当前页
		model.addAttribute("page", page);
		//总页码
		model.addAttribute("totalPages", spage.getTotalPages());
		//总记录
		model.addAttribute("itemList", spage.getItemList());
		return "search";
	}
}
