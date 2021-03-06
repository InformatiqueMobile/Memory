package com.memory.memory.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.memory.memory.R;

/**
 * Created by Zalila on 2015-02-03.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(230, 230));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2,
            R.drawable.back_red_2, R.drawable.back_red_2
    };
}
