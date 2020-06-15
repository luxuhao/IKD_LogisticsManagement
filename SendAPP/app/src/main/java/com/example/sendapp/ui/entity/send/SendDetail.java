package com.example.sendapp.ui.entity.send;

public class SendDetail {
    //id
    private String id;
    //主订单ID
    private String sendId;
    //产品编号
    private String material_id;
    //产品名称
    private String material_name;
    //型腔
    private String cav_no;
    //库位
    private String site;
    //批次号
    private String trace_no;
    //数量
    private int qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getCav_no() {
        return cav_no;
    }

    public void setCav_no(String cav_no) {
        this.cav_no = cav_no;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTrace_no() {
        return trace_no;
    }

    public void setTrace_no(String trace_no) {
        this.trace_no = trace_no;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
