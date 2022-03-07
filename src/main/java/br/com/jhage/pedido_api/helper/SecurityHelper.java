package br.com.jhage.pedido_api.helper;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

import br.com.jhage.pedido_api.excecao.ByteToHexException;
import br.com.jhage.pedido_api.excecao.PedidoException;

/**
 * 
 * @author Alexsander Melo
 * @since 17/12/2016
 *
 */

final public class SecurityHelper {

	public static String criptografar(final String value)	throws PedidoException {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.reset();
			return byteToHex(md.digest(value.getBytes("UTF-8")));
			
		} catch (Exception e) {
			
			throw new PedidoException(e);
		}
	}

	public static String getToken(){
		
		 UUID uuid = UUID.randomUUID();
	     return uuid.toString();
	}
	
	protected static String byteToHex(final byte[] hash) throws PedidoException {
		
		try {
			@SuppressWarnings("resource")
			final Formatter formatter = new Formatter();
			for (final byte b : hash) {
				formatter.format("%02x", b);
			}
			return formatter.toString();
		}catch (Exception e) {
			
			throw new ByteToHexException();
		}
	}
	
}
