package com.ikdzz.domain.shiro;

import java.util.Date;

public class Permission {
    private Integer id;
    private String permission_id;
    private String name;
    private String description;
    private String url;
    private String perms;
    private Integer parent_id;
    private Integer type;
    private Integer order_num;
    private String icon;
    private Integer status;
    private Date create_time;
    private Date update_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission_id='" + permission_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                ", parent_id=" + parent_id +
                ", type=" + type +
                ", order_num=" + order_num +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
