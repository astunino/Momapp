package ar.com.astun.momapp.Modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Artist  {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String influencedBy;
    private String artistId;
    private String name;
    private String nationality;

    @Ignore
    public Artist() {
    }

    public Artist(Integer id,String influencedBy, String artistId, String name, String nationality) {
        this.id=id;
        this.influencedBy = influencedBy;
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
    }

    public String getInfluencedBy() {
        return influencedBy;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public Integer getId() {
        return id;
    }
}