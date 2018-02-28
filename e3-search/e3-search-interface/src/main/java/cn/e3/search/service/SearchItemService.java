package cn.e3.search.service;

import cn.e3.search.pojo.SolrPage;
import cn.e3.utils.E3mallResult;

public interface SearchItemService {
	
	/**
	 * 需求: 查询索引库域字段对应数据库字段值,把数据值导入索引库
	 * 搜索服务接口: 用来给表现层调用
	 * 参数:无
	 * 返回值:E3mallResult
	 */
	public E3mallResult findDatabaseToSolrIndex();
	
	/**
	 * 需求:查询索引库
	 * 参数:String qName,Integer page,Integer rows
	 * 返回值:分页包装类对象
	 */
	public SolrPage findSolrIndex(String qName,Integer page,Integer rows);

}
