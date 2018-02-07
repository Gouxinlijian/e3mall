package cn.e3.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.e3.manager.service.ItemCatService;
import cn.e3.mapper.TbItemCatMapper;
import cn.e3.pojo.TbItemCat;
import cn.e3.pojo.TbItemCatExample;
import cn.e3.pojo.TbItemCatExample.Criteria;
import cn.e3.utils.TreeNode;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	public List<TreeNode> findItemCatTreeNodeList(Long cid) {
		//创建example对象
		TbItemCatExample example = new TbItemCatExample();
		//创建筛选条件
		Criteria criteria = example.createCriteria().andParentIdEqualTo(cid);
		//返回结果
		List<TbItemCat> list_itemCat = itemCatMapper.selectByExample(example);
		//创建TreeNode集合用来封装数据
		List<TreeNode> list_TreeNode = new ArrayList<>();
		//循环遍历通过数据库查询到的结果
		for (TbItemCat itemCat : list_itemCat) {
			//创建TreeNode对象
			TreeNode treeNode = new TreeNode();
			//封装数据
			treeNode.setId(itemCat.getId());
			treeNode.setText(itemCat.getName());
			//is_parent=1,表示此节点有子节点,state="closed",表示处于可打开	1=true
			//is_parent=0,表示此节点没有子节点,state="open",表示已经是最后一级节点,已经打开了,不能再打开	0=false
			treeNode.setState((itemCat.getIsParent()?"closed":"open"));
			list_TreeNode.add(treeNode);
		}
		return list_TreeNode;
	}
}
