package com.example.guest.imago.services;

import android.util.Log;

import com.example.guest.imago.Constants;
import com.example.guest.imago.models.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.guest.imago.Constants.UNSPLASH_TOKEN;
public class UnsplashService {

    public ArrayList<Image> processResults(Response response) {
        ArrayList<Image> images = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject unsplashJSON = new JSONObject(jsonData);

            JSONArray businessesJSON = unsplashJSON.getJSONArray("images");
            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject imageJSON = businessesJSON.getJSONObject(i);
                String imageUrl = imageJSON.getString("imageURL");
                String imageName = imageJSON.getString("username");
                String photographerName = imageJSON.getString("profile_image");
                String photographerWebsite = imageJSON.getString("profile_url");
                String location = imageJSON.getString("location");
//                ArrayList<String> images = new ArrayList<>();
//                JSONArray photographsJSON = imageJSON.getJSONObject("photos")
//                        .getJSONArray("urls");
//                for (int y = 0; y < photographsJSON.length(); y++) {
//                    images.add(photographsJSON.get(y).toString());
//                }

                Image image = new Image(imageUrl, imageName, photographerName, photographerWebsite, location);
                images.add(image);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return images;
    }

    public static void findImages (String query, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.unsplash.com/search/photos").newBuilder();
        urlBuilder.addQueryParameter("client_id", "e3d57945b0b8f1b07a7136f70efc3f7038495d6f2f58a7e4daaeb54d97eac394");
        urlBuilder.addQueryParameter(Constants.UNSPLASH_QUERY_PARAMETER, query);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
