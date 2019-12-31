package com.bwie.wangyanmin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.wangyanmin.App;
import com.bwie.wangyanmin.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:9:16
 *@Description:
 * */
public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okhttpclient;
    private final RequestQueue requestQueue;

    private NetUtil(){
        //创建Handler来切换线程
        handler = new Handler();
        // TODO: 2019/12/31 创建拦截器
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        // TODO: 2019/12/31 设置拦截器
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // TODO: 2019/12/31  创建OkHttp
        okhttpclient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                // TODO: 2019/12/31 添加拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();

        requestQueue = Volley.newRequestQueue(App.app);
    }

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }

    // TODO: 2019/12/31 获取数据
    public void getJsonGet(String httpUrl,MyCallback myCallback){
        // TODO: 2019/12/31 设置方法
        Request build = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();

        Call call = okhttpclient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 2019/12/31 判断网络和是否为空
                if (response!=null && response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onGetJson(string);
                        }
                    });
                }else {
                            myCallback.onError(new Exception("解析失败"));
                }
            }
        });

    }



    // TODO: 2019/12/31 判断网络有网的方法
    public static boolean getNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }

    }

    public static boolean ifWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return false;
        }

    }

    public void getPhoto(String photo, ImageView imageView){
        Glide.with(imageView).load(photo)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);

    }

    public static boolean ifMoble(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return false;
        }

    }

    public void  getJson (String httpUrl,MyCallback myCallback ){
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, httpUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallback.onGetJson(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallback.onError(error);
            }
        });
        requestQueue.add(stringRequest);

    }



    // TODO: 2019/12/31  返回接口
    public  interface MyCallback{
        void onGetJson(String json);
        void onError(Throwable throwable);
    }
}
