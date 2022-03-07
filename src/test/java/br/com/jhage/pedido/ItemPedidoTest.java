package br.com.jhage.pedido;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.jhage.pedido_api.modelo.ItemPedido;

@Ignore
public class ItemPedidoTest {
	
	private final static Double VALOR_DEFAULT = 2.8;
	private final static Double ZERO = 0.;
	
	@Test
	public void totalItemPedidoIgualaZero(){
		
		Assert.assertEquals(ZERO, new ItemPedido(null, null, null).total());
	}
	
	@Test
	public void totalItemPedidoIgualaValorDefaultCenarioFeliz(){
		
		Assert.assertEquals(VALOR_DEFAULT, new ItemPedido("Teste", 1.4, 2).total());
	}
	
	@Test
	public void totalItemPedidoIgualaValorDefaultComQtdNull(){
		
		Assert.assertEquals(VALOR_DEFAULT, new ItemPedido("Teste", 2.8, null).total());
	}
	
	@Test
	public void totalItemPedidoIgualaValorDefaultComValorNull(){
		
		Assert.assertEquals(ZERO, new ItemPedido("Teste", null, 2).total());
	}
	
}
