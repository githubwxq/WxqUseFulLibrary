//package com.example.wxq.wxqutilslibrary.widget.adapter;
// 多种布局参考
//public class MsgCenterAdapter extends BaseAdapter {
//    private final int REPLYVIEW = 0; //回复类型的View  R.layout.layout_msgcenter_replyview
//    private final int ONLINESCHOOLREPLYVIEW = 1;//线上学堂作者回复  R.layout.layout_msgcenter_onlineschoolreply
//    private final int MEDALEXCHANGEVIEW = 2;//勋章兑换  R.layout.layout_msgcenter_medalexchange
//    private final int DIRECTBROADCASTVIEW = 3;  //直播卡券  R.layout.layout_msgcenter_directbroadcast
//    private final int CLASSDYNAMICVIEW = 4;  //班级动态和积分商城  R.layout.layout_msgcenter_classdynamic
//    private Context mContext;
//    private ArrayList<?> arrayList;
//    private SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
//
//    public MsgCenterAdapter(Context mContext, ArrayList<?> arrayList) {
//        this.mContext = mContext;
//        this.arrayList = arrayList;
//    }
//
//    @Override
//    public int getCount() {
//        return 10;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        int type = getItemViewType(position);
//        switch (type) {
//            case REPLYVIEW:
//                convertView = getReplyView(position, convertView, parent);
//                break;
//            case ONLINESCHOOLREPLYVIEW:
//                convertView = getOnlineSchoolReplyView(position, convertView, parent);
//                break;
//            case MEDALEXCHANGEVIEW:
//                convertView = getMedalExchangeView(position, convertView, parent);
//                break;
//            case DIRECTBROADCASTVIEW:
//                convertView = getDirectBroadcastView(position, convertView, parent);
//                break;
//            case CLASSDYNAMICVIEW:
//                convertView = getClassDynamicView(position, convertView, parent);
//                break;
//        }
//        return convertView;
//    }
//
//    private View getReplyView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_msgcenter_replyview, parent, false);
//            viewHolder.usericon = (RectImageView) convertView.findViewById(R.id.usericon);
//            viewHolder.username = (TextView) convertView.findViewById(R.id.user_name);
//            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
//            viewHolder.reply_content = (ExpandableTextView) convertView.findViewById(R.id.reply_content);
//            viewHolder.reply_time = (TextView) convertView.findViewById(R.id.reply_time);
//            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.time.setText(CommonTools.formatDateTime(CommonTools.getCurrentTime()));
//        viewHolder.reply_content.setText("这里显示回复内容", sparseBooleanArray, position);
//        LoadingImgUtil.displayImageWithImageSize("https://test.juziwl.com/data/psmg/2016/07/18/201607182050515996312nxP.png",
//                viewHolder.usericon, new ImageSize(70, 70), null, true);
//        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onDescClickListener != null){
//                    onDescClickListener.onClick(ASKANSWER, null);
//                }
//            }
//        });
//        return convertView;
//    }
//
//    private View getOnlineSchoolReplyView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_msgcenter_onlineschoolreply, parent, false);
//            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
//            viewHolder.title = (TextView) convertView.findViewById(R.id.reply_title);
//            viewHolder.reply_content = (ExpandableTextView) convertView.findViewById(R.id.reply_content);
//            viewHolder.reply_time = (TextView) convertView.findViewById(R.id.reply_time);
//            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.time.setText(CommonTools.formatDateTime(CommonTools.getCurrentTime()));
//        viewHolder.reply_content.setText("这里显示回复内容", sparseBooleanArray, position, true);
//        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onDescClickListener != null){
//                    onDescClickListener.onClick(ONLINESCHOOL, null);
//                }
//            }
//        });
//        return convertView;
//    }
//
//    private View getMedalExchangeView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_msgcenter_medalexchange, parent, false);
//            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
//            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
//            viewHolder.exchange_desc = (TextView) convertView.findViewById(R.id.exchange_desc);
//            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.time.setText(CommonTools.formatDateTime(CommonTools.getCurrentTime()));
//        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onDescClickListener != null){
//                    onDescClickListener.onClick(ACCOUNT, null);
//                }
//            }
//        });
//        return convertView;
//    }
//
//    private View getDirectBroadcastView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_msgcenter_directbroadcast, parent, false);
//            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
//            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
//            viewHolder.card_desc = (TextView) convertView.findViewById(R.id.card_desc);
//            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
//            viewHolder.provide = (TextView) convertView.findViewById(R.id.provide);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.time.setText(CommonTools.formatDateTime(CommonTools.getCurrentTime()));
//        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onDescClickListener != null){
//                    onDescClickListener.onClick(CARD, null);
//                }
//            }
//        });
//        return convertView;
//    }
//
//    private View getClassDynamicView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_msgcenter_classdynamic, parent, false);
//            viewHolder.usericon = (RectImageView) convertView.findViewById(R.id.usericon);
//            viewHolder.username = (TextView) convertView.findViewById(R.id.user_name);
//            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
//            viewHolder.reply_content = (ExpandableTextView) convertView.findViewById(R.id.content);
//            viewHolder.gift = (TextView) convertView.findViewById(R.id.gift);
//            viewHolder.article_image = (ImageView) convertView.findViewById(R.id.article_img);
//            viewHolder.class_dynamic_image = (ImageView) convertView.findViewById(R.id.image);
//            viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
//            viewHolder.title = (TextView) convertView.findViewById(R.id.article_title);
//            viewHolder.article_ll = convertView.findViewById(R.id.article_ll);
//            viewHolder.line = convertView.findViewById(R.id.line);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        LoadingImgUtil.displayImageWithImageSize("https://test.juziwl.com/data/psmg/2016/07/18/201607182050515996312nxP.png",
//                viewHolder.usericon, new ImageSize(70, 70), null, true);
//        viewHolder.time.setText(CommonTools.formatDateTime(CommonTools.getCurrentTime()));
//        if (position == 4) {
//            viewHolder.reply_content.setText("这里显示只有礼物的情况", sparseBooleanArray, position);
//            viewHolder.article_ll.setVisibility(View.GONE);
//            viewHolder.class_dynamic_image.setVisibility(View.GONE);
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewHolder.line.getLayoutParams();
//            lp.addRule(RelativeLayout.BELOW, R.id.content);
//        } else if (position == 5) {
//            viewHolder.reply_content.setText("这里显示有礼物和图片的情况", sparseBooleanArray, position);
//            viewHolder.article_ll.setVisibility(View.GONE);
//            viewHolder.class_dynamic_image.setVisibility(View.VISIBLE);
//            LoadingImgUtil.displayImageWithImageSize("https://test.juziwl.com/data/news/image/normal/2017/03/24/201703242000260266233.jpg", viewHolder.class_dynamic_image, null, null, false);
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewHolder.line.getLayoutParams();
//            lp.addRule(RelativeLayout.BELOW, R.id.image);
//        } else {
//            viewHolder.article_ll.setVisibility(View.VISIBLE);
//            viewHolder.class_dynamic_image.setVisibility(View.GONE);
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewHolder.line.getLayoutParams();
//            lp.addRule(RelativeLayout.BELOW, R.id.article_ll);
//            LoadingImgUtil.displayImageWithImageSize("https://test.juziwl.com/data/news/image/normal/2017/03/24/201703242000260266233.jpg", viewHolder.article_image, new ImageSize(100, 100), null, false);
//        }
//        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(onDescClickListener != null){
//                    onDescClickListener.onClick(CLASSDYNAMIC, null);
//                }
//            }
//        });
//        return convertView;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {//回复
//            return REPLYVIEW;
//        } else if (position == 1) {//线上学堂
//            return ONLINESCHOOLREPLYVIEW;
//        } else if (position == 2) {//勋章兑换
//            return MEDALEXCHANGEVIEW;
//        } else if (position == 3) {//直播卡券
//            return DIRECTBROADCASTVIEW;
//        } else {
//            return CLASSDYNAMICVIEW;
//        }
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 5;
//    }
//
//    class ViewHolder {
//        TextView type, title, content, reply_time, time, username, gift, card_desc, provide, exchange_desc, desc;
//        ImageView article_image, class_dynamic_image;
//        RectImageView usericon;
//        ExpandableTextView reply_content;
//        View article_ll, line;
//    }
//
//    public static final int CLASSDYNAMIC = 0, ONLINESCHOOL = 1, ASKANSWER = 2, CARD = 3, SCOREMALL = 4, NOTICE = 5, ACCOUNT = 6, HOMEWORK = 7;
//    @Retention(RetentionPolicy.SOURCE)
//    @Target(ElementType.PARAMETER)
//    @IntDef({CLASSDYNAMIC, ONLINESCHOOL, ASKANSWER, CARD, SCOREMALL, NOTICE, ACCOUNT, HOMEWORK})
//    public @interface ItemType{}
//
//    private OnDescClickListener onDescClickListener;
//
//    public void setOnDescClickListener(OnDescClickListener onDescClickListener) {
//        this.onDescClickListener = onDescClickListener;
//    }
//
//    public interface OnDescClickListener{
//        void onClick(@ItemType int itemType, Object data);
//    }
//}