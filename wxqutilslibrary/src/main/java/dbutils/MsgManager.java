package dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ztn
 * @description 单聊界面消息管理
 */
public class MsgManager {

    private String tableName = "Msg";
    private static MsgManager msgManager = null;
    private static DbHelper msgDbHelper = null;

    private MsgManager(Context context) {
        if (msgDbHelper == null) {
            msgDbHelper = DbHelper.getInstance(context);
        }
    }

    public static MsgManager getInstance(Context context) {
        if (msgManager == null) {
            msgManager = new MsgManager(context);
        }
        return msgManager;
    }

    public void updateMsg(String id, String isRead) {
        synchronized (msgDbHelper) {
            ContentValues cv = new ContentValues();
            cv.put("isread", isRead);
            try {
                msgDbHelper.open();
                msgDbHelper.update(tableName, cv, "_id=?", new String[]{id});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public void updateMsgFail(String id, String filepath, String msgtext) {
        synchronized (msgDbHelper) {
            ContentValues cv = new ContentValues();
            cv.put("filepath", filepath);
            cv.put("msgtext", msgtext);
            try {
                msgDbHelper.open();
                msgDbHelper.update(tableName, cv, "_id=?", new String[]{id});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public void updateMsgCardID(String id, String cardID) {
        synchronized (msgDbHelper) {
            ContentValues cv = new ContentValues();
            cv.put("cardId", cardID);
            try {
                msgDbHelper.open();
                msgDbHelper.update(tableName, cv, "_id=?", new String[]{id});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public void updateMsgFile(String id, String fileUrl) {
        synchronized (msgDbHelper) {
            ContentValues cv = new ContentValues();
            cv.put("filepath", fileUrl);
            try {
                msgDbHelper.open();
                boolean b = msgDbHelper.update(tableName, cv, "_id=?", new String[]{id});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public void deleteOneMsg(String id) {
        synchronized (msgDbHelper) {
            try {
                msgDbHelper.open();
                msgDbHelper.delete(tableName, "_id=?", new String[]{id});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public void clear(String mid) {
        synchronized (msgDbHelper) {
            String selection = "whoid = ?";
            String delete[] = new String[1];
            delete[0] = mid;
            try {
                msgDbHelper.open();
                msgDbHelper.delete(tableName, selection, delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public void clearPush(String mid) {
        synchronized (msgDbHelper) {
            String selection = "touser = ? and chatflag = ?";
            String delete[] = new String[2];
            delete[0] = mid;
            delete[1] = "2";
            try {
                msgDbHelper.open();
                msgDbHelper.delete(tableName, selection, delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
        }
    }

    public long insertMsg(Msg msg) {
        synchronized (msgDbHelper) {
            long id = 0;
            try {
                ContentValues cv = new ContentValues();
                cv.put("fromuser", msg.fromUser);
                cv.put("touser", msg.toUser);
                cv.put("msgtext", msg.msgtext);
                cv.put("datetime", msg.datetime);
                cv.put("isfrom", msg.isFrom);
                cv.put("isread", msg.isRead);
                cv.put("msgtype", msg.msgtype);
                cv.put("cardId", msg.cardId);
                cv.put("title", msg.title);
                cv.put("iconurl", msg.iconurl);
                cv.put("filepath", msg.filepath);
                cv.put("speechlength", msg.speechlength);
                cv.put("whoid", msg.whoid);
                cv.put("fid", msg.fid);
                cv.put("username", msg.username);
                cv.put("face", msg.face);
                cv.put("clazzid", msg.clazzid);
                cv.put("clazzimg", msg.clazzimg);
                cv.put("clazzname", msg.clazzname);
                cv.put("chatflag", msg.chatflag);
                msgDbHelper.open();
                id = msgDbHelper.insert(tableName, cv);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return id;
        }
    }

    public ArrayList<Msg> insertListMsg(List<Msg> msgs) {
        synchronized (msgDbHelper) {
            ArrayList<Msg> arrayList = new ArrayList<Msg>();
            try {
                msgDbHelper.open();
                for (int j = msgs.size() - 1; j >= 0; j--) {
                    Msg msg = msgs.get(j);
                    ContentValues cv = new ContentValues();
                    cv.put("fromuser", msg.fromUser);
                    cv.put("touser", msg.toUser);
                    cv.put("msgtext", msg.msgtext);
                    cv.put("datetime", msg.datetime);
                    cv.put("isfrom", msg.isFrom);
                    cv.put("isread", msg.isRead);
                    cv.put("msgtype", msg.msgtype);
                    cv.put("cardId", msg.cardId);
                    cv.put("title", msg.title);
                    cv.put("iconurl", msg.iconurl);
                    cv.put("filepath", msg.filepath);
                    cv.put("speechlength", msg.speechlength);
                    cv.put("whoid", msg.whoid);
                    cv.put("fid", msg.fid);
                    cv.put("username", msg.username);
                    cv.put("face", msg.face);
                    cv.put("clazzid", msg.clazzid);
                    cv.put("clazzimg", msg.clazzimg);
                    cv.put("clazzname", msg.clazzname);
                    cv.put("chatflag", msg.chatflag);
                    long id = msgDbHelper.insert(tableName, cv);
                    msg.id = String.valueOf(id);
                    arrayList.add(msg);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return arrayList;
        }
    }


    public boolean insertListMsgs(List<Msg> msgs) {
        synchronized (msgDbHelper) {
            SQLiteDatabase db = msgDbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + tableName + "(fromuser,touser,msgtext,datetime,isfrom,isread,msgtype,cardId,title,iconurl,filepath,speechlength,whoid,fid,username,face,clazzid,clazzimg,clazzname,chatflag)values(" +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                db.beginTransaction();
                for (Msg msg : msgs) {
                    statement.bindString(1, msg.fromUser);
                    statement.bindString(2, msg.toUser);
                    statement.bindString(3, msg.msgtext);
                    statement.bindString(4, msg.datetime);
                    statement.bindString(5, msg.isFrom);
                    statement.bindString(6, msg.isRead);
                    statement.bindString(7, msg.msgtype);
                    statement.bindString(8, msg.cardId);
                    statement.bindString(9, msg.title);
                    statement.bindString(10, msg.iconurl);
                    statement.bindString(11, msg.filepath);
                    statement.bindString(12, msg.speechlength + "");
                    statement.bindString(13, msg.whoid);
                    statement.bindString(14, msg.fid);
                    statement.bindString(15, msg.username);
                    statement.bindString(16, msg.face);
                    statement.bindString(17, msg.clazzid);
                    statement.bindString(18, msg.clazzimg);
                    statement.bindString(19, msg.clazzname);
                    statement.bindString(20, msg.chatflag);
                    statement.executeInsert();
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (db != null) {
                    if (db.isOpen()) {
                        db.endTransaction();
                    }
                    db.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return true;
        }
    }

    public long insertGroupMsg(Msg msg) {
        synchronized (msgDbHelper) {
            long id = 0;
            try {
                ContentValues cv = new ContentValues();
                cv.put("fromuser", msg.clazzid);
                cv.put("touser", msg.fromUser);
                cv.put("msgtext", msg.msgtext);
                cv.put("datetime", msg.datetime);
                cv.put("isfrom", msg.isFrom);
                cv.put("isread", msg.isRead);
                cv.put("msgtype", msg.msgtype);
                cv.put("cardId", msg.cardId);
                cv.put("title", msg.title);
                cv.put("iconurl", msg.iconurl);
                cv.put("filepath", msg.filepath);
                cv.put("speechlength", msg.speechlength);
                cv.put("whoid", msg.whoid);
                cv.put("fid", msg.fid);
                cv.put("username", msg.username);
                cv.put("face", msg.face);
                cv.put("clazzid", msg.clazzid);
                cv.put("clazzimg", msg.clazzimg);
                cv.put("clazzname", msg.clazzname);
                cv.put("chatflag", msg.chatflag);
                msgDbHelper.open();
                id = msgDbHelper.insert(tableName, cv);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return id;
        }
    }

    public Msg getOneMsg(String fid, String uid) {
        synchronized (msgDbHelper) {
            Msg msg = new Msg();
            String orderBy = "_id desc";
            String a[] = new String[3];
            a[0] = fid;
            a[1] = uid;
            a[2] = uid;
            Cursor checkCarCursor = null;
            try {
                msgDbHelper.open();
                checkCarCursor = msgDbHelper.select(tableName, null, "fromuser=? and touser=? and whoid=?", a, null, null, orderBy, null);
                for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                        .moveToNext()) {
                    int _id = checkCarCursor.getColumnIndex("_id");
                    int fromuser = checkCarCursor.getColumnIndex("fromuser");
                    int touser = checkCarCursor.getColumnIndex("touser");
                    int msgtext = checkCarCursor.getColumnIndex("msgtext");
                    int datetime = checkCarCursor.getColumnIndex("datetime");
                    int isFrom = checkCarCursor.getColumnIndex("isfrom");
                    int isRead = checkCarCursor.getColumnIndex("isread");
                    int msgtype = checkCarCursor.getColumnIndex("msgtype");
                    int cardId = checkCarCursor.getColumnIndex("cardId");
                    int title = checkCarCursor.getColumnIndex("title");
                    int iconurl = checkCarCursor.getColumnIndex("iconurl");
                    int filepath = checkCarCursor.getColumnIndex("filepath");
                    int speechlength = checkCarCursor.getColumnIndex("speechlength");
                    int whoid = checkCarCursor.getColumnIndex("whoid");
                    int username = checkCarCursor.getColumnIndex("username");
                    int face = checkCarCursor.getColumnIndex("face");
                    int clazzid = checkCarCursor.getColumnIndex("clazzid");
                    int clazzimg = checkCarCursor.getColumnIndex("clazzimg");
                    int clazzname = checkCarCursor.getColumnIndex("clazzname");
                    int chatflag = checkCarCursor.getColumnIndex("chatflag");
                    msg.id = checkCarCursor.getString(_id);
                    msg.fromUser = checkCarCursor.getString(fromuser);
                    msg.toUser = checkCarCursor.getString(touser);
                    msg.msgtext = checkCarCursor.getString(msgtext);
                    msg.datetime = checkCarCursor.getString(datetime);
                    msg.isFrom = checkCarCursor.getString(isFrom);
                    msg.isRead = checkCarCursor.getString(isRead);
                    msg.msgtype = checkCarCursor.getString(msgtype);
                    msg.cardId = checkCarCursor.getString(cardId);
                    msg.title = checkCarCursor.getString(title);
                    msg.iconurl = checkCarCursor.getString(iconurl);
                    msg.filepath = checkCarCursor.getString(filepath);
                    msg.speechlength = checkCarCursor.getLong(speechlength);
                    msg.whoid = checkCarCursor.getString(whoid);
                    msg.username = checkCarCursor.getString(username);
                    msg.face = checkCarCursor.getString(face);
                    msg.clazzid = checkCarCursor.getString(clazzid);
                    msg.clazzimg = checkCarCursor.getString(clazzimg);
                    msg.clazzname = checkCarCursor.getString(clazzname);
                    msg.chatflag = checkCarCursor.getString(chatflag);
                    break;
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return msg;
        }
    }


    public ArrayList<Msg> getMsgList(int flag, int currentPage, int count, int newcount, String fid, String uid) {
        synchronized (msgDbHelper) {
            ArrayList<Msg> msList = new ArrayList<Msg>();
            String orderBy = "_id desc";
            String a[] = new String[3];
            a[0] = fid;
            a[1] = uid;
            a[2] = uid;
            if ((currentPage - 1) * count + newcount < 0) {
                newcount = 0;
            }
            String limit = String.valueOf(((currentPage - 1) * count) + newcount) + "," + String.valueOf(count);
            Cursor checkCarCursor = null;
            try {
                msgDbHelper.open();
                checkCarCursor = msgDbHelper.select(tableName, null, "fromuser=? and touser=? and whoid=?", a, null, null, orderBy, limit);
                for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                        .moveToNext()) {
                    Msg msg = new Msg();
                    int _id = checkCarCursor.getColumnIndex("_id");
                    int fromuser = checkCarCursor.getColumnIndex("fromuser");
                    int touser = checkCarCursor.getColumnIndex("touser");
                    int msgtext = checkCarCursor.getColumnIndex("msgtext");
                    int datetime = checkCarCursor.getColumnIndex("datetime");
                    int isFrom = checkCarCursor.getColumnIndex("isfrom");
                    int isRead = checkCarCursor.getColumnIndex("isread");
                    int msgtype = checkCarCursor.getColumnIndex("msgtype");
                    int cardId = checkCarCursor.getColumnIndex("cardId");
                    int title = checkCarCursor.getColumnIndex("title");
                    int iconurl = checkCarCursor.getColumnIndex("iconurl");
                    int filepath = checkCarCursor.getColumnIndex("filepath");
                    int speechlength = checkCarCursor.getColumnIndex("speechlength");
                    int whoid = checkCarCursor.getColumnIndex("whoid");
                    int username = checkCarCursor.getColumnIndex("username");
                    int face = checkCarCursor.getColumnIndex("face");
                    int clazzid = checkCarCursor.getColumnIndex("clazzid");
                    int clazzimg = checkCarCursor.getColumnIndex("clazzimg");
                    int clazzname = checkCarCursor.getColumnIndex("clazzname");
                    int chatflag = checkCarCursor.getColumnIndex("chatflag");
                    msg.id = checkCarCursor.getString(_id);
                    msg.fromUser = checkCarCursor.getString(fromuser);
                    msg.toUser = checkCarCursor.getString(touser);
                    msg.msgtext = checkCarCursor.getString(msgtext);
                    msg.datetime = checkCarCursor.getString(datetime);
                    msg.isFrom = checkCarCursor.getString(isFrom);
                    msg.isRead = checkCarCursor.getString(isRead);
                    msg.msgtype = checkCarCursor.getString(msgtype);
                    msg.cardId = checkCarCursor.getString(cardId);
                    msg.title = checkCarCursor.getString(title);
                    msg.iconurl = checkCarCursor.getString(iconurl);
                    msg.filepath = checkCarCursor.getString(filepath);
                    msg.speechlength = checkCarCursor.getLong(speechlength);
                    msg.whoid = checkCarCursor.getString(whoid);
                    msg.username = checkCarCursor.getString(username);
                    msg.face = checkCarCursor.getString(face);
                    msg.clazzid = checkCarCursor.getString(clazzid);
                    msg.clazzimg = checkCarCursor.getString(clazzimg);
                    msg.clazzname = checkCarCursor.getString(clazzname);
                    msg.chatflag = checkCarCursor.getString(chatflag);
                    msList.add(msg);
                }
                Collections.sort(msList, new IdComparator());
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return msList;
        }
    }


    public ArrayList<Msg> getMsgPhotoList(int flag, String fid, String uid) {
        synchronized (msgDbHelper) {
            ArrayList<Msg> msList = new ArrayList<Msg>();
            String orderBy = "_id asc";
            String a[] = new String[4];
            a[0] = fid;
            a[1] = uid;
            a[2] = "1";
            a[3] = uid;
            Cursor checkCarCursor = null;
            try {
                msgDbHelper.open();
                checkCarCursor = msgDbHelper.select(tableName, null, "fromuser=? and touser=? and msgtype=? and whoid=?", a, null, null, orderBy);
                for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                        .moveToNext()) {
                    Msg msg = new Msg();
                    int msgtext = checkCarCursor.getColumnIndex("msgtext");
                    msg.msgtext = checkCarCursor.getString(msgtext);
                    msg.filepath = checkCarCursor.getString(checkCarCursor.getColumnIndex("filepath"));
                    msList.add(msg);
                }
                Collections.sort(msList, new IdComparator());
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return msList;
        }
    }


//	public ArrayList<Msg> getMsgRecordList(String uid) {
//		synchronized (msgDbHelper) {
//			ArrayList<Msg> dBList = new ArrayList<Msg>();
//			Cursor checkCarCursor = null;
//			try {
//				msgDbHelper.open();
//				checkCarCursor = msgDbHelper.queryMsgLists(uid);
//				for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
//						.moveToNext()) {
//					Msg msg = new Msg();
//					int _id = checkCarCursor.getColumnIndex("_id");
//					int fromuser = checkCarCursor.getColumnIndex("fromuser");
//					int touser = checkCarCursor.getColumnIndex("touser");
//					int msgtext = checkCarCursor.getColumnIndex("msgtext");
//					int datetime = checkCarCursor.getColumnIndex("datetime");
//					int isFrom = checkCarCursor.getColumnIndex("isfrom");
//					int isRead = checkCarCursor.getColumnIndex("isread");
//					int msgtype = checkCarCursor.getColumnIndex("msgtype");
//					int filepath = checkCarCursor.getColumnIndex("filepath");
//					int speechlength = checkCarCursor.getColumnIndex("speechlength");
//					int whoid = checkCarCursor.getColumnIndex("whoid");
//					int username = checkCarCursor.getColumnIndex("username");
//					int face = checkCarCursor.getColumnIndex("face");
//					int clazzid = checkCarCursor.getColumnIndex("clazzid");
//					int clazzimg = checkCarCursor.getColumnIndex("clazzimg");
//					int clazzname = checkCarCursor.getColumnIndex("clazzname");
//					int chatflag = checkCarCursor.getColumnIndex("chatflag");
//					msg.id = checkCarCursor.getString(_id);
//					msg.fromUser = checkCarCursor.getString(fromuser);
//					msg.toUser = checkCarCursor.getString(touser);
//					msg.msgtext = checkCarCursor.getString(msgtext);
//					msg.datetime = checkCarCursor.getString(datetime);
//					msg.isFrom = checkCarCursor.getString(isFrom);
//					msg.isRead = checkCarCursor.getString(isRead);
//					msg.msgtype = checkCarCursor.getString(msgtype);
//					msg.filepath = checkCarCursor.getString(filepath);
//					msg.speechlength = checkCarCursor.getLong(speechlength);
//					msg.whoid = checkCarCursor.getString(whoid);
//					msg.username = checkCarCursor.getString(username);
//					msg.face = checkCarCursor.getString(face);
//					msg.clazzid = checkCarCursor.getString(clazzid);
//					msg.clazzimg = checkCarCursor.getString(clazzimg);
//					msg.clazzname = checkCarCursor.getString(clazzname);
//					msg.chatflag = checkCarCursor.getString(chatflag);
//					dBList.add(msg);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}finally{
//				if(checkCarCursor!=null){
//					checkCarCursor.close();
//				}
//				if(msgDbHelper!=null){
//					msgDbHelper.close();
//				}
//			}
//			return dBList;
//		}
//	}

    public ArrayList<Msg> getGroupMsgList(String clazzId, String uid, int currentPage, int count, int newcount) {
        synchronized (msgDbHelper) {
            ArrayList<Msg> dBList = new ArrayList<Msg>();
            String a[] = new String[2];
            a[0] = clazzId;
            a[1] = uid;
            String orderBy = "_id desc";
            if ((currentPage - 1) * count + newcount < 0) {
                newcount = 0;
            }
            String limit = String.valueOf(((currentPage - 1) * count) + newcount) + "," + String.valueOf(count);
            Cursor checkCarCursor = null;
            try {
                msgDbHelper.open();
                checkCarCursor = msgDbHelper.select(tableName, null,
                        "fromuser=? and whoid=?", a, null, null, orderBy, limit);
                if (checkCarCursor != null) {
                    for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                            .moveToNext()) {
                        Msg msg = new Msg();
                        int _id = checkCarCursor.getColumnIndex("_id");
                        int fromuser = checkCarCursor.getColumnIndex("fromuser");
                        int touser = checkCarCursor.getColumnIndex("touser");
                        int msgtext = checkCarCursor.getColumnIndex("msgtext");
                        int datetime = checkCarCursor.getColumnIndex("datetime");
                        int isFrom = checkCarCursor.getColumnIndex("isfrom");
                        int isRead = checkCarCursor.getColumnIndex("isread");
                        int msgtype = checkCarCursor.getColumnIndex("msgtype");
                        int cardId = checkCarCursor.getColumnIndex("cardId");
                        int title = checkCarCursor.getColumnIndex("title");
                        int iconurl = checkCarCursor.getColumnIndex("iconurl");
                        int filepath = checkCarCursor.getColumnIndex("filepath");
                        int speechlength = checkCarCursor.getColumnIndex("speechlength");
                        int whoid = checkCarCursor.getColumnIndex("whoid");
                        int fid = checkCarCursor.getColumnIndex("fid");
                        int username = checkCarCursor.getColumnIndex("username");
                        int face = checkCarCursor.getColumnIndex("face");
                        int clazzid = checkCarCursor.getColumnIndex("clazzid");
                        int clazzimg = checkCarCursor.getColumnIndex("clazzimg");
                        int clazzname = checkCarCursor.getColumnIndex("clazzname");
                        int chatflag = checkCarCursor.getColumnIndex("chatflag");

                        msg.id = checkCarCursor.getString(_id);
                        msg.fromUser = checkCarCursor.getString(fromuser);
                        msg.toUser = checkCarCursor.getString(touser);
                        msg.msgtext = checkCarCursor.getString(msgtext);
                        msg.datetime = checkCarCursor.getString(datetime);
                        msg.isFrom = checkCarCursor.getString(isFrom);
                        msg.isRead = checkCarCursor.getString(isRead);
                        msg.msgtype = checkCarCursor.getString(msgtype);
                        msg.cardId = checkCarCursor.getString(cardId);
                        msg.title = checkCarCursor.getString(title);
                        msg.iconurl = checkCarCursor.getString(iconurl);
                        msg.filepath = checkCarCursor.getString(filepath);
                        msg.speechlength = checkCarCursor.getLong(speechlength);
                        msg.whoid = checkCarCursor.getString(whoid);
                        msg.fid = checkCarCursor.getString(fid);
                        msg.username = checkCarCursor.getString(username);
                        msg.face = checkCarCursor.getString(face);
                        msg.clazzid = checkCarCursor.getString(clazzid);
                        msg.clazzimg = checkCarCursor.getString(clazzimg);
                        msg.clazzname = checkCarCursor.getString(clazzname);
                        msg.chatflag = checkCarCursor.getString(chatflag);
                        dBList.add(msg);
                    }
                }
                Collections.sort(dBList, new IdComparator());
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return dBList;
        }
    }

    public ArrayList<Msg> getGroupMsgPhotoList(String clazzId, String uid) {
        synchronized (msgDbHelper) {
            ArrayList<Msg> dBList = new ArrayList<Msg>();
            String a[] = new String[3];
            a[0] = clazzId;
            a[1] = uid;
            a[2] = "1";
            String orderBy = "_id asc";
            Cursor checkCarCursor = null;
            try {
                msgDbHelper.open();
                checkCarCursor = msgDbHelper.select(tableName, null,
                        "fromuser=? and whoid=? and msgtype=?", a, null, null, orderBy);
                if (checkCarCursor != null) {
                    for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                            .moveToNext()) {
                        Msg msg = new Msg();
                        int msgtext = checkCarCursor.getColumnIndex("msgtext");
                        msg.msgtext = checkCarCursor.getString(msgtext);
                        msg.filepath = checkCarCursor.getString(checkCarCursor.getColumnIndex("filepath"));
                        dBList.add(msg);
                    }
                }
                Collections.sort(dBList, new IdComparator());
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return dBList;
        }
    }


    public boolean deleteMsg(String groupId, String flag, String uid) {
        synchronized (msgDbHelper) {
            String a[] = new String[2];
            a[0] = groupId;
            a[1] = uid;
            boolean isSucc = true;
            try {
                msgDbHelper.open();
                if ("1".equals(flag)) {
                    isSucc = msgDbHelper.delete(tableName, "fromuser=? and whoid=?", a);
                } else {
                    isSucc = msgDbHelper.delete(tableName, "clazzid=? and whoid=?", a);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return isSucc;
        }
    }

    public String getGroupMsgTime(String uid) {
        synchronized (msgDbHelper) {
            String date = "";
            String a[] = new String[2];
            a[0] = uid;
            a[1] = "y";
            String orderBy = "datetime desc";
            try {
                msgDbHelper.open();
                Cursor checkCarCursor = msgDbHelper.select(tableName, null,
                        "whoid=? and isfrom=?", a, null, null, orderBy);
                if (checkCarCursor != null) {
                    for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                            .moveToNext()) {
                        int msgtext = checkCarCursor.getColumnIndex("msgtext");
                        int filepath = checkCarCursor.getColumnIndex("filepath");
                        String text = checkCarCursor.getString(msgtext);
                        String file = checkCarCursor.getString(filepath);
                        if (!"f-ai-l".equals(text) && !"f-ai-l".equals(file)) {
                            int DATETIME = checkCarCursor.getColumnIndex("datetime");
                            date = checkCarCursor.getString(DATETIME);
                            checkCarCursor.close();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return date;
        }
    }

    public String getAllData() {
        synchronized (msgDbHelper) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------Msg---------------------------------------\n");
            Cursor checkCarCursor = null;
            try {
                msgDbHelper.open();
                checkCarCursor = msgDbHelper.select(tableName, null, null, null, null, null, "_id asc");
                for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                        .moveToNext()) {
                    Msg msg = new Msg();
                    int _id = checkCarCursor.getColumnIndex("_id");
                    int fromuser = checkCarCursor.getColumnIndex("fromuser");
                    int touser = checkCarCursor.getColumnIndex("touser");
                    int msgtext = checkCarCursor.getColumnIndex("msgtext");
                    int datetime = checkCarCursor.getColumnIndex("datetime");
                    int isFrom = checkCarCursor.getColumnIndex("isfrom");
                    int isRead = checkCarCursor.getColumnIndex("isread");
                    int msgtype = checkCarCursor.getColumnIndex("msgtype");
                    int cardId = checkCarCursor.getColumnIndex("cardId");
                    int title = checkCarCursor.getColumnIndex("title");
                    int iconurl = checkCarCursor.getColumnIndex("iconurl");
                    int filepath = checkCarCursor.getColumnIndex("filepath");
                    int speechlength = checkCarCursor.getColumnIndex("speechlength");
                    int whoid = checkCarCursor.getColumnIndex("whoid");
                    int username = checkCarCursor.getColumnIndex("username");
                    int face = checkCarCursor.getColumnIndex("face");
                    int clazzid = checkCarCursor.getColumnIndex("clazzid");
                    int clazzimg = checkCarCursor.getColumnIndex("clazzimg");
                    int clazzname = checkCarCursor.getColumnIndex("clazzname");
                    int chatflag = checkCarCursor.getColumnIndex("chatflag");
                    msg.id = checkCarCursor.getString(_id);
                    msg.fromUser = checkCarCursor.getString(fromuser);
                    msg.toUser = checkCarCursor.getString(touser);
                    msg.msgtext = checkCarCursor.getString(msgtext);
                    msg.datetime = checkCarCursor.getString(datetime);
                    msg.isFrom = checkCarCursor.getString(isFrom);
                    msg.isRead = checkCarCursor.getString(isRead);
                    msg.msgtype = checkCarCursor.getString(msgtype);
                    msg.cardId = checkCarCursor.getString(cardId);
                    msg.title = checkCarCursor.getString(title);
                    msg.iconurl = checkCarCursor.getString(iconurl);
                    msg.filepath = checkCarCursor.getString(filepath);
                    msg.speechlength = checkCarCursor.getLong(speechlength);
                    msg.whoid = checkCarCursor.getString(whoid);
                    msg.username = checkCarCursor.getString(username);
                    msg.face = checkCarCursor.getString(face);
                    msg.clazzid = checkCarCursor.getString(clazzid);
                    msg.clazzimg = checkCarCursor.getString(clazzimg);
                    msg.clazzname = checkCarCursor.getString(clazzname);
                    msg.chatflag = checkCarCursor.getString(chatflag);
                    sb.append(msg.toString()).append("\n");
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (msgDbHelper != null) {
                    msgDbHelper.close();
                }
            }
            return sb.toString();
        }
    }
}
