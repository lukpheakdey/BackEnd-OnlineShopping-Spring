package com.group.backend.demo.admin;

public class Status {

    private long id;
    private String status;

    public Status(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Status() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
