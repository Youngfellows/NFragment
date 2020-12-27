package com.yanzhenjie.fragment.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.fragment.sample.R;

import java.util.ArrayList;
import java.util.List;

public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.ViewHolder> {

    private String TAG = this.getClass().getSimpleName();

    private final LayoutInflater mLayoutInflater;

    private List<String> mData;
    private OnClickListener mOnClickListener;

    public ListMenuAdapter(Context context, List<String> data) {
        mLayoutInflater = LayoutInflater.from(context);
        if (mData == null) {
            this.mData = new ArrayList<>();
        }
        mData.addAll(data);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_menue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvMenuName.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public String getData(int index) {
        return mData.get(index);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvMenuName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvMenuName = itemView.findViewById(R.id.tv_menu_name);
        }
    }

    public interface OnClickListener {
        void onClick(View view, int position);
    }

}
