package com.pmcc.my_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.pmcc.my_base.mvp.BasePresenter;
import com.pmcc.my_base.mvp.DataPresenter;
import com.pmcc.my_base.utils.ActivityCollector;
import com.pmcc.my_base.utils.ScreenUtils;
import com.pmcc.my_base.utils.StatusBarUtil;
import com.pmcc.my_base.utils.ToastUtils;

import butterknife.ButterKnife;

import static com.pmcc.my_base.utils.ToastUtils.showToast;

/**
 * Created by ${zhangshuai} on 2018/11/29.
 * 2751157603@qq.com
 */
public abstract class BaseActivity <T extends BasePresenter> extends FragmentActivity {
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMyConfig();
    }

    protected  void initMyConfig(){
        ActivityCollector.getInstance().addActivity(this.getClass(), this);

        mPresenter = createPresenter();
        if (!setNoScreenFit()) {
            ScreenUtils.setCustomDensity(this);
        }

        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        try {
            initData();
            initView();
            initListener();
        } catch (Exception e) {
            //异常处理
            showToast(e.getMessage());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            if (mPresenter instanceof DataPresenter) {
                ((DataPresenter) mPresenter).cancleUrls();
            }
            mPresenter = null;
        }
    }

    /**
     * 消息提示语句
     *
     * @param message
     */
    protected void showToast(String message) {
        ToastUtils.showToast(message);
    }

    /**
     * 设置mvp
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 是否采用屏幕适配
     *
     * @return
     */
    public abstract boolean setNoScreenFit();


    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutResID();
    /**
     * 设置标题颜色
     *
     * @return
     */
    public  void setStatusBarColor(int color){
        StatusBarUtil.setColorStatusBar(this,color);
    };

    /**
     * 初始化数据
     *
     * @throws Exception
     */
    protected void initData() throws Exception {

    }

    /**
     * 初始化界面
     *
     * @throws Exception
     */
    protected void initView() throws Exception {

    }

    /**
     * 设置监听
     *
     * @throws Exception
     */
    protected void initListener() throws Exception {

    }
    ;
}
