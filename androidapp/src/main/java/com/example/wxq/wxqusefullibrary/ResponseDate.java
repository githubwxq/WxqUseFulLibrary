package com.example.wxq.wxqusefullibrary;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ResponseDate {

    /**
     * id : 204
     * description : 不能因为我超可爱你就欺负我啊
     * path : i/2015-07-31-4c92424a38c5db9c01abd63203584e1d.png
     * size : 61923
     * width : 200
     * height : 200
     * created_at : 2015-07-31 14:44:26
     * updated_at : 2015-10-12 00:15:43
     * user_id : 1
     * permitted_at : 2015-07-31 14:44:42
     * disk : qiniu
     * hotpoint : 59
     * channel : null
     * upload_id : 181
     * image_url : http://zhuangbi.idagou.com/i/2015-07-31-4c92424a38c5db9c01abd63203584e1d.png
     * file_size : 60.47 KB
     * upload : {"id":181,"name":"App/Picture","description":"不能因为我超可爱你就欺负我啊","disk":"qiniu","path":"i/2015-07-31-4c92424a38c5db9c01abd63203584e1d.png","size":61923,"user_id":1,"created_at":"2015-07-31 14:44:26","updated_at":"2015-07-31 14:45:10","uploadable_id":null,"uploadable_type":null,"url":"http://zhuangbi.idagou.com/i/2015-07-31-4c92424a38c5db9c01abd63203584e1d.png"}
     */

    private int id;
    private String description;
    private String path;
    private int size;
    private int width;
    private int height;
    private String created_at;
    private String updated_at;
    private int user_id;
    private String permitted_at;
    private String disk;
    private int hotpoint;
    private Object channel;
    private int upload_id;
    private String image_url;
    private String file_size;
    /**
     * id : 181
     * name : App/Picture
     * description : 不能因为我超可爱你就欺负我啊
     * disk : qiniu
     * path : i/2015-07-31-4c92424a38c5db9c01abd63203584e1d.png
     * size : 61923
     * user_id : 1
     * created_at : 2015-07-31 14:44:26
     * updated_at : 2015-07-31 14:45:10
     * uploadable_id : null
     * uploadable_type : null
     * url : http://zhuangbi.idagou.com/i/2015-07-31-4c92424a38c5db9c01abd63203584e1d.png
     */

    private UploadBean upload;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPermitted_at() {
        return permitted_at;
    }

    public void setPermitted_at(String permitted_at) {
        this.permitted_at = permitted_at;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public int getHotpoint() {
        return hotpoint;
    }

    public void setHotpoint(int hotpoint) {
        this.hotpoint = hotpoint;
    }

    public Object getChannel() {
        return channel;
    }

    public void setChannel(Object channel) {
        this.channel = channel;
    }

    public int getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(int upload_id) {
        this.upload_id = upload_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public UploadBean getUpload() {
        return upload;
    }

    public void setUpload(UploadBean upload) {
        this.upload = upload;
    }

    public static class UploadBean {
        private int id;
        private String name;
        private String description;
        private String disk;
        private String path;
        private int size;
        private int user_id;
        private String created_at;
        private String updated_at;
        private Object uploadable_id;
        private Object uploadable_type;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisk() {
            return disk;
        }

        public void setDisk(String disk) {
            this.disk = disk;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getUploadable_id() {
            return uploadable_id;
        }

        public void setUploadable_id(Object uploadable_id) {
            this.uploadable_id = uploadable_id;
        }

        public Object getUploadable_type() {
            return uploadable_type;
        }

        public void setUploadable_type(Object uploadable_type) {
            this.uploadable_type = uploadable_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
