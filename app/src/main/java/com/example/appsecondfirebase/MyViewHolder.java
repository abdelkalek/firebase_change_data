package com.example.appsecondfirebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView nom, prenom, telephone;
    public ImageView myphoto;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nom = itemView.findViewById(R.id.txt_nom_item);
        prenom = itemView.findViewById(R.id.txt_prenom_item);
        telephone = itemView.findViewById(R.id.txt_telephone_item);
        myphoto = itemView.findViewById(R.id.imageView2);
    }
}
