package me.iwf.photopicker.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPickUtils;
import me.iwf.photopicker.PhotoPreview;
import me.iwf.photopicker.R;
import me.iwf.photopicker.utils.BimpUtils;
import me.iwf.photopicker.utils.CommonUtils;
import me.iwf.photopicker.utils.ImagePreference;
import me.iwf.photopicker.utils.LoadingImgUtil;

/**
 * Created by donglua on 15/5/31.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<String> photoPaths;
    private LayoutInflater inflater;
    private int PhotoCount;
    private Context mContext;
    private ImagePreference instance;

    public void setAction(@MultiPickResultView.MultiPicAction int action) {
        this.action = action;
    }

    public int getPhotoCount() {
        return PhotoCount;
    }

    public void setPhotoCount(int photoCount) {
        PhotoCount = photoCount;
    }

    private int action;

    public PhotoAdapter(Context mContext, ArrayList<String> photoPaths) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        instance = ImagePreference.getInstance(mContext);
    }

    public void add(ArrayList<String> photoPaths) {
        if (photoPaths != null && photoPaths.size() > 0) {
            this.photoPaths.addAll(photoPaths);
            notifyDataSetChanged();
        }
    }

    public void refresh(ArrayList<String> photoPaths) {
        this.photoPaths.clear();
        if (photoPaths != null && photoPaths.size() > 0) {
            this.photoPaths.addAll(photoPaths);
        }
        notifyDataSetChanged();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.__picker_item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        if (action == MultiPickResultView.ACTION_SELECT) {
//            System.out.println("----------------------------------------------------------------------------------------------");
//            System.out.println("BimpUtils.PHOTOSCOUNT==" + BimpUtils.PHOTOSCOUNT);
//            System.out.println("BimpUtils.drr.size()==" + BimpUtils.drr.size());
//            System.out.println("BimpUtils.cacheDir.size()==" + BimpUtils.cacheDir.size());
//            System.out.println("BimpUtils.uploadDir.size()==" + BimpUtils.uploadDir.size());
//            System.out.println("onBindViewHolder   position=" + position + " getItemCount()=" + getItemCount());
            if (position == getItemCount() - 1) { //最后一个始终是+号，点击能够跳去添加图片
//                System.out.println("position == getItemCount() - 1");
                if (instance.getImagesList(ImagePreference.DRR).size() == instance.getPhotoCount()) {
//                    System.out.println("BimpUtils.drr.size() == BimpUtils.PHOTOSCOUNT");
                    LoadingImgUtil.displayImage(holder.ivPhoto, photoPaths.get(position), new LoadingImgUtil.ImageSize(100, 100), 0.5f, true, false);
                } else {
//                    System.out.println("BimpUtils.drr.size() != BimpUtils.PHOTOSCOUNT");
                    //预留的添加默认图位置
                    if (BimpUtils.ADDPICCON.equals("")) {
                        holder.ivPhoto.setImageResource(R.drawable.addphoto);
                    } else {
                        holder.ivPhoto.setImageResource(R.drawable.addphoto);
                    }
                }
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (photoPaths != null && photoPaths.size() == instance.getPhotoCount()) {
                            PhotoPreview.builder()
                                    .setPhotos(photoPaths)
                                    .setAction(action)
                                    .setCurrentItem(position)
                                    .start2((Activity) mContext);
                        } else {
                            if (CommonUtils.checkPermission((Activity) mContext, null, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 30)) {
                                return;
                            }
                            PhotoPickUtils.startPickWithCount((Activity) mContext, instance.getImagesList(ImagePreference.DRR), getPhotoCount());
                        }
                    }
                });
            } else {
//                System.out.println("position != getItemCount() - 1");
                LoadingImgUtil.displayImage(holder.ivPhoto, photoPaths.get(position), new LoadingImgUtil.ImageSize(100, 100), 0.5f, true, false);
                holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PhotoPreview.builder()
                                .setPhotos(photoPaths)
                                .setAction(action)
                                .setCurrentItem(position)
                                .start2((Activity) mContext);
                    }
                });
            }
//            System.out.println("----------------------------------------------------------------------------------------------");
        } else if (action == MultiPickResultView.ACTION_ONLY_SHOW) {
            LoadingImgUtil.displayImage(holder.ivPhoto, photoPaths.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (photoPaths.size() == instance.getPhotoCount()) {
            return instance.getPhotoCount();
        } else {
            return action == MultiPickResultView.ACTION_SELECT ? photoPaths.size() + 1 : photoPaths.size();
        }
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;
        public View cover;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
            vSelected.setVisibility(View.GONE);
            cover = itemView.findViewById(R.id.cover);
            cover.setVisibility(View.GONE);
        }
    }

}
