package com.bwie.wangyanmin.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.wangyanmin.R;
import com.bwie.wangyanmin.base.BaseActivity;
import com.bwie.wangyanmin.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondeActivity extends BaseActivity {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.bt_one)
    Button btOne;
    @BindView(R.id.img_er)
    ImageView imgEr;
    @BindView(R.id.bt_qq)
    Button btQq;
    @BindView(R.id.bt_wx)
    Button btWx;

    @Override
    protected void initData() {
        imgEr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imgEr, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondeActivity.this, "名字是:"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondeActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return  true;
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_seconde;
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }



    @OnClick({R.id.bt_one, R.id.bt_qq, R.id.bt_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_one:
                String name = etName.getText().toString();
                if (!TextUtils.isEmpty(name)){
                    Bitmap image = CodeUtils.createImage(name, 400, 400, null);
                    imgEr.setImageBitmap(image);
                }

                break;
            case R.id.bt_qq:
                EventBus.getDefault().post("QQ");
                break;
            case R.id.bt_wx:
                EventBus.getDefault().post("微信");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onString(String string){
        Toast.makeText(this, ""+string, Toast.LENGTH_SHORT).show();
    }
}
