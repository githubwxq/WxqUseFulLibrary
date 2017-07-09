package viewgroupadapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/12/18.
 */
public class ViewGroupUtils { // 帮助类

    public static void addViews(final ViewGroup viewGroup, IViewGroupAdapter adapter) {
        addViews(viewGroup, adapter, true, null, null);
    }

    public static void addViews(final ViewGroup viewGroup, IViewGroupAdapter adapter
            , final OnItemClickListener onItemClickListener) {
        addViews(viewGroup, adapter, true, onItemClickListener, null);
    }

    public static  void addViews(final ViewGroup viewGroup,IViewGroupAdapter adapter,boolean removeViews,final OnItemClickListener onItemClickListener,final OnItemLongClickListener onItemLongClickListener){
       if(viewGroup==null||adapter==null){
           return;
       }

        if(removeViews && viewGroup.getChildCount()>0){
            viewGroup.removeAllViews();
        }

        int count=adapter.getCount();

        for (int i=0;i<count;i++){
            View itemView=adapter.getView(viewGroup,i);// 适配器
            viewGroup.addView(itemView);
         if(null!=onItemClickListener&& !itemView.isClickable()){
             final int finali = i;
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     onItemClickListener.onItemClick(viewGroup,view,finali ); //由上层处理

                 }
             });

             //添加长按事件itemView之前没有长按事件才会去设置
             if (null != onItemLongClickListener && !itemView.isLongClickable()) {
                 final int finalI = i;
                 itemView.setOnLongClickListener(new View.OnLongClickListener() {
                     @Override
                     public boolean onLongClick(View view) {
                         return onItemLongClickListener.onItemLongClick(viewGroup, view, finalI);
                     }
                 });
             }
         }






        }










    }





}


//、、使用方法

//    private void initView() {
//        ll_content = (LinearLayout) findViewById(R.id.ll_content);
//        ll_content_different = (LinearLayout) findViewById(R.id.ll_content_different);
//        ll_content_alldifferent = (LinearLayout) findViewById(R.id.ll_content_alldifferent);
//
//
//        //单一ItemView
//        ViewGroupUtils.addViews(ll_content, new BookSingleAdapter(), onItemClickListener);
//
//
//        //多种ItemViewType，但是数据结构相同，可以传入数据结构泛型，避免强转
//        ViewGroupUtils.addViews(ll_content_different, new MulTypeAdapter<Book>(this, differbooks) {
//            @Override
//            public void onBindView(ViewGroup parent, View itemView, final Book data, int pos) {
//                ((TextView) itemView.findViewById(R.id.tv_bookname)).setText(data.getName() + "");
////                Glide.with(MulTypeActivity.this)
////                        .load(data.getAvatar())
////                        .into((ImageView) itemView.findViewById(ivAvatar));
//                //#### Adapter.onBindView()里设置 优先级更高
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(mContext, "onBindView里设置:文字是:" + data.getName(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }, onItemClickListener);
//        //可以在`ViewGroupUtils.addViews`直接作为参数传入.\
//        // 也可以用`ViewGroupUtils.setOnItemClickListener(）`设置
//        // **优先级比`Adapter.onBindView()`里设置低，**
//        //ViewGroupUtils.setOnItemClickListener(linearLayout,onItemClickListener);
//
//
//        //多种Item类型：数据结构不同 不传泛型了 使用时需要强转javaBean，判断ItemLayoutId
//        ViewGroupUtils.addViews(ll_content_alldifferent, new MulTypeAdapter(this, alldifferbooks) {
//            @Override
//            public void onBindView(ViewGroup parent, View itemView, IMulTypeHelper data, int pos) {
//                switch (data.getItemLayoutId()) {
//                    case R.layout.book_list_item2:
//                        MulBean1 mulBean1 = (MulBean1) data;
//                        Glide.with(ViewGroupActivity.this)
//                                .load(mulBean1.getUrl())
//                                .into((ImageView) (itemView.findViewById(R.id.imageView)));
//                        break;
//                    case R.layout.book_list_item:
//                        MulBean2 mulBean2 = (MulBean2) data;
//                        TextView tv = (TextView) (itemView.findViewById(R.id.tv_bookname));
//                        tv.setText(mulBean2.getName());
//                        //  BreakIterator
//                }
//            }
//        }, new OnItemClickListener() {
//            @Override
//            public void onItemClick(ViewGroup parent, View itemView, int position) {
//                showToast("你点击了alldifferent位子"+position);
//            }
//        });
//
//
//
//
//    }
//
//
//private class MyOnItemClickListener implements OnItemClickListener {
//    private final int myposition;
//
//    public MyOnItemClickListener(int myposition) {
//        this.myposition = myposition;
//    }
//
//    @Override
//    public void onItemClick(ViewGroup parent, View itemView, int position) {
//        Toast.makeText(ViewGroupActivity.this, "通过OnItemClickListener设置:" + myposition + books.size(), Toast.LENGTH_SHORT).show();
//    }
//}
//
//private class BookSingleAdapter extends SingleAdapter<Book> {
//    public BookSingleAdapter() {
//        super(ViewGroupActivity.this, ViewGroupActivity.this.books, R.layout.book_list_item);
//    }
//
//    @Override
//    public View getView(ViewGroup parent, int pos, Book data) {
//        return super.getView(parent, pos, data);
//    }
//
//    @Override
//    public void onBindView(ViewGroup parent, View itemView, final Book data, int pos) {
//        TextView price = (TextView) itemView.findViewById(R.id.tv_price);
//        price.setText("价格为" + data.getPrice());
//        ((TextView) itemView.findViewById(R.id.tv_bookname)).setText(data.getName());
//        price.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("点击了价格为" + data.getPrice());
//            }
//        });
//        ((TextView) itemView.findViewById(R.id.tv_bookname)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("点击了姓名为" + data.getName());
//            }
//        });
//
//    }
//}
//}