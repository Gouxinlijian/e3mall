package cn.e3.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemDescMapper;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemDesc;
import cn.e3.pojo.TbItemExample;
import cn.e3.utils.E3mallResult;
import cn.e3.utils.IDUtils;
import cn.e3.utils.PageResult;
@Service
public class ItemServiceImpl implements ItemService {

	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;
	
	//注入商品描述mapper接口代理对象
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
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

	/**
	 * 需求:保存商品数据
	 * 参数:TbItem item,TbItemDesc itemDesc
	 * 返回值:E3mallResult
	 * 
	 */
	public E3mallResult saveItem(TbItem item, TbItemDesc itemDesc) {
		//使用毫秒值加随机数生成商品id(商品表的id不是自增长所以要自己生成)
		long itemId = IDUtils.genItemId();
		//传入参数
		item.setId(itemId);
		//添加商品状态	:	商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		Date date = new Date();
		//创建时&修改时间
		item.setCreated(date);
		item.setUpdated(date);
		//保存商品表
		itemMapper.insertSelective(item);
		
		//将参数保存到商品描述对象中
		itemDesc.setItemId(itemId);
		//创建时间&修改时间
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//保存操作
		itemDescMapper.insertSelective(itemDesc);
		return E3mallResult.ok();
	}
}
