package cn.e3.manager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.e3.content.service.ContentService;
import cn.e3.pojo.TbContent;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.PageResult;

/**
 * 广告内容Controller
 * @author 轮廓
 *
 */
@RestController
public class ContentController {
	//注入广告内容服务对象
	@Autowired
	private ContentService contentService;
	
	/**
	 * 需求:根据分类id查询分类内容信息
	 * 请求:/content/query/list
	 * 参数:Long categoryId,Integer page,Integer rows
	 * 返回值:PageResult
	 * 
	 */
	@RequestMapping("/content/query/list")
	public PageResult findContentByPage(Long categoryId,
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="20")Integer rows){
		//调用远程服务方法
		PageResult result = contentService.findContentListByPage(categoryId, page, rows);
		return result;
	}
	
	/**
	 * 需求:保存广告内容数据
	 * 请求:/content/save
	 * 参数:TbContent content
	 * 返回值:E3mallResult
	 */
	@RequestMapping("/content/save")
	public E3mallResult saveContent(TbContent content){
		//调用远程服务方法
		return contentService.saveContent(content);
	}
	
}
