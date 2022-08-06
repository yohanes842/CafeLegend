package com.example.cafelegend.adapter;

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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private Vector<Food> foodVector;
    private OnEventListener mOnEventListener;

    public HomeAdapter(Context context, Vector<Food> foodVector, OnEventListener onEventListener) {
        this.context = context;
        this.foodVector = foodVector;
        this.mOnEventListener = onEventListener;
    }

    public HomeAdapter(Vector<Food> foodVector, OnEventListener onEventListener) {
        this.foodVector = foodVector;
        this.mOnEventListener = onEventListener;
    }

    public void setFoodVector(Vector<Food> foodVector) {
        this.foodVector = foodVector;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_list, parent, false);
        return new ViewHolder(view, mOnEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Food food = foodVector.get(position);
        BigDecimal formatPrice = new BigDecimal(food.getFoodPrice()).movePointLeft(3);
        holder.tvfoodname.setText(food.getFoodName());
//        holder.tvfoodprice.setText("Rp. " + formatPrice + ",-");
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

            tvfoodname = itemView.findViewById(R.id.food_name);
//            tvfoodprice = itemView.findViewById(R.id.food_price);
            img = itemView.findViewById(R.id.food_img);
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
