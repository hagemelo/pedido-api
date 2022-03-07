package br.com.jhage.pedido_api.excecao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class PedidoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT= "ERRRO_ACAO_ABRUPTA";

	public Logger inicializarLogger() {
		return LogManager.getLogger(PedidoException.class);
	}

	public PedidoException() {
		super(DEFAULT);
		this.inicializarLogger().error(DEFAULT);
	}
	
	public PedidoException(String message) {
		super(message);
		this.inicializarLogger().error(message);
	}

	public PedidoException(final Exception cause) {

		super(DEFAULT, cause);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
	}

	public PedidoException(final Throwable cause) {

		super(DEFAULT);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
	}

	public ResponseEntity<RespostaErro> respostaErro(){
		
		return new ResponseEntity<RespostaErro>(new RespostaErro(this.getMessage()),  HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@Override
	public String getMessage() {

		String message = super.getMessage();
		if (message == null || message.isEmpty()) {
			message = DEFAULT;
		}
		return message;
	}

}
