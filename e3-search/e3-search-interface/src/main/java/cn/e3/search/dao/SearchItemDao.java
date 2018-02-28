package cn.e3.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.e3.search.pojo.SolrPage;

public interface SearchItemDao {
	
	/**
	 * 需求:查询索引库
	 * 参数:SolrQuery(封装所有查询条件参数)
	 * 返回值:分页包装类对象
	 */
	public SolrPage findSolrIndex(SolrQuery solrQuery);

}
