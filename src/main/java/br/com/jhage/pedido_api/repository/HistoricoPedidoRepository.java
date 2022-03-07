package br.com.jhage.pedido_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jhage.pedido_api.modelo.HistoricoPedido;

/**
 * 
 * @author Alexsander Melo
 * @since 18/02/2018
 *
 */
public interface HistoricoPedidoRepository extends JpaRepository<HistoricoPedido, Long>{
	
	@Query("select h "
			 + "from HistoricoPedido h join h.pedido p "
			 + "where p.id =:id")
	public List<HistoricoPedido> historicoDoPedido(@Param("id") Long idpedido);
	
	@Query(value ="SELECT a.tipo, avg(a.tempodecorrido) media, max(a.tempodecorrido) maximo, min(a.tempodecorrido) minimo " + 
			"from (SELECT " + 
			"	h.pedido_id, " + 
			"	'Tempo p Ficar Pronto' tipo, " + 
			"	max(case when h.status = 'PRONTO' then h.ocorrencia end ) - " + 
			"	max(case when h.status = 'REALIZADO' then h.ocorrencia end) tempodecorrido " + 
			"FROM public.historico_pedido as h " + 
			"where h.status in ('REALIZADO', 'PRONTO') " + 
			"	and h.ocorrencia > (current_date -30) " + 
			"group by h.pedido_id " + 
			"having max(case when h.status = 'PRONTO' then h.ocorrencia end) is not null " + 
			"union all " + 
			"SELECT " + 
			"	h.pedido_id, " + 
			"	'Tempo p Entregar' tipo, " + 
			"	max(case when h.status = 'ENTREGUE' then h.ocorrencia end ) - " + 
			"	max(case when h.status = 'PRONTO' then h.ocorrencia end) tempodecorrido " + 
			"FROM public.historico_pedido as h " + 
			"where h.status in ('ENTREGUE', 'PRONTO') " + 
			"	and h.ocorrencia > (current_date -30) " + 
			"group by h.pedido_id " + 
			"having max(case when h.status = 'ENTREGUE' then h.ocorrencia end) is not null) as a " + 
			"group by a.tipo", 
		nativeQuery = true)
	public  List<Object> tempoPedido();
}
