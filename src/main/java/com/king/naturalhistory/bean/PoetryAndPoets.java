package com.king.naturalhistory.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;

public class PoetryAndPoets extends BaseQuery{
    private String name;
    private String title;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }
    @Basic
    @Column(name = "created_at")
    public Timestamp getCreated_at() {
        return created_at;
    }
    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
