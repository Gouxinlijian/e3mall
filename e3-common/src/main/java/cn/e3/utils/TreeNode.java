package cn.e3.utils;

import java.io.Serializable;

/*
 * 树结构需要的参数
 */
@SuppressWarnings("serial")
public class TreeNode implements Serializable {

	// 树节点的id也就是数据库中的id
	private Long id;
	// 树节点的名称
	private String text;
	// 树节点的状态
	// is_parent=1,表示此节点有子节点,state="closed",表示处于可打开
	// is_parent=0,表示此节点没有子节点,state="open",表示已经是最后一级节点,已经打开了,不能再打开
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
