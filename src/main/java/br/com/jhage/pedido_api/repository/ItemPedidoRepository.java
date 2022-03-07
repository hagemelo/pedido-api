package br.com.jhage.pedido_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jhage.pedido_api.excecao.PedidoException;
import br.com.jhage.pedido_api.modelo.ItemPedido;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
	
	
	@Query("select i "
			  + "from ItemPedido i join i.pedido p "
			  + "where p.id =:idPedido")
	public List<ItemPedido> carregarItensDoPedido(@Param("idPedido") Long idPedido)  throws PedidoException;
	

}
