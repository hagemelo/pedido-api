package br.com.jhage.pedido_api.constante;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {
	
	REALIZADO,
	PRONTO,
	ENTREGUE,
	CANCELADO;
	
	private static Map<String, StatusPedido> namesMap = new HashMap<String, StatusPedido>();

	static {
		
		for (StatusPedido e: StatusPedido.values()) {
			namesMap.put(e.toString(), e); 
		}
	}
	
	
	public static StatusPedido get(String find) {

		StatusPedido result = REALIZADO;
		try{
			result  = valueOf(StatusPedido.class, find);
		}catch (IllegalArgumentException e) {
			
			System.out.println("Tamanho Nao Encontrado");
		}
		return result;
	}
	
	
	@JsonCreator
    public static StatusPedido forValue(String value) {
		
        return namesMap.get(value);
    }
	
	
	@JsonValue
    public String toValue() {
        for (Entry<String, StatusPedido> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null;
    }
}
