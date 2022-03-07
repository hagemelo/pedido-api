package br.com.jhage.pedido_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_api.helper.NumberHelp;

/**
 * 
 * @author Alexsander Melo
 * @since 10/03/2018
 *
 */

public class VendaDTO {
	
	String dia;
	Integer quantidade;
	Double venda;
	
	VendaDTO(){}
	
	public VendaDTO(Object[] ob){
		
		dia = ob[0].toString();
		quantidade = new Integer(ob[1].toString());
		venda = new Double(ob[2].toString());
	}

	public String getDia() {
		return dia;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	@JsonProperty
	public String getVendaToString() {
		
		return NumberHelp.parseDoubleToString(this.venda);
	}
	
	public Double getVenda() {
		return venda;
	}
	
}
