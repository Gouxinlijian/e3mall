package cn.e3.utils;

import java.io.Serializable;

//广告图片实体类
@SuppressWarnings("serial")
public class AdItem implements Serializable {
	/*
	 * { "srcB": "http "height": 240, "alt": "", "width": 670, "src": "http://im
	 * "widthB": 550, "href": "http://s "heightB": 240 },
	 */
	// 备用商品图片地址
	private String srcB;
	// 备用商品图片高度
	private Integer heightB;
	// 备用商品图片宽度
	private Integer widthB;
	// 商品内容
	private String alt;
	// 商品图片地址
	private String src;
	// 商品图片高度
	private Integer height;
	// 商品图片宽度
	private Integer width;
	// 商品售卖地址
	private String href;

	public String getSrcB() {
		return srcB;
	}

	public void setSrcB(String srcB) {
		this.srcB = srcB;
	}

	public Integer getHeightB() {
		return heightB;
	}

	public void setHeightB(Integer heightB) {
		this.heightB = heightB;
	}

	public Integer getWidthB() {
		return widthB;
	}

	public void setWidthB(Integer widthB) {
		this.widthB = widthB;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
