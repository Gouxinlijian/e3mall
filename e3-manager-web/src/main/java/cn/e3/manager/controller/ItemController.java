package cn.e3.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.e3.manager.service.ItemService;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemDesc;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.PageResult;
import cn.e3.utils.TreeNode;

//返回json 数据的 controller
@RestController
public class ItemController {
	
	//注入service服务对象
	@Autowired
	private ItemService itemService;
	
	
	/**
	 * 需求:根据商品id查询商品数据
	 * 请求:item/{itemId}
	 * 参数:Long itemId
	 * 返回值:TbItem
	 */
	@RequestMapping("item/{itemId}")
	public TbItem findItemById(@PathVariable Long itemId){
		//调用service服务方法
		TbItem item = itemService.findItemById(itemId);
		return item;
	}
	
	/**
	 * 需求:查询商品列表,进行分页展示
	 * 请求:/item/list
	 * 参数:Integer page,Integer rows
	 * 返回值:json格式PageResult
	 */
	@RequestMapping("/item/list")
	public PageResult findItemListByPage(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="20") Integer rows){
		//调用远程service服务代理对象方法
		PageResult result = itemService.findItemListByPage(page, rows);
		return result;
	}
	
	/**
	 * 需求:保存商品数据
	 * 参数:TbItem item,TbItemDesc itemDesc
	 * 返回值:E3mallResult
	 * 
	 */
	@RequestMapping("/item/save")
	public E3mallResult saveItem(TbItem item,TbItemDesc itemDesc){
		System.out.println("进入方法");
		E3mallResult result = itemService.saveItem(item, itemDesc);
		return result;
	}
}
