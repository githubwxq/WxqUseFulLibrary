package com.example.wxq.wxqutilslibrary.sqlitedb;

import java.io.Serializable;

public class HisMsg implements Serializable{
	
	private static final long serialVersionUID = 2367464348263411331L;
	public String id ="";
	public String fromId="";
	public String msgImg=""; 
	public String msgText="";
	public String msgTitle="";
	public String msgTime="";
	public String chatType="";//1代表单聊，2代表群聊
	public String isDel="";//0代表正常聊天，1代表不能聊天
	public String newNums="";
	public String flag1=""; 
	public String flag2=""; 
	public String flag3="";//代表是否是开放班级
	public String mid="";

}
