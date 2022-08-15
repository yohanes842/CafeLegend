package com.example.cafelegend.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafelegend.R;
import com.example.cafelegend.model.Food;

import java.math.BigDecimal;
import java.util.Vector;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final Context context;
    private Vector<Food> foodVector;
    private final OnEventListener mOnEventListener;

    public ItemsAdapter(Context context, Vector<Food> foodVector, OnEventListener onEventListener) {
        this.context = context;
        this.foodVector = foodVector;
        this.mOnEventListener = onEventListener;
    }

    public void setFoodVector(Vector<Food> foodVector) {
        this.foodVector = foodVector;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_list, parent, false);
        return new ViewHolder(view, mOnEventListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        Food food = foodVector.get(position);
        BigDecimal formatPrice = new BigDecimal(food.getFoodPrice()).movePointLeft(3);
        holder.tvfoodname.setText(food.getFoodName());
        holder.tvfoodprice.setText("Rp. " + formatPrice + ",-");
        holder.img.setImageDrawable(holder.itemView.getContext().getDrawable(food.getFoodImage()));
    }

    @Override
    public int getItemCount() {
        return foodVector.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvfoodname, tvfoodprice;
        ImageView img;
        OnEventListener onEventListener;

        public ViewHolder(@NonNull View itemView, OnEventListener onEventListener) {
            super(itemView);

            tvfoodname = itemView.findViewById(R.id.item_name);
            tvfoodprice = itemView.findViewById(R.id.item_price);
            img = itemView.findViewById(R.id.item_img);
            this.onEventListener = onEventListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onEventListener.onCardClick(foodVector, getAdapterPosition());
        }
    }

    public interface OnEventListener{
        void onCardClick(Vector<Food> foodVector, int position);
    }
}
