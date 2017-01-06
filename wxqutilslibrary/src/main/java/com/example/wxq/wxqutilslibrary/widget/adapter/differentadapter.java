package com.example.wxq.wxqutilslibrary.widget.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.v4.util.ArrayMap;
//import android.text.SpannableString;
//import android.text.TextUtils;
//import android.text.style.DynamicDrawableSpan;
//import android.text.style.ImageSpan;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.nostra13.universalimageloader.core.assist.ImageSize;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//
///**
// * Created by Administrator on 2017/1/5 0005.
// */
//
public class differentadapter {  // 可以配置数据源决定样式 notifdatachange 重走4方法重新布局
//
//    package com.juziwl.xiaoxin.service.nearbyedu;
//
//    import android.content.Context;
//    import android.content.Intent;
//    import android.graphics.Bitmap;
//    import android.graphics.BitmapFactory;
//    import android.os.Bundle;
//    import android.support.v4.util.ArrayMap;
//    import android.text.SpannableString;
//    import android.text.TextUtils;
//    import android.text.style.DynamicDrawableSpan;
//    import android.text.style.ImageSpan;
//    import android.view.LayoutInflater;
//    import android.view.View;
//    import android.view.ViewGroup;
//    import android.widget.AdapterView;
//    import android.widget.BaseAdapter;
//    import android.widget.ImageView;
//    import android.widget.LinearLayout;
//    import android.widget.RelativeLayout;
//    import android.widget.TextView;
//
//    import com.juziwl.xiaoxin.R;
//    import com.juziwl.xiaoxin.config.Global;
//    import com.juziwl.xiaoxin.model.Article;
//    import com.juziwl.xiaoxin.model.Comment;
//    import com.juziwl.xiaoxin.model.InstititionArticle;
//    import com.juziwl.xiaoxin.model.Teacher;
//    import com.juziwl.xiaoxin.model.User;
//    import com.juziwl.xiaoxin.msg.addfriend.AddFriendDetailActivity;
//    import com.juziwl.xiaoxin.msg.addfriend.FriendsDetailActivity;
//    import com.juziwl.xiaoxin.service.adapter.CharacterAdapter;
//    import com.juziwl.xiaoxin.service.adapter.NearbyEduVideoPhotoAdapter;
//    import com.juziwl.xiaoxin.service.adapter.OnlineSchoolNewsListviewAdapter;
//    import com.juziwl.xiaoxin.service.main.ClazzPhotoActivity;
//    import com.juziwl.xiaoxin.service.onlineschool.WebviewJavaScriptActivityS;
//    import com.juziwl.xiaoxin.service.topedu.CallOtherOpeanFile;
//    import com.juziwl.xiaoxin.service.topedu.FileActivity;
//    import com.juziwl.xiaoxin.splash.main.DetailNewsActivity;
//    import com.juziwl.xiaoxin.util.CommonTools;
//    import com.juziwl.xiaoxin.util.JsonUtil;
//    import com.juziwl.xiaoxin.util.LoadingImgUtil;
//    import com.juziwl.xiaoxin.util.NetConnectTools;
//    import com.juziwl.xiaoxin.util.NetworkUtils;
//    import com.juziwl.xiaoxin.util.UserPreference;
//    import com.juziwl.xiaoxin.util.Utils;
//    import com.juziwl.xiaoxin.view.NoScrollGridView;
//    import com.juziwl.xiaoxin.view.NoScrollListView;
//    import com.juziwl.xiaoxin.view.RectImageView;
//    import com.nostra13.universalimageloader.core.assist.ImageSize;
//
//    import org.json.JSONArray;
//    import org.json.JSONException;
//    import org.json.JSONObject;
//
//    import java.io.File;
//    import java.text.DecimalFormat;
//    import java.text.SimpleDateFormat;
//    import java.util.ArrayList;
//
//
//    /**  不同数据不同数量加载跟多适配器  避免了多个fragment 多个listview  listview的不同布局然后加上头部 实现复杂布局
//     * Created by Administrator on 2016/11/5 0005.
//     */
//    public class InstitutionAdapter extends BaseAdapter implements View.OnClickListener {
//        private static final int TYPE_COUNT = 7;
//        private static final int TYPE_INFO = 0;
//        private static final int TYPE_ARTICLE = 1;
//        private static final int TYPE_FOOT = 2;
//        private static final int TYPE_KEJIAN = 3;
//        private static final int TYPE_SHIPIN = 4;
//        private static final int TYPE_NODATA = 5;
//        private static final int TYPE_ARTICLE2 = 6;
//        private String info = ""; //用来判断是基本信息 还是文章
//        private boolean flag = true;
//        private boolean add_flag;
//        private Boolean loadmore = false;
//        private Teacher teacher;
//        private Comment comment;
//        private ArrayList<String> s = new ArrayList<>();
//        private ArrayList<InstititionArticle> data;
//        private Context context;
//        private int width;
//
//
//        public InstitutionAdapter(Context context, String flag) {
//            this.context = context;
//            this.info = flag;
//            width = CommonTools.dip2px(context, 100);
//        }
//
//        public InstitutionAdapter(Context context, ArrayList data) {
//            this.context = context;
//            this.data = data;
//            width = CommonTools.dip2px(context, 100);
//
//        }
//
//        public InstitutionAdapter(Context context, Teacher data, String flag) {
//            this.context = context;
//            this.teacher = data;
//            this.info = flag;
//            width = CommonTools.dip2px(context, 100);
//        }
//
//        public InstitutionAdapter(Context context, Teacher teacher, String flag, Comment comment,ArrayList articles) {
//            this.context = context;
//            this.teacher = teacher;
//            this.info = flag;
//            this.comment = comment;
//            this.data=articles;
//
//            width = CommonTools.dip2px(context, 100);
//
//        }
//
//        @Override
//        public int getCount() {
//            if (data.get(0).flag.equals("info")) {
//                return 1;
//            } else if (data.get(0).flag.equals("false")) {
//                return 1;
//            } else {
//                if (data.size() >= 1 && loadmore) {
//                    return data.size() + 1;
//                } else {
//                    return data.size();
//                }
//            }
//        }
//
//        @Override
//        public Object getItem(int position) {
//            if (data.get(0).flag.equals("false")) {
//                return "";
//            } else {
//                return data.get(0).flag.equals("info") ? "" : data.get(position);
//            }
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (data.get(0).flag.equals("info")) {
//                return getInfoView(position, convertView, parent);
//            } else if (data.get(0).flag.equals("false")) {
//                return getFalseView(position, convertView, parent);
//            } else {
//                if (getItemViewType(position) == TYPE_ARTICLE) {
//                    return getCardView(position, convertView, parent);
//                } else if (getItemViewType(position) == TYPE_FOOT) {
//                    return getFootView(position, convertView, parent);
//                } else if (getItemViewType(position) == TYPE_KEJIAN) {
//                    return getKejianView(position, convertView, parent);
//                } else if (getItemViewType(position) == TYPE_SHIPIN) {
//                    return getVideoView(position, convertView, parent);
//                } else if (getItemViewType(position) == TYPE_ARTICLE2) {
//                    return getCardView2(position, convertView, parent);
//                } else {
//                    return null;
//                }
//            }
////        return flag.equals("info") ? getInfoView(position, convertView, parent) : getCardView(position, convertView, parent);
//        }
//
//        private View getVideoView(final int position, View convertView, ViewGroup parent) {
//            VideoHolder holder = null;
//            if (convertView == null) {
//                holder = new VideoHolder();
//                convertView = View.inflate(context, R.layout.layout_onlineschool_news_video_item, null);
//                holder.video_desc = (TextView) convertView.findViewById(R.id.desc);
//                holder.video_time = (TextView) convertView.findViewById(R.id.time);
//                holder.video_image = (ImageView) convertView.findViewById(R.id.image);
//                holder.video_more = (TextView) convertView.findViewById(R.id.more);
//                holder.video_brief = (TextView) convertView.findViewById(R.id.brief);
//                holder.video_re = (LinearLayout) convertView.findViewById(R.id.video_re);
//                convertView.setTag(holder);
//            } else {
//                holder = (VideoHolder) convertView.getTag();
//            }
//            final InstititionArticle.Article article = data.get(position).getArticles().get(0);
//            holder.video_time.setText(CommonTools.formatDateTime(article.getCreate_time()));
//            holder.video_desc.setText(getContent(holder.video_desc, article.getTitle()));
//            holder.video_re.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Article a = new Article();
//                    a.p_id = article.getP_id();
//                    a.s_creater_time = article.getCreate_time();
//                    a.s_pic = article.getPic();
//                    a.fileName = article.getS_origin_filename();
//                    a.s_url = article.getUrl();
//                    a.s_html = article.getS_html();
//                    a.s_abstract = article.getAbstracts();
//                    a.s_title = article.getTitle();
//                    a.likeId = article.getLikeId();
//                    a.readId = article.getReadId();
//                    a.platId = article.getPlatId();
//                    a.platName = article.getPlatName();
//                    a.s_creator = article.getS_creator();
//                    a.platImg = article.getPlatImg();
//                    a.fileSize = article.getS_size();
//                    a.collectId = article.getCollectId();
//
//                    Intent intent = new Intent(context, WebviewJavaScriptActivityS.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("article", a);
//                    bundle.putInt("outPosition", position);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
//            });
//            holder.video_brief.setText(article.getAbstracts());
//            if (CommonTools.isEmpty(article.getPic())) {
//                holder.video_image.setImageResource(R.mipmap.falseimg);
//            } else {
//                LoadingImgUtil.displayImageWithImageSize(article.getPic(), holder.video_image, new ImageSize(width, width), null, false);
//            }
//            return convertView;
//        }
//
//
//        public SpannableString getContent(TextView tv, String source) {
//            source += "[视频]";
//            SpannableString spannableString = new SpannableString(source);
//            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.onlineschool_shipin);
//            if (bitmap != null) {
//                bitmap = Bitmap.createScaledBitmap(bitmap,
//                        (int) (tv.getTextSize() * bitmap.getWidth() * 0.9 / bitmap.getHeight()),
//                        (int) (tv.getTextSize() * 0.9), true);
//                ImageSpan imageSpan = new ImageSpan(context, bitmap, DynamicDrawableSpan.ALIGN_BASELINE);
//                spannableString.setSpan(imageSpan, source.length() - 4, source.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            return spannableString;
//        }
//
//        private View getFalseView(int position, View convertView, ViewGroup parent) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.bigfalse, parent, false);
//            return convertView;
//        }
//
//        private View getKejianView(final int position, View convertView, ViewGroup parent) {
//
//            ItemHolder holder;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_institution_kejian, parent, false);
//                holder = new ItemHolder();
//                holder.itemView = convertView.findViewById(R.id.itemView);
//                holder.title = (TextView) convertView.findViewById(R.id.text_body);
//                holder.abstracts = (TextView) convertView.findViewById(R.id.text_size);
//                holder.createTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
//                holder.imageView = (ImageView) convertView.findViewById(R.id.item_avatar);
//                convertView.setTag(holder);
//            } else {
//                holder = (ItemHolder) convertView.getTag();
//            }
//            holder.title.setText(data.get(position).getArticles().get(0).getTitle());
//            String s = data.get(position).getArticles().get(0).getCreate_time();
//            Long date = java.sql.Timestamp.valueOf(s).getTime();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
//            String dateString = formatter.format(date);
//            holder.createTime.setText(dateString);
////        holder.createTime.setText(data.get(position).getArticles().get(0).getCreate_time());
//            holder.abstracts.setText(data.get(position).getArticles().get(0).getAbstracts());
//            DecimalFormat df = new DecimalFormat("0.00");
//            int fileSize = (data.get(position).getArticles().get(0).getS_size());
//            if (fileSize > 1000) {
//                holder.abstracts.setText(df.format(fileSize / 1000) + "MB");
//            } else {
//                holder.abstracts.setText(df.format(fileSize) + "KB");
//            }
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                /*课件的点击事件*/
//                    InstititionArticle.Article a = data.get(position).getArticles().get(0);
//                    Article article = new Article();
//                    article.p_id = a.getP_id();
//                    article.s_creater_time = a.getCreate_time();
//                    article.s_pic = a.getPic();
//                    article.fileName = a.getS_origin_filename();
//                    article.s_url = a.getUrl();
//                    article.s_html = a.getS_html();
//                    article.s_abstract = a.getAbstracts();
//                    article.s_title = a.getTitle();
//                    article.likeId = a.getLikeId();
//                    article.readId = a.getReadId();
//                    article.platId = a.getPlatId();
//                    article.platName = a.getPlatName();
//                    article.s_creator = a.getS_creator();
//                    article.platImg = a.getPlatImg();
//                    article.fileSize = a.getS_size();
//                    article.collectId = a.getCollectId();
//
//                    if (!TextUtils.isEmpty(article.s_url) && article.s_url.length() > 7) {
//
//                        String uid = UserPreference.getInstance(context).getUid();
//                        File file;
//                        try {
//                            file = new File(Global.filePath + "files/" + uid + "/"
//                                    + article.fileName.substring(0, article.fileName.lastIndexOf("."))
//                                    + article.s_url.substring(article.s_url.lastIndexOf("/") + 1));
//                        } catch (Exception e) {
//                            file = new File(Global.filePath + "files/" + uid + "/" + article.s_url.substring(article.s_url.lastIndexOf("/") + 1));
//                        }
//                        if (file.exists()) {
//                            CallOtherOpeanFile call = new CallOtherOpeanFile();
//                            call.openFile(context, file);
//                        } else {
//                            Bundle bundle = new Bundle();
//                            bundle.putString("downUrl", article.s_url);
//                            bundle.putString("fileName", article.fileName);
//                            bundle.putInt("fileSize", article.fileSize);
//                            bundle.putString("brief", article.s_abstract);
//                            Intent intent = new Intent(context, FileActivity.class);
//                            intent.putExtras(bundle);
//                            context.startActivity(intent);
//                        }
//                    }
//                }
//            });
//            String url = data.get(position).getArticles().get(0).getUrl();
//            int res = CommonTools.getFileImgResource(url.substring(url.lastIndexOf(".") + 1));
//            holder.imageView.setImageResource(res);
//            return convertView;
//        }
//
//        private View getFootView(int position, View convertView, ViewGroup parent) {
//            FooterViewHolder footer;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.moredata, parent, false);
//                footer = new FooterViewHolder(convertView);
//                convertView.setTag(footer);
//            } else {
//                footer = (FooterViewHolder) convertView.getTag();
//            }
//            if (loadMoreListener != null) {
//                loadMoreListener.loadMore(convertView);
//            }
//            return convertView;
//        }
//
//        private View getCardView(int position, View convertView, ViewGroup parent) {
//            ItemHolder holder;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context)
//                        .inflate(R.layout.list_item_institution_article2, parent, false);
//                holder = new ItemHolder();
//                holder.yueduquanwen = (TextView) convertView.findViewById(R.id.yueduquanwen);
//                holder.title = (TextView) convertView.findViewById(R.id.title);
//                holder.abstracts = (TextView) convertView.findViewById(R.id.near_abstract);
//                holder.createTime = (TextView) convertView.findViewById(R.id.date);
//                holder.imageView = (ImageView) convertView.findViewById(R.id.picture);
//                holder.itemView = convertView.findViewById(R.id.itemView);
//                convertView.setTag(holder);
//            } else {
//                holder = (ItemHolder) convertView.getTag();
//            }
//            initCardView(holder, position);
//            return convertView;
//        }
//
//        private View getCardView2(int position, View convertView, ViewGroup parent) {
//            ItemHolder holder;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context)
//                        .inflate(R.layout.list_item_institution_article, parent, false);
//                holder = new ItemHolder();
//                holder.createTime = (TextView) convertView.findViewById(R.id.date);
//                holder.listView = (NoScrollListView) convertView.findViewById(R.id.listview);
//                holder.itemView = convertView.findViewById(R.id.itemView);
//                holder.adapter = new OnlineSchoolNewsListviewAdapter2(new ArrayList<InstititionArticle.Article>(), context);
//                holder.listView.setAdapter(holder.adapter);
//                convertView.setTag(holder);
//            } else {
//                holder = (ItemHolder) convertView.getTag();
//            }
//            initCardView2(holder, position);
//            return convertView;
//        }
//
//        private void initCardView2(ItemHolder holder, final int position) {
//            String s = data.get(position).getArticles().get(0).getCreate_time();
//            Long date = java.sql.Timestamp.valueOf(s).getTime();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
//            String dateString = formatter.format(date);
//            holder.createTime.setText(dateString);
////            holder.createTime.setText(data.get(position).getArticles().get(0).getCreate_time());
//            final ArrayList<InstititionArticle.Article> list = data.get(position).getArticles();
//            holder.adapter.setArticles(list);
//            holder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//                    InstititionArticle.Article a = data.get(position).getArticles().get(pos);
//                    Article article = new Article();
//                    article.s_creater_time = a.getCreate_time();
//                    article.s_pic = a.getPic();
//                    article.p_id = a.getP_id();
//                    article.fileName = a.getS_origin_filename();
//                    article.s_url = a.getUrl();
//                    article.s_html = a.getS_html();
//                    article.s_abstract = a.getAbstracts();
//                    article.s_title = a.getTitle();
//                    article.likeId = a.getLikeId();
//                    article.readId = a.getReadId();
//                    article.platId = a.getS_creator();
//                    article.platName = a.getPlatName();
//                    article.s_creator = a.getS_creator();
//                    article.platImg = a.getPlatImg();
//                    article.fileSize = a.getS_size();
//                    article.collectId = a.getCollectId();
//
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("article", article);
//                    bundle.putInt("outPosition", position);
//                    bundle.putString("Activity", "OnlineSchoolActivity");
//                    bundle.putString("p_id", article.p_id);
//                    Intent intent = new Intent(context, WebviewJavaScriptActivityS.class);
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        ViewHolder holder;
//
//        private View getInfoView(int position, View view, ViewGroup parent) {
//
//            if (view == null) {
//                holder = new ViewHolder();
//                view = LayoutInflater.from(context)
//                        .inflate(R.layout.list_item_institution_info, parent, false);
//                findInfoViewById(holder, view);
//                view.setTag(holder);
//            } else {
//                holder = (ViewHolder) view.getTag();
//            }
//
//            initView(holder);
//            return view;
//        }
//
//        private void initCardView(ItemHolder holder, final int position) {
//            if (holder.title == null) {
//
//            } else {
//                holder.title.setText(data.get(position).getArticles().get(0).getTitle());
//                String s = data.get(position).getArticles().get(0).getCreate_time();
//                Long date = java.sql.Timestamp.valueOf(s).getTime();
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
//                String dateString = formatter.format(date);
//                holder.createTime.setText(dateString);
//                holder.abstracts.setText(data.get(position).getArticles().get(0).getAbstracts());
//            }
//
//            if (holder.yueduquanwen == null) {
//
//            } else {
//                holder.yueduquanwen.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                 /*阅读全文*/
//                        InstititionArticle.Article a = data.get(position).getArticles().get(0);
//                        Article article = new Article();
//                        article.s_creater_time = a.getCreate_time();
//                        article.s_pic = a.getPic();
//                        article.p_id = a.getP_id();
//                        article.fileName = a.getS_origin_filename();
//                        article.s_url = a.getUrl();
//                        article.s_html = a.getS_html();
//                        article.s_abstract = a.getAbstracts();
//                        article.s_title = a.getTitle();
//                        article.likeId = a.getLikeId();
//                        article.readId = a.getReadId();
//                        article.platId = a.getS_creator();
//                        article.platName = a.getPlatName();
//                        article.s_creator = a.getS_creator();
//                        article.platImg = a.getPlatImg();
//                        article.fileSize = a.getS_size();
//                        article.collectId = a.getCollectId();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("article", article);
//                        bundle.putInt("outPosition", position);
//                        bundle.putString("Activity", "OnlineSchoolActivity");
//                        bundle.putString("p_id", article.p_id);
//                        Intent intent = new Intent(context, WebviewJavaScriptActivityS.class);
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
//                    }
//                });
//
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        InstititionArticle.Article a = data.get(position).getArticles().get(0);
//                        Article article = new Article();
//                        article.s_creater_time = a.getCreate_time();
//                        article.s_pic = a.getPic();
//                        article.p_id = a.getP_id();
//                        article.fileName = a.getS_origin_filename();
//                        article.s_url = a.getUrl();
//                        article.s_html = a.getS_html();
//                        article.s_abstract = a.getAbstracts();
//                        article.s_title = a.getTitle();
//                        article.likeId = a.getLikeId();
//                        article.readId = a.getReadId();
//                        article.platId = a.getS_creator();
//                        article.platName = a.getPlatName();
//                        article.s_creator = a.getS_creator();
//                        article.platImg = a.getPlatImg();
//                        article.fileSize = a.getS_size();
//                        article.collectId = a.getCollectId();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("article", article);
//                        bundle.putInt("outPosition", position);
//                        bundle.putString("Activity", "OnlineSchoolActivity");
//                        bundle.putString("p_id", article.p_id);
//                        Intent intent = new Intent(context, WebviewJavaScriptActivityS.class);
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
//                    }
//                });
//            }
//
//            LoadingImgUtil.displayImageWithImageSize(data.get(position).getArticles().get(0).getPic(), holder.imageView, new ImageSize(100, 100), null, false);
//
//        }
//
//        private void findInfoViewById(ViewHolder holder, View view) {
//            holder.all_videophoto = (LinearLayout) view.findViewById(R.id.all_videophoto);
//            holder.distance_ll = (RelativeLayout) view.findViewById(R.id.distance_ll);
//            holder.renzheng_re = (RelativeLayout) view.findViewById(R.id.renzheng_re);
//            holder.top_edu = (RelativeLayout) view.findViewById(R.id.top_edu);
//            holder.video_photo_ll = (LinearLayout) view.findViewById(R.id.video_photo_ll);
//            holder.renzheng = (TextView) view.findViewById(R.id.renzheng);
//            holder.renzheng1 = (TextView) view.findViewById(R.id.renzheng1);
//            holder.renzheng2 = (TextView) view.findViewById(R.id.renzheng2);
//            holder.renzheng3 = (TextView) view.findViewById(R.id.renzheng3);
//            holder.renzheng4 = (TextView) view.findViewById(R.id.renzheng4);
//            holder.desc = (TextView) view.findViewById(R.id.desc);
//            holder.youhui_tv = (TextView) view.findViewById(R.id.youhui_tv);
//            holder.youhui_desc = (TextView) view.findViewById(R.id.youhui_desc);
//            holder.address = (TextView) view.findViewById(R.id.address);
//            holder.character = (TextView) view.findViewById(R.id.character);
//            holder.gridview1 = (NoScrollGridView) view.findViewById(R.id.grid_view);
//            holder.content = (TextView) view.findViewById(R.id.content);
//            holder.photo = (TextView) view.findViewById(R.id.photo);
//            holder.arrow = (ImageView) view.findViewById(R.id.arrow);
//            holder.renzheng_arrow = (ImageView) view.findViewById(R.id.renzheng_arrow);
//            holder.gridview = (NoScrollGridView) view.findViewById(R.id.gridview);
//            holder.video_line1 = view.findViewById(R.id.video_line1);
//            holder.video_line2 = view.findViewById(R.id.video_line2);
//            holder.right_arrow = (ImageView) view.findViewById(R.id.right_arrow);
//
//        /*评价的一些控价*/
//            holder.pingjia_count = (TextView) view.findViewById(R.id.text_pingjiacount);
//            holder.pingjia_all = (TextView) view.findViewById(R.id.all_pingjia);
//            holder.pingjia_content = (TextView) view.findViewById(R.id.pingjia_content);
//            holder.pingjia_username = (TextView) view.findViewById(R.id.user_name);
//            holder.iv = (RectImageView) view.findViewById(R.id.user_head);
//            holder.pingjia = view.findViewById(R.id.pinglun);
//        }
//
//        public void setComment(Comment comment, int allComment) {
//            holder.pingjia_count.setText("(" + allComment + ")");
//
//            if (CommonTools.isEmpty(comment.avatar)) {
//                holder.iv.setImageResource(R.mipmap.default_head);
//            } else {
//                LoadingImgUtil.loadimg(Global.baseURL + comment.avatar, holder.iv, null, true);
//            }
//            holder.pingjia_username.setText(comment.userName);
//            holder.pingjia_content.setText(comment.commentInfo);
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            if (data.get(0).flag.equals("info")) {
//                return TYPE_INFO;
//            } else if (data.get(0).flag.equals("false")) {
//                return TYPE_NODATA;
//            } else {
//                if (position == data.size() && data.size() >= 1) {
//                    return TYPE_FOOT;
//                } else {
//                    if (data.get(position).getArticles().get(0).getType().equals("1")) {
//                        return TYPE_SHIPIN;
//                    } else if (data.get(position).getArticles().get(0).getType().equals("2")) {
//                        return TYPE_KEJIAN;
//                    } else {
//                        if (data.get(position).getArticles().size() == 1) {
//                            return TYPE_ARTICLE;
//                        } else {
//                            return TYPE_ARTICLE2;
//                        }
//                    }
//                }
//            }
//        }
//
//        @Override
//        public int getViewTypeCount() {
//            return TYPE_COUNT;
//        }
//
//        public void setLoadmore(Boolean loadmore) {
//            this.loadmore = loadmore;
//        }
//
//        protected void initView(ViewHolder holder) {
//
//            if (teacher.type == 0) {
//                if (!TextUtils.isEmpty(teacher.idcardUrl) && !"null".equals(teacher.idcardUrl)) {
//                    holder.renzheng1.setVisibility(View.VISIBLE);
//                }
//                if (!TextUtils.isEmpty(teacher.certUrl) && !"null".equals(teacher.certUrl)) {
//                    holder.renzheng2.setVisibility(View.VISIBLE);
//                }
//                if (!TextUtils.isEmpty(teacher.majorUrl) && !"null".equals(teacher.majorUrl)) {
//                    //renzheng4.setVisibility(View.VISIBLE);
//                }
//                holder.youhui_tv.setText("成果分享：");
//                holder.arrow.setVisibility(View.GONE);
//                holder.video_photo_ll.setVisibility(View.GONE);
//                holder.renzheng_re.setOnClickListener(this);
//                if (TextUtils.isEmpty(teacher.share) || teacher.share.equalsIgnoreCase("null")) {
//                    holder.youhui_desc.setText("暂无成果");
//                } else {
//                    holder.youhui_desc.setText(teacher.share);
//                }
//
//            } else {
//                holder.distance_ll.setOnClickListener(this);
//                if (TextUtils.isEmpty(teacher.couponUrl) || teacher.couponUrl.equalsIgnoreCase("null")) {
//                    holder.arrow.setVisibility(View.GONE);
//                    holder.distance_ll.setClickable(false);
//                    holder.youhui_desc.setText("暂无优惠信息");
//                } else {
//                    holder.arrow.setVisibility(View.VISIBLE);
//                    holder.distance_ll.setClickable(true);
//                    holder.youhui_desc.setText(teacher.couponTitle);
//                }
//                if (!TextUtils.isEmpty(teacher.licenseUrl) && !"null".equals(teacher.licenseUrl)) {
//                    holder.renzheng.setVisibility(View.VISIBLE);
//                }
//                holder.youhui_tv.setText("优惠：");
//                holder.renzheng_arrow.setVisibility(View.GONE);
//
//            }
//            if (CommonTools.isEmpty(teacher.articleId)) {
//                holder.right_arrow.setVisibility(View.GONE);
//                holder.content.setText("暂无头条信息");
//            } else {
//                holder.right_arrow.setVisibility(View.VISIBLE);
//                holder.top_edu.setOnClickListener(this);
//                holder.content.setText(teacher.articleTitle);
//            }
//            holder.desc.setText(teacher.desc);
//            if (teacher.type == 1) {
//                int imgCount = 0;
//
//                if (!TextUtils.isEmpty(teacher.url) && !teacher.url.equalsIgnoreCase("null")) {
//                    imgCount += teacher.url.split(";").length;
//
//                }
//                if (imgCount == 0) {
//                    holder.video_line1.setVisibility(View.GONE);
//                    holder.all_videophoto.setVisibility(View.GONE);
//                    holder.gridview.setVisibility(View.GONE);
//                    holder.video_line2.setVisibility(View.GONE);
//                } else {
//                    String split[] = teacher.url.split(";");
//                    s.clear();
//                    for (int i = 0; i < split.length && i < 4; i++) {
//                        s.add(split[i]);
//                    }
//                    NearbyEduVideoPhotoAdapter adapter = new NearbyEduVideoPhotoAdapter(context, s, true);
//                    holder.gridview.setAdapter(adapter);
//
//
//                    holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("ID", position);
//                            bundle.putString("pics", teacher.url);
//                            bundle.putBoolean("question", true);
//                            Intent intent = new Intent(context, ClazzPhotoActivity.class);
//                            intent.putExtras(bundle);
//                            context.startActivity(intent);
//                        }
//                    });
//                }
//                holder.photo.setText(imgCount + "张照片");
//
//            }
//            holder.all_videophoto.setOnClickListener(this);
//            String split1[] = teacher.characteristic.split(";");
//            holder.address.setText(teacher.address);
//            CharacterAdapter characterAdapter = new CharacterAdapter(split1, context);
//            holder.gridview1.setAdapter(characterAdapter);
//
//            if (comment == null) {
//                holder.pingjia.setVisibility(View.GONE);
//            } else {
//                holder.pingjia.setVisibility(View.VISIBLE);
//                holder.pingjia_count.setText("(" + comment.allNumComment + ")");
//
//                if (CommonTools.isEmpty(comment.avatar)) {
//                    holder.iv.setImageResource(R.mipmap.default_head);
//                } else {
//                    LoadingImgUtil.loadimg(Global.baseURL + comment.avatar, holder.iv, null, true);
//                }
//                holder.iv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String uid = UserPreference.getInstance(context).getUid();
//                        String token = UserPreference.getInstance(context).getToken();
//                        if (flag) {
//                            flag = false;
//                            if (!comment.userId.equals(uid)) {
//                                String fids = UserPreference.getInstance(context)
//                                        .getFriendUids();
//                                String[] ids = fids.split(";");
//                                add_flag = false;
//                                if (ids != null && ids.length > 0) {
//                                    for (String id : ids) {
//                                        if (id.equals(comment.userId)) {
//                                            add_flag = true;
//                                            break;
//                                        }
//                                    }
//                                }
//                                if (NetworkUtils.isNetworkAvailable(context)) {
//                                    String url = Global.UrlApi + "api/v2/users/" + uid
//                                            + "/searchFriends";
//                                    try {
//                                        ArrayMap<String, String> map = new ArrayMap<String, String>();
//                                        map.put("AccessToken", token);
//                                        map.put("Uid", uid);
//                                        JSONObject obj = new JSONObject();
//                                        JSONArray array = new JSONArray();
//                                        array.put(comment.userId);
//                                        obj.put("friendIds", array);
//                                        NetConnectTools.getInstance().postData(url, map, null, obj.toString(), new NetConnectTools.CallBackListen() {
//                                            @Override
//                                            public void onSuccess(String result) {
//                                                ArrayList<User> users = JsonUtil.getUsersByIdJson(result);
//                                                Bundle bundle = new Bundle();
//                                                bundle.putSerializable("user",
//                                                        users.get(0));
//                                                if (add_flag) {
//                                                    Utils.startActivity(context, FriendsDetailActivity.class, bundle);
//                                                } else {
//                                                    Utils.startActivity(context, AddFriendDetailActivity.class, bundle);
//                                                }
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable ex, boolean isOnCallback) {
//
//                                            }
//
//                                            @Override
//                                            public void onFinished() {
//                                                flag = true;
//                                            }
//                                        });
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                        flag = true;
//                                    }
//                                } else {
//                                    CommonTools.showToast(context, R.string.useless_network);
//                                    flag = true;
//                                }
//                            } else {
//                                CommonTools.showToast(context, "亲，这是您自己哦");
//                                flag = true;
//                            }
//                        } else {
//                            CommonTools.showToast(context, "亲，该用户不存在哦");
//                            flag = true;
//                        }
//                    }
//                });
//                holder.pingjia_username.setText(comment.userName);
//                holder.pingjia_content.setText(comment.commentInfo);
//                holder.pingjia_all.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(context, NearbyEduCommentListActivity.class);
//                        Bundle bundle1 = new Bundle();
//                        bundle1.putString("platId", teacher.id);
//                        bundle1.putString("name", teacher.name);
//                        bundle1.putString("subject", teacher.subjectName);
//                        bundle1.putInt("type", teacher.type);
//                        intent.putExtras(bundle1);
//                        ((InstitutionInfoActivity) context).startActivityForResult(intent, 999);
//                    }
//                });
//            }
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.all_videophoto:
//                    Bundle data = new Bundle();
//                    data.putSerializable("teacher", teacher);
//                    Intent intent = new Intent(context, NearbyEduVideoPhotoActivity.class);
//                    intent.putExtras(data);
//                    context.startActivity(intent);
//                    break;
//
//                case R.id.distance_ll:
//                    Bundle data1 = new Bundle();
//                    data1.putString("url", teacher.couponUrl);
//                    data1.putString("title", "优惠说明");
//                    data1.putString("picurl", "");
//                    data1.putString("desc", "优惠说明");
//                    data1.putBoolean("isShowTrueTitle", true);
//                    data1.putBoolean("isShare", false);
//                    Intent intent1 = new Intent(context, DetailNewsActivity.class);
//                    intent1.putExtras(data1);
//                    context.startActivity(intent1);
//                    break;
//
//                case R.id.renzheng_re:
//                    Bundle data2 = new Bundle();
//                    data2.putSerializable("teacher", teacher);
//                    Intent intent2 = new Intent(context, AuthenticationActivity.class);
//                    intent2.putExtras(data2);
//                    context.startActivity(intent2);
//                    break;
////
////            case R.id.onlineschool: //原线上学堂栏
////                Bundle bundle3 = new Bundle();
////                OnlineSchool onlineSchool = new OnlineSchool();
////                onlineSchool.p_id = teacher.platId;
////                onlineSchool.staticPath = teacher.headLineHtml;
////                onlineSchool.s_img = teacher.platImg;
////                onlineSchool.s_name = teacher.platName;
////                bundle3.putSerializable("onlineSchool", onlineSchool);
////                bundle3.putBoolean("isFromNearby", true);
////                Intent intent3 = new Intent(context, WebviewJavaScriptActivityS.class);
////                intent3.putExtras(bundle3);
////                context.startActivity(intent3);
////                break;
//
//                case R.id.top_edu:
//                    Bundle bundle4 = new Bundle();
//                    Article article = new Article();
//                    article.p_id = teacher.articleId;
//                    article.s_pic = teacher.articlePic;
//                    article.s_title = teacher.articleTitle;
//                    article.s_html = teacher.articleHtml;
//                    article.collectId = teacher.collectId;
//                    article.platId = teacher.platId;
//                    article.s_creator = teacher.platId;
//                    article.platName = teacher.platName;
//                    article.s_abstract = teacher.articleDesc;
//                    article.platImg = teacher.platImg;
//                    bundle4.putSerializable("article", article);
//                    Intent intent4 = new Intent(context, WebviewJavaScriptActivityS.class);
//                    intent4.putExtras(bundle4);
//                    ((InstitutionInfoActivity) context).startActivityForResult(intent4, 50);
//                    break;
//            }
//        }
//
//        /**
//         * 加载更多的回调
//         */
//        private OnLoadMoreListener loadMoreListener;
//
//        //对外暴露设置接口方法
//        public void setLoadMoreListener(OnLoadMoreListener loadMore) {
//            this.loadMoreListener = loadMore;
//        }
//
//        public interface OnLoadMoreListener {
//            void loadMore(View v);
//        }
//
//        class ViewHolder {
//            private View itemView;
//            private ImageView arrow, renzheng_arrow;
//            private LinearLayout all_videophoto, video_photo_ll;
//            private RelativeLayout distance_ll;
//            private NoScrollGridView gridview, gridview1;
//            private RelativeLayout renzheng_re, top_edu;
//            private TextView renzheng, renzheng1, renzheng2, renzheng3, renzheng4, desc;
//            private TextView youhui_tv, youhui_desc, address, character, onlineschool, content, photo;
//            private View video_line1;
//            private View video_line2;
//            private ImageView right_arrow;
//
//            private View pingjia;
//            private TextView pingjia_username, pingjia_content, pingjia_all, pingjia_count;
//            private RectImageView iv;
//        }
//
//        class ItemHolder {
//            private OnlineSchoolNewsListviewAdapter2 adapter;
//            private View itemView;
//            private ImageView imageView;
//            private TextView title, abstracts, createTime, yueduquanwen;
//            private NoScrollListView listView;
//        }
//
//        class VideoHolder {
//            TextView image_time, file_time, video_time, file_title, file_size, video_desc, video_more, video_brief;
//            ImageView video_image, file_image;
//            OnlineSchoolNewsListviewAdapter adapter;
//            RelativeLayout file_re;
//            LinearLayout video_re;
//        }
//
//        class FooterViewHolder {
//            public FooterViewHolder(View view) {
//            }
//        }
//    }
//
}
