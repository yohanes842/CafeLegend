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
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment implements ItemsAdapter.OnEventListener{

    View v;
    private Vector<Food> foodVector;

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodVector = new Vector<>();
        foodVector.add(new Food("Chocolate Frappucino", "Mocha sauce and Frappuccino chips are blended with milk and ice, layered on top of whipped cream and chocolate cookie crumble and topped with vanilla whipped cream, mocha drizzle and even more chocolate cookie crumble. These layers ensure each sip is as good as the last; all the way to the end.", 35000, R.drawable.chocolate_frappucinno));
        foodVector.add(new Food("Earl Grey Tea", "We take a strong black tea base and add the essence of bergamot, a citrus fruit with subtle lemon and floral lavender notes, to create this aromatically awesome tea flavor.", 18000, R.drawable.earl_grey_tea));
        foodVector.add(new Food("Hot Chocolate", "Steamed milk and mocha sauce topped with sweetened whipped cream and a chocolate-flavored drizzle. A timeless classic made to sweeten your spirits.", 25000, R.drawable.hot_choco));
        foodVector.add(new Food("Iced Americano", "Freshly brewed Iced Coffee Blend served chilled and sweetened over ice. An absolutely, seriously, refreshingly lift to any day.", 23000, R.drawable.iced_americano));
        foodVector.add(new Food("Iced caramel Macchiato", "We combine our rich, full-bodied espresso with vanilla-flavored syrup, milk and ice, then top it off with a caramel drizzle for an oh-so-sweet finish.", 35000, R.drawable.iced_caramel_macchiato));
        foodVector.add(new Food("Iced Latte", "Small-lot espresso combined with milk and served over ice creates perfectly handcrafted sips of cool.", 23000, R.drawable.iced_latte));
        foodVector.add(new Food("Kiwi-StarFruit Refreshers", "Starfruit- and kiwi-flavored juice and real kiwi fruit pieces shaken with ice. Deliciously refreshing.", 27000, R.drawable.kiwi_starfruit));
        foodVector.add(new Food("Pumpkin Spice Latte", "Our signature espresso and steamed milk with the celebrated flavor combination of pumpkin, cinnamon, nutmeg and clove. Enjoy it topped with whipped cream and real pumpkin-pie spices.", 35000, R.drawable.pumpkin_spice_latte));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_3, container, false);
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