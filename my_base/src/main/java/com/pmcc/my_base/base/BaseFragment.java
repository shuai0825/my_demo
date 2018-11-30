package com.pmcc.my_base.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pmcc.my_base.mvp.BasePresenter;
import com.pmcc.my_base.utils.ToastUtils;
import com.pmcc.my_base.widgets.LazyLoadFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${zhangshuai} on 2018/9/10.
 * 2751157603@qq.com
 */
public abstract class BaseFragment<T extends BasePresenter> extends LazyLoadFragment {
    private View mRootView;
    protected T mPresenter;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutResID(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);
            try {
                initView(mRootView);
                initData();
                initListener();
            } catch (Exception e) {
                //LogUtils.e(e.getMessage());

            }
        } else {//避免重新加载时，页面重复问题
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        getData();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
            mRootView = null;
        }
    }

    /**
     * 消息提示语句
     *
     * @param message
     */
    private void showToast(String message) {
        ToastUtils.showToast(message);
    }

    /**
     * 第一次可见，请求数据
     */
    protected abstract void getData();

    /**
     * 设置mvp
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutResID();

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
    protected void initView(View mRootView) throws Exception {

    }

    /**
     * 设置监听
     *
     * @throws Exception
     */
    protected void initListener() throws Exception {

    }


}
