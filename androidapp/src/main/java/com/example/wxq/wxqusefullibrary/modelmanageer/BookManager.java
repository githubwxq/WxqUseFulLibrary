package com.example.wxq.wxqusefullibrary.modelmanageer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.wxq.wxqusefullibrary.modedbhelp.MyDbHelper;
import com.example.wxq.wxqusefullibrary.model.Book;

import java.util.ArrayList;


public class BookManager {
    private static BookManager bookManager;
    private static MyDbHelper bookDbhelp;
    private String tableName = "BOOK";

    private BookManager(Context context) {

        if (bookDbhelp == null) {
            bookDbhelp = MyDbHelper.getInstance(context);

        }


    }

    public static BookManager getInstance(Context context) {
        if (bookManager == null) {
            bookManager = new BookManager(context);
        }
        return bookManager;
    }

    public long insertBook(Book book) {
        synchronized (bookDbhelp) {
            long id = 0;
            try {
                ContentValues cv = new ContentValues();
                cv.put("name", book.getName());
                cv.put("price", book.getPrice());
                cv.put("type", book.getType());
                cv.put("tag", book.getTag());
                id = bookDbhelp.insert(tableName, cv);
            } catch (Exception e) {
                // TODO: handle exception
            }finally{
                if(bookDbhelp!=null){
                    bookDbhelp.close();
                }
            }
            return id;
        }
    }

public ArrayList<Book> getAllBook(){
    ArrayList<Book> books=new ArrayList<>();
    Cursor cursor = bookDbhelp.getSqLiteDatabase().rawQuery("select * from book", null);
    while (cursor.moveToNext()) {
        Book book=new Book();
        String name = cursor.getString(0);//获取第二列的值
        String price = cursor.getString(1);//获取第二列的值
        String type = cursor.getString(2);//获取第二列的值
        int tag = cursor.getInt(3);//获取第二列的值
        book.setName(name);
        book.setPrice(price);
        book.setType(type);
        book.setTag(tag);
        books.add(book);
    }
    cursor.close();
    bookDbhelp.close();
    return  books;
}

}
