package com.example.myapplication2.ui.friends;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.user.User;
import com.example.myapplication2.api.codewars.UserFriends;
import com.example.myapplication2.databinding.FriendsFragmentBinding;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FriendsFragment extends Fragment {

    private FriendsViewModel friendsViewModel;
    private FriendsFragmentBinding binding;

//    private static ;

    private AlertDialog.Builder alertDialog;
    private View dialogLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        friendsViewModel = new ViewModelProvider(this).get(FriendsViewModel.class);

        binding = FriendsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Init UserFriends list
        ListView list = binding.listFriends;
        //Init new adapter, for binding custom row into list
        CustomFriendsAdapter adapter = new CustomFriendsAdapter(getContext(), R.layout.friend_item_layout, UserFriends.getFriends().getValue());
        list.setAdapter(adapter);

        UserFriends.getFriends().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.notifyDataSetChanged();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = (User) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), selectedUser.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

//        UserFriends.initUserFriendsList(getViewLifecycleOwner(), adapter);


        //Init AlertDialog
        alertDialog = new AlertDialog.Builder(getContext());
        dialogLayout = inflater.inflate(R.layout.friend_add_dialog_box_layout, null);
        alertDialog.setView(dialogLayout);

        initAlertDialog();


        return root;
    }

    private void initAlertDialog() {

        AlertDialog alert = alertDialog.create();

        Button btnAdd = dialogLayout.findViewById(R.id.btn_add);
        Button btnCancel = dialogLayout.findViewById(R.id.btn_cancel);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() ->
                        UserFriends.tryToAddByUsername(((EditText) dialogLayout.findViewById(R.id.editTextUsername)).getText().toString())).start();
                alert.cancel();
            }
        });

        btnCancel.setOnClickListener(v -> alert.cancel());


        View fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        //Showing alert Box
        fab.setOnClickListener(v -> alert.show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}