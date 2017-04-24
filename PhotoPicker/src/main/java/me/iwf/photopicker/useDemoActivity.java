//package me.iwf.photopicker;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.support.annotation.IdRes;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import me.iwf.photopicker.widget.MultiPickResultView;
//import me.iwf.photopicker.widget.PhotoAdapter;
//
//public class useDemoActivity extends AppCompatActivity {
//
//  enum RequestCode {
//    Button(R.id.button),
//    ButtonNoCamera(R.id.button_no_camera),
//    ButtonOnePhoto(R.id.button_one_photo),
//    ButtonPhotoGif(R.id.button_photo_gif),
//    ButtonMultiplePicked(R.id.button_multiple_picked);
//
//    @IdRes final int mViewId;
//    RequestCode(@IdRes int viewId) {
//      mViewId = viewId;
//    }
//  }
//  MultiPickResultView recyclerView;
//
//  MultiPickResultView recyclerViewShowOnly;
// PhotoAdapter photoAdapter;
//
//  ArrayList<String> selectedPhotos = new ArrayList<>();
//
//  //public final static int REQUEST_CODE = 1;
//
//  ArrayList<String> pathslook ;
//
//  @Override protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//    pathslook = new ArrayList<>();
//
//    recyclerView = (MultiPickResultView) findViewById(R.id.recycler_view);
//    recyclerView.init(this,MultiPickResultView.ACTION_SELECT,null);
//
//    recyclerViewShowOnly = (MultiPickResultView) findViewById(R.id.recycler_onlylook);
//    recyclerViewShowOnly.init(this,MultiPickResultView.ACTION_ONLY_SHOW,pathslook);
//    ArrayList<String> photos = new ArrayList<>();
//    photos.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2545179197,2573899739&fm=21&gp=0.jpg");
//    photos.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1471325032244&di=71570ed352a1b823584c3b3b1b5bd57f&imgtype=jpg&src=http%3A%2F%2Ffile27.mafengwo.net%2FM00%2FB2%2F12%2FwKgB6lO0ahWAMhL8AAV1yBFJDJw20.jpeg");
//    photos.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1471325032243&di=67dfaed98491c3a94965571ed4343951&imgtype=jpg&src=http%3A%2F%2Fwww.5068.com%2Fu%2Ffaceimg%2F20140725173411.jpg");
//    photos.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1471325032243&di=d40f796d46782144ba0adf798253f080&imgtype=jpg&src=http%3A%2F%2Fimglf0.ph.126.net%2F1EnYPI5Vzo2fCkyy2GsJKg%3D%3D%2F2829667940890114965.jpg");
//    photos.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1471325032243&di=bbb10b09ddb5338b53432af1c3789c39&imgtype=jpg&src=http%3A%2F%2Ffile25.mafengwo.net%2FM00%2F0A%2FAA%2FwKgB4lMC256AYLqGAAGklurKzyM52.rbook_comment.w1024.jpeg");
//
//
//  /*  photoAdapter = new PhotoAdapter(this, selectedPhotos);
//
//    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
//    recyclerView.setAdapter(photoAdapter);*/
//
//
//    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        checkPermission(RequestCode.Button);
//      }
//    });
//
//
//    findViewById(R.id.button_no_camera).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        checkPermission(RequestCode.ButtonNoCamera);
//      }
//    });
//
//    findViewById(R.id.button_one_photo).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        checkPermission(RequestCode.ButtonOnePhoto);
//      }
//    });
//
//    findViewById(R.id.button_photo_gif).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        checkPermission(RequestCode.ButtonPhotoGif);
//      }
//    });
//
//    findViewById(R.id.button_multiple_picked).setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        checkPermission(RequestCode.ButtonMultiplePicked);
//      }
//
//    });
//
//   /* recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new OnItemClickListener() {
//      @Override public void onItemClick(View view, int position) {
//
//
//        PhotoPreview.builder()
//            .setPhotos(selectedPhotos)
//            .setCurrentItem(position)
//            .start(MainActivity.this);
//      }
//    }));*/
//  }
//
//  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    recyclerView.onActivityResult(requestCode,resultCode,data);
//
//
//
//    recyclerViewShowOnly.showPics(recyclerView.getPhotos());
//
//   /* PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
//      @Override
//      public void onPickSuccess(ArrayList<String> photos) {
//
//        photoAdapter.add(photos);
//      }
//
//      @Override
//      public void onPreviewBack(ArrayList<String> photos) {
//        photoAdapter.refresh(photos);
//      }
//
//      @Override
//      public void onPickFail(String error) {
//        Toast.makeText(MainActivity.this,error,Toast.LENGTH_LONG).show();
//        selectedPhotos.clear();
//        photoAdapter.notifyDataSetChanged();
//      }
//
//      @Override
//      public void onPickCancle() {
//        Toast.makeText(MainActivity.this,"取消选择",Toast.LENGTH_LONG).show();
//      }
//    });
//*/
//    //photoAdapter.refresh();
//
//
//
//
////    if (resultCode == RESULT_OK &&
////        (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
////
////      List<String> photos = null;
////      if (data != null) {
////        photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
////      }
////      selectedPhotos.clear();
////
////      if (photos != null) {
////
////        selectedPhotos.addAll(photos);
////      }
////
////
////        for (int i = 0; i < photos.size(); i++) {
////            File  file=new File(photos.get(i));
////            Log.i("wxq","size"+"源文件"+file.length());
////
////        }
////   String temimg=Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AAAaaawxq/takePhotoImageTemp";
////        File file = new File(temimg);
////        //判断文件夹是否存在,如果不存在则创建文件夹
////        if (!file.exists()) {
////
////            if(file.mkdir()){
////                long afb=121212;
////                //创建陈宫
////                //读写权限
////            }else{
////                long afb=11;
////                //创建陈宫
////            }
////
////
////        }
////        for (int i = 0; i < photos.size(); i++) {
////            File  oldFile=new File(photos.get(i));
////            long size3=oldFile.length();
////
////               File   newFile = new CompressHelper.Builder(this)
////                .setMaxWidth(720)  // 默认最大宽度为720
////                .setMaxHeight(960) // 默认最大高度为960
////                .setQuality(80)    // 默认压缩质量为80
////                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
////               .setDestinationDirectoryPath(temimg)
////                  .setFileName(oldFile.getName().substring(0, oldFile.getName().lastIndexOf("."))) // 设置你的文件名
////                       .build()
////                       .compressToFile(oldFile);
////
////            Log.i("wxq","size"+"压缩后台"+newFile.length());
////
////            long size=newFile.length();
////            long size2=newFile.length();
////        }
////
//
//
//
//    //  photoAdapter.notifyDataSetChanged();
////    }
//  }
//
//  @Override
//  public void onRequestPermissionsResult(int requestCode,
//                                         @NonNull String[] permissions,
//                                         @NonNull int[] grantResults) {
//    // If request is cancelled, the result arrays are empty.
//    if (grantResults.length > 0
//            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//      // permission was granted, yay!
//      onClick(RequestCode.values()[requestCode].mViewId);
//
//    } else {
//      // permission denied, boo! Disable the
//      // functionality that depends on this permission.
//      Toast.makeText(this, "No read storage permission! Cannot perform the action.", Toast.LENGTH_SHORT).show();
//    }
//  }
//
//  @Override
//  public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
//    switch (permission) {
//      case Manifest.permission.WRITE_EXTERNAL_STORAGE:
//      case Manifest.permission.CAMERA:
//        // No need to explain to user as it is obvious
//        return false;
//      default:
//        return true;
//    }
//  }
//
//  private void checkPermission(@NonNull RequestCode requestCode) {
//
//    int readStoragePermissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//    int cameraPermissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//
//    boolean readStoragePermissionGranted = readStoragePermissionState != PackageManager.PERMISSION_GRANTED;
//    boolean cameraPermissionGranted = cameraPermissionState != PackageManager.PERMISSION_GRANTED;
//
//    if (readStoragePermissionGranted || cameraPermissionGranted) {
//
//      // Should we show an explanation?
//      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//          Manifest.permission.WRITE_EXTERNAL_STORAGE)
//          || ActivityCompat.shouldShowRequestPermissionRationale(this,
//          Manifest.permission.CAMERA)) {
//
//        // Show an expanation to the user *asynchronously* -- don't block
//        // this thread waiting for the user's response! After the user
//        // sees the explanation, try again to request the permission.
//
//      } else {
//        String[] permissions;
//        if (readStoragePermissionGranted && cameraPermissionGranted) {
//          permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
//        } else {
//          permissions = new String[] {
//              readStoragePermissionGranted ? Manifest.permission.WRITE_EXTERNAL_STORAGE
//                  : Manifest.permission.CAMERA
//          };
//        }
//        ActivityCompat.requestPermissions(this,
//                permissions,
//                requestCode.ordinal());
//      }
//
//    } else {
//      // Permission granted
//      onClick(requestCode.mViewId);
//    }
//
//  }
//
//  private void onClick(@IdRes int viewId) {
//
//    switch (viewId) {
//      case R.id.button: {
//        //Intent intent = new Intent(MainActivity.this, PhotoPickerActivity.class);
//        //PhotoPickerIntent.setPhotoCount(intent, 9);
//        //PhotoPickerIntent.setColumn(intent, 4);
//        //startActivityForResult(intent, REQUEST_CODE);
////      PhotoPicker.builder()
////            .setPhotoCount(9)
////              .setShowCamera(true)
////            .setGridColumnCount(4)
////            .start(this);
//       PhotoPickUtils.startPick(this, false, 1, null);
//        break;
//      }
//
//      case R.id.button_no_camera: {
//        //Intent intent = new Intent(MainActivity.this, PhotoPickerActivity.class);
//        //PhotoPickerIntent.setPhotoCount(intent, 7);
//        //PhotoPickerIntent.setShowCamera(intent, false);
//        //startActivityForResult(intent, REQUEST_CODE);
//        PhotoPicker.builder()
//            .setPhotoCount(7)
//            .setShowCamera(false)
//            .setPreviewEnabled(false)
//            .start(this);
//        break;
//      }
//
//      case R.id.button_one_photo: {
//        //Intent intent = new Intent(MainActivity.this, PhotoPickerActivity.class);
//        //PhotoPickerIntent.setPhotoCount(intent, 1);
//        //PhotoPickerIntent.setShowCamera(intent, true);
//        //startActivityForResult(intent, REQUEST_CODE);
//        PhotoPicker.builder()
//            .setPhotoCount(1)
//            .start(this);
//        break;
//      }
//
//      case R.id.button_photo_gif : {
//        //Intent intent = new Intent(MainActivity.this, PhotoPickerActivity.class);
//        //PhotoPickerIntent.setPhotoCount(intent, 4);
//        //PhotoPickerIntent.setShowCamera(intent, true);
//        //PhotoPickerIntent.setShowGif(intent, true);
//        //startActivityForResult(intent, REQUEST_CODE);
//        PhotoPicker.builder()
//            .setShowCamera(true)
//            .setShowGif(true)
//            .start(this);
//        break;
//      }
//
//      case R.id.button_multiple_picked:{
//        //Intent intent = new Intent(MainActivity.this, PhotoPickerActivity.class);
//        //PhotoPickerIntent.setPhotoCount(intent, 4);
//        //PhotoPickerIntent.setShowCamera(intent, true);
//        //PhotoPickerIntent.setSelected(intent,selectedPhotos);
//        //startActivityForResult(intent, REQUEST_CODE);
//       /* PhotoPicker.builder()
//            .setPhotoCount(4)
//            .setShowCamera(true)
//            .setSelected(selectedPhotos)
//            .start(this);
//        break;*/
//      }
//    }
//  }
//}
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:layout_width="match_parent"
//        android:orientation="vertical"
//        android:layout_height="match_parent"
//        tools:context=".MainActivity"
//        >
//
//<ScrollView
//android:layout_width="match_parent"
//        android:layout_height="match_parent">
//<LinearLayout
//android:orientation="vertical"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent">
//
//
//<Button
//android:layout_marginTop="10dip"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/pick_photo"
//        android:id="@+id/button"
//        android:layout_centerHorizontal="true"
//        />
//
//<Button
//android:layout_below="@id/button"
//        android:layout_marginTop="10dip"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/pick_photo_without_camera"
//        android:id="@+id/button_no_camera"
//        android:layout_centerHorizontal="true"
//        />
//
//<Button
//android:layout_below="@id/button_no_camera"
//        android:layout_marginTop="10dip"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/pick_one_photo"
//        android:id="@+id/button_one_photo"
//        android:layout_centerHorizontal="true"
//        />
//
//<Button
//android:layout_below="@id/button_one_photo"
//        android:layout_marginTop="10dip"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/pick_photo_gif"
//        android:id="@+id/button_photo_gif"
//        android:layout_centerHorizontal="true"
//        />
//
//<Button
//android:layout_below="@id/button_photo_gif"
//        android:layout_marginTop="10dip"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="@string/multiple_pick_photo"
//        android:id="@+id/button_multiple_picked"
//        android:layout_centerHorizontal="true"
//        />
//
//<!--<android.support.v7.widget.RecyclerView
//        android:id="@+id/recycler_view"
//        android:layout_below="@id/button_multiple_picked"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        />-->
//
//<me.iwf.photopicker.widget.MultiPickResultView
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:id="@+id/recycler_view"/>
//
//<me.iwf.photopicker.widget.MultiPickResultView
//        android:layout_marginTop="20dp"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:id="@+id/recycler_onlylook"
//        android:layout_below="@id/button_multiple_picked"/>
//
//</LinearLayout>
//
//
//</ScrollView>
//
//
//
//
//</LinearLayout>
