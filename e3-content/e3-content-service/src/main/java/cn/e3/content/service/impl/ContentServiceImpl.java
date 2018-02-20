package cn.e3.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.content.service.ContentService;
import cn.e3.mapper.TbContentMapper;
import cn.e3.pojo.TbContent;
import cn.e3.pojo.TbContentExample;
import cn.e3.pojo.TbContentExample.Criteria;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.PageResult;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	
	
	/**
	 * 需求:根据分类id查询分类内容信息
	 * 参数:Long categoryId,Integer page,Integer rows
	 * 返回值:PageResult
	 * 
	 */
	public PageResult findContentListByPage(Long categoryId, Integer page, Integer rows) {
		
		//创建example对象
		TbContentExample example = new TbContentExample();
		//创建criteria对象
		Criteria criteria = example.createCriteria();
		//设置查询参数
		criteria.andCategoryIdEqualTo(categoryId);
		
		//设置分页查询信息
		PageHelper.startPage(page, rows);
		
		//根据categoryId查询分类内容信息
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//创建pageInfo 对象 	获取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		
		//创建pageResult分页包装对象,封装分页数据
		PageResult result = new PageResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}



	/**
	 * 需求:保存广告内容数据
	 * 参数:TbContent content
	 * 返回值:E3mallResult
	 */
	public E3mallResult saveContent(TbContent content) {
		Date date = new Date();
		//传递参数--因为数据库中的 主键字段为自增
		//传递时间
		content.setCreated(date);
		content.setUpdated(date);
		//执行保存
		contentMapper.insertSelective(content);
		return E3mallResult.ok();
	}
}