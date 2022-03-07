package br.com.jhage.pedido_api.dto;

import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jhage.pedido_api.helper.Helper;

/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */


public class TempoPedidoDto {
	
	private String tipo;
	
	@JsonFormat(pattern="HH:mm:ss.SSS")
	private Date tempo;
	
	private final static int POSICAO_TIPO = 0; 
	private final static int POSICAO_TEMPO = 1; 
	
	TempoPedidoDto(){}
	
	
	public TempoPedidoDto(Object[] ob){
		
		addTipo(ob);
		addTempo(ob);
	}

	public String getTipo() {
		return tipo;
	}


	public Date getTempo() {
		return tempo;
	}
	
	private void addTipo(Object[] ob) {
		
		if (!Helper.ENULO.enulo(ob) && ob.length>0) {
			
			tipo = ob[POSICAO_TIPO].toString();
		}else {
			
			tipo = "";
		}
	}
	
	private void addTempo(Object[] ob) {
		
		if (!Helper.ENULO.enulo(ob) && ob.length>0) {
			
			try {
				tempo = java.text.DateFormat.getDateInstance().parse(ob[POSICAO_TEMPO].toString());
			} catch (ParseException e) {
				
				tempo = new Date();
			} 
		}else {
			
			tempo = new Date();
		}
	}
	
}
