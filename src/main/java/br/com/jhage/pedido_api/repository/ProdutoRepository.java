package br.com.jhage.pedido_api.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jhage.pedido_api.modelo.Produto;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */

public interface ProdutoRepository extends CrudRepository<Produto, Long>{
	
	

}
