package com.imooc.security.rbac.vo;

import java.util.List;

/**
 * easyUI树形控件节点格式
 * <p>Title: EUTreeNode</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月4日上午9:13:14
 * @version 1.0
 */
public class EUTreeNode {

	//节点ID，对加载远程数据很重要
	private long id;
	//显示节点文本
	private String text;
	//节点状态 'open' 或 'closed'，默认：'open'
	private String state;
	//表示该节点是否被选中
	private boolean checked;
	// 一个节点数组声明了若干节点
	private List<EUTreeNode> children;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<EUTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EUTreeNode> children) {
		this.children = children;
	}
}
