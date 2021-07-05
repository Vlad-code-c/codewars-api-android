package com.example.myapplication2.api.codewars;

import com.example.myapplication2.user.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;


import org.json.JSONException;
import org.json.JSONObject;

public class CodewarsAPI {
    private static String URL_USER = "https://www.codewars.com/api/v1/users/";


    public static User getUser(String username) {
        try {
            URL url = new URL(URL_USER + username);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                return null;
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                JSONObject jsonObject = new JSONObject(inline);

                User user = new User();
                user.setUsername(jsonObject.getString("username"));
                user.setName(jsonObject.getString("name"));
                user.setHonor(jsonObject.getInt("honor"));
                user.setLeaderboardPosition(jsonObject.getInt("leaderboardPosition"));
                user.setRankName(jsonObject.getJSONObject("ranks").getJSONObject("overall").getString("name"));
                user.setTotalCompletedKata(jsonObject.getJSONObject("codeChallenges").getInt("totalCompleted"));

                return user;
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static HashMap<String, String> getUserInfo(String username) {
        User user = getUser(username);
        if (user == null) return new HashMap<>();
        else return user.getHashMap();
    }

    public boolean checkIfExists(String username) {
        return true;
    }

}
