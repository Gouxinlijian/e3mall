package cn.e3.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.e3.utils.FastDFSClient;
import cn.e3.utils.JsonUtils;
import cn.e3.utils.PicResult;

/**
 * 图片上传Controller
 * @author 轮廓
 *
 */
@RestController
public class UpLoadController {

	//注入数据
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	public String uploadPic(MultipartFile uploadFile){
		//获取上传的文件名称
		String filename = uploadFile.getOriginalFilename();
		//切割上传的文件后缀名称
		String extName = filename.substring(filename.lastIndexOf(".")+1);
		try {
			//创建上传文件对象的工具类
			FastDFSClient dfsClient = new FastDFSClient("classpath:/conf/client.conf");
			//直接上传返回文件上传路径
			String url = dfsClient.uploadFile(uploadFile.getBytes(), extName);
			//组合图片的绝对路径
			url = IMAGE_SERVER_URL+url;
			System.out.println("图片的上传地址是:"+url);
			//上传成功返回一个Result
			PicResult result = new PicResult();
			result.setError(0);
			result.setUrl(url);
			//返回json字符串
			String PicJson = JsonUtils.objectToJson(result);
			return PicJson;
		} catch (Exception e) {
			e.printStackTrace();
			//如果出现异常那么就返回上传失败
			PicResult picResult = new PicResult();
			picResult.setError(1);
			picResult.setMessage("上传失败,请重试!");
			//转换成json字符串
			String PicJson = JsonUtils.objectToJson(picResult);
			return PicJson;
		}
	}
}
