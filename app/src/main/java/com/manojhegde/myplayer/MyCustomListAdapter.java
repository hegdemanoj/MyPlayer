package com.manojhegde.myplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Manoj on 03-06-2018.
 */
public class MyCustomListAdapter extends ArrayAdapter{
    private ArrayList<File> fileList = new ArrayList<File>();
    private final Activity context;

    public MyCustomListAdapter(Activity context, ArrayList<File> fileList) {
        super(context, R.layout.mylist, fileList);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.fileList=fileList;

    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(fileList.get(position).getName());
        subtitleText.setText(fileList.get(position).getPath());

        return rowView;

    }


}
