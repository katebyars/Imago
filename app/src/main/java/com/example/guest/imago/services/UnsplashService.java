
import android.util.Log;
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
        ArrayList<Image> photographers = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject unsplashJSON = new JSONObject(jsonData);

            JSONArray businessesJSON = unsplashJSON.getJSONArray("photographers");
            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject photographerJSON = businessesJSON.getJSONObject(i);
                String name = photographerJSON.getString("name");
                String username = photographerJSON.getString("username");
                String profile_image = photographerJSON.getString("profile_image");
                String profile_url = photographerJSON.getString("profile_url");
                String location = photographerJSON.getString("location");
                ArrayList<String> photographs = new ArrayList<>();
                JSONArray photographsJSON = photographerJSON.getJSONObject("photos")
                        .getJSONArray("urls");
                for (int y = 0; y < photographsJSON.length(); y++) {
                    photographs.add(photographsJSON.get(y).toString());
                }

                Image images = new Image(name, username, profile_url, photographs, profile_image, location);
                photographers.add(images);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return photographers;
    }


    public static void findPhotographers (String query, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.unsplash.com/search/users").newBuilder();
        urlBuilder.addQueryParameter("client_id", "e3d57945b0b8f1b07a7136f70efc3f7038495d6f2f58a7e4daaeb54d97eac394");
        urlBuilder.addQueryParameter(Constants.UNSPLASH_LOCATION_QUERY_PARAMETER, query);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
