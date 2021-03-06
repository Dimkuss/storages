package com.example.storages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends BaseAdapter {
    private List<Sample> data = new ArrayList<>();
    public List<Sample> getData(){
        return data;
    }
public void setData(List<Sample> data){
    this.data.clear();
    this.data.addAll(data);
    notifyDataSetChanged();

}
    public void addData(Sample sample) {
        data.add(sample);
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    View view = null;
    if (convertView!=null){
        view = convertView;
    } else {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample,parent,false);
    }
        TextView textTv = view.findViewById(R.id.textTv);
        TextView textLengthTv = view.findViewById(R.id.textlengthTv);
        Sample sample = data.get(position);
        textTv.setText(sample.getText());
        textLengthTv.setText(sample.getText()+"");

        return view;
    }
}
