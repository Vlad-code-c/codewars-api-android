package com.example.myapplication2.ui.profile;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<String> username = new MutableLiveData<String>();
    private MutableLiveData<HashMap<String, String>> properties = new MutableLiveData<HashMap<String, String>>();

    public ProfileViewModel() {
        username.setValue("Unknown");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Value", "Undefined");
        properties.setValue(map);
    }

    public MutableLiveData<HashMap<String, String>> getMutableProperties() {
        return properties;
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<String> getProperties() {
        ArrayList<String> props = new ArrayList<String>();
        properties.getValue().forEach((key, value) -> {
            props.add(key + " - " + value);
        });

        return props;
    }

    public void postProperties(HashMap<String, String> properties) {
        this.properties.postValue(properties);
    }

    public void postName(String name) {
        this.username.postValue(name);
    }
}