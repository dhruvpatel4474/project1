package com.geekcoders.payingguest.Objects;

/**
 * Created by dhruvpatel on 3/15/2018.
 */

public class Category {
    String objectId;
    String name;
    String img;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return name;
    }
}
