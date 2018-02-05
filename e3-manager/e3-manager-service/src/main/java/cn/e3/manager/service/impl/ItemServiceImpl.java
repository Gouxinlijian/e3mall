package cn.e3.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemExample;
import cn.e3.utils.PageResult;
@Service
public class ItemServiceImpl implements ItemService {

	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;
	
	/**
	 * 需求:根据商品id查询商品数据
	 * 参数:Long itemId
	 * 返回值:TbItem
	 */
	public TbItem findItemById(Long itemId) {
		// 根据商品id查询商品数据
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}

	/**
	 * 需求:查询商品列表,进行分页展示
	 * 参数:Integer page,Integer rows
	 * 返回值:PageResult
	 * 思考:服务是否发布?
	 */
	public PageResult findItemListByPage(Integer page, Integer rows) {
		// 创建example对象
		TbItemExample example = new TbItemExample();
		
		//在执行查询之前,设置分页参数,即可实现分页
		PageHelper.startPage(page, rows);
		//下面查询将会被拦截器拦截,自动执行分页
		//list集合数据变化: page(total,pages.....num),list
		//执行查询,查询所有
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//创建分页信息对象Pageinfo,获取list集合中分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//创建分页包装类对象,包装分页结果
		PageResult result = new PageResult();
		//设置总记录数
		result.setTotal(pageInfo.getTotal());
		//设置总记录
		result.setRows(list);
		return result;
	}

}
