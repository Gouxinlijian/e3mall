package cn.e3.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3.content.service.ContentCategoryService;
import cn.e3.mapper.TbContentCategoryMapper;
import cn.e3.pojo.TbContentCategory;
import cn.e3.pojo.TbContentCategoryExample;
import cn.e3.pojo.TbContentCategoryExample.Criteria;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.TreeNode;

/*
 * 广告分类服务实现类
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	
	//注入ContentCategoryMapper接口代理对象
	@Autowired
	private TbContentCategoryMapper categoryMapper;
	
	
	/**
	 * 需求:根据父id查询子节点
	 * 参数:Long parentId
	 * 返回值:List<TreeNode>
	 */
	public List<TreeNode> findcontentCategoryTreeNodeList(Long parentId) {
		//创建TreeNode List 对象
		List<TreeNode> TreeNodeList = new ArrayList<>();
		//创建example对象
		TbContentCategoryExample example = new TbContentCategoryExample();
		//创建criteria对象
		Criteria criteria = example.createCriteria();
		//设置查询参数
		criteria.andParentIdEqualTo(parentId);
		//进行查询
		List<TbContentCategory> ContentCategoryList = categoryMapper.selectByExample(example);
		//循环遍历出结果然后放入到TreeNode集合中
		for (TbContentCategory contentCategory : ContentCategoryList) {
			//创建TreeNode对象,封装广告分类对象数据
			TreeNode tn = new TreeNode();
			//传入商品id
			tn.setId(contentCategory.getId());
			//传入商品名称
			tn.setText(contentCategory.getName());
			//传入状态
			tn.setState(contentCategory.getIsParent()?"closed":"open");
			//将树属性放入到集合中
			TreeNodeList.add(tn);
		}
		return TreeNodeList;
	}


	/**
	 * 需求:创建一个新的广告分类
	 * 参数:Long parentId,String name
	 * 返回值:E3mallResult
	 * 业务:
	 * 1,当新建节点父节点是子节点的时候,修改父节点状态
	 * 2,当新建节点的父节点是 父节点,直接添加即可
	 */
	public E3mallResult createNode(Long parentId, String name) {
		//创建广告分类对象,封装广告分类数据,实现保存
		TbContentCategory contentCategory = new TbContentCategory();
		//传递参数
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		//状态。可选值:1(正常),2(删除)
		contentCategory.setStatus(1);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		contentCategory.setSortOrder(1);
		//该类目是否为父类目，1为true，0为false
		contentCategory.setIsParent(false);
		//保存节点数据操作
		System.out.println("添加之前的id为:"+contentCategory.getId());
		categoryMapper.insertSelective(contentCategory);
		//新建节点父id就是父节点的id
		//根据新建节点父id查询出父节点对象
		TbContentCategory tbContentCategory = categoryMapper.selectByPrimaryKey(parentId);
		//判断此父节点对象是否是子节点
		if(!tbContentCategory.getIsParent()){
			//表示此节点就是一个子节点
			//修改状态
			tbContentCategory.setIsParent(true);
			//修改
			categoryMapper.updateByPrimaryKey(tbContentCategory);
		}
		System.out.println("添加之后的id为:"+contentCategory.getId());
		//返回前台页面需要的对象
		return E3mallResult.ok(contentCategory);
	}
}
