package br.com.jhage.pedido_api.helper;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * @author Alexsander Melo
 * @since 25/01/2017
 *
 */
public class NumberHelp {

	public static String parseDoubleToString(Double valor){
		
		StringBuffer build = new StringBuffer();
		try{
			
			build.append(String.format("%,.2f", valor));
		}catch (Exception e) {

			return "";
		}
		return build.toString().trim();
	}
	
}
