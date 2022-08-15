package com.example.cafelegend;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cafelegend.adapter.ItemsAdapter;
import com.example.cafelegend.model.Food;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment implements ItemsAdapter.OnEventListener{

    View v;
    private Vector<Food> foodVector;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodVector = new Vector<>();
        foodVector.add(new Food("Grilled Chicken With Fresh Cherry Salsa", "Juicy grilled chicken breasts, topped with a delicious ruby-red cherry salsa, made from the season’s best local cherries. Quick, easy and super yummy, this the ultimate summer plate!", 49000, R.drawable.grilled_chicken));
        foodVector.add(new Food("Roasted Portobello With Shrimp", "Kick Off The Spring/Summer With This Delicious Roasted Portobello Mushroom With A Light, Tangy And Slightly Spicy Shrimp ‘N Tomato Bruschetta Topping. It Doesn’t Taste Any Fresher Than This!", 58000, R.drawable.roasted_protobello));
        foodVector.add(new Food("Almond Crusted Chicken", "A gluten free and healthy chicken dinner with maple dijon sauce that will become weeknight favorite!", 61000, R.drawable.almond_crusted_chicken));
        foodVector.add(new Food("Beef Goulash", "Beef Goulash is essentially a hearty beef stew seasoned with paprika and other spices, that originated in Hungary.", 57000, R.drawable.beef_goulash));
        foodVector.add(new Food("Crispy Belly", "Roasted crispy pork belly, served with mango salad and thai chili sauce.", 48000, R.drawable.crispy_belly));
        foodVector.add(new Food("Truffle Cream Wild Mushroom", "Creamy Fettuccine, Grated Parmesan. ", 55000, R.drawable.truffle));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_2, container, false);
        RecyclerView rv = v.findViewById(R.id.menu_food);
        ItemsAdapter foodAdapter = new ItemsAdapter(getContext(), foodVector, this);
        rv.setAdapter(foodAdapter);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return v;
    }

    @Override
    public void onCardClick(Vector<Food> foodVector, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);

        intent.putExtra("food", foodVector.get(position));
        startActivity(intent);
    }
}