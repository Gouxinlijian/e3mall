package cn.e3.manager.service;

import java.util.List;

import cn.e3.utils.TreeNode;

/*
 * 商品分类接口
 */
public interface ItemCatService {

	List<TreeNode> findItemCatTreeNodeList(Long cid);

}
