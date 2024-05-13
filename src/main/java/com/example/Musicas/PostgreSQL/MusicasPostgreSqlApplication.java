package com.example.Musicas.PostgreSQL;

import com.example.Musicas.PostgreSQL.main.Main;
import com.example.Musicas.PostgreSQL.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicasPostgreSqlApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MusicasPostgreSqlApplication.class, args);
	}
	@Autowired
	ArtistaRepository repository;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.run();
	}
}
