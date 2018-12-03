package ar.com.astun.momapp.Modelo;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ar.com.astun.momapp.DB.PaintRepositorio;

public class ContenedorPaint {
    private List<Paint> paints;

    public List<Paint> getPaints() {
        return paints;
    }

}
