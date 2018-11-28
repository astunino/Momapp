package ar.com.astun.momapp.Modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Paint implements Serializable {

    private String image;
    @PrimaryKey(autoGenerate = false)
    private String name;
    private String artistId;

    public Paint() {
    }

    public Paint(String image, String name, String artistId){
        this.image=image;
        this.name=name;
        this.artistId=artistId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
