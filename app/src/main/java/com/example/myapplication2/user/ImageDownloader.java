package com.example.myapplication2.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask {
    private String url;
    private final String defaultUrl = "https://cdn.icon-icons.com/icons2/1736/PNG/512/4043260-avatar-male-man-portrait_113269.png";
//    private ImageView image;
    private MutableLiveData<Bitmap> mutableImage;
    private Bitmap bitmap;

    public ImageDownloader(String url, MutableLiveData<Bitmap> image) {
        this.url = url;
        this.mutableImage = image;
    }

    public ImageDownloader(MutableLiveData<Bitmap> image) {
        this.mutableImage = image;
        this.url = defaultUrl;
    }

    @Override
    protected Bitmap doInBackground(Object[] objects) {

        try {
            InputStream input = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    @Override
    protected void onPostExecute(Object o) {
        mutableImage.setValue(bitmap);
    }
}

