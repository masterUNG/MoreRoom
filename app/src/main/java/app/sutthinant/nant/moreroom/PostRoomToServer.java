package app.sutthinant.nant.moreroom;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by NantHome on 8/19/2017.
 */

public class PostRoomToServer extends AsyncTask<String, Void, String>{
    private Context context;

    public PostRoomToServer(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", strings[0])
                    .add("Price", strings[1])
                    .add("Phone", strings[2])
                    .add("Image", strings[3])
                    .add("Option", strings[4])
                    .add("Lat", strings[5])
                    .add("Lng", strings[6])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[7]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("19AugV3", "e PostRoom==>" + e.toString());
            return null;
        }



    }
}  // class
