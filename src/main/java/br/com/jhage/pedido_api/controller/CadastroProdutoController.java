package br.com.jhage.pedido_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.pedido_api.modelo.Produto;
import br.com.jhage.pedido_api.repository.ProdutoRepository;

/**
 * 
 * @author Alexsander Melo
 * @since 05/02/2018
 *
 */
@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3000)
@RequestMapping("/cadastro/produto")
@RestController
public class CadastroProdutoController extends DefaultController{
	
	@Autowired
	private ProdutoRepository repository;
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Produto> getAllUsers() {

		return repository.findAll();
	}
	
	

}
