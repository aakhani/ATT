package com.att;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.att.helper.EndlessRecyclerOnScrollListener;
import com.att.model.Post;
import com.att.model.Test;
import com.att.rest.ATTClient;
import com.att.rest.ATTWs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements Callback<ResponseBody> {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private Test test;
    ArrayList<Post> postList = new ArrayList<Post>();
    private ATTWs attWsAPI;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        setUpListener();
        loadData();
    }


    private void setUpUI() {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PostAdapter(postList,R.layout.rowlayout_post,this);
        recyclerView.setAdapter(adapter);
    }

    private void setUpListener() {

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(
                linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page<=test.getTotalPages()) {
                    Log.e("Current Page", current_page+"");
                    loadMorePage(current_page);
                }
                else {
                    Toast.makeText(MainActivity.this, "No More Data!", Toast.LENGTH_SHORT).show();
                }
                }

        });
    }


    private void loadData() {
        attWsAPI = ATTClient.getClient().create(ATTWs.class);
        Call<ResponseBody> call = attWsAPI.loadJson();
        call.enqueue(this);
    }


    private void loadMorePage(int currentpage) {
        Call<ResponseBody> call = attWsAPI.loadMorepages(currentpage);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseJson(json);

        adapter.notifyDataSetChanged();
    }

    private void parseJson(String json) {

        test = new Test();
        try {
            JSONObject obj = new JSONObject(json);

            int totalPage = obj.getInt("total_pages");
            int page = obj.getInt("page");

            JSONArray array = obj.getJSONArray("posts");
            Log.e("Array Size",array.length()+"" );
            for (int i = 0;i<array.length();i++){
                Post post = new Post();


                JSONObject jobj = array.getJSONObject(i);

                if (jobj.has("id")) {
                    Integer id = jobj.getInt("id");
                    post.setId(id);
                    Log.e("ID "+i,id+"" );
                }
                if (jobj.has("user_name")){
                    String userName=jobj.getString("user_name");
                    post.setUserName(userName);
                }
                if (jobj.has("user_id")){
                    String userId=jobj.getString("user_id");
                    post.setUserId(userId);
                }
                if (jobj.has("user_pic")) {
                    String userPic = jobj.getString("user_pic");
                    post.setUserPic(userPic);
                }
                if (jobj.has("message")){
                    String message=jobj.getString("message");
                    post.setMessage(message);
                }
                if (jobj.has("photo")){
                    String photo=jobj.getString("photo");;
                    post.setPhoto(photo);
                }
                if (jobj.has("date")){
                    String date=jobj.getString("date");;
                    post.setDate(date);
                }

                postList.add(post);

            }

            test.setPage(page);
            test.setTotalPages(totalPage);
            test.setPosts(postList);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e("Responce","Failure");
    }


}
