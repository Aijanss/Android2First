package com.example.android2first.Board;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android2first.App;
import com.example.android2first.Note;
import com.example.android2first.OnItemClickListener;
import com.example.android2first.Prefs.Prefs;
import com.example.android2first.R;

public class BordFragment extends Fragment {
    private Button skipButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bord, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager=view.findViewById(R.id.viewPager);
        BordAdapter adapter=new BordAdapter();
        viewPager.setAdapter(adapter);
        skipButton=view.findViewById(R.id.skip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                       new OnBackPressedCallback(true) {
                           @Override
                           public void handleOnBackPressed() {
                               requireActivity().finish();

                           }
                       }
                );

                close();

            }

            @Override
            public void longClick(int position, Note note) {

            }
        });

    }


    private void close() {
        App.getPrefs().saveIsShown();
        NavController navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();

    }
    private void skip() {
        NavController navController= Navigation.findNavController(requireActivity(),R.id.page_board);
        navController.navigateUp();
    }


}