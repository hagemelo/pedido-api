package br.com.jhage.pedido_api.excecao;

public class ByteToHexException extends PedidoException{
	
	private static final long serialVersionUID = 1L;

	private final static String MENSAGEM = "Erro ao Converter Byte to Hex";

	public ByteToHexException(){
		
		super(MENSAGEM);
	}
}
