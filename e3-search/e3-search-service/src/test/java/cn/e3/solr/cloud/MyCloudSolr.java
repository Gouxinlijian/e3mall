package cn.e3.solr.cloud;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

/**
 * 测试使用Solr集群获取信息
 * @author 轮廓
 *
 */
public class MyCloudSolr {

	@Test
	public void findSolrCloud() throws Exception{
		//指定Zookeeper集群地址
		String zkHost = "192.168.66.66:2182,192.168.66.66:2183,192.168.66.66:2184";
		//创建SolrCloudServer,连接集群
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		//指定使用索引库
		solrServer.setDefaultCollection("gouxin");
		//创建封装参数对象:solrQuery
		SolrQuery query = new SolrQuery();
		//查询所有
		query.setQuery("*:*");
		//查询操作
		QueryResponse response = solrServer.query(query);
		//获取结果集
		SolrDocumentList results = response.getResults();
		//遍历结果集,获取结果
		for (SolrDocument doc : results) {
			String title = (String) doc.get("item_title");
			//打印查询出来的结果
			System.out.println(title);
		}
	}
	
}
