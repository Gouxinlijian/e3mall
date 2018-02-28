package cn.e3.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3.search.dao.SearchItemDao;
import cn.e3.search.mapper.SearchItemMapper;
import cn.e3.search.pojo.SearchItem;
import cn.e3.search.pojo.SolrPage;
import cn.e3.search.service.SearchItemService;
import cn.e3.utils.E3mallResult;

@Service
public class SearchItemServiceImpl implements SearchItemService {
	// 注入mapper接口代理对象
	@Autowired
	private SearchItemMapper searchitemMapper;
	//注入solr服务
	@Autowired
	private SolrServer solrServer;
	//注入dao
	@Autowired
	private SearchItemDao searchItemDao;
	
	/**
	 * 需求: 查询索引库域字段对应数据库字段值,把数据值导入索引库 搜索服务接口: 用来给表现层调用 参数:无 返回值:E3mallResult 业务:
	 * 1,查询数据库 2,把数据库数据写入索引库
	 */
	public E3mallResult findDatabaseToSolrIndex() {
		
		try {
			//1.查询数据库获取需要放入到索引库的数据
			List<SearchItem> solrList = searchitemMapper.findDatabaseToSolrIndex();
			//循环遍历放入到索引库对象中
			for (SearchItem searchItem : solrList) {
				//创建文档对象
				SolrInputDocument doc = new SolrInputDocument();
				// 根据域字段一一对应封装数据库值
				//id
				doc.addField("id", searchItem.getId());
				//title
				doc.addField("item_title", searchItem.getTitle());
				//item_sell_point
				doc.addField("item_sell_point", searchItem.getSell_point());
				//item_price
				doc.addField("item_price", searchItem.getPrice());
				//item_image
				doc.addField("item_image", searchItem.getImage());
				//item_category_name
				doc.addField("item_category_name", searchItem.getCategory_name());
				//item_desc
				doc.addField("item_desc", searchItem.getItem_desc());
				//使用SolrServer把文档对象写入到索引库中
				solrServer.add(doc);
			}
			//提交
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return E3mallResult.ok();
	}

	/**
	 * 需求:查询索引库 参数:String qName,Integer page,Integer rows 返回值:分页包装类对象
	 */
	public SolrPage findSolrIndex(String qName, Integer page, Integer rows) {
		// 创建solrQuery对象,封装所有查询条件
		SolrQuery solrQuery = new SolrQuery();
		// 判断参数是否为空
		if (qName != null && !"".equals(qName)) {
			solrQuery.setQuery(qName);
		} else {
			solrQuery.setQuery("*:*");
		}

		// 分页
		// start,rows 设置分页
		int startNo = (page - 1) * rows;
		solrQuery.setStart(startNo);
		solrQuery.setRows(rows);

		// 6,df 设置默认查询字段
		// 注意事项:df默认查询一般都设置为复制域
		solrQuery.set("df", "item_keywords");

		// 7,hl 高亮显示
		// 开启高亮
		solrQuery.setHighlight(true);
		// 设置高亮字段
		solrQuery.addHighlightField("item_title");
		// 设置高亮前缀
		solrQuery.setHighlightSimplePre("<font color='red'>");
		// 后缀
		solrQuery.setHighlightSimplePost("</font>");
		
		//调用dao实现索引库查询
		SolrPage spage = searchItemDao.findSolrIndex(solrQuery);
		//给分页对象设置当前页
		spage.setCurPage(page);
		//设置总页码数
		//计算总页码
		//获取总记录数
		Integer totalCount = spage.getTotalCount();
		int pages = totalCount/rows;
		if(totalCount%rows>0){
			pages++;
		}
		spage.setTotalPages(pages);
		return spage;
	}

}
