package com.example.cafelegend.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cafelegend.Fragment1;
import com.example.cafelegend.Fragment2;
import com.example.cafelegend.Fragment3;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new Fragment1();
            case 1: return new Fragment2();
            case 2: return new Fragment3();
        }
        return new Fragment1();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
