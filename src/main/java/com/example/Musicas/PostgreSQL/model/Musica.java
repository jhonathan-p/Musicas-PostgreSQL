package com.example.Musicas.PostgreSQL.model;

import jakarta.persistence.*;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String musica;

    @ManyToOne
    private Artista artista;

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "musica=" + musica;
    }
}
