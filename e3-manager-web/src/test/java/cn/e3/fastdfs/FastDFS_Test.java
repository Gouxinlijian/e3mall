package cn.e3.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import cn.e3.utils.FastDFSClient;
/**
 * 测试FastDFS上传图片
 * @author 轮廓
 *
 */
public class FastDFS_Test {
	
/*	@Test
	public void test01() throws Exception{
		//1.指定上传图片的绝对路径
		String path = "D:\\用户目录\\我的文档\\58203efff3bfa.jpg";
		//2.指定服务器路径,指定Client.conf的绝对路径
		String clientPath = "E:\\e3mall\\e3-manager-web\\"
				+ "src\\main\\resources\\conf\\client.conf";
		//3.加载配置文件,连接服务器
		ClientGlobal.init(clientPath);
		//4.创建tracker客户端
		TrackerClient tclient = new TrackerClient();
		//5.从客户端获取tracker对象
		TrackerServer trackerServer = tclient.getConnection();
		StorageServer storageServer = null;
		//6.创建storageClient客户端
		StorageClient sclient = new StorageClient(trackerServer, storageServer);
		//7.上传
		String[] str = sclient.upload_file(path, "jpg", null);
		for (String s : str) {
			//返回存储在服务器中的文件ID
			System.out.println(s);
		}
	}*/
	
	/*
	 * 使用工具类上传图片
	 */
	/*@Test
	public void test02() throws Exception{
		//1.指定上传图片的绝对路径
		String path = "D:\\用户目录\\我的文档\\523.jpg";
		//2.指定服务器路径,指定Client.conf的绝对路径
		String clientPath = "E:\\e3mall\\e3-manager-web\\"
				+ "src\\main\\resources\\conf\\client.conf";
		//3.使用工具类获取StroageClient客户端
		FastDFSClient client = new FastDFSClient(clientPath);
		//4.执行上传
		String str = client.uploadFile(path);
		System.out.println(str);
	}*/
}
