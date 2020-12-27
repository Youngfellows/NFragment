package com.yanzhenjie.fragment.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import com.yanzhenjie.fragment.NoFragment;
import com.yanzhenjie.fragment.sample.R;
import com.yanzhenjie.fragment.sample.adapter.ListMenuAdapter;

import java.util.ArrayList;

public class ListMenuFragment extends NoFragment implements ListMenuAdapter.OnClickListener {
    private static Callback mCallback;
    private RecyclerView mRvListMenu;
    private ListMenuAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_menu;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRvListMenu = view.findViewById(R.id.rv_list_menu);

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("菜单" + (i + 1));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getCompatActivity());
        mRvListMenu.setLayoutManager(linearLayoutManager);
        mAdapter = new ListMenuAdapter(getActivity(), datas);
        mAdapter.setOnClickListener(this);
        mRvListMenu.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        Log.d(TAG, "onClick:: click position " + position + "," + getParentFragment() + ",mCallback:" + mCallback);
        //MainFragment mainFragment = (MainFragment) getParentFragment();
        //mainFragment.showDetailsFragment(mAdapter.getData(position));
        if (mCallback != null) {
            mCallback.onCallback(mAdapter.getData(position));
        }
    }

    public static void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onCallback(String string);
    }

}
