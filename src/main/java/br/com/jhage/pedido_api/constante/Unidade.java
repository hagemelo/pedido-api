package br.com.jhage.pedido_api.constante;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
public enum Unidade  implements Serializable {
	
	KG,
	GR,
	LT,
	ML,
	CX,
	LA
	;
	
	private static Map<String, Unidade> namesMap = new HashMap<String, Unidade>();

	
	public static Unidade get(String find) {

		Unidade result = KG;
		try{
			result  = valueOf(Unidade.class, find);
		}catch (IllegalArgumentException e) {
			
			System.out.println("Tamanho Nao Encontrado");
		}
		return result;
	}
	
	
	@JsonCreator
    public static Unidade forValue(String value) {
		
        return namesMap.get(value);
    }
	
	public static Set<Unidade> getAll(){
		
		Set<Unidade> result = new HashSet<>();
		namesMap.entrySet().forEach(e -> result.add(e.getValue()));
		return result;
	}
	
	@JsonValue
    public String toValue() {
        for (Entry<String, Unidade> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null;
    }

}
