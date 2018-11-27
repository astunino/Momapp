package ar.com.astun.momapp.Modelo;

public class Paint {

    private String image;
    private String name;
    private String artistId;

    public Paint(String image,String name,String artistId){
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

}
