package silicar.sample.net.okhttp;

import com.google.gson.annotations.Expose;

/**
 * Created by Tutu on 2016/8/17.
 */
public class Message {

    @Expose(serialize = false, deserialize = false)
    private int code;
    @Expose(serialize = false)
    private String error;
    @Expose(serialize = false)
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
