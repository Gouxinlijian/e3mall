package cn.e3.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3.search.dao.SearchItemDao;
import cn.e3.search.pojo.SearchItem;
import cn.e3.search.pojo.SolrPage;
@Repository
public class SearchItemDaoImpl implements SearchItemDao {

	//注入solr服务
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 需求:查询索引库
	 * 参数:SolrQuery(封装所有查询条件参数)
	 * 返回值:分页包装类对象
	 */
	public SolrPage findSolrIndex(SolrQuery solrQuery) {
		
			//创建分页包装类对象,封装分页数据
			SolrPage page = new SolrPage();
			//创建封装记录集合对象
			List<SearchItem> itemList = new ArrayList<SearchItem>();
			
		try {
			//使用solr服务对象查询索引库
			QueryResponse response = solrServer.query(solrQuery);
			//获取文档集合
			SolrDocumentList results = response.getResults();
			//获取命中总记录数
			Long numFound = results.getNumFound();
			//把总记录数设置到分页包装类对象
			page.setTotalCount(numFound.intValue());
			
			//循环文档集合,把文档集合中所有数据封装List<SearchItem>
			for (SolrDocument sdoc : results) {
				//获取文档数据,把每一个文档数据封装SearchItem对象
				//创建SearchItem对象
				SearchItem item = new SearchItem();
				//获取id
				String id = (String) sdoc.get("id");
				item.setId(Long.parseLong(id));
				//title
				String item_title = (String) sdoc.get("item_title");
				//获取标题高亮
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				Map<String, List<String>> map = highlighting.get(id);
				List<String> list = map.get("item_title");
				//判断高亮是否存在
				if(list!=null && list.size()>0){
					item_title = list.get(0);
				}
				
				//买点
				String item_sell_point = (String) sdoc.get("item_sell_point");
				item.setSell_point(item_sell_point);
				
				//价格
				Long item_price = (Long) sdoc.get("item_price");
				item.setPrice(item_price);
				
				//图片地址
				String item_image = (String) sdoc.get("item_image");
				item.setImage(item_image);
				
				//类别名称
				String item_category_name = (String) sdoc.get("item_category_name");
				item.setCategory_name(item_category_name);
				
				//把从索引库查询商品对象封装itemList集合
				itemList.add(item);
			}
			
			//把商品集合放入分页包装类对象
			page.setItemList(itemList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
