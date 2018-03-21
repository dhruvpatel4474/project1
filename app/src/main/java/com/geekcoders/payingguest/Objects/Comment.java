package com.geekcoders.payingguest.Objects;

/**
 * Created by dhruvpatel on 3/18/2018.
 */

public class Comment {
    String commentMessage;
    String userId;
    String userName;
    String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    String objectId;

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPGId() {
        return PGId;
    }

    public void setPGId(String PGId) {
        this.PGId = PGId;
    }

    String PGId;

}
