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
package com.yanzhenjie.fragment.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.fragment.NoFragment;
import com.yanzhenjie.fragment.sample.R;

/**
 * Created by Yan Zhenjie on 2017/1/15.
 */
public class ArgumentFragment extends NoFragment {

    private Toolbar mToolbar;
    private TextView mTvMessage;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_argument, container, false);
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_argument;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setToolbar(mToolbar);
        setTitle(R.string.title_fragment_argument);
        displayHomeAsUpEnabled(R.drawable.ic_close_white);

        Bundle bundle = getArguments();
        String message = bundle.getString("hehe") + "\r\n";
        message += bundle.getString("meng") + "\r\n";
        message += bundle.getString("bang") + "\r\n";
        message += bundle.getString("meme") + "\r\n";

        mTvMessage.setText(message);
    }
}
