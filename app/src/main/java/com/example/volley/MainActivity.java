package com.example.volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText ed;
static String query;
static ArrayList<String> myphoto,mytext;
RecyclerView lv;

static MainActivity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main=this;
        ed=findViewById(R.id.editText2);
        query=ed.getText().toString();
       lv=findViewById(R.id.lv);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }

//https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&text=tesla
    public void getphotos(View view) {

                Retrofit r = new Retrofit.Builder().baseUrl("https://api.flickr.com/services/").addConverterFactory(GsonConverterFactory.create()).build();
                PhotoInterface pi = r.create(PhotoInterface.class);

                retrofit2.Call<PhotosPojo> call = pi.getPhotos(ed.getText().toString());
                call.enqueue(new Callback<PhotosPojo>() {
                    @Override
                    public void onResponse(Call<PhotosPojo> call, Response<PhotosPojo> response) {
                        PhotosPojo p=response.body();
                       Photos c=p.getPhotos();
                        List<Photo> k=c.getPhoto();
                        myphoto = new ArrayList<>();
                        mytext=new ArrayList<>();
                        for(int i=0;i<k.size();i++){
                            myphoto.add("http://farm" + k.get(i).getFarm() + ".static.flickr.com/" + k.get(i).getServer() + "/" + k.get(i).getId() + "_" + k.get(i).getSecret() + ".jpg");
                          mytext.add( k.get(i).getTitle());
                           // Toast.makeText(getApplicationContext(),mytext[i],Toast.LENGTH_LONG).show();

                        }
                        RecyclerView.LayoutManager lm=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                    //      StaggeredGridLayoutManager sg=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                        lv.setLayoutManager(lm);
                        lv.setAdapter(new MyRecycleAdapter());
                        // to get gallery view use LinearLAYOUTmANGER.HORIZONTAL.
                        // for grid layout use
                        //  StaggeredGridLayoutManager sg=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

                    }

                    @Override
                    public void onFailure(Call<PhotosPojo> call, Throwable t) {

                    }
                });



            }

    public void mic(View view) {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"HI SPEAK SOMETHING....");
        try{
            startActivityForResult(intent,100);
        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                if(resultCode==RESULT_OK && null!=data){
                    ArrayList<String> al=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ed.setText(al.get(0));
                    StyleableToast.makeText(this, "Hello World!", R.style.exampleToast).show();

                    // TO USE CUSTOM TOAST

                    //   new StyleableToast
                    //                .Builder(context)
                    //                .text("Hello world!")
                    //                .textColor(Color.WHITE)
                    //                .backgroundColor(Color.BLUE)
                    //                .show();
                }
                break;
        }
    }
}




