package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nu on 1/18/2017.
 */
public class ReserveAdapter extends BaseAdapter {

    Context mContext;
    List<Room> items;

    public ReserveAdapter(Context context, List<Room> items){
        this.mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_reserve_detail,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item_text1.setText(items.get(position).getRoom());
        viewHolder.item_text2.setText(String.valueOf(items.get(position).getSizeMin())+"-"+String.valueOf(items.get(position).getSizeMax()));
        return convertView;
    }

    private class ViewHolder {
        public TextView item_text1;
        public TextView item_text2;

        public ViewHolder(View converView){
            item_text1 = (TextView) converView.findViewById(R.id.listReserveRoom);
            item_text2 = (TextView) converView.findViewById(R.id.listReserveSize);
        }
    }
}
