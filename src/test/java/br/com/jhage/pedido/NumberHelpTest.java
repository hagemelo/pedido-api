package br.com.jhage.pedido;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.jhage.pedido_api.helper.NumberHelp;

@Ignore
public class NumberHelpTest {
	
	
	@Test
	public void deverConverterDoubletoString() {
		
		Double db = 2.3;
		String valor = NumberHelp.parseDoubleToString(db);
		System.out.println("valor::" + valor);
		
		
		double amount = 2000000;    
		System.out.println(String.format("%,.2f", db));    
				
		Assert.assertTrue("2,30".equals(valor));
	}

}
