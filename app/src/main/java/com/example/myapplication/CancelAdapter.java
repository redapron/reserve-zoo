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

/**
 * Created by nu on 1/18/2017.
 */
public class CancelAdapter extends BaseAdapter {

    Context mContext;
    List<Room> items;

    public CancelAdapter(Context context, List<Room> items){
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_cancel_detail,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.listCancelDate.setText(StringUtil.getClientDateFormat(items.get(position).getFrom()));
        viewHolder.listCancelTime.setText(StringUtil.getClientTimeFormat(items.get(position).getFrom())+"-"+StringUtil.getClientTimeFormat(items.get(position).getTo()));
        viewHolder.listCancelRoom.setText(items.get(position).getRoom());
        viewHolder.listCancelNote.setText(items.get(position).getNote());
        viewHolder.listCancelForName.setText(items.get(position).getForUserTH());
        viewHolder.listCancelForPhone.setText(items.get(position).getForPhone());
        viewHolder.listCancelUserName.setText(items.get(position).getUserTH());
        viewHolder.listCancelUserPhone.setText(items.get(position).getPhone());
        return convertView;
    }

    private class ViewHolder {
        public TextView listCancelDate;
        public TextView listCancelTime;
        public TextView listCancelRoom;
        public TextView listCancelNote;
        public TextView listCancelForName;
        public TextView listCancelForPhone;
        public TextView listCancelUserName;
        public TextView listCancelUserPhone;

        public ViewHolder(View converView){
            listCancelDate = (TextView) converView.findViewById(R.id.listCancelDate);
            listCancelTime = (TextView) converView.findViewById(R.id.listCancelTime);
            listCancelRoom = (TextView) converView.findViewById(R.id.listCancelRoom);
            listCancelNote = (TextView) converView.findViewById(R.id.listCancelNote);
            listCancelForName = (TextView) converView.findViewById(R.id.listCancelForName);
            listCancelForPhone = (TextView) converView.findViewById(R.id.listCancelForPhone);
            listCancelUserName = (TextView) converView.findViewById(R.id.listCancelUserName);
            listCancelUserPhone = (TextView) converView.findViewById(R.id.listCancelUserPhone);
        }
    }
}
