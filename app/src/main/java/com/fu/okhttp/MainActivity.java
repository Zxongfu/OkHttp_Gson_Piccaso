package com.fu.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private BookGson mData;
    private CustomListAdapter mListAdapter;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButton =findViewById(R.id.btn);
        mListView =findViewById(R.id.personlist);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/11/14   GetWebservice

                OkHttpClient okHttpClient =new OkHttpClient();

                final Request request = new Request.Builder()
                        .url("https://reqres.in/api/users?page=2")
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("internet","Fail");
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            final String responseString = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // TODO: 2018/11/14 運用Gson解析Json
                                    Gson gson = new Gson();
                                    mData =gson.fromJson(responseString,BookGson.class);
                                    mListAdapter =new CustomListAdapter(MainActivity.this,mData.getData());
                                    mListView.setAdapter(mListAdapter);

                                }
                            });
                        }
                    }
                });
            }
        });

    }
}
