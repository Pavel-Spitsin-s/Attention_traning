package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class PictureEtAdapter extends ArrayAdapter<PictureEt> {
    public PictureEtAdapter(Context context, PictureEt[] arr) {
        super(context, R.layout.adapter_item, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PictureEt pet = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }

        ((ImageView) convertView.findViewById(R.id.adapter_iw)).setImageBitmap(pet.bitmap);
        EditText et = ((EditText) convertView.findViewById(R.id.adapter_et));
        return convertView;
    }

}
