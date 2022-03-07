package br.com.jhage.pedido_api.excecao;

public class ConverterDataParaCaracterException extends PedidoException{
	
	private static final long serialVersionUID = 1L;

	private final static String MENSAGEM = "Erro ao Converter Data para Caracter";
	
	public ConverterDataParaCaracterException(){
	
		super(MENSAGEM);
	}
	
	

}
