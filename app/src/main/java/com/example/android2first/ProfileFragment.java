package com.example.android2first;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android2first.Prefs.Prefs;

import java.util.Date;


public class ProfileFragment extends Fragment {
    TextView textView;
    ImageView imageView;
    String image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.text);
        imageView = view.findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p.launch("image/*");

            }
        });
        setFragmentListener();




        textView.setText( App.getPrefs().name());
    }

    ActivityResultLauncher<String> p = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            image = result.toString();
            Glide.with(getContext())
                    .load(result)
                    .centerCrop()
                    .circleCrop()
                    .into(imageView);


        }
    });

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener(
                "rk_edit",
                getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String text=result.getString("hi");
                        textView.setText(text);
                        App.getPrefs().saveName(text);
                    }
                });


    }

}