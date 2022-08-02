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

import java.util.Vector;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private Vector<Food> foodVector;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public HomeAdapter(Vector<Food> foodVector) {
        this.foodVector = foodVector;
    }

    public void setFoodVector(Vector<Food> foodVector) {
        this.foodVector = foodVector;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Food food = foodVector.get(position);
        holder.tvfoodname.setText(food.getFoodName());
        holder.tvfoodprice.setText(food.getFoodPrice()+"");
        holder.img.setImageDrawable(holder.itemView.getContext().getDrawable(food.getFoodImage()));
    }

    @Override
    public int getItemCount() {
        return foodVector.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvfoodname, tvfoodprice, tvfooddesc;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvfoodname = itemView.findViewById(R.id.food_name);
            tvfoodprice = itemView.findViewById(R.id.food_price);
            img = itemView.findViewById(R.id.food_img);
        }
    }
}
