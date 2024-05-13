package com.example.Musicas.PostgreSQL.main;

import com.example.Musicas.PostgreSQL.model.Artista;
import com.example.Musicas.PostgreSQL.model.Musica;
import com.example.Musicas.PostgreSQL.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private boolean loop = true;
    private Scanner scanner = new Scanner(System.in);
    private ArtistaRepository repository;
    List<Artista> artistaEncontrado;
    String nomeArtista;

    public Main(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void run() {
        while (loop) {
            System.out.print("""
                
                1 - Cadastrar artista
                2 - Cadastrar música
                3 - Pesquisar músicas por artista
                
                9 - Limpar banco de dados
                0 - Sair (tabelas do DB são excluídas ao sair)
                
                Opção:\s""");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    loop = false;
                    break;
                case 1:
                    cadastraArtista();
                    break;
                case 2:
                    cadastraMusica();
                    break;
                case 3:
                    pesquisaMusicaPorArtista();
                    break;
                case 9:
                    limpaDB();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastraArtista() {
        if (artistaEncontrado != null) {
            Artista artista = new Artista();
            artista.setArtista(nomeArtista);
            repository.save(artista);
        } else {
            System.out.print("Nome do artista para cadastro: ");
            scanner.nextLine();
            nomeArtista = scanner.nextLine();
            Artista artista = new Artista();
            artista.setArtista(nomeArtista);
            repository.save(artista);
            artistaEncontrado = null;
        }
        System.out.println("Artista cadastrado!");
    }

    private void cadastraMusica() {
        System.out.print("Nome do artista para cadastrar a música: ");
        scanner.nextLine();
        nomeArtista = scanner.nextLine();
        artistaEncontrado = repository.findByArtistaContainingIgnoreCase(nomeArtista);
//        System.out.println(artistaEncontrado);
        if (artistaEncontrado.isEmpty()){
            cadastraArtista();
            artistaEncontrado = repository.findByArtistaContainingIgnoreCase(nomeArtista);
        }
        System.out.println("Artista selecionado: " + artistaEncontrado.get(0).getArtista());
        System.out.print("Nome da música para cadastro: ");
        String nomeMusica = scanner.nextLine();

        Musica musica = new Musica();
        musica.setMusica(nomeMusica);
//        List<Musica> musicaList = new ArrayList<>();
//        musicaList.add(musica);
        Artista artista = artistaEncontrado.get(0);
        musica.setArtista(artista);
        artista.getMusicaList().add(musica);
        repository.save(artista);
        artistaEncontrado = null;
        System.out.println("Música cadastrada!");
    }

    private void pesquisaMusicaPorArtista() {
        repository.findAll().forEach(System.out::println);
    }

    private void limpaDB() {
        System.out.println("Limpando banco de dados...");
        repository.deleteAll();
    }
}