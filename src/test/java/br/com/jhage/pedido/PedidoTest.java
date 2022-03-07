package br.com.jhage.pedido;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jhage.pedido_api.modelo.ItemPedido;
import br.com.jhage.pedido_api.modelo.Pedido;

@Ignore
public class PedidoTest {

	private static final Double VALOR_DEFAUL = 5.6;
	
	@Mock
	private ItemPedido itemmok;
	
	@Mock
	private Pedido pedidomok;
	
	@Before
	public void before(){
		
		MockitoAnnotations.initMocks(this);
		pedidomok.getItens().add(itemmok);
	}
	
	@Test
	public void TotalPedidoDeveSerZero(){
		
		Double zero = 0.;
//		Assert.assertTrue(Double.compare(zero, buildPedidoZero().total()) ==0);
	}
	
	@Test
	public void naoDevehaverNullnosCampos(){
		
		Pedido ped = new Pedido(null, null, null, null);
		Assert.assertNotNull(ped.getCadastro());
		Assert.assertNotNull(ped.getContato());
		Assert.assertNotNull(ped.getItens());
		Assert.assertNotNull(ped.getId());
		Assert.assertNotNull(ped.getStatus());
		Assert.assertNotNull(ped.getTroco());
		Assert.assertNotNull(ped.getDesconto());
		Assert.assertNotNull(ped.getEntrega());
	}
	
	@Test
	public void totalPedidoDeverSerIgualAoValorDefaulPedidoModelo1(){
		
		Pedido pedidoModelo1 = new Pedido("", "", 90.9, 0.0);
		pedidoModelo1.addItemPedido(new ItemPedido("Teste", 1.4, 2));
		pedidoModelo1.addItemPedido(new ItemPedido("Teste", 2.8, null));
		Assert.assertEquals(VALOR_DEFAUL, pedidoModelo1.total());
	}
	
	@Test
	public void totalPedidoDeverSerIgualAoValorDefaulPedidoModelo2(){
		
		Pedido pedidoModelo2 = new Pedido("", "", 90.9, 0.0);
		pedidoModelo2.addItemPedido(new ItemPedido("Teste", 2.8, 1));
		pedidoModelo2.addItemPedido(null);
		pedidoModelo2.addItemPedido(new ItemPedido("Teste", 2.8, 1));
		Assert.assertEquals(VALOR_DEFAUL, pedidoModelo2.total());
	}
	
//	@Test
//	public void testarMokito(){
//		
//		when(itemmok.getValor()).thenReturn(2.8);
//		when(itemmok.getQuantidade()).thenReturn(2);
//		System.out.println("Valor pedimok::" + itemmok.getValor());
//		
//		Assert.assertEquals(pedidomok.total(), new Double(5.6));
//		
//		Assert.assertNotNull(pedidomok);
//	}
	
	private Pedido buildPedidoZero(){
		
		return new Pedido(null, null, null, null);
	}
}
