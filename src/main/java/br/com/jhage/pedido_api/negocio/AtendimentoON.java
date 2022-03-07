package br.com.jhage.pedido_api.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jhage.pedido_api.constante.StatusPedido;
import br.com.jhage.pedido_api.excecao.PedidoException;
import br.com.jhage.pedido_api.helper.FormatDateHelper;
import br.com.jhage.pedido_api.modelo.HistoricoPedido;
import br.com.jhage.pedido_api.modelo.ItemPedido;
import br.com.jhage.pedido_api.modelo.Pedido;
import br.com.jhage.pedido_api.repository.HistoricoPedidoRepository;
import br.com.jhage.pedido_api.repository.ItemPedidoRepository;
import br.com.jhage.pedido_api.repository.PedidoRepository;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */

@Component
public class AtendimentoON {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ItemPedidoRepository itemRepository;
	
	@Autowired
	private HistoricoPedidoRepository historicorepository;
	
	private Pedido pedido;
	
	public Iterable<ItemPedido> carregarItensDoPedido(Long idPedido)  throws PedidoException{
		
		return itemRepository.carregarItensDoPedido(idPedido);
	}
	
	public List<Pedido> carregarPedidoDeHoje() throws PedidoException{
		
		List<Pedido> result = null;
		String hoje;
		hoje = FormatDateHelper.getInstance().converterDataParaCaracter(new Date());
		result = repository.carregarPedidoPorData(hoje);
		return result;
	}
	
	public List<Pedido> carregarPedidoCancelados() throws PedidoException{
		
		List<Pedido> result = null;
		String hoje;
		hoje = FormatDateHelper.getInstance().converterDataParaCaracter(new Date());
		result = repository.carregarPedidoCancelados(hoje);
		return result;
	}
	
	public Pedido salvar(Pedido pedido) {
		
		this.pedido = pedido;
		
		savarPedido();
		registrarHistorico();
		return this.pedido;
	}
	
	public Pedido salvar(long idpedido, StatusPedido status) throws PedidoException{
		
		carregarPedidoPorId(idpedido);
		this.pedido.setStatus(status);
		savarPedido();
		registrarHistorico();
		return this.pedido;
	}
	
	private void carregarPedidoPorId(Long idpedido) {
		
		this.pedido = repository.findById(idpedido).get();
	}
	
	private void savarPedido() {
		
		this.pedido = repository.save(this.pedido);
	}
	
	private void registrarHistorico() {
		
		HistoricoPedido hp = new HistoricoPedido(this.pedido);
		historicorepository.save(hp);
	}

}
