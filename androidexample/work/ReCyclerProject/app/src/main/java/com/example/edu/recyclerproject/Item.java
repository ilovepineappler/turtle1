package com.example.edu.recyclerproject;

/**
 * Created by user on 2017-10-06.
 */

public class Item {
    public int image;
    public String imagetitle;
    public int getImage() { return image; }
    public String getImagetitle() { return imagetitle; }
    public Item(int image, String imagetitle){
        this.image=image; this.imagetitle=imagetitle;
    }

}
