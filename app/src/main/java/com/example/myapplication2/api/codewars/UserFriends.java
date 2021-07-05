package com.example.myapplication2.api.codewars;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication2.ui.friends.CustomFriendsAdapter;
import com.example.myapplication2.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class UserFriends {
    private static MutableLiveData<List<User>> friends = new MutableLiveData<>();
    private static Comparator<User> userComparator = Comparator.comparing(User::getUsername);


    static {
        //TODO: read from file
        friends.setValue(new ArrayList<>());
    }


    private static void checkParams() {
        //TODO: implement
    }

    public static void tryToAddByUsername(String friendUsername) {
        if (userExist(friendUsername)) return;

        User user = CodewarsAPI.getUser(friendUsername);
        if (user != null) {
            addFriend(user);
        } else {
            Log.e("Error", "User don't exists");
        }
    }

    public static boolean userExist(String username) {
        boolean match = friends.getValue().stream().anyMatch(user -> user.getUsername().equals(username));
        return match;
    }

    public static void addFriend(User friend) {
        friends.getValue().add(friend);

        //TODO: Combine CustomFriendsAdapter and UserFriends
//        adapter.notifyDataSetChanged();

//        setMutableImage(friend);
        sortFriends();
    }

//    private static void setMutableImage(User user) {
////        User user = new User("Vlad-code-c", "name", 0, 0, "rankName", 0, "https://png.pngtree.com/png-vector/20191104/ourmid/pngtree-businessman-avatar-cartoon-style-png-image_1953664.jpg", "Score: 9999+");
//        try {
//            //Check if is on main thread
//            if (Looper.myLooper() != Looper.getMainLooper()) {
//                Looper.getMainLooper().getThread().join();
//            }
//            user.getMutableAvatar().observe(viewLifecycleOwner, new Observer<Bitmap>() {
//                @Override
//                public void onChanged(Bitmap imageView) {
//                    getFriends().getValue()
//                            .stream()
//                            .filter(user1 -> user1.getUsername().equals(user.getUsername()))
//                            .findFirst().get()
//                            .setAvatarImage(imageView);
//
//                    adapter.notifyDataSetChanged();
//                }
//            });
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void removeFriend(String username) {
        friends.getValue().removeIf(user -> user.getUsername().equals(username));
    }

    private static void sortFriends() {
        //TODO: Test and see why dont work correctly
//        friends.postValue((friends.getValue().stream()
//                .sorted(userComparator)
//                .collect(Collectors.toList())));
        friends.postValue(friends.getValue());
    }

    public static void setSortingCritery(SortBy sortBy) {
        switch (sortBy) {
            case USERNAME_ASC:
                userComparator = Comparator.comparing(User::getUsername);
                break;
            case USERNAME_DESC:
                userComparator = Comparator.comparing(User::getUsername).reversed();
                break;
            case HONOR_ASC:
                userComparator = Comparator.comparing(User::getHonor);
                break;
            case HONOR_DESC:
                userComparator = Comparator.comparing(User::getHonor).reversed();
                break;
            case LEADERBOARD_POSITION_ASC:
                userComparator = Comparator.comparing(User::getLeaderboardPosition);
                break;
            case LEADERBOARD_POSITION_DESC:
                userComparator = Comparator.comparing(User::getLeaderboardPosition).reversed();
                break;
            case RANK_NAME_ASC:
                userComparator = Comparator.comparing(User::getRankName);
                break;
            case RANK_NAME_DESC:
                userComparator = Comparator.comparing(User::getRankName).reversed();
                break;
            case TOTAL_COMPLETED_KATA_ASC:
                userComparator = Comparator.comparing(User::getTotalCompletedKata);
                break;
            case TOTAL_COMPLETED_KATA_DESC:
                userComparator = Comparator.comparing(User::getTotalCompletedKata).reversed();
                break;
        }
        sortFriends();
    }

    public static MutableLiveData<List<User>> getFriends() {
        return friends;
    }

}
