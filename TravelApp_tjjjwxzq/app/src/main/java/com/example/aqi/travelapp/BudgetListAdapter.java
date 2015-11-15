package com.example.aqi.travelapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BudgetListAdapter extends BaseAdapter {

    private static final String TAG = "BudgetAdapter";

    private ArrayList<ExpItem> listData;

    private LayoutInflater layoutInflater;

    private BudgetAdapterCallback callback;

    public BudgetListAdapter(Context aContext, ArrayList<ExpItem> listData){
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            final int position1 = position;
            convertView = layoutInflater.inflate(R.layout.budget_list, parent, false);
            holder = new ViewHolder();
            holder.expView = (TextView) convertView.findViewById(R.id.title);
            holder.amtView = (TextView) convertView.findViewById(R.id.amount);
            holder.image = (ImageButton) convertView.findViewById(R.id.editExpenditure);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.editExpenditure(position1);
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.d(TAG, "budget list updating");
        Log.d(TAG, "exp item is " + listData.get(position).getExp());
        Log.d(TAG, "number of exp items" + listData.size());
        holder.expView.setText(listData.get(position).getExp());
        holder.amtView.setText(String.format("%.2f", Double.parseDouble(listData.get(position).getAmt())));
        return convertView;
    }

    public void setCallback(BudgetAdapterCallback callback) {
        this.callback = callback;
    }

    static class ViewHolder {
        TextView expView;
        TextView amtView;
        ImageButton image;
    }

}

