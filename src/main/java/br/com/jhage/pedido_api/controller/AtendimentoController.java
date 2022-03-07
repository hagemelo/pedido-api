package br.com.jhage.pedido_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.pedido_api.constante.StatusPedido;
import br.com.jhage.pedido_api.excecao.PedidoException;
import br.com.jhage.pedido_api.modelo.ItemPedido;
import br.com.jhage.pedido_api.modelo.Pedido;
import br.com.jhage.pedido_api.negocio.AtendimentoON;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */
@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3000)
@RequestMapping("/atendimento")
@RestController
public class AtendimentoController extends DefaultController{
	
	@Autowired
	private AtendimentoON on;
	
	@GetMapping(path= "/pedidosdodia")
	public @ResponseBody List<Pedido> carregarPedidosDodia()  throws PedidoException{

		return on.carregarPedidoDeHoje();
	}
	
	@GetMapping(path= "/pedidoscancelados")
	public @ResponseBody List<Pedido> carregarPedidosCancelados()  throws PedidoException{

		return on.carregarPedidoCancelados();
	}
	
	@PostMapping
	@Transactional
	public @ResponseBody Pedido registrarPedido(@RequestBody Pedido pedido) {

		return on.salvar(pedido);
	}
	
	@GetMapping(path= "/itensdopedido/{id}")
	public @ResponseBody Iterable<ItemPedido> carregarItensDoPedido(@PathVariable("id") Long idPedido)  throws PedidoException{

		return on.carregarItensDoPedido(idPedido) ;
	}

	@PostMapping(path= "/pedidopronto/{id}")
	@Transactional
	public @ResponseBody Pedido pedidoPronto(@PathVariable("id") Long idPedido) throws PedidoException{

		return on.salvar(idPedido, StatusPedido.PRONTO);
	}
	
	@PostMapping(path= "/pedidoentregue/{id}")
	@Transactional
	public @ResponseBody Pedido pedidoEntregue(@PathVariable("id") Long idPedido) throws PedidoException{

		return on.salvar(idPedido, StatusPedido.ENTREGUE);
	}
	
	@DeleteMapping(path= "/pedidocancelado/{id}")
	@Transactional
	public @ResponseBody Pedido pedidoCancelado(@PathVariable("id") Long idPedido) throws PedidoException{

		return on.salvar(idPedido, StatusPedido.CANCELADO);
	}
	
}
