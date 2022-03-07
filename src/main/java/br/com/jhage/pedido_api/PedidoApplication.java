package br.com.jhage.pedido_api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Alexsander Melo
 * @since jan/2018
 *
 */

@SpringBootApplication
public class PedidoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args).close();
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
			
		System.in.read();
	}



}
