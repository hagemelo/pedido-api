package br.com.jhage.pedido_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jhage.pedido_api.modelo.Pedido;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("select p " +
		   "from Pedido p "+
		   "where to_char(p.cadastro, 'dd/MM/yyyy') = :hoje " +
		    	  "and not p.status like 'CANCELADO' " +
		   "order by p.cadastro desc")
	public List<Pedido> carregarPedidoPorData(@Param("hoje") String data);
	
	@Query("select p "
					  + "from Pedido p "
					  + "where to_char(p.cadastro, 'dd/MM/yyyy') = :hoje "
					  + "and p.status like 'CANCELADO'"
					  + " order by p.cadastro desc")
	public List<Pedido> carregarPedidoCancelados(@Param("hoje") String data);
	
	
	@Query(value = "select to_char(pe.cadastro, 'dd/MM/yyyy') dia, pe.q quantidade, pe.t total "+
				   "from (	select "+
							"p.cadastro ,  "+
							"count(p.pedido_id) q,  "+
							"round(cast(sum(i.quantidade * i.valor) as decimal)) t  "+
						"from Pedido p  "+
						     "join item_pedido i on (i.pedido_pedido_id = p.pedido_id)  "+
						"where not p.status like 'CANCELADO'  "+
						"group by 1   "+
						"order by p.cadastro desc) as pe", 
		   nativeQuery = true)
	public List<Object> vendasdia();
	
	
	@Query(value = "Select " + 
					"	i.descricao item, " + 
					"	sum(i.quantidade) quantidade, " + 
					"	round(cast(count(i.itempedido_id)as decimal)/cast(max(tudo.qtdtotal)as decimal),2)  Percentual, " + 
					"	round(cast(sum(i.quantidade * i.valor) as decimal)) total " + 
					"from Pedido p  " + 
					"	join item_pedido i on (i.pedido_pedido_id = p.pedido_id) " + 
					"	join (Select to_char(pp.cadastro, 'dd/MM/yyyy') datas,  " + 
					"				 count(ii.itempedido_id) qtdtotal " + 
							 "from Pedido pp  " + 
							 	  "join item_pedido ii on (ii.pedido_pedido_id = pp.pedido_id)  " + 
							 "where  " + 
							 "not pp.status like 'CANCELADO' group by 1) as tudo on (tudo.datas = to_char(p.cadastro, 'dd/MM/yyyy')) " + 
				
					"where to_char(p.cadastro, 'dd-MM-yyyy') = :hoje " + 
					"	and not p.status like 'CANCELADO' " + 
					"group by i.descricao",
			nativeQuery = true)
	public List<Object> itenspedidosdia(@Param("hoje") String data);
}
