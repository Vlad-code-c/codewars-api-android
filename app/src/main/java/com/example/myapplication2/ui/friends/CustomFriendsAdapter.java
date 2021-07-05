package com.example.myapplication2.ui.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.user.User;

import java.util.List;

public class CustomFriendsAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflater;
    private int layout;
    private List<User> users;

    public CustomFriendsAdapter(Context context, int resource, List<User> users) {
        super(context, resource, users);
        this.layout = resource;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }


//
//    @Override
//    public void onClick(View v) {
//        int position = lastPosition;
//        Object object = getItem(position);
//        User user = (User) object;
//
//        if (v.getId() == R.id.img_more) {
//            //TODO: do this :)
//        } else {
//            //TODO: Open new Activity with user info
//            Toast.makeText(mContext, user.getUsername(), Toast.LENGTH_SHORT).show();
//        }
//    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        TextView name = view.findViewById(R.id.txt_name);
        ImageView avatar = view.findViewById(R.id.img_avatar);
        TextView param = view.findViewById(R.id.txt_paramer);
//        ImageView more = convertView.findViewById(R.id.img_more);

        User user = users.get(position);

        name.setText(user.getName());
        //TODO: Change for filter setup
        param.setText(user.getCustomParam());
        //TODO: set to user random image
//        avatar.setImageBitmap(user.getAvatar());

        return view;
    }
}
