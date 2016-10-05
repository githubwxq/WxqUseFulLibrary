package com.example.wxq.wxqutilslibrary.model;

public class MsgEvent {
    public String jsonData;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String  purpose;
    public MsgEvent(String jsonData) {
        this.jsonData = jsonData;
    }




}