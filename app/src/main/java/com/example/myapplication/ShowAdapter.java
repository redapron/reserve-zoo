package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.model.Room;
import com.example.myapplication.util.StringUtil;

import java.util.List;

public class ShowAdapter extends BaseAdapter {

    Context mContext;
    List<Room> items;

    public ShowAdapter(Context context, List<Room> items){
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_show_detail,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.listShowDate.setText(StringUtil.getClientDateFormat(items.get(position).getFrom()));
        viewHolder.listShowTime.setText(StringUtil.getClientTimeFormat(items.get(position).getFrom())+"-"+StringUtil.getClientTimeFormat(items.get(position).getTo()));
        viewHolder.listShowRoom.setText(items.get(position).getRoom());
        viewHolder.listShowNote.setText(items.get(position).getNote());
        viewHolder.listShowForName.setText(items.get(position).getForUserTH());
        viewHolder.listShowForPhone.setText(items.get(position).getForPhone());
        viewHolder.listShowUserName.setText(items.get(position).getUserTH());
        viewHolder.listShowUserPhone.setText(items.get(position).getPhone());
        return convertView;
    }

    private class ViewHolder {
        public TextView listShowDate;
        public TextView listShowTime;
        public TextView listShowRoom;
        public TextView listShowNote;
        public TextView listShowForName;
        public TextView listShowForPhone;
        public TextView listShowUserName;
        public TextView listShowUserPhone;

        public ViewHolder(View converView){
            listShowDate = (TextView) converView.findViewById(R.id.listShowDate);
            listShowTime = (TextView) converView.findViewById(R.id.listShowTime);
            listShowRoom = (TextView) converView.findViewById(R.id.listShowRoom);
            listShowNote = (TextView) converView.findViewById(R.id.listShowNote);
            listShowForName = (TextView) converView.findViewById(R.id.listShowForName);
            listShowForPhone = (TextView) converView.findViewById(R.id.listShowForPhone);
            listShowUserName = (TextView) converView.findViewById(R.id.listShowUserName);
            listShowUserPhone = (TextView) converView.findViewById(R.id.listShowUserPhone);
        }
    }
}
