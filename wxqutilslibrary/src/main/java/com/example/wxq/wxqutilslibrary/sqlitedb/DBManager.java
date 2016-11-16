package com.example.wxq.wxqutilslibrary.sqlitedb;
/**
 * Created by ztn
 * 数据库sql 语句集中管理
 */
public class DBManager {

    public static final String CREATE_TBL_MSG = "Create TABLE MSG("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "fromuser text,"
            + "touser text,"
            + "msgtext text,"
            + "datetime text,"
            + "isfrom text,"
            + "isread text,"
            + "msgtype text,"
            + "cardId text,"
            + "title text,"
            + "iconurl text,"
            + "filepath text,"
            + "speechlength text,"
            + "whoid text,"
            + "fid text,"
            + "username text,"
            + "face text,"
            + "clazzid text,"
            + "clazzimg text,"
            + "clazzname text,"
            + "chatflag text"
            + ")";
    public static final String CREATE_TAB_WEIDUMSG = "Create Table WEIDUMSG("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "uid text,"
            + "mid text,"
            + "count text"
            + ")";
    public static final String CREATE_TBL_USERMODEL = "Create TABLE USERMODEL("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "username text,"
            + "age text,"
            + "high text"
            + ")";
    public static final String CREATE_TBL_HISMSG = "Create TABLE HISMSG("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "fromId text,"
            + "msgImg text,"
            + "msgText text,"
            + "msgTitle text,"
            + "msgTime text,"
            + "chatType text,"
            + "isDel text,"
            + "newNums text,"
            + "flag1 text,"
            + "flag2 text,"
            + "flag3 text,"
            + "mid text"
            + ")";
    public static final String CREATE_TBL_USERINFOINFO = "CREATE TABLE USERINFO("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "userId text,"
            + "xlId text,"
            + "accessToken text,"
            + "time text,"
            + "userName TEXT,"
            + "fullName text,"
            + "phoneNumber text,"
            + "userImageUrl text,"
            + "provinceName text,"
            + "provinceId text,"
            + "cityName text,"
            + "cityId text,"
            + "districtName text,"
            + "districtId text,"
            + "deviceToken text,"
            + "registered text,"
            + "totalRole text,"
            + "totalName text,"
            + "addFlag text,"
            + "flag text,"
            + "xxCode text,"
            + "pinyin text,"
            + "type INTEGER,"
            + "openType INTEGER,"
            + "mid text"
            + ")";
    public static final String CREATE_TAB_CLAZZS = "CREATE TABLE CLAZZS("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "userId text,"
            + "role text,"
            + "schoolId text,"
            + "schoolName text,"
            + "classId text,"
            + "className text,"
            + "studentId text,"
            + "studentName text,"
            + "address text,"
            + "subjectName text,"
            + "subjectId text,"
            + "disflag text,"
            + "shiflag text,"
            + "isnickname text,"
            + "classBlocked text,"
            + "flag text,"
            + "pingyin text,"
            + "owner text,"
            + "classNo text,"
            + "classNamePinyin text,"
            + "isTeacher integer,"
            + "isChat integer,"
            + "mid text"
            + ")";

    public static final String CREATE_TBL_FUNCTION = "Create TABLE FUNCTION("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "setId text,"
            + "value text,"
            + "name text,"
            + "desc text,"
            + "functionId text,"
            + "value2 text,"
            + "flag1 text,"
            + "flag2 text,"
            + "flag3 text,"
            + "mid text"
            + ")";

    public static final String CREATE_TAB_SERVICE_ITEM = "Create Table SERVICE_ITEM("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "ID integer,"
            + "itemName text,"
            + "orderId integer,"
            + "isHasNew integer,"
            + "selected integer,"
            + "userId text"
            + ")";
    public static final String CREATE_TAB_CLAZZDYNAMIC = "CREATE TABLE CLAZZDYNAMIC("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "mid text,"
            + "eventId text,"
            + "isFavourite text,"
            + "userId text,"
            + "eventInfo text,"
            + "fullName text,"
            + "avatar text,"
            + "createTime text,"
            + "eventImage text,"
            + "classId text,"
            + "className text,"
            + "favouriteNum text,"
            + "type text,"
            + "commentNum text"
            + ")";

    public static final String CREATE_TAB_SCHOOLNEWS = "CREATE TABLE SCHOOLNEWS("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "news_code text,"
            + "news_url text,"
            + "news_picUrl2 text,"
            + "news_picUrl text,"
            + "news_title text,"
            + "news_time text,"
            + "desc text,"
            + "flag text,"
            + "mid text"
            + ")";

    public static final String CREATE_TAB_STARTENDTIME = "CREATE TABLE STARTENDTIME("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "mid text,"
            + "startTime text,"
            + "endTime text,"
            + "classId text,"
            + "updateTime text"
            + ")";

    public static final String CREATE_TAB_NEARBYEDUSEARCHHISTORY = "CREATE TABLE NEARBYEDUSEARCHHISTORY("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "userid text,"
            + "content text"
            + ")";

    public static final String CREATE_TAB_CITYLIST = "CREATE TABLE CITYLIST("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "areaid text,"
            + "areaName text,"
            + "firstLetter text,"
            + "pinyin text,"
            + "parentCityName text,"
            + "parentCityId text,"
            + "hotcity integer"
            + ")";

    public static final String CREATE_TAB_NEWTIME = "Create Table NEWTIME("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "uid text,"
            + "time text" + ")";
    public static final String CREATE_TAB_ONLINESCHOOLARTICLE = "CREATE TABLE ONLINESCHOOLARTICLE("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "p_id text,"
            + "s_title text,"
            + "s_author text,"
            + "s_pic text,"
            + "s_category text,"
            + "s_tag text,"
            + "s_abstract text,"
            + "s_html text,"
            + "s_url text,"
            + "s_type text,"
            + "f_groups_id text,"
            + "f_groups_layer text,"
            + "s_isdel text,"
            + "s_read_num integer,"
            + "s_click_num integer,"
            + "s_forward_num integer,"
            + "s_collect_num integer,"
            + "s_like_num integer,"
            + "s_adv_num integer,"
            + "s_reward_num integer,"
            + "s_creator text,"
            + "s_creater_time text,"
            + "s_update_time text,"
            + "s_isPerm integer,"
            + "fileName text,"
            + "platId text,"
            + "platName text,"
            + "platImg text,"
            + "fileSize integer,"
            + "isUploadFile integer,"
            + "collectId text,"
            + "mid text"
            + ")";

    public static final String CREATE_TAB_MYCOURSEFILE = "CREATE TABLE MYCOURSEFILE("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "fileName text,"
            + "fileSize integer,"
            + "filePath text,"
            + "userId text,"
            + "time text,"
            + "isLocalUpload integer,"
            + "downloadUrl text"
            + ")";

    public static final String CREATE_TAB_ONLINESCHOOL = "CREATE TABLE ONLINESCHOOL("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "p_id text,"
            + "s_mail text,"
            + "s_account text,"
            + "s_phone text,"
            + "s_xiaoxin_code text,"
            + "s_name text,"
            + "s_img text,"
            + "s_type text,"
            + "s_principal text,"
            + "s_identity text,"
            + "s_auth_status text,"
            + "s_auth_time text,"
            + "s_desc text,"
            + "s_blance text,"
            + "s_province text,"
            + "s_city text,"
            + "s_area text,"
            + "s_addr text,"
            + "s_state text,"
            + "s_login_time text,"
            + "s_create_time text,"
            + "s_update_time text,"
            + "s_isAudit text,"
            + "s_user_num text,"
            + "s_report_num text,"
            + "s_point text,"
            + "s_rank text,"
            + "s_grand text,"
            + "isFollow text,"
            + "tedian text,"
            + "staticPath text,"
            + "receiveMsg integer,"
            + "pinyin text,"
            + "msgNum INTEGER,"
            + "userId text"
            + ")";

    public final static String CREATE_TAB_SNHIS = "CREATE TABLE SNHIS("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "content text,"
            + "time text,"
            + "type integer,"
            + "mid text,"
            + "title text"
            + ")";

    public final static String CREATE_TAB_CLASSDYNAMIC_NEW = "CREATE TABLE CLASSDYNAMIC_NEW("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "classId text,"
            + "mid text"
            + ")";
}