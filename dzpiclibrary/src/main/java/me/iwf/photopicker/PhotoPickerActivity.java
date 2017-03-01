package me.iwf.photopicker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.adapter.SelectableAdapter;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.event.OnItemCheckListener;
import me.iwf.photopicker.fragment.ImagePagerFragment;
import me.iwf.photopicker.fragment.PhotoPickerFragment;
import me.iwf.photopicker.utils.BimpUtils;
import me.iwf.photopicker.utils.CommonUtils;
import me.iwf.photopicker.utils.ImagePreference;
import me.iwf.photopicker.utils.LoadingImgUtil;
import me.iwf.photopicker.widget.Titlebar;

import static me.iwf.photopicker.PhotoPicker.DEFAULT_COLUMN_NUMBER;
import static me.iwf.photopicker.PhotoPicker.DEFAULT_MAX_COUNT;
import static me.iwf.photopicker.PhotoPicker.EXTRA_GRID_COLUMN;
import static me.iwf.photopicker.PhotoPicker.EXTRA_MAX_COUNT;
import static me.iwf.photopicker.PhotoPicker.EXTRA_ORIGINAL_PHOTOS;
import static me.iwf.photopicker.PhotoPicker.EXTRA_PREVIEW_ENABLED;
import static me.iwf.photopicker.PhotoPicker.EXTRA_SHOW_CAMERA;
import static me.iwf.photopicker.PhotoPicker.EXTRA_SHOW_GIF;

public class PhotoPickerActivity extends FragmentActivity implements PhotoPickerFragment.OnAfterTakePhotoListener {

    private PhotoPickerFragment pickerFragment;
    private ImagePagerFragment imagePagerFragment;
    //private MenuItem menuDoneItem;
    public List<Photo> picList;
    private File mimgFile;
    private int maxCount = DEFAULT_MAX_COUNT;
    /**
     * to prevent multiple calls to inflate menu
     */
    // private boolean menuIsInflated = false;
    private boolean showGif = false;
    private int columnNumber = DEFAULT_COLUMN_NUMBER;
    private ArrayList<String> originalPhotos = null;
    private Titlebar titlebar;
    private ArrayList<String> photos;
    private int total = 0;
    private String whereFrom = "";
    private boolean oneClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean showCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        boolean showGif = getIntent().getBooleanExtra(EXTRA_SHOW_GIF, false);
        boolean previewEnabled = getIntent().getBooleanExtra(EXTRA_PREVIEW_ENABLED, true);

        setShowGif(showGif);

        setContentView(R.layout.__picker_activity_photo_picker);

        titlebar = (Titlebar) findViewById(R.id.titlebar);
        titlebar.init(this);
        whereFrom = getIntent().getStringExtra(PhotoPicker.WHERE_FROM);
        maxCount = getIntent().getIntExtra(EXTRA_MAX_COUNT, DEFAULT_MAX_COUNT);
        columnNumber = getIntent().getIntExtra(EXTRA_GRID_COLUMN, DEFAULT_COLUMN_NUMBER);
        originalPhotos = getIntent().getStringArrayListExtra(EXTRA_ORIGINAL_PHOTOS);

        pickerFragment = (PhotoPickerFragment) getSupportFragmentManager().findFragmentByTag("tag");
        if (pickerFragment == null) {
            pickerFragment = PhotoPickerFragment
                    .newInstance(showCamera, showGif, previewEnabled, columnNumber, maxCount, originalPhotos, whereFrom);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, pickerFragment, "tag")
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }
        //左边的点击事件
        titlebar.getIvLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomethingBeforeExit();
            }
        });
        //右边的点击事件
        titlebar.getTvRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oneClick) {
                    oneClick = false;
                    System.out.println("titlebar.getTvRight().setOnClickListener");
                    new Thread(){
                        @Override
                        public void run() {
                            chooseImageFinish();
                        }
                    }.start();
                }

            }
        });
        pickerFragment.getPhotoGridAdapter().setOnItemCheckListener(new OnItemCheckListener() {
            @Override
            public boolean OnItemCheck(int position, Photo photo, final boolean isCheck, int selectedItemCount) {
                total = selectedItemCount + (isCheck ? -1 : 1);
                // menuDoneItem.setEnabled(total > 0);
                if (maxCount <= 1) {
                    List<Photo> photos = pickerFragment.getPhotoGridAdapter().getSelectedPhotos();
                    if (!photos.contains(photo)) {
                        photos.clear();
                        pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
                    }
                    return true;
                }
                if (total > maxCount) {
                    total = maxCount;
                    CommonUtils.showToast(getApplicationContext(), getString(R.string.__picker_over_max_count_tips, maxCount));
                    return false;
                }
                if (total == 0) {
                    titlebar.getTvRight().setText(getString(R.string.__picker_done));
                } else {
                    titlebar.getTvRight().setText(getString(R.string.__picker_done_with_count, total, maxCount));
                }
                return true;
            }
        });

    }

    private void chooseImageFinish() {
        ArrayList<String> drr = ImagePreference.getInstance(getApplicationContext()).getImagesList(ImagePreference.DRR);
        int flag = 0;
        for (int i = drr.size() - 1; i >= 0; i--) {
            if (!new File(drr.get(i)).exists()) {
                flag++;
                break;
            }
        }
        if (flag > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CommonUtils.showToast(getApplicationContext(), "您选择的图片中有已删除的图片，请重新选择");
                    oneClick = true;
                }
            });
            return;
        }
        ImagePreference.getInstance(getApplicationContext()).clearImagesList(ImagePreference.CACHEDIR);
        ImagePreference.getInstance(getApplicationContext()).storeImagesList(ImagePreference.CACHEDIR, drr);
        final ArrayList<String> uploadDir = BimpUtils.getUploadDir(getApplicationContext());
        if (uploadDir != null && uploadDir.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (ImagePreference.getInstance(getApplicationContext()).getPhotoCount() == 1 && whereFrom.equals("changeUserIcon")) {
                        //上传头像
                        startPhotoZoom(new File(uploadDir.get(0)));
                    } else {
                        backResult(uploadDir);
                    }
                }
            });
        } else {
            oneClick = true;
            if (flag == 0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CommonUtils.showToast(getApplicationContext(), "还没有选择图片");
                    }
                });
            }
        }
    }

    private void backResult(ArrayList<String> uploadDir) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS, uploadDir);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void startPhotoZoom(File file) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            // 设置裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 640);
            intent.putExtra("outputY", 640);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);//黑边
            File dir = new File(BimpUtils.savePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            mimgFile = new File(dir, CommonUtils.getMsgCurrentTime().trim() + ".png");
            intent.setDataAndType(CommonUtils.getImageContentUri(getActivity(), file), "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mimgFile));
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            startActivityForResult(intent, 2);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        oneClick = true;
    }

    private void doSomethingBeforeExit() {
        if (imagePagerFragment != null && imagePagerFragment.isVisible()) {
            imagePagerFragment.runExitAnimation(new Runnable() {
                public void run() {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getSupportFragmentManager().popBackStack();
                    }
                }
            });
        } else {
            if (ImagePreference.getInstance(getApplicationContext()).getPhotoCount() == 1 && whereFrom.equals("changeUserIcon")) {
                ImagePreference.getInstance(getApplicationContext()).clearAllImagesList();
                finish();
            } else {
                if (null != whereFrom && PhotoPicker.CHOOSEIMAGENOTUSECACHE.equals(whereFrom)) {
                    ImagePreference.getInstance(getApplicationContext()).clearImagesList(ImagePreference.DRR);
                    ArrayList<String> uploadDir = BimpUtils.getUploadDir(getApplicationContext());
                    backResult(uploadDir);
                } else {
                    ImagePreference.getInstance(getApplicationContext()).clearImagesList(ImagePreference.DRR);
                    ArrayList<String> cacheDir = ImagePreference.getInstance(getApplicationContext()).getImagesList(ImagePreference.CACHEDIR);
                    ImagePreference.getInstance(getApplicationContext()).storeImagesList(ImagePreference.DRR, cacheDir);
                    //  BimpUtils.uploadDir.clear();
                    //   BimpUtils.uploadDir = BimpUtils.getUploadDir();
//                    backResult(new ArrayList<String>());
                    finish();
                }
            }
        }
    }

    /**
     * Overriding this method allows us to run our exit animation first, then exiting
     * the activity when it complete.
     */
    @Override
    public void onBackPressed() {
        doSomethingBeforeExit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK && mimgFile != null) {
                    String path = mimgFile.getAbsolutePath();
                    ArrayList<String> imagesList = ImagePreference.getInstance(getApplicationContext()).getImagesList(ImagePreference.UPLOADDIR);
                    onAfterPhotoZoom(imagesList.get(0), path);
                }
                break;
            case PhotoPreview.REQUEST_CODE:
                if(pickerFragment != null){
                    pickerFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
        total = ImagePreference.getInstance(getApplicationContext()).getImagesList(ImagePreference.DRR).size();
        if (total == 0) {
            titlebar.getTvRight().setText(getString(R.string.__picker_done));
        } else {
            titlebar.getTvRight().setText(getString(R.string.__picker_done_with_count, total, maxCount));
        }
    }

    public void addImagePagerFragment(ImagePagerFragment imagePagerFragment) {
        this.imagePagerFragment = imagePagerFragment;
        picList = SelectableAdapter.selectedPhotos;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, this.imagePagerFragment)
                .addToBackStack(null)
                .commit();
        imagePagerFragment.setOnCheckBoxClickListener(new ImagePagerFragment.OnCheckBoxClickListener() {
            @Override
            public int OnCheckBoxClick(int position, Photo photo, boolean isCheckBoxChecked) {
                if (maxCount <= 1) {
                    List<Photo> photos = pickerFragment.getPhotoGridAdapter().getSelectedPhotos();
                    if (photos.contains(photo)) {
                        photos.clear();
                        pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
                        return 2;
                    } else {
                        photos.clear();
                        photos.add(photo);
                        pickerFragment.getPhotoGridAdapter().notifyDataSetChanged();
                    }
                    return 1;
                }
                total = isCheckBoxChecked ? total + 1 : total - 1;
//                if ((isCheckBoxChecked && total + 1 > maxCount)){
                if (total > maxCount) {
                    total = maxCount;
                    CommonUtils.showToast(getActivity(), getString(R.string.__picker_over_max_count_tips, maxCount));
                    return 0;
                }
                int isSelect;
                if (SelectableAdapter.selection(photo, getApplicationContext())) {
//                    total = total - 1;
                    isSelect = 2;
                } else {
//                    total = total + 1;
                    isSelect = 1;
                }

                if (total == 0) {
                    titlebar.getTvRight().setText(getString(R.string.__picker_done));
                } else {
                    if (ImagePreference.getInstance(getApplicationContext()).getPhotoCount() == 1) {
                        titlebar.getTvRight().setText(getString(R.string.__picker_done));
                    } else {
                        if ((isCheckBoxChecked && isSelect == 1) || (!isCheckBoxChecked && isSelect == 2)) {
                            titlebar.getTvRight().setText(getString(R.string.__picker_done_with_count, total, maxCount));
                        }
                    }
                }
                return isSelect;
            }
        });
    }

    @Override
    protected void onDestroy() {
        oneClick = true;
        LoadingImgUtil.clearMemoryCache(getApplicationContext());
//        System.gc();
        super.onDestroy();
    }

    public PhotoPickerActivity getActivity() {
        return this;
    }

    public boolean isShowGif() {
        return showGif;
    }

    public void setShowGif(boolean showGif) {
        this.showGif = showGif;
    }

    @Override
    public void onAfterPhotoZoom(String sourceFile, String path) {
        CommonUtils.deleteImage(this, sourceFile);
        ImagePreference.getInstance(getApplicationContext()).clearImagesList(ImagePreference.UPLOADDIR);
        ImagePreference.getInstance(getApplicationContext()).addImagesList(ImagePreference.UPLOADDIR, path);
        finish();
    }

    @Override
    public void onAfterTakePhoto(String currentPhotoPath) {
        ImagePreference instance = ImagePreference.getInstance(getApplicationContext());
        if (PhotoPicker.CHOOSEIMAGENOTUSECACHE.equals(whereFrom)) {
            instance.storeImagesList(ImagePreference.DRR, currentPhotoPath);
        } else {
            instance.addImagesList(ImagePreference.CACHEDIR, currentPhotoPath);
            instance.storeImagesList(ImagePreference.DRR, instance.getImagesList(ImagePreference.CACHEDIR));
        }
        ArrayList<String> uploadDir = BimpUtils.getUploadDir(getApplicationContext());
        backResult(uploadDir);
    }
}
