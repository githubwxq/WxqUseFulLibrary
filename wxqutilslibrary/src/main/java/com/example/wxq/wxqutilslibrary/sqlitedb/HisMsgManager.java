package com.example.wxq.wxqutilslibrary.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author ztn
 * @description
 * 他的消息管理包括单聊消息和群消息在为消息framgment中listview提供数据
 * 对应的数据库操作在此
 */
public class HisMsgManager {
	
	private String tableName = "HISMSG";
	private static HisMsgManager hismsgManager = null;  //单列
	private static DbHelper hismsgDbHelper = null; //代理工具类工具

	private HisMsgManager(Context context) {
		if(hismsgDbHelper==null){
			hismsgDbHelper = DbHelper.getInstance(context);
		}
	}

	public static HisMsgManager getInstance(Context context) {
		if (hismsgManager == null) {
			hismsgManager = new HisMsgManager(context);
		}
		return hismsgManager;
	}

	public void updateMsgNum(String fromId, String mid) {
		synchronized (hismsgDbHelper) {
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.getSqLiteDatabase().execSQL("update HISMSG set newNums = '0' where fromId='" + fromId + "' and mid='" + mid + "'");
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (hismsgDbHelper != null) {
					hismsgDbHelper.close();
				}
			}
		}
	}
	
	public void updateMsg(String mid, HisMsg hismsg) {
		synchronized (hismsgDbHelper) {
			ContentValues cv = new ContentValues();
			cv.put("msgImg", hismsg.msgImg);
			cv.put("msgText", hismsg.msgText);
			cv.put("msgTitle", hismsg.msgTitle);
			cv.put("msgTime", hismsg.msgTime);
			cv.put("chatType", hismsg.chatType);
			cv.put("isDel", hismsg.isDel);
			cv.put("newNums", hismsg.newNums);
			cv.put("flag1", hismsg.flag1);
			cv.put("flag2", hismsg.flag2);
			cv.put("flag3", hismsg.flag3);
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.update(tableName, cv, "mid=? and fromId=?", new String[] { mid ,hismsg.fromId});
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}
	
	public void updateHisMsgIsDel(String mid, String isDel,String fromId) {
		synchronized (hismsgDbHelper) {
			ContentValues cv = new ContentValues();
			cv.put("isDel", isDel);
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.update(tableName, cv, "mid=? and fromId=?", new String[] { mid ,fromId});
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}

	public void updateMsgText(String mid, String msgtext,String fromId) {
		synchronized (hismsgDbHelper) {
			ContentValues cv = new ContentValues();
			cv.put("msgTitle", msgtext);
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.update(tableName, cv, "mid=? and fromId=?", new String[] { mid ,fromId});
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}


	public void updateMsgTitle(String mid, String msgTitle,String fromId) {
		synchronized (hismsgDbHelper) {
			ContentValues cv = new ContentValues();
			cv.put("msgText", msgTitle);
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.update(tableName, cv, "mid=? and fromId=?", new String[] { mid ,fromId});
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}
	
	public void updateAllMsgText(String mid) {
		synchronized (hismsgDbHelper) {
			ContentValues cv = new ContentValues();
			cv.put("msgText", "");
			cv.put("flag1", "");
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.update(tableName, cv, "mid=? and newNums = ?", new String[] { mid ,"0"});
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}


	public void deleteOneHisMsg(String fromId,String mid) {
		synchronized (hismsgDbHelper) {
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.delete(tableName, "fromId=? and mid=?", new String[]{fromId, mid});
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}

	public void deleteAllHisMsg(String mid) {
		synchronized (hismsgDbHelper) {
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.delete(tableName, "mid=?", new String[] { mid });
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}

	public void clear(String mid) {
		synchronized (hismsgDbHelper) {
			String selection = "isDel = ? and mid = ?";
			String delete[] = new String[2];
			delete[0] = "1";
			delete[1] = mid;
			try {
				hismsgDbHelper.open();
				hismsgDbHelper.delete(tableName, selection, delete);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
		}
	}
	
	public long insertListHisMsg(ArrayList<Clazz> clazzs,String mid) {
		synchronized (hismsgDbHelper) {
			long id = 0;
			try {
				hismsgDbHelper.open();
				for(int i=0;i<clazzs.size();i++){
						ContentValues cv = new ContentValues();
						cv.put("fromId", clazzs.get(i).classId);
						cv.put("msgImg", "");
						cv.put("msgText", "");
						cv.put("msgTitle", clazzs.get(i).className);
						cv.put("chatType", "2");
						cv.put("isDel", "0");
						cv.put("newNums", "0");
						cv.put("flag1", "");
						cv.put("flag2", "");
						cv.put("flag3", "");
						cv.put("mid", mid);
						id = hismsgDbHelper.insert(tableName, cv);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
			return id;
		}
	}
	
	public long insertHisMsg(HisMsg hismsg) {
		synchronized (hismsgDbHelper) {
			long id = 0;
			try {
				ContentValues cv = new ContentValues();
				cv.put("fromId", hismsg.fromId);
				cv.put("msgImg", hismsg.msgImg);
				cv.put("msgText", hismsg.msgText);
				cv.put("msgTitle", hismsg.msgTitle);
				cv.put("msgTime", hismsg.msgTime);
				cv.put("chatType", hismsg.chatType);
				cv.put("isDel", hismsg.isDel);
				cv.put("newNums", hismsg.newNums);
				cv.put("flag1", hismsg.flag1);
				cv.put("flag2", hismsg.flag2);
				cv.put("flag3", hismsg.flag3);
				cv.put("mid", hismsg.mid);
				hismsgDbHelper.open();
				id = hismsgDbHelper.insert(tableName, cv);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
			return id;
		}
	}
	
	public long insertListHisMsg(HashMap<String, HisMsg> updateMsg,String mid) {
		synchronized (hismsgDbHelper) {
			long id = 0;
			try {
				Set set=updateMsg.entrySet();
				Iterator it=set.iterator();
				hismsgDbHelper.open();
				while(it.hasNext()){
					Map.Entry me=(Map.Entry)it.next();
					ContentValues value = new ContentValues();
					value.put("fromId", me.getKey()+"");
					value.put("msgImg", me.getValue()+"");
					value.put("msgText", me.getValue()+"");
					value.put("msgTitle", me.getKey()+"");
					value.put("msgTime", me.getValue()+"");
					value.put("chatType", me.getValue()+"");
					value.put("isDel", me.getKey()+"");
					value.put("newNums", me.getValue()+"");
					value.put("flag1", me.getValue()+"");
					value.put("flag2", me.getKey()+"");
					value.put("flag3", me.getValue()+"");
					value.put("mid", mid);
					hismsgDbHelper.insert(tableName, value);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
			return id;
		}
	}
	
//	public HashMap<String, HisMsg> getHisMsgList(String mid) {
//		synchronized (hismsgDbHelper) {
//			String orderBy = "msgTime asc";
//			String a[] = new String[1];
//			a[0] = mid;
//			Cursor checkCarCursor = null;
//			try {
//				hismsgDbHelper.open();
//				checkCarCursor = hismsgDbHelper.select(tableName, null,"mid=?", a, null, null, orderBy,null);
//				for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
//						.moveToNext()) {
//					HisMsg hisMsg = new HisMsg();
//					int _id = checkCarCursor.getColumnIndex("_id");
//					int fromId = checkCarCursor.getColumnIndex("fromId");
//					int msgImg = checkCarCursor.getColumnIndex("msgImg");
//					int msgText = checkCarCursor.getColumnIndex("msgText");
//					int msgTitle = checkCarCursor.getColumnIndex("msgTitle");
//					int msgTime = checkCarCursor.getColumnIndex("msgTime");
//					int chatType = checkCarCursor.getColumnIndex("chatType");
//					int isDel = checkCarCursor.getColumnIndex("isDel");
//					int newNums = checkCarCursor.getColumnIndex("newNums");
//					int flag1 = checkCarCursor.getColumnIndex("flag1");
//					int flag2 = checkCarCursor.getColumnIndex("flag2");
//					int flag3 = checkCarCursor.getColumnIndex("flag3");
//					int uid = checkCarCursor.getColumnIndex("mid");
//					hisMsg.id = checkCarCursor.getString(_id);
//					hisMsg.fromId = checkCarCursor.getString(fromId);
//					hisMsg.msgImg = checkCarCursor.getString(msgImg);
//					hisMsg.msgText = checkCarCursor.getString(msgText);
//					hisMsg.msgTitle = checkCarCursor.getString(msgTitle);
//					hisMsg.msgTime = checkCarCursor.getString(msgTime);
//					hisMsg.chatType = checkCarCursor.getString(chatType);
//					hisMsg.isDel = checkCarCursor.getString(isDel);
//					hisMsg.newNums = checkCarCursor.getString(newNums);
//					hisMsg.flag1 = checkCarCursor.getString(flag1);
//					hisMsg.flag2 = checkCarCursor.getString(flag2);
//					hisMsg.flag3 = checkCarCursor.getString(flag3);
//					hisMsg.mid = checkCarCursor.getString(uid);
//				//	Global.hismsg.put(hisMsg.fromId,hisMsg);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}finally{
//				if(checkCarCursor!=null){
//					checkCarCursor.close();
//				}
//				if(hismsgDbHelper!=null){
//					hismsgDbHelper.close();
//				}
//			}
//			return Global.hismsg;
//		}
//	}
	
	public HisMsg getOneHisMsg(String mid,String clazzId) {
		synchronized (hismsgDbHelper) {
			String a[] = new String[2];
			a[0] = mid;
			a[1] = clazzId;
			Cursor checkCarCursor = null;
			HisMsg hisMsg = new HisMsg();			
			try {
				hismsgDbHelper.open();
				checkCarCursor = hismsgDbHelper.select(tableName, null,"mid=? and fromId=?", a, null, null, null,null);
				for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
						.moveToNext()) {
					int _id = checkCarCursor.getColumnIndex("_id");
					int fromId = checkCarCursor.getColumnIndex("fromId");
					int msgImg = checkCarCursor.getColumnIndex("msgImg");
					int msgText = checkCarCursor.getColumnIndex("msgText");
					int msgTitle = checkCarCursor.getColumnIndex("msgTitle");
					int msgTime = checkCarCursor.getColumnIndex("msgTime");
					int chatType = checkCarCursor.getColumnIndex("chatType");
					int isDel = checkCarCursor.getColumnIndex("isDel");
					int newNums = checkCarCursor.getColumnIndex("newNums");
					int flag1 = checkCarCursor.getColumnIndex("flag1");
					int flag2 = checkCarCursor.getColumnIndex("flag2");
					int flag3 = checkCarCursor.getColumnIndex("flag3");
					int uid = checkCarCursor.getColumnIndex("mid");
					hisMsg.id = checkCarCursor.getString(_id);
					hisMsg.fromId = checkCarCursor.getString(fromId);
					hisMsg.msgImg = checkCarCursor.getString(msgImg);
					hisMsg.msgText = checkCarCursor.getString(msgText);
					hisMsg.msgTitle = checkCarCursor.getString(msgTitle);
					hisMsg.msgTime = checkCarCursor.getString(msgTime);
					hisMsg.chatType = checkCarCursor.getString(chatType);
					hisMsg.isDel = checkCarCursor.getString(isDel);
					hisMsg.newNums = checkCarCursor.getString(newNums);
					hisMsg.flag1 = checkCarCursor.getString(flag1);
					hisMsg.flag2 = checkCarCursor.getString(flag2);
					hisMsg.flag3 = checkCarCursor.getString(flag3);
					hisMsg.mid = checkCarCursor.getString(uid);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(checkCarCursor!=null){
					checkCarCursor.close();
				}
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
			return hisMsg;
		}
	}
	
	public ArrayList<HisMsg> getClazzHisMsgList(String mid,String chat) {
		synchronized (hismsgDbHelper) {
			String orderBy = "msgTime desc";
			String a[] = new String[2];
			a[0] = mid;
			a[1] = chat;
			Cursor checkCarCursor = null;
			ArrayList<HisMsg> arrayList = new ArrayList<HisMsg>();
			try {
				hismsgDbHelper.open();
				checkCarCursor = hismsgDbHelper.select(tableName, null,"mid=? and chatType=?", a, null, null, orderBy,null);
				for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
						.moveToNext()) {
					HisMsg hisMsg = new HisMsg();			
					int _id = checkCarCursor.getColumnIndex("_id");
					int fromId = checkCarCursor.getColumnIndex("fromId");
					int msgImg = checkCarCursor.getColumnIndex("msgImg");
					int msgText = checkCarCursor.getColumnIndex("msgText");
					int msgTitle = checkCarCursor.getColumnIndex("msgTitle");
					int msgTime = checkCarCursor.getColumnIndex("msgTime");
					int chatType = checkCarCursor.getColumnIndex("chatType");
					int isDel = checkCarCursor.getColumnIndex("isDel");
					int newNums = checkCarCursor.getColumnIndex("newNums");
					int flag1 = checkCarCursor.getColumnIndex("flag1");
					int flag2 = checkCarCursor.getColumnIndex("flag2");
					int flag3 = checkCarCursor.getColumnIndex("flag3");
					int uid = checkCarCursor.getColumnIndex("mid");
					hisMsg.id = checkCarCursor.getString(_id);
					hisMsg.fromId = checkCarCursor.getString(fromId);
					hisMsg.msgImg = checkCarCursor.getString(msgImg);
					hisMsg.msgText = checkCarCursor.getString(msgText);
					hisMsg.msgTitle = checkCarCursor.getString(msgTitle);
					hisMsg.msgTime = checkCarCursor.getString(msgTime);
					hisMsg.chatType = checkCarCursor.getString(chatType);
					hisMsg.isDel = checkCarCursor.getString(isDel);
					hisMsg.newNums = checkCarCursor.getString(newNums);
					hisMsg.flag1 = checkCarCursor.getString(flag1);
					hisMsg.flag2 = checkCarCursor.getString(flag2);
					hisMsg.flag3 = checkCarCursor.getString(flag3);
					hisMsg.mid = checkCarCursor.getString(uid);
					arrayList.add(hisMsg);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(checkCarCursor!=null){
					checkCarCursor.close();
				}
				if(hismsgDbHelper!=null){
					hismsgDbHelper.close();
				}
			}
			return arrayList;
		}
	}
}
