package cn.e3.content.service;

import java.util.List;

import cn.e3.utils.E3mallResult;
import cn.e3.utils.TreeNode;

/*
 * 广告分类服务接口
 */
public interface ContentCategoryService {

	/**
	 * 需求:根据父id查询子节点
	 * 参数:Long parentId
	 * 返回值:List<TreeNode>
	 */
	public List<TreeNode> findcontentCategoryTreeNodeList(Long parentId);
	
	
	/**
	 * 需求:创建一个新的广告分类
	 * 参数:Long parentId,String name
	 * 返回值:E3mallResult
	 */
	public E3mallResult createNode(Long parentId,String name);
	
	
}
