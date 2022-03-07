package br.com.jhage.pedido_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_api.helper.NumberHelp;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */


public class ItemVendaDto {

	private String item;
	private Integer quantidade;
	private Double percentual;
	private Double total;
	private Double desconto;
	
	private final int POSICAO_ITEM = 0; 
	private final int POSICAO_QUANTIDADE = 1; 
	private final int POSICAO_PERCENTUAL = 2; 
	private final int POSICAO_TOTAL = 3; 
	private final int POSICAO_DESCONTO = 4; 
	
	ItemVendaDto(){}
	
	public ItemVendaDto(Object[] ob) {
		
		item 		= ob[POSICAO_ITEM].toString();
		quantidade 	= new Integer(ob[POSICAO_QUANTIDADE].toString());
		percentual 	= new Double(ob[POSICAO_PERCENTUAL].toString());
		total 		= new Double(ob[POSICAO_TOTAL].toString());
		desconto	= new Double(ob[POSICAO_DESCONTO].toString());
	}

	public String getItem() {
		return item;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getPercentual() {
		return percentual;
	}

	public Double getTotal() {
		return total;
	}
	
	public Double getDesconto() {
		return desconto;
	}

	
	@JsonProperty
	public String getPercentualToString() {
		
		return NumberHelp.parseDoubleToString(this.getPercentual() * 100);
	}
	
	@JsonProperty
	public String getTotalToString() {
		
		return NumberHelp.parseDoubleToString(this.getTotal());
	}
	
	@JsonProperty
	public String getDescontoToString() {
		
		return NumberHelp.parseDoubleToString(this.getDesconto());
	}
	
}
