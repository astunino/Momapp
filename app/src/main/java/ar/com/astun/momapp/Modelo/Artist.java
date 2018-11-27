package ar.com.astun.momapp.Modelo;

import com.google.gson.annotations.SerializedName;

public class Artist {

    private String influencedBy;
    private String artistId;
    private String name;
    private String nationality;

    public Artist() {
    }

    public Artist(String influencedBy, String artistId, String name, String nationality) {
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
}
