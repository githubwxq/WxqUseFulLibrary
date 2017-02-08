package com.example.wxq.wxqusefullibrary.bmob.activity.homepage.childfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.Comment;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.MyUser;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.Post;
import com.example.wxq.wxqusefullibrary.luban.Luban;
import com.example.wxq.wxqusefullibrary.model.Book;
import com.example.wxq.wxqutilslibrary.fragment.SuperFragment;
import com.example.wxq.wxqutilslibrary.myutils.imageloader.LoadingImgUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;
import rx.functions.Action1;

public class BmobDataConectionFragment extends SuperFragment implements View.OnClickListener {
    private String mParam1;
    private String mParam2;
    private TextView one_add;
    private TextView one_del;
    private TextView one_update;
    private TextView one_query;
    private TextView one_more_add;
    private TextView one_more_del;
    private TextView one_more_update;
    private TextView one_more_query;
    private TextView more_more_add;
    private TextView more_more_del;
    private TextView more_more_update;
    private TextView more_more_query;
    Button loginout;
    MyUser user;
    ImageView header;
    Button add_pic, upload_pic;
    ArrayList<String> photos;

    public BmobDataConectionFragment() {
        // Required empty public constructor
    }

    public static BmobDataConectionFragment newInstance() {
        BmobDataConectionFragment fragment = new BmobDataConectionFragment();
        return fragment;
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_bmob_data_conection;
    }

    @Override
    protected void initDataAndView(View view) {
        // 懒加载
        one_add = (TextView) view.findViewById(R.id.one_add);
        one_add.setOnClickListener(this);
        one_del = (TextView) view.findViewById(R.id.one_del);
        one_del.setOnClickListener(this);
        one_update = (TextView) view.findViewById(R.id.one_update);
        one_update.setOnClickListener(this);
        one_query = (TextView) view.findViewById(R.id.one_query);
        one_query.setOnClickListener(this);
        one_more_add = (TextView) view.findViewById(R.id.one_more_add);
        one_more_add.setOnClickListener(this);
        one_more_del = (TextView) view.findViewById(R.id.one_more_del);
        one_more_del.setOnClickListener(this);
        one_more_update = (TextView) view.findViewById(R.id.one_more_update);
        one_more_update.setOnClickListener(this);
        one_more_query = (TextView) view.findViewById(R.id.one_more_query);
        one_more_query.setOnClickListener(this);
        more_more_add = (TextView) view.findViewById(R.id.more_more_add);
        more_more_add.setOnClickListener(this);
        more_more_del = (TextView) view.findViewById(R.id.more_more_del);
        more_more_del.setOnClickListener(this);
        more_more_update = (TextView) view.findViewById(R.id.more_more_update);
        more_more_update.setOnClickListener(this);
        more_more_query = (TextView) view.findViewById(R.id.more_more_query);
        more_more_query.setOnClickListener(this);
        loginout = (Button) view.findViewById(R.id.btn_loginout);
        loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
            }
        });
        header = (ImageView) view.findViewById(R.id.header);
        add_pic = (Button) view.findViewById(R.id.add_pic);
        add_pic.setOnClickListener(this);

        upload_pic = (Button) view.findViewById(R.id.upload_pic);
        upload_pic.setOnClickListener(this);

        user = BmobUser.getCurrentUser(MyUser.class);


    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void commonLoad(View view) {
        super.commonLoad(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_add:
                one_add();
                break;
            case R.id.one_query:
                one_query();
                break;
            case R.id.one_del:
                one_del();
                break;

            case R.id.one_update:
                one_update();
                break;
            case R.id.one_more_add:
                one_more_add();
                break;
            case R.id.one_more_query:
                one_more_query();
                break;

            case R.id.more_more_add:
                more_more_add();
                break;


            case R.id.more_more_query:
                more_more_query();
                break;

            case R.id.add_pic:
                Intent intent = new Intent(getActivity(), PhotoPickerActivity.class);
                //PhotoPickerIntent.setPhotoCount(intent, 1);
                //PhotoPickerIntent.setShowCamera(intent, true);
                //startActivityForResult(intent, REQUEST_CODE);
//                showToast("laalla ");
//                    PhotoPicker.builder()
//                            .setPhotoCount(1)
//                            .start(getActivity());

                Intent intent2 = new Intent(getActivity(), PhotoPickerActivity.class);
                PhotoPickerIntent.setPhotoCount(intent2, 2);
                PhotoPickerIntent.setShowCamera(intent2, true);
                startActivityForResult(intent2,  PhotoPicker.REQUEST_CODE);
                break;


            case R.id.upload_pic:
                //上传图片
                if (photos != null && photos.size() > 0) {
                    final ArrayList<File> mFileList = new ArrayList<>();
                    for (String photo : photos) {
                        File file = new File(photo);
                        mFileList.add(file);
                    }

                    Luban.compress(getActivity(), mFileList)
                            .setMaxSize(100)
                            .putGear(Luban.CUSTOM_GEAR)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .asListObservable()
                            .doOnRequest(new Action1<Long>() {
                                @Override
                                public void call(Long aLong) {
                                    Log.i("TAG:origin", Formatter.formatFileSize(getActivity(), mFileList.get(0).length()
                                    ));
                                    //    start = System.currentTimeMillis();
                                }
                            })
                            .subscribe(new Action1<List<File>>() {
                                @Override
                                public void call(List<File> files) {
                                    Log.i("TAG:result",
                                            Formatter.formatFileSize(getActivity(), files.get(0).length()));
                                    Log.i("TAG:result", files.get(0).getAbsolutePath());
//                                    Log.i("TAG:result",
//                                            "运行时间:" + (System.currentTimeMillis() - start) / 1000f + "s");
//                                    mImageView.setImageURI(Uri.parse(files.get(0).getPath()));
//                                    image1.setImageURI(Uri.parse(files.get(1).getPath()));
//                                    image2.setImageURI(Uri.parse(files.get(2).getPath()));
//                                    image3.setImageURI(Uri.parse(files.get(3).getPath()));


                                    final BmobFile bmobFile = new BmobFile(files.get(0));
//                   user.setAvatar(bmobFile);
//                    user.update(new UpdateListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e==null) {
//                                showToast("更新成功");
//                            }else{
//                                showToast(e.toString());
//                            }
//                        }
//                    });
//                    bmobFile.uploadblock(new UploadFileListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e==null) {
//showToast("ok");
//                                bmobFile.getFileUrl();
//                                user.setAvatarUrl(bmobFile.getUrl());
//                                user.update();
//                            }else{
//                                showToast(e.toString());
//                            }
//                        }
//                    });
                                    //详细示例可查看BmobExample工程中BmobFileActivity类
                                    String filePath_mp3 = photos.get(0).toString();
                                    //    String filePath_lrc = "/mnt/sdcard/testbmob/test2.png";
                                    final String[] filePaths = new String[files.size()];
                                    for (int i = 0; i < files.size(); i++) {
                                        filePaths[i] = files.get(i).getAbsolutePath();
                                    }

                                    //   filePaths[0] = files.;
                                    //  filePaths[1] = filePath_lrc;


                                    BmobFile.uploadBatch(filePaths, new UploadBatchListener() {

                                        @Override
                                        public void onSuccess(List<BmobFile> files, List<String> urls) {
                                            //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                                            //2、urls-上传文件的完整url地址
                                            if (urls.size() == filePaths.length) {//如果数量相等，则代表文件全部上传完成
                                                showToast(urls.get(0).toString());
                                            }
                                            user.setAvatarUrl(urls.get(0).toString());
//                                            user.save(new SaveListener() {
//                                                @Override
//                                                public void done(Object o, BmobException e) {
//                                                    if (e == null) {
//                                                        showToast("保存成功");
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void done(Object o, Object o2) {
//
//                                                }
//                                            });

                                            user.update(new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    showToast("更新成功");
                                                }
                                            });
                                        }

                                        @Override
                                        public void onError(int statuscode, String errormsg) {
                                            showToast("错误码" + statuscode + ",错误描述：" + errormsg);
                                        }

                                        @Override
                                        public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                                            //1、curIndex--表示当前第几个文件正在上传
                                            //2、curPercent--表示当前上传文件的进度值（百分比）
                                            //3、total--表示总的上传文件数
                                            //4、totalPercent--表示总的上传进度（百分比）
                                            //    showToast("totalPercent"+totalPercent);
                                        }
                                    });


                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            });

                } else {
                    showToast("请选择图片");
                }


                break;


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == PhotoPicker.REQUEST_CODE) {
            photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            if (photos != null && photos.size() > 0) {
                showToast(photos.get(0).toString());
                //  GlideUtil.getInstance().loadImage(getActivity(), header, photos.get(0).toString(), true);
                LoadingImgUtil.displayFromSDCard(photos.get(0).toString(), header);
            }

        }
//        PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
//            @Override
//            public void onPickSuccess(ArrayList<String> photos) {
//                showToast("onPickSuccess" + photos.size()+photos.get(0).toString());
//                //     photoAdapter.add(photos);
//                LoadingImgUtil.loading(photos.get(0).toString(),header,null,false);
//
//            }
//
//            @Override
//            public void onPreviewBack(ArrayList<String> photos) {
//                //   photoAdapter.refresh(photos);
//                showToast("onPreviewBack" + photos.get(0).toString());
//            }
//
//            @Override
//            public void onPickFail(String error) {
////        Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
////        selectedPhotos.clear();
////        photoAdapter.notifyDataSetChanged();
//                showToast("false" );
//            }
//
//            @Override
//            public void onPickCancle() {
//                //Toast.makeText(MainActivity.this,"取消选择",Toast.LENGTH_LONG).show();
//            }
//        });
    }


    private void more_more_query() {
        // duodui  post xia shuo you ping
        BmobQuery<Comment> query = new BmobQuery<Comment>();

        Post post = new Post();
        post.setObjectId("62d1cd6e78");

        query.addWhereRelatedTo("comments", new BmobPointer(post));
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                showToast("list大小" + list.size());
//                for (Comment comment : list) {
//                    showToast(comment.getContent() + "用户名" + comment.getUser().getUsername() + "当前post的集合post的内容" + comment.getPost().getContent());
//                }

            }
        });
    }

    // 多对多relationBmobRelation
    private void more_more_add() {
        Post post = new Post();
        post.setObjectId("62d1cd6e78");
        Comment comment = new Comment();
        comment.setObjectId("eea7212dd4"); //其中的一条commentid
        BmobRelation relation = new BmobRelation();
//将当前用户添加到多对多关联中
        relation.add(comment);
//多对多关联指向`post`的`likes`字段
        post.setComments(relation);
        post.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showToast("多对多添加成功");
                }

            }
        });
    }

    private void one_more_query() {

        BmobQuery<Comment> query = new BmobQuery<>();
        Post post = new Post();
        post.setObjectId("6398276cd3");
        query.addWhereEqualTo("post", post);
        query.order("-updateAt");
        query.include("user,post");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                for (Comment comment : list) {
                    showToast(comment.getContent() + "用户名" + comment.getUser().getUsername() + "当前post的集合post的内容" + comment.getPost().getContent());
                }
            }
        });


    }

    private void one_more_add() {
        Post post = new Post();
        post.setObjectId("6398276cd3");
        final Comment comment = new Comment();
        comment.setContent("评论3");
        comment.setPost(post);
        comment.setUser(user);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    showToast("评论发表成功");
                } else {
                    Log.i("bmob", "失败：" + e.getMessage());
                }
            }
        });


    }

    private void one_update() {
        Post p = new Post();
//构造用户B，如果你知道用户B的objectId的话，可以使用这种方式进行关联，如果不知道的话，你需要将用户B查询出来
// 这里假设已知用户B的objectId为aJyG2224
        MyUser userB = new MyUser();
        userB.setObjectId("b2daaa1059");
        p.setAuthor(userB);//重新设置帖子作者
        p.update("ea9a640fd7", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("更新数据", "成功");
                } else {
                    Log.i("bmob", "失败：" + e.getMessage());
                }
            }

        });
    }

    private void one_del() {

        Post p = new Post();
        p.remove("author");
        p.update("ea9a640fd7", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showToast("删除关系成功");
                } else {
                    Log.i("bmob", "失败：" + e.getMessage());
                }
            }
        });

    }

    private void one_query() {
        BmobQuery<Post> query = new BmobQuery<Post>();
//        query.addWhereEqualTo("author", user);
//        query.order("-updateAt");
//        query.include("author");
//        query.findObjects(new FindListener<Post>() {
//            @Override
//            public void done(List<Post> list, BmobException e) {
//                if (e == null) {
//                    for (Post post : list) {
//                        showToast("内容为：" + post.getContent() + post.getAuthor().getUsername());
//                    }
//                } else {
//                    Log.i("bmob", "失败：" + e.getMessage());
//                }
//            }
//        });

        query.getObject("2afc488d2c", new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                showToast("post 集合数据"+post.books.get(0).getName());
                showToast("post 集合数据"+post.books.get(1).getName());
            }
        });

    }

    private void one_add() {
        //一对一添加
        showToast("一对一添加");
        Post post = new Post();

        ArrayList<Book> books=new ArrayList<>();
         Book a=new Book();
        a.setName("wxq");
        Book b=new Book();
        b.setName("bbb");
        books.add(a);
        books.add(b);
        post.books=books;
        post.setContent("帖子内容中有集合");
        post.setAuthor(user);
        // 创建帖子
        post.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null) {
                    showToast("保存成功id为" + s);

                } else {
                    showToast("保存失败" + s);
                }
            }
        });

//
//        Post post = new Post();
//        post.setObjectId("6398276cd3");
//        Post post2 = new Post();
//        post2.setObjectId("6275345766");
////将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子
//        BmobRelation relation = new BmobRelation();
////将当前用户添加到多对多关联中
//        relation.add(post);
//        relation.add(post2);
////多对多关联指向`post`的`likes`字段
//        user.setPosts(relation);
//        user.update(new UpdateListener() {
//            @Override
//            public void done(BmobException e) {
//                if (e == null) {
//                    Log.i("bmob", "多对多关联添加成功");
//                } else {
//                    Log.i("bmob", "失败：" + e.getMessage());
//                }
//            }
//
//        });


    }
}
