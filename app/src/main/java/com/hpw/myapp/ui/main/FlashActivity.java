package com.hpw.myapp.ui.main;

import android.Manifest;
import android.os.Bundle;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.helper.RxUtil;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.zhihu.activity.ZhihuMainActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by hpw on 16/10/28.
 */

public class FlashActivity extends CoreBaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(RxPermissions.getInstance(this).ensure(Manifest.permission.READ_PHONE_STATE))
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(granted -> {
                    if (granted) {
                        startActivity(ZhihuMainActivity.class);
                        finish();
                    }
                });
    }
}
