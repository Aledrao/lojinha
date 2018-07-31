package br.com.asas.lojinha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.asas.lojinha.entity"})
@EnableJpaRepositories(basePackages = {"br.com.asas.lojinha.repository"})
@ComponentScan(basePackages = {"br.com.asas.lojinha.controller", "br.com.asas.lojinha.service"})
public class LojinhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}
}
