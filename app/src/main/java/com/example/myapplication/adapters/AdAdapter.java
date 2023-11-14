package com.example.myapplication.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Ad;

import java.util.List;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder> {

    private List<Ad> ads;

    public AdAdapter(List<Ad> ads) {
        this.ads = ads;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        return new AdViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_ad,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
    holder.setAd(ads.get(position));
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    static class AdViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textSubtitle, textDateTime;
        LinearLayout layoutAd;

        AdViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textDateTime);
            layoutAd = itemView.findViewById(R.id.layoutAd);
        }

        void setAd(Ad ad) {
            textTitle.setText(ad.getTitle());
            if (ad.getSubtitle().trim().isEmpty()) {
                textSubtitle.setVisibility(View.GONE);
            } else {
                textSubtitle.setText(ad.getSubtitle());
            }
            textDateTime.setText(ad.getDateTime());

            GradientDrawable gradientDrawable = (GradientDrawable) layoutAd.getBackground();
            if (ad.getColor() != null) {
                gradientDrawable.setColor(Color.parseColor(ad.getColor()));
            }else {
                gradientDrawable.setColor(Color.parseColor("#333333"));
            }
        }
        }
    }

