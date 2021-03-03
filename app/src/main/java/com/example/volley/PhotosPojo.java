
package com.example.volley;

import com.google.gson.annotations.SerializedName;

public class PhotosPojo {


    @SerializedName("photos")
    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }



}
