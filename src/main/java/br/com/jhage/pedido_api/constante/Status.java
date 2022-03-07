package br.com.jhage.pedido_api.constante;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
public enum Status implements Serializable {

	ATIVO, INATIVO;

	private static Map<String, Status> namesMap = new HashMap<String, Status>();

	public static Status get(String find) {

		Status result = ATIVO;
		try {
			result = valueOf(Status.class, find);
		} catch (IllegalArgumentException e) {

			System.out.println("Tamanho Nao Encontrado");
		}
		return result;
	}

	@JsonCreator
	public static Status forValue(String value) {

		return namesMap.get(value);
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, Status> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}
}
