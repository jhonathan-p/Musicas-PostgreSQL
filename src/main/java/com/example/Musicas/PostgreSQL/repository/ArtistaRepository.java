package com.example.Musicas.PostgreSQL.repository;

import com.example.Musicas.PostgreSQL.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByArtistaContainingIgnoreCase(String nomeArtista);
}
