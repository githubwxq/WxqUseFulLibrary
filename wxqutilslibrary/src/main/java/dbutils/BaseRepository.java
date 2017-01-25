package dbutils;

import android.content.Context;

/**
 * 操作数据库表的基类，每张表对应一个操作类，实现增删改查
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.caidongdong.databasedemo.repository.BaseRepository.java
 * @author: wxq
 * @date: 2016-06-25 15:01
 */
public class BaseRepository<T> {

    protected DbHelper dbHelper;

    public BaseRepository(Context context) {
        dbHelper = DbHelper.getInstance(context);
    }











}
