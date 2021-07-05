package com.example.myapplication2.ui.profile;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication2.api.codewars.CodewarsAPI;
import com.example.myapplication2.databinding.ProfileFragmentBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private ProfileFragmentBinding binding;


    private ArrayAdapter<String> adapter;
    private ArrayList<String> propsArray;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = ProfileFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView txtName = binding.txtUsername;
        profileViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtName.setText(s);
            }
        });


        propsArray = profileViewModel.getProperties();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, propsArray);
        binding.listProperties.setAdapter(adapter);

        profileViewModel.getMutableProperties().observe(getViewLifecycleOwner(), new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> propertiesMap) {
                propsArray.clear();
                propsArray.addAll(profileViewModel.getProperties());
                Collections.reverse(propsArray);
                adapter.notifyDataSetChanged();
            }
        });

        new Thread(() -> {
            HashMap<String, String> userInfo = CodewarsAPI.getUserInfo("Vlad-code-c");
            if (userInfo.containsKey("name")) profileViewModel.postName(userInfo.get("name"));
            profileViewModel.postProperties(userInfo);
        }).start();

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


}