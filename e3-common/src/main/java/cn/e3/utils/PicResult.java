package cn.e3.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PicResult implements Serializable{
	/*{"error":0,"url":"http://...","message":null} */
	private int error;
	//文件地址
	private String url;
	//上传错误信息
	private String message;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
