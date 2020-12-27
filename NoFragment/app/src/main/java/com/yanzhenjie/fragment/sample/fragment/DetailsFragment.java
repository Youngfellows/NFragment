package com.yanzhenjie.fragment.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.fragment.NoFragment;
import com.yanzhenjie.fragment.sample.R;

public class DetailsFragment extends NoFragment {

    private String TAG = DetailsFragment.class.getSimpleName();
    private TextView mTvTitle;
    private String mTitleText;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        //mTitleText = bundle.getString("title");
        mTitleText = (String) bundle.getSerializable("args");
        Log.d(TAG, "onAttach:: mTitleText:" + mTitleText);
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        //return super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.fragment_details, container, false);
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        mTvTitle = view.findViewById(R.id.tv_title);
        update(mTitleText);
    }

    public void refreshFragment(Bundle bundle) {
        mTitleText = (String) bundle.getSerializable("args");
        update(mTitleText);
    }

    public void update(String title) {
        mTvTitle.setText(title);
    }
}
