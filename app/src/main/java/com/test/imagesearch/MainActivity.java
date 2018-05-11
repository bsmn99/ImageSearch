package com.test.imagesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText editText;
    private Gson gson = new Gson();
    private MyAdapter myAdapter;
    String URL = "https://en.wikipedia.org/w/api.php? action=query&prop=pageimages&format=json&piprop=thumbnail&pithumbsize=50& pilimit=50&generator=prefixsearch&gpssearch={term}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        editText = (EditText) findViewById(R.id.editText);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        myAdapter = new MyAdapter();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equalsIgnoreCase("")) {
                    getData(s.toString());
                } else {
                    if(myAdapter!=null) {
                        myAdapter.clearData();
                    }
                }
            }
        });
    }

    public void getData(String term) {
        AndroidNetworking.get(URL)
                .addPathParameter("term", term)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Response",response.toString());

                        parseResponse(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void parseResponse(JSONObject response) {

        QueryData queryData  = gson.fromJson(response.toString(),
                                        new TypeToken<QueryData>(){}.getType());
        ArrayList<Data> dataArrayList = new ArrayList<>();
        for ( Map.Entry<String, Data> entry : queryData.getQuery().getPages().entrySet()) {
            String key = entry.getKey();
            Data data = entry.getValue();
            dataArrayList.add(data);
        }
        myAdapter.setDataForAdapter(dataArrayList);
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }
}
