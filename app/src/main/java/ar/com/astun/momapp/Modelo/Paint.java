package ar.com.astun.momapp.Modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Paint {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String image;
    private String name;
    private String artistId;

    @Ignore
    public Paint() {
    }

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

}
