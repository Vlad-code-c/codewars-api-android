package com.example.myapplication2.user;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

public class User {
    private String username;
    private String name;
    private int honor;
    private int leaderboardPosition;
    private String rankName;
    private int totalCompletedKata;

//    private MutableLiveData<Bitmap> mutableAvatar = new MutableLiveData<>();
//    private Bitmap avatarImage = null;
    private String customParam;

//    private String avatarUrl;

    public User() {
    }

    public User(String username, String name, int honor, int leaderboardPosition, String rankName, int totalCompletedKata, String customParam) {
        this.username = username;
        this.name = name;
        this.honor = honor;
        this.leaderboardPosition = leaderboardPosition;
        this.rankName = rankName;
        this.totalCompletedKata = totalCompletedKata;
//        this.avatarUrl = avatarUrl;
        this.customParam = customParam;

//        new ImageDownloader(avatarUrl, mutableAvatar).execute();
    }

    public User(String username, String name, int honor, int leaderboardPosition, String rankName, int totalCompletedKata) {
        this.username = username;
        this.name = name;
        this.honor = honor;
        this.leaderboardPosition = leaderboardPosition;
        this.rankName = rankName;
        this.totalCompletedKata = totalCompletedKata;
    }


//    public Bitmap getAvatarImage() {
//        return avatarImage;
//    }
//
//    public void setAvatarImage(Bitmap avatarImage) {
//        this.avatarImage = avatarImage;
//    }

    public String getCustomParam() {
        return customParam;
    }

    public void setCustomParam(String customParam) {
        this.customParam = customParam;
    }

//    public MutableLiveData<Bitmap> getMutableAvatar() {
//        return mutableAvatar;
//    }
//
//    public void setMutableAvatar(MutableLiveData<Bitmap> mutableAvatar) {
//        this.mutableAvatar = mutableAvatar;
//    }

//    public String getAvatarUrl() {
//        return avatarUrl;
//    }

//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
////        new ImageDownloader(avatarUrl, mutableAvatar).execute();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public int getLeaderboardPosition() {
        return leaderboardPosition;
    }

    public void setLeaderboardPosition(int leaderboardPosition) {
        this.leaderboardPosition = leaderboardPosition;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public int getTotalCompletedKata() {
        return totalCompletedKata;
    }

    public void setTotalCompletedKata(int totalCompletedKata) {
        this.totalCompletedKata = totalCompletedKata;
    }

    public HashMap<String, String> getHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("name", name);
        map.put("honor", String.valueOf(honor));
        map.put("leaderboardPosition", String.valueOf(leaderboardPosition));
        map.put("rankName", rankName);
        map.put("totalCompletedKata", String.valueOf(totalCompletedKata));

        return map;
    }
}
