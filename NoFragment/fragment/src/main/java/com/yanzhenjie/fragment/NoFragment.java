/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yan Zhenjie on 2017/1/13.
 */
public abstract class NoFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();
    public static final int RESULT_OK = Activity.RESULT_OK;
    public static final int RESULT_CANCELED = Activity.RESULT_CANCELED;

    private static final int REQUEST_CODE_INVALID = CompatActivity.REQUEST_CODE_INVALID;

    /**
     * 布局的资源id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 向一个已经显示的Fragment传递数据,更新页面,子类选择覆写
     *
     * @param bundle 传递数据
     */
    protected void refreshFragment(Bundle bundle) {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param context       context.
     * @param fragmentClass class of fragment.
     * @param <T>           subclass of {@link NoFragment}.
     * @return new instance.
     * @deprecated In {@code Activity} with {@link CompatActivity#fragment(Class)} instead;
     * in the {@code Fragment} width {@link #fragment(Class)} instead.
     */
    @Deprecated
    public static <T extends NoFragment> T instantiate(Context context, Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) instantiate(context, fragmentClass.getName(), null);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param context       context.
     * @param fragmentClass class of fragment.
     * @param bundle        argument.
     * @param <T>           subclass of {@link NoFragment}.
     * @return new instance.
     * @deprecated In {@code Activity} with {@link CompatActivity#fragment(Class, Bundle)} instead;
     * in the {@code Fragment} width {@link #fragment(Class, Bundle)} instead.
     */
    @Deprecated
    public static <T extends NoFragment> T instantiate(Context context, Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) instantiate(context, fragmentClass.getName(), bundle);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param fragmentClass class of fragment.
     * @param <T>           subclass of {@link NoFragment}.
     * @return new instance.
     */
    public final <T extends NoFragment> T fragment(Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) instantiate(getContext(), fragmentClass.getName(), null);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param fragmentClass class of fragment.
     * @param bundle        argument.
     * @param <T>           subclass of {@link NoFragment}.
     * @return new instance.
     */
    public final <T extends NoFragment> T fragment(Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) instantiate(getContext(), fragmentClass.getName(), bundle);
    }

    /**
     * Toolbar.
     */
    private Toolbar mToolbar;

    /**
     * CompatActivity.
     */
    private CompatActivity mActivity;

    /**
     * Get BaseActivity.
     *
     * @return {@link CompatActivity}.
     */
    protected final CompatActivity getCompatActivity() {
        return mActivity;
    }

    /**
     * Start activity.
     *
     * @param clazz class for activity.
     * @param <T>   {@link Activity}.
     */
    protected final <T extends Activity> void startActivity(Class<T> clazz) {
        startActivity(new Intent(mActivity, clazz));
    }

    /**
     * Start activity and finish my parent.
     *
     * @param clazz class for activity.
     * @param <T>   {@link Activity}.
     */
    protected final <T extends Activity> void startActivityFinish(Class<T> clazz) {
        startActivity(new Intent(mActivity, clazz));
        mActivity.finish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (CompatActivity) context;
    }

    /**
     * Destroy me.
     */
    public void finish() {
        mActivity.onBackPressed();
    }

    /**
     * Set Toolbar.
     *
     * @param toolbar {@link Toolbar}.
     */
    @SuppressLint("RestrictedApi")
    public final void setToolbar(@NonNull Toolbar toolbar) {
        this.mToolbar = toolbar;
        onCreateOptionsMenu(mToolbar.getMenu(), new SupportMenuInflater(mActivity));
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
    }

    /**
     * Display home up button.
     *
     * @param drawableId drawable id.
     */
    public final void displayHomeAsUpEnabled(@DrawableRes int drawableId) {
        displayHomeAsUpEnabled(ContextCompat.getDrawable(mActivity, drawableId));
    }

    /**
     * Display home up button.
     *
     * @param drawable {@link Drawable}.
     */
    public final void displayHomeAsUpEnabled(Drawable drawable) {
        mToolbar.setNavigationIcon(drawable);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!onInterceptToolbarBack())
                    finish();
            }
        });
    }

    /**
     * Override this method, intercept backPressed of ToolBar.
     *
     * @return true, other wise false.
     */
    public boolean onInterceptToolbarBack() {
        return false;
    }

    /**
     * Get Toolbar.
     *
     * @return {@link Toolbar}.
     */
    protected final
    @Nullable
    Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * Set title.
     *
     * @param title title.
     */
    protected void setTitle(CharSequence title) {
        if (mToolbar != null)
            mToolbar.setTitle(title);
    }

    /**
     * Set title.
     *
     * @param titleId string resource id from {@code string.xml}.
     */
    protected void setTitle(int titleId) {
        if (mToolbar != null)
            mToolbar.setTitle(titleId);
    }

    /**
     * Set sub title.
     *
     * @param title sub title.
     */
    protected void setSubtitle(CharSequence title) {
        if (mToolbar != null)
            mToolbar.setSubtitle(title);
    }

    /**
     * Set sub title.
     *
     * @param titleId string resource id from {@code string.xml}.
     */
    protected void setSubtitle(int titleId) {
        if (mToolbar != null)
            mToolbar.setSubtitle(titleId);
    }

    // ------------------------- Stack ------------------------- //

    /**
     * Stack info.
     */
    private CompatActivity.FragmentStackEntity mStackEntity;

    /**
     * Set result.
     *
     * @param resultCode result code, one of {@link NoFragment#RESULT_OK}, {@link NoFragment#RESULT_CANCELED}.
     */
    @SuppressLint("WrongConstant")
    protected final void setResult(@ResultCode int resultCode) {
        mStackEntity.resultCode = resultCode;
    }

    /**
     * Set result.
     *
     * @param resultCode resultCode, use {@link }.
     * @param result     {@link Bundle}.
     */
    @SuppressLint("WrongConstant")
    protected final void setResult(@ResultCode int resultCode, @NonNull Bundle result) {
        mStackEntity.resultCode = resultCode;
        mStackEntity.result = result;
    }

    /**
     * Get the resultCode for requestCode.
     */
    final void setStackEntity(@NonNull CompatActivity.FragmentStackEntity stackEntity) {
        this.mStackEntity = stackEntity;
    }

    /**
     * You should override it
     *
     * @param requestCode 请求码
     * @param resultCode  响应码
     * @param result      {@link Bundle}.   响应的数据包
     */
    public void onFragmentResult(int requestCode, @ResultCode int resultCode, @Nullable Bundle result) {

    }

    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     * @param <T>   {@link NoFragment}.
     */
    public final <T extends NoFragment> void startFragment(Class<T> clazz) {
        try {
            NoFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz       fragment class.
     * @param stickyStack sticky to back stack.
     * @param <T>         {@link NoFragment}.
     */
    public final <T extends NoFragment> void startFragment(Class<T> clazz, boolean stickyStack) {
        try {
            NoFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, stickyStack, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param <T>            {@link NoFragment}.
     */
    public final <T extends NoFragment> void startFragment(T targetFragment) {
        startFragment(targetFragment, true, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param <T>            {@link NoFragment}.
     */
    public final <T extends NoFragment> void startFragment(T targetFragment, boolean stickyStack) {
        startFragment(targetFragment, stickyStack, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment for result.
     *
     * @param clazz       fragment to display.
     * @param requestCode requestCode.
     * @param <T>         {@link NoFragment}.
     * @deprecated use {@link #startFragmentForResult(Class, int)} instead.
     */
    @Deprecated
    public final <T extends NoFragment> void startFragmentForResquest(Class<T> clazz, int requestCode) {
        startFragmentForResult(clazz, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link NoFragment}.
     * @deprecated use {@link #startFragmentForResult(Class, int)} instead.
     */
    @Deprecated
    public final <T extends NoFragment> void startFragmentForResquest(T targetFragment, int requestCode) {
        startFragmentForResult(targetFragment, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param clazz       fragment to display.
     * @param requestCode requestCode.
     * @param <T>         {@link NoFragment}.
     */
    public final <T extends NoFragment> void startFragmentForResult(Class<T> clazz, int requestCode) {
        try {
            NoFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link NoFragment}.
     */
    public final <T extends NoFragment> void startFragmentForResult(T targetFragment, int requestCode) {
        startFragment(targetFragment, true, requestCode);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param requestCode    requestCode.
     * @param <T>            {@link NoFragment}.
     */
    private <T extends NoFragment> void startFragment(T targetFragment, boolean stickyStack, int requestCode) {
        mActivity.startFragment(this, targetFragment, stickyStack, requestCode);
    }

    /**
     * Show a fragment.
     *
     * @param targetClazz fragment to display.
     * @param args        args
     * @param targetId    targetId.
     * @param <T>         {@link NoFragment}.
     */
    public <T extends NoFragment> void startFragment(Class<T> targetClazz, int targetId, String args) {
        try {
            NoFragment targetFragment = targetClazz.newInstance();
            mActivity.startFragment(targetFragment, targetId, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
