package cn.e3.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.e3.content.service.ContentCategoryService;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.TreeNode;

/**
 * 广告分类Controller
 * @author 轮廓
 *
 */
@RestController
public class ContentCategoryController {

	//注入广告服务对象
	//1,引入广告服务接口
	//2,dubbo引入广告服务服务
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 需求:根据父id查询树形节点
	 * 请求:/content/category/list
	 * 参数:Long parentId
	 * 返回值:json格式List<TreeNode>
	 * 业务:
	 * 1,初始化顶级节点 ,设置默认值为0
	 * 2,easyUI加载树形节点时候发送参数名称是id
	 */
	@RequestMapping("/content/category/list")
	public List<TreeNode> findContentCategoryTreeNodeList(@RequestParam(defaultValue="0",value="id") Long parentId){
		//调用远程广告内容服务对象,实现广告分类查询
		List<TreeNode> list = contentCategoryService.findcontentCategoryTreeNodeList(parentId);
		return list;
	}
	
	/**
	 * 需求:创建一个新的广告分类
	 * 参数:Long parentId,String name
	 * 返回值:E3mallResult
	 * 业务:
	 * 1,当新建节点父节点是子节点的时候,修改父节点状态
	 * 2,当新建节点的父节点是 父节点,直接添加即可
	 */
	@RequestMapping("/content/category/create")
	public E3mallResult createNode(Long parentId,String name){
		//调用远程service服务方法,实现树形节点添加
		E3mallResult result = contentCategoryService.createNode(parentId, name);
		return result;
	}
	
	
	
	
}
