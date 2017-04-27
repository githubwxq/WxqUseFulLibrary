package dbutils;

import java.io.Serializable;

public class Msg implements Serializable {
	
	private static final long serialVersionUID = 2367464348263411331L;
	public String id ="";
	public String fromUser=""; //来自哪个用户
	public String toUser=""; //发给哪个用户
	public String msgtext=""; //如果是文件或者语音为本地路径
	public String datetime="";
	public String isFrom=""; //
	public String isRead="";
	public Boolean isLoadOver=true;
	public String msgtype="";//文件类型  0 文字 1图片 2语音 3名片 4文章 7课件
	public String cardId ="";//名片的id
	public String title= "";//名片的标题
	public String iconurl = "";//名片的头像
	public String filepath=""; // 本地路径，图片、语音、文章的链接
	public long speechlength=0; //语音的长度
	public String whoid="";
	public String fid="";
	public String username="";
	public String face="";
	public String clazzid="";
	public String clazzimg="";
	public String clazzname="";
	public String chatflag="";
	public String role="";
	public String isLocal=""; //是否是本地的文件，本地为1
	public String courseId="";

	@Override
	public String toString() {
		return "Msg{" +
				"id='" + id + '\'' +
				", fromUser='" + fromUser + '\'' +
				", toUser='" + toUser + '\'' +
				", msgtext='" + msgtext + '\'' +
				", datetime='" + datetime + '\'' +
				", isFrom='" + isFrom + '\'' +
				", isRead='" + isRead + '\'' +
				", isLoadOver=" + isLoadOver +
				", msgtype='" + msgtype + '\'' +
				", cardId='" + cardId + '\'' +
				", title='" + title + '\'' +
				", iconurl='" + iconurl + '\'' +
				", filepath='" + filepath + '\'' +
				", speechlength=" + speechlength +
				", whoid='" + whoid + '\'' +
				", fid='" + fid + '\'' +
				", username='" + username + '\'' +
				", face='" + face + '\'' +
				", clazzid='" + clazzid + '\'' +
				", clazzimg='" + clazzimg + '\'' +
				", clazzname='" + clazzname + '\'' +
				", chatflag='" + chatflag + '\'' +
				", role='" + role + '\'' +
				", isLocal='" + isLocal + '\'' +
				", courseId='" + courseId + '\'' +
				'}';
	}
}
