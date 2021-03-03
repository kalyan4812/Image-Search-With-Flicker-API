package com.example.volley;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PhotoInterface {

        @GET("rest/?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=acf2ba45adda50d371aec7af22128aa3")
        Call<PhotosPojo> getPhotos (@Query("text") String text);

}

