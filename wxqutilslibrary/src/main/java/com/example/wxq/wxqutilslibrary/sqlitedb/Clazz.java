package com.example.wxq.wxqutilslibrary.sqlitedb;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月21日
 * @description 班级类
 */
public class Clazz implements Serializable {
    public String userId = "";
    public String role = "";
    public String schoolId = "";
    public String schoolName = "";
    public String classId = "";
    public String className = "";
    public String studentId = "";
    public String studentName = "";
    public String address = "";
    public String subjectName = "";
    public String subjectId = "";
    public String disflag = "0";
    public String shiflag = "0";
    public String isnickname = "";
    public String classBlocked = "false";
    public String owner = "false";
    public String classNo = "";
    public boolean isSelected = false;//班级选择要用
    public String createTime = "";//创建时间
    public String memsNum = "";//成员人数
    public boolean hasNew = false;
    public int isChat = 1;

    @Override
    public String toString() {
        return "Clazz{" +
                "userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", disflag='" + disflag + '\'' +
                ", shiflag='" + shiflag + '\'' +
                ", isnickname='" + isnickname + '\'' +
                ", classBlocked='" + classBlocked + '\'' +
                ", owner='" + owner + '\'' +
                ", classNo='" + classNo + '\'' +
                ", isSelected=" + isSelected +
                ", createTime='" + createTime + '\'' +
                ", memsNum='" + memsNum + '\'' +
                ", hasNew=" + hasNew +
                '}';
    }
}
