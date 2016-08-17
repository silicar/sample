package silicar.sample.net.okhttp;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import silicar.library.util.ToastUtil;
import silicar.sample.R;
import silicar.sample.databinding.ActivityOkHttpBinding;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Get获取用户列表
     * Post创建新用户
     * Put修改用户名和密码
     * Delete删除帐号
     */

    OkHttpClient okHttpClient = new OkHttpClient();
    ActivityOkHttpBinding binding;
    ToastUtil toastUtil;
    String sessionToken = "";
    final int GET=1
            ,POST = 2
            ,PUT = 3
            ,DELETE = 4
            ,LOGIN = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toastUtil = new ToastUtil(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ok_http);
        binding.get.setOnClickListener(this);
        binding.post.setOnClickListener(this);
        binding.login.setOnClickListener(this);
        binding.put.setOnClickListener(this);
        binding.delete.setOnClickListener(this);
    }

    //同步GET获取用户列表
    private void getSyn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("https://api.bmob.cn/1/classes/_User")
                            .addHeader("X-Bmob-Application-Id", "6f8e594c84de36cdbe0d889fe10b8457")
                            .addHeader("X-Bmob-REST-API-Key", "82c5c3c5089cf7a18414f32e0a7d94d7")
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()){
                        Message message = handler.obtainMessage(GET, response.body().string());
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //异步GET获取用户列表
    private void get(){
        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/classes/_User")
                .addHeader("X-Bmob-Application-Id", "6f8e594c84de36cdbe0d889fe10b8457")
                .addHeader("X-Bmob-REST-API-Key", "82c5c3c5089cf7a18414f32e0a7d94d7")
                .addHeader("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage(GET, response.body().string());
                handler.sendMessage(message);
            }
        });
    }

    //POST创建用户
    private void post() {
        Gson gson = new Gson();
        String createUser = null;
        UserVo userVo = new UserVo();
        userVo.setUsername(binding.username.getText().toString());
        userVo.setPassword(binding.password.getText().toString());
        createUser = gson.toJson(userVo);
        Log.e("post", createUser);
        RequestBody requestBody = RequestBody.create(null, createUser);
        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/classes/_User")
                .post(requestBody)
                .addHeader("X-Bmob-Application-Id", "6f8e594c84de36cdbe0d889fe10b8457")
                .addHeader("X-Bmob-REST-API-Key", "82c5c3c5089cf7a18414f32e0a7d94d7")
                .addHeader("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage(POST, response.body().string());
                handler.sendMessage(message);
            }
        });
    }

    //GET获取登录Token
    private void login(){
        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/login" +
                        "?username=" +  binding.username.getText().toString() +
                        "&password=" + binding.password.getText().toString())
                .addHeader("X-Bmob-Application-Id", "6f8e594c84de36cdbe0d889fe10b8457")
                .addHeader("X-Bmob-REST-API-Key", "82c5c3c5089cf7a18414f32e0a7d94d7")
                .addHeader("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage(LOGIN, response.body().string());
                handler.sendMessage(message);
            }
        });
    }

    //PUT修改用户
    private void put() {
        Gson gson = new Gson();
        String modifyUser = null;
        UserVo userVo = new UserVo();
        userVo.setUsername(binding.username.getText().toString());
        userVo.setPassword(binding.password.getText().toString());
        modifyUser = gson.toJson(userVo);
        Log.e("put", modifyUser);
        Log.e("putid", binding.objectId.getText().toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), modifyUser);
        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/classes/_User/" + binding.objectId.getText().toString())
                .put(requestBody)
                .addHeader("X-Bmob-Application-Id", "6f8e594c84de36cdbe0d889fe10b8457")
                .addHeader("X-Bmob-REST-API-Key", "82c5c3c5089cf7a18414f32e0a7d94d7")
                .addHeader("X-Bmob-Session-Token", sessionToken)
                .addHeader("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage(PUT, response.body().string());
                handler.sendMessage(message);
            }
        });
    }

    //DELETE删除用户
    private void delete() {
        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/classes/_User/" + binding.objectId.getText().toString())
                .delete()
                .addHeader("X-Bmob-Application-Id", "6f8e594c84de36cdbe0d889fe10b8457")
                .addHeader("X-Bmob-REST-API-Key", "82c5c3c5089cf7a18414f32e0a7d94d7")
                .addHeader("X-Bmob-Session-Token", sessionToken)
                .addHeader("Content-Type", "application/json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage(DELETE, response.body().string());
                handler.sendMessage(message);
            }
        });
    }

    //处理GET获取用户列表请求
    private void getHandle(String response){
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            UserListVo listVo = gson.fromJson(response, UserListVo.class);
            if (listVo.getCode() == 0){
                List<UserVo> userVoList = listVo.getResults();
                if (userVoList != null && !userVoList.isEmpty()){
                    binding.objectId.setText(userVoList.get(0).getObjectId());
                    binding.username.setText(userVoList.get(0).getUsername());
                }
            } else {
                toastUtil.showLong("code" + listVo.getCode() + "; error:" + listVo.getError());
            }
        }
    }

    //处理POST创建用户
    private void postHandle(String response) {
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            UserVo userVo = gson.fromJson(response, UserVo.class);
            if (userVo.getCode() == 0){
                if (userVo != null ){
                    sessionToken = userVo.getSessionToken();
                    binding.sessionToken.setText(sessionToken);
                    binding.objectId.setText(userVo.getObjectId());
                    //binding.username.setText(userVo.getUsername());
                }
            } else {
                toastUtil.showLong("code" + userVo.getCode() + "; error:" + userVo.getError());
            }
        }
    }

    //处理GET获取登录Token
    private void loginHandle(String response) {
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            UserVo userVo = gson.fromJson(response, UserVo.class);
            if (userVo.getCode() == 0){
                if (userVo != null ){
                    sessionToken = userVo.getSessionToken();
                    binding.sessionToken.setText(sessionToken);
                    binding.objectId.setText(userVo.getObjectId());
                    //binding.username.setText(userVo.getUsername());
                }
            } else {
                toastUtil.showLong("code" + userVo.getCode() + "; error:" + userVo.getError());
            }
        }
    }

    //处理PUT修改用户
    private void putHandle(String response) {
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            UserVo userVo = gson.fromJson(response, UserVo.class);
            if (userVo.getCode() == 0){
                if (userVo != null ){
                    toastUtil.showLong("修改成功:" + userVo.getUpdatedAt());
                }
            } else {
                toastUtil.showLong("code" + userVo.getCode() + "; error:" + userVo.getError());
            }
        }
    }

    //处理DELETE删除用户
    private void deleteHandle(String response) {
        if (!TextUtils.isEmpty(response)){
            Gson gson = new Gson();
            UserVo userVo = gson.fromJson(response, UserVo.class);
            if (userVo.getCode() == 0){
                if (userVo.getMsg().equals("ok") ){
                    toastUtil.showLong("删除成功" );
                }
            } else {
                toastUtil.showLong("code" + userVo.getCode() + "; error:" + userVo.getError());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                get();
                break;
            case R.id.post:
                post();
                break;
            case R.id.login:
                login();
                break;
            case R.id.put:
                put();
                break;
            case R.id.delete:
                delete();
                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            binding.response.setText(response);
            Log.e("response", response);
            switch (msg.what){
                case GET:
                    getHandle(response);
                    break;
                case POST:
                    postHandle(response);
                    break;
                case LOGIN:
                    loginHandle(response);
                    break;
                case PUT:
                    putHandle(response);
                    break;
                case DELETE:
                    deleteHandle(response);
                    break;
            }
        }
    };
}
