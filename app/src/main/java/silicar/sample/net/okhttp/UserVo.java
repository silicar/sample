package silicar.sample.net.okhttp;

import com.google.gson.annotations.Expose;

/**
 * Created by Tutu on 2016/8/17.
 */
public class UserVo extends Message {
    @Expose(serialize = false)
    private String createdAt;
    @Expose(serialize = false)
    private String updatedAt;
    @Expose(serialize = false)
    private String sessionToken;
    private String objectId;
    private String username;
    private String password;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
