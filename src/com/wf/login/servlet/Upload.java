package com.wf.login.servlet;



public class Upload {

    private String id;
    private String filename;

    public Upload() {
    }

    public Upload(String id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String getGetFilename() {
        return filename;
    }

    public String getGetId() {
        return id;
    }
}