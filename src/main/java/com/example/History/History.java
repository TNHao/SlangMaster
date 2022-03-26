package com.example.History;

import java.io.Serializable;

public class History implements Serializable {
    String time;
    String actionType;
    String status;
    String detail;

    public History(String time, String actionType, String status, String detail) {
        this.time = time;
        this.actionType = actionType;
        this.status = status;
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public String getActionType() {
        return actionType;
    }

    public String getDetail() {
        return detail;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "History{" +
                "time='" + time + '\'' +
                ", actionType='" + actionType + '\'' +
                ", status='" + status + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
