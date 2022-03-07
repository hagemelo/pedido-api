package br.com.jhage.pedido_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.pedido_api.dto.ItemVendaDto;
import br.com.jhage.pedido_api.dto.TempoPedidoDto;
import br.com.jhage.pedido_api.dto.VendaDTO;
import br.com.jhage.pedido_api.excecao.PedidoException;
import br.com.jhage.pedido_api.negocio.RelatorioON;

@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3000)
@RequestMapping("/relatorio")
@RestController
public class RelatorioController extends DefaultController{
	
	@Autowired
	private RelatorioON on;
	
	@GetMapping(path= "/vendasdia")
	public @ResponseBody Iterable<VendaDTO> vendasDia()  throws PedidoException{

		return on.vendasDia();
	}
	
	@GetMapping(path= "/itenspedidodia/{datapedido}")
	public @ResponseBody Iterable<ItemVendaDto> itensPedidosDia(@PathVariable("datapedido") String dataPedido)  throws PedidoException{

		return on.itenspedidosdia(dataPedido);
	}
	
	
	@GetMapping(path= "/tempopedido")
	public @ResponseBody Iterable<TempoPedidoDto> itensPedidosDia()  throws PedidoException{

		return on.tempoDoPedido();
	}
}
