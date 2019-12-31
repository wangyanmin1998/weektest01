package com.bwie.wangyanmin.model;

import com.bwie.wangyanmin.contract.IHomeContract;
import com.bwie.wangyanmin.model.bean.Bean;
import com.bwie.wangyanmin.util.NetUtil;
import com.google.gson.Gson;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:9:12
 *@Description:
 * */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(IHomeCallback iHomeCallback) {
        String httpUrl="http://blog.zhaoliang5156.cn/api/news/ranking.json";
//        NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallback() {
//            @Override
//            public void onGetJson(String json) {
//                Bean bean = new Gson().fromJson(httpUrl,Bean.class);
//                iHomeCallback.onHomeSuccess(bean);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                iHomeCallback.onHomeFailure(throwable);
//            }
//        });
        NetUtil.getInstance().getJson(httpUrl, new NetUtil.MyCallback() {
            @Override
            public void onGetJson(String json) {
                Bean bean = new Gson().fromJson(json, Bean.class);
                iHomeCallback.onHomeSuccess(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                iHomeCallback.onHomeFailure(throwable);
            }
        });
    }
}
