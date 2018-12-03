package ar.com.astun.momapp.Modelo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

@Entity
public class Paint implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String image;
    private String name;
    private String artistId;

    public Paint() {
    }

    @Ignore
    public Paint(Integer id,String image, String name, String artistId){
        this.id=id;
        this.image=image;
        this.name=name;
        this.artistId=artistId;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

}
