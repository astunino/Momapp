package ar.com.astun.momapp.Modelo;

public class Chat {

    private String nombre;
    private String chat;

    public Chat(){

    }

    public Chat(String nombre,String chat){
        this.nombre=nombre;
        this.chat=chat;
    }

    public String getNombre() {
        return nombre;
    }

    public String getChat() {
        return chat;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
