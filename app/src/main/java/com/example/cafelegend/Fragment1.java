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
 * Use the {@link Fragment1#} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment implements ItemsAdapter.OnEventListener{

    View v;
    private Vector<Food> foodVector;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodVector = new Vector<>();
        foodVector.add(new Food("Slow-Roasted Cherry Tomato Bruschetta", "Tangy goat cheese and crispy baguette slices are the ideal canvas for this comforting yet light take on bruschetta.", 27000, R.drawable.slow_roasted_cherry_tomato_bruschetta));
        foodVector.add(new Food("Asparagus Ribbon Crostini", "Serving ribbons of asparagus on top of baguette slices turns it from a side dish into a spring-ready finger food. Fresh mint helps make it even more colorful.", 35000, R.drawable.asparagus_ribbon_crostini));
        foodVector.add(new Food("Warm Cheese Box Bites", "Move over cheese straws! These cheesy bites aren't quite crackers, puffs, or straws. They are just as tasty though.", 21000, R.drawable.warm_cheese_box_bites));
        foodVector.add(new Food("Smoked Beef Quiche", "A savory quiche pie made from Smoked Beef and Cheese", 38000, R.drawable.smoked_beef_quiche));
        foodVector.add(new Food("Picnic Egg Salad", "Toppings like bacon, cheddar, sour cream, and chives aren't just for potatoes. Get the taste of two classic dishes, loaded baked potatoes and deviled eggs, in one bite.", 28000, R.drawable.picnic_egg_salad));
        foodVector.add(new Food("Loaded Devilled Eggs", "Toppings like bacon, cheddar, sour cream, and chives aren't just for potatoes. Get the taste of two classic dishes, loaded baked potatoes and deviled eggs, in one bite.", 31000, R.drawable.loaded_deviled_eggs));
        foodVector.add(new Food("Beef Sausage & Cheese Croissant", " A New York style bagel topped with Asiago cheese, poppy and sesame seeds, onion and garlic.", 37000, R.drawable.beef_sausage_and_cheese_croissants));
        foodVector.add(new Food("Cheese Bagels", " A New York style bagel topped with Asiago cheese, poppy and sesame seeds, onion and garlic.", 26000, R.drawable.cheese_bagels));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_1, container, false);
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