package com.fu.okhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private List<BookGson.DataBean> bookItem;

    public CustomListAdapter(Context context, List<BookGson.DataBean> bookItem) {
        this.context = context;
        this.bookItem = bookItem;
    }

    @Override
    public int getCount() {
        if (bookItem != null) {
            return bookItem.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return bookItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO: 2018/11/14 Making ListView Scrolling Smooth
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_row_items, parent, false);

            viewHolder.first_name = convertView.findViewById(R.id.first_name);
            viewHolder.last_name = convertView.findViewById(R.id.last_name);
            viewHolder.image =convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BookGson.DataBean item = (BookGson.DataBean) getItem(position);
        viewHolder.first_name.setText(item.getFirst_name());
        viewHolder.last_name.setText(item.getLast_name());

        // TODO: 2018/11/14  利用picasso第三方讀取圖片
        String url = item.getAvatar();
        Picasso.get().load(url).into(viewHolder.image);


        return convertView;
    }

    private final static class ViewHolder {
        TextView first_name;
        TextView last_name;
        ImageView image;
    }
}
