package com.example.wxq.wxqusefullibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wxq.wxqusefullibrary.model.Book;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.model.MulBean1;
import com.example.wxq.wxqusefullibrary.model.MulBean2;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import viewgroupadapter.IMulTypeHelper;
import viewgroupadapter.MulTypeAdapter;
import viewgroupadapter.OnItemClickListener;
import viewgroupadapter.SingleAdapter;
import viewgroupadapter.ViewGroupUtils;

public class ViewGroupActivity extends BaseActivity {

    private LinearLayout ll_content;
    private LinearLayout ll_content_different,ll_content_alldifferent;

    private ArrayList<Book> books;
    private ArrayList<Book> differbooks;
    private List<IMulTypeHelper> alldifferbooks;

    OnItemClickListener onItemClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        //设置OnItemClickListener
        int myposition=0;

        onItemClickListener = new MyOnItemClickListener(myposition);
        initData();
        initView();

    }

    private void initData() {
        books = new ArrayList<>();
        Book a1 = new Book("wxq", "15");
        Book a2 = new Book("ty", "21");
        Book a3 = new Book("xxq", "44");
        Book a4 = new Book("wxg", "123");
        books.add(a1);
        books.add(a2);
        books.add(a3);
        books.add(a4);

        differbooks = new ArrayList<>();
        Book b1 = new Book("bbbwxq", "bb1");
        b1.setType("flag1");
        Book b2 = new Book("bbbty", "bb2");
        b2.setType("flag2");
        Book b3 = new Book("bbbxxq", "b3");
        b3.setType("flag3");
        Book b4 = new Book("bbbwxg", "b4");
        b4.setType("flag4");

        differbooks.add(b1);
        differbooks.add(b2);
        differbooks.add(b3);
        differbooks.add(b4);
        alldifferbooks=new ArrayList<IMulTypeHelper>();
        MulBean1 bean1 = new MulBean1("http://p14.go007.com/2014_11_02_05/a03541088cce31b8_1.jpg");
        alldifferbooks.add(bean1);
        alldifferbooks.add(new MulBean2("张旭童"));
        alldifferbooks.add(new MulBean1("http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg"));
    }

    @Override
    public void widgetClick(View v) {

    }

    private void initView() {
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        ll_content_different = (LinearLayout) findViewById(R.id.ll_content_different);
        ll_content_alldifferent = (LinearLayout) findViewById(R.id.ll_content_alldifferent);


        //单一ItemView
        ViewGroupUtils.addViews(ll_content, new BookSingleAdapter(), onItemClickListener);


        //多种ItemViewType，但是数据结构相同，可以传入数据结构泛型，避免强转
        ViewGroupUtils.addViews(ll_content_different, new MulTypeAdapter<Book>(this, differbooks) {
            @Override
            public void onBindView(ViewGroup parent, View itemView, final Book data, int pos) {
                ((TextView) itemView.findViewById(R.id.tv_bookname)).setText(data.getName() + "");
//                Glide.with(MulTypeActivity.this)
//                        .load(data.getAvatar())
//                        .into((ImageView) itemView.findViewById(ivAvatar));
                //#### Adapter.onBindView()里设置 优先级更高
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "onBindView里设置:文字是:" + data.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, onItemClickListener);
        //可以在`ViewGroupUtils.addViews`直接作为参数传入.\
        // 也可以用`ViewGroupUtils.setOnItemClickListener(）`设置
        // **优先级比`Adapter.onBindView()`里设置低，**
        //ViewGroupUtils.setOnItemClickListener(linearLayout,onItemClickListener);


        //多种Item类型：数据结构不同 不传泛型了 使用时需要强转javaBean，判断ItemLayoutId
        ViewGroupUtils.addViews(ll_content_alldifferent, new MulTypeAdapter(this, alldifferbooks) {
            @Override
            public void onBindView(ViewGroup parent, View itemView, IMulTypeHelper data, int pos) {
                switch (data.getItemLayoutId()) {
                    case R.layout.book_list_item2:
                        MulBean1 mulBean1 = (MulBean1) data;
                        Glide.with(ViewGroupActivity.this)
                                .load(mulBean1.getUrl())
                                .into((ImageView) (itemView.findViewById(R.id.imageView)));
                        break;
                    case R.layout.book_list_item:
                        MulBean2 mulBean2 = (MulBean2) data;
                        TextView tv = (TextView) (itemView.findViewById(R.id.tv_bookname));
                        tv.setText(mulBean2.getName());
                        //  BreakIterator
                }
            }
        }, new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View itemView, int position) {
                showToast("你点击了alldifferent位子"+position);
            }
        });




    }


    private class MyOnItemClickListener implements OnItemClickListener {
        private final int myposition;

        public MyOnItemClickListener(int myposition) {
            this.myposition = myposition;
        }

        @Override
        public void onItemClick(ViewGroup parent, View itemView, int position) {
            Toast.makeText(ViewGroupActivity.this, "通过OnItemClickListener设置:" + myposition + books.size(), Toast.LENGTH_SHORT).show();
        }
    }

    private class BookSingleAdapter extends SingleAdapter<Book> {
        public BookSingleAdapter() {
            super(ViewGroupActivity.this, ViewGroupActivity.this.books, R.layout.book_list_item);
        }

        @Override
        public View getView(ViewGroup parent, int pos, Book data) {
            return super.getView(parent, pos, data);
        }

        @Override
        public void onBindView(ViewGroup parent, View itemView, final Book data, int pos) {
            TextView price = (TextView) itemView.findViewById(R.id.tv_price);
            price.setText("价格为" + data.getPrice());
            ((TextView) itemView.findViewById(R.id.tv_bookname)).setText(data.getName());
            price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("点击了价格为" + data.getPrice());
                }
            });
            ((TextView) itemView.findViewById(R.id.tv_bookname)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("点击了姓名为" + data.getName());
                }
            });

        }
    }
}
