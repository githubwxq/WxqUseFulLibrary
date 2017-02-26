package imagewatch;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.wxq.wxqutilslibrary.Global;
import com.example.wxq.wxqutilslibrary.R;

import java.io.File;

import commontools.CommonTools;
import commontools.FileUtils;

/**
 * 选择更新用的dialog
 */
public class PicSaveDialog implements OnClickListener{
    private static final int OPEN_ALBUM = 123;
    private static PicSaveDialog savePicDialogManager = null;
    private EditText tv_title;
    private Button btnSure;
    private TextView tv_content;
    private  ImageWatcher  vImageWatcher;
    private  Context  mcontext;
    private String currentPath;
    public Button getBtnSure() {
        return btnSure;
    }

    public EditText getTv_title() {
        return tv_title;
    }
    public TextView getTv_content() {
        return tv_content;
    }
    public static PicSaveDialog getInstance() {
        if (savePicDialogManager == null) {
            savePicDialogManager = new PicSaveDialog();
        }
        return savePicDialogManager;
    }

    private Dialog savePicDialog;
    private Handler mhandler;
    public Dialog createSavePicDialog(Context context, ImageWatcher  ImageWatcher, String path, Handler handler) {
        mcontext=context;
        vImageWatcher=ImageWatcher;
        currentPath=path;
        mhandler=handler;
        showDialog(context,ImageWatcher);
        return savePicDialog;

    }

    public void showDialog(Context context,ImageWatcher  ImageWatcher) {
        if (savePicDialog == null) {
            View view = LayoutInflater.from(
                    context)
                    .inflate(R.layout.popmenulongbtn, null);
            Button btn_keep = (Button) view.findViewById(R.id.btn_keep);
            Button btn_shutdown = (Button) view.findViewById(R.id.btn_shutdown);
            Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
            btn_keep.setOnClickListener(this);
            btn_shutdown.setOnClickListener(this);
            btn_cancel.setOnClickListener(this);
            savePicDialog = new Dialog(context, R.style.chooseDialog);
            savePicDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        savePicDialog.show();
        //在show调用之后设置宽度铺满
        Window window = savePicDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = CommonTools.getScreenWidth(context);
        window.setAttributes(lp);


    }



    public void cancelsavePicDialog() {
        if (savePicDialog != null) {
            savePicDialog.dismiss();
        }
    }

    public boolean isShowsavePicDialog() {
        return savePicDialog != null && savePicDialog.isShowing();
    }



    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_shutdown) {
            savePicDialog.dismiss();
            vImageWatcher.handleBackPressed(); //返回页面

        } else if (i == R.id.btn_keep) {
            if (CommonTools.checkPermission((Activity) mcontext, null, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, OPEN_ALBUM))
                return;
            savePic();

        } else if (i == R.id.btn_cancel) {
            savePicDialog.dismiss();

        }


    }
         public static boolean hasKitkat() {
             return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        }
          private void savePic() {
            final String path=currentPath;
             downLoadImage(path);
             // onDownLoad(path);


          }

    private void downLoadImage(String path) {
        Glide.with(mcontext).load(path).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {

                try {
                    MediaStore.Images.Media.insertImage(mcontext.getContentResolver(), resource.getAbsolutePath(), "wxq", null);
                   // CommonTools.updateAlbum(mcontext.getApplicationContext());
                    Toast.makeText(mcontext,"保存成功", Toast.LENGTH_LONG).show();
                    //dd
                    //将文件转移到另外目录
                    FileUtils.movePicToDir(resource, Global.SAVEIMAGE);
                    if (savePicDialog != null) {
                        savePicDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mcontext,"保存失败", Toast.LENGTH_LONG).show();
                 //   CommonTools.showToast(mcontext, "保存失败");
                    if (savePicDialog != null) {
                        savePicDialog.dismiss();
                    }
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);



            }
        });
    }


    /**
     * 启动图片下载线程
     */
    private void onDownLoad(String url) {
        DownLoadImageService service = new DownLoadImageService(mcontext.getApplicationContext(),
                url,
                new ImageDownLoadCallBack() {

                    @Override
                    public void onDownLoadSuccess(File file) {
            //            CommonTools.showToast(mcontext, "保存成功");
                        if (savePicDialog != null) {
                            savePicDialog.dismiss();
                        }
                    }
                    @Override
                    public void onDownLoadSuccess(Bitmap bitmap) {
                        // 在这里执行图片保存方法
                 //       CommonTools.showToast(mcontext, "保存失败");
                        if (savePicDialog != null) {
                            savePicDialog.dismiss();
                        }
                    }

                    @Override
                    public void onDownLoadFailed() {
                        // 图片保存失败

                    }
                });
        //启动图片下载线程
        new Thread(service).start();
    }

//    private static String replaceImageUrlHost(String url){
//        String httpUrl = url.replace("https://", "http://");
//        String replaceHost = Global.UrlApi.contains("test.juziwl.com") ? "//test.juziwl.com/" : "//m.imexue.com/";
//        if(httpUrl.contains("//exiaoxin.com/")){
//            return httpUrl.replace("//exiaoxin.com/", replaceHost);
//        } else if(httpUrl.contains("//mp.imexue.com/")){
//            return httpUrl.replace("//mp.imexue.com/", replaceHost);
//        } else if(httpUrl.contains("//platform.exiaoxin.com/")){
//            return httpUrl.replace("//platform.exiaoxin.com/", replaceHost);
//        } else if(httpUrl.contains("//platform.imexue.com/")){
//            return httpUrl.replace("//platform.imexue.com/", replaceHost);
//        }
//        return httpUrl;
//    }
}
