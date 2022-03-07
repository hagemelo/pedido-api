package br.com.jhage.pedido;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.jhage.pedido_api.PedidoApplication;
import br.com.jhage.pedido_api.excecao.PedidoException;
import br.com.jhage.pedido_api.helper.FormatDateHelper;
import br.com.jhage.pedido_api.helper.Helper;
import br.com.jhage.pedido_api.modelo.ItemPedido;
import br.com.jhage.pedido_api.modelo.Pedido;
import br.com.jhage.pedido_api.repository.PedidoRepository;

/**
 * 
 * @author Alexsander Melo
 * @since 13/02/2018
 *
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PedidoApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoRepositoryTest {

	@Autowired
	private PedidoRepository repository;
	
	private Long LONG_ZERO = new Long(0);
	
	@Before
	public void deveSalvarPedidoComItemPedido(){
		
		Pedido teste = new Pedido("9898989898", "apt 344 bl3", 3.0, 0.0);
		teste.addItemPedido(new ItemPedido("Teste", 2.8, 1).comPedido(teste));
		teste.addItemPedido(new ItemPedido("Teste", 2.8, 1).comPedido(teste));
		Pedido pedidoto  = repository.save(teste);
		assertTrue(pedidoto.getId() > LONG_ZERO);
		assertTrue(!Helper.ENULO.test(pedidoto));
		assertTrue(!Helper.ENULO.enulo(pedidoto.getItens()));
		assertTrue(!pedidoto.getItens().isEmpty());
	}
	
	@Test
	public void deveListarItemPedido(){
		
		String hoje = "";
		try {
			hoje = FormatDateHelper.getInstance().converterDataParaCaracter(new Date());
			List<Pedido> pedidos = new ArrayList<Pedido>(repository.carregarPedidoPorData(hoje));
			assertTrue(!pedidos.isEmpty());
		} catch (PedidoException e) {
			e.printStackTrace();
		}
	}
	
}
