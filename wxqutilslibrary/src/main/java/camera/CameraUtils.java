//package camera;
//
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.ImageFormat;
//import android.graphics.SurfaceTexture;
//import android.hardware.Camera;
//import android.os.Environment;
//import android.util.Log;
//import android.view.Surface;
//
//import com.juziwl.banbantong.config.Global;
//import com.orhanobut.logger.Logger;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * @author Army
// * @version V_1.0.0
// * @date 2017/05/12
// * @description
// */
//public class CameraUtils {
//
//    private static Camera camera;
//    private static boolean g_checkCamera;
//    private static Camera.Parameters params;
//    private static List<String> focusModes;
//    private static int displayRotation;
//
//    public static void openCamera(int cameraId) {
//        try {
//            stopPreview();
//            camera = Camera.open(cameraId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static boolean initial(Context context, int rotation) {
//        g_checkCamera = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
//        displayRotation = rotation;
//        params = null;
//        focusModes = null;
//        return g_checkCamera;
//    }
//
//    public static void startPreview(SurfaceTexture surface) {
//        int picWidth = 0;
//        int picHeight = 0;
//
//        if (!g_checkCamera) {
//            Logger.e("camera check failed");
//            return;
//        }
//
//        if (null == camera) return;
//        Logger.d("startPreview");
//        // 开启相机预览
//        try {
//            // 如果相机支持自动对焦，则打开相机的自动对焦
//            params = camera.getParameters();
//            params.setPictureFormat(ImageFormat.JPEG);
//            focusModes = params.getSupportedFocusModes();
//            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
//                if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
//                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//                } else {
//                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
//                }
//            }
//            setCameraDisplayOrientation(displayRotation, camera);
//
//
//            // 选择合适的预览尺寸
//            List<Camera.Size> sizeList = params.getSupportedPictureSizes();
//            // 如果sizeList只有一个我们也没有必要做什么了，因为就他一个别无选择
//            if (sizeList.size() > 1) {
//                Iterator<Camera.Size> itor = sizeList.iterator();
//                while (itor.hasNext()) {
//                    Camera.Size cur = itor.next();
//                    if (cur.width >= picWidth
//                            && cur.height >= picHeight) {
//                        picWidth = cur.width;
//                        picHeight = cur.height;
//
//                    }
//                    if (picHeight == 480) {
//                        break;
//                    }
//                }
//            }
//
//
//            params.setPictureSize(picWidth, picHeight);
//            camera.setParameters(params);
//            camera.setPreviewTexture(surface);
//            camera.startPreview();
//            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
//                camera.cancelAutoFocus();
//            }
//            Logger.d("start camera preview");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
////    public static void startPreview(SurfaceHolder holder) {
////        if (!g_checkCamera) {
////            Logger.e("camera check failed");
////            return;
////        }
////
////        if (null == camera) return;
////        Logger.d("startPreview");
////        // 开启相机预览
////        try {
////            // 如果相机支持自动对焦，则打开相机的自动对焦
////            params = camera.getParameters();
////            params.setPictureFormat(ImageFormat.JPEG);
////            params.set("jpeg-quality", 100);
////            focusModes = params.getSupportedFocusModes();
////            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
////                if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
////                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
////                } else {
////                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
////                }
////            }
////            setCameraDisplayOrientation(displayRotation, camera);
////            params.setPictureSize(1280, 720);
////            camera.setParameters(params);
////            camera.setPreviewDisplay(holder);
////            camera.startPreview();
////            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
////                camera.cancelAutoFocus();
////            }
////            Logger.d("start camera preview");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//    public static void setCameraDisplayOrientation(int rotation, Camera camera) {
//        Camera.CameraInfo info = new Camera.CameraInfo();
//        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, info);
//        int degrees = 0;
//        switch (rotation) {
//            case Surface.ROTATION_0:
//                degrees = 0;
//                break;
//            case Surface.ROTATION_90:
//                degrees = 90;
////                degrees = 0;
////             degrees = 180;
//                break;
//            case Surface.ROTATION_180:
//                degrees = 180;
//                break;
//            case Surface.ROTATION_270:
//                degrees = 270;
//                break;
//        }
//        int result;
//        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//            result = (info.orientation + degrees) % 360;
//            result = (360 - result) % 360;  // compensate the mirror
//        } else {  // back-facing
//            result = (info.orientation - degrees + 360) % 360;
//        }
//        camera.setDisplayOrientation(result);
//    }
//
//    /**
//     * 停止相机预览
//     */
//    public static void stopPreview() {
//        if (!g_checkCamera) {
//
//            return;
//        }
//
//        if (null == camera) return;
//        // 停止相机预览
//        try {
//            camera.stopPreview();
//
//        } catch (Exception e) {
//
//        }
//        camera.release();
//        camera = null;
//    }
//
//    public static void takePhoto(String cardId, TakePhoteListener listener) {
//        if (camera == null) {
//
//            return;
//        }
//
////        params.setPictureSize(1280,720);
//        camera.takePicture(null, null ,(data, camera1) -> {
//
//            camera.cancelAutoFocus();
//            camera.setParameters(params);
//            camera.startPreview();
//
//            if (data == null) {
//
//                return;
//            }
//
//            File cachefile = getCacheFile();
////            File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
//            File file = new File(cachefile, System.currentTimeMillis() + ".jpg");
//            OutputStream outputStream = null;
//            try {
//                outputStream = new FileOutputStream(file);
//                outputStream.write(data);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            } finally {
//                if (outputStream != null) {
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            Log.d("","");
//
////            CommonTools.showToast("take photo success");
//            if (listener != null) {
//                listener.getFilePath(file.getAbsolutePath(), cardId);
//            }
//        });
//    }
//
//
//    //    Context context
//    public static File getCacheFile() {
//        File cacheFile = null;
//        String externalStorageState = Environment.getExternalStorageState();
////        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
////            //    cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wxq/ErrorException");
//        cacheFile = new File(externalStorageState+"/wxq");
//        if (!cacheFile.exists()) {
//
//            boolean isok = cacheFile.mkdirs();
//            if (isok) {
//
//            }
//        }
////        } else {
////            cacheFile = context.getCacheDir();
////        }
//        return cacheFile;
//    }
//
//    public interface TakePhoteListener {
//        void getFilePath(String path, String cardId);
//    }
//}
