package br.com.jhage.pedido_api.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.jhage.pedido_api.excecao.ConverterDataParaCaracterException;
import br.com.jhage.pedido_api.excecao.FormatarDataParaPadraoException;
import br.com.jhage.pedido_api.excecao.PedidoException;

/***
 * 
 * @author Alexsander Melo
 * @since 09/01/2013
 * @version 1.0 Classe responsavel por fomata��o e valida��o de datas
 */
final public class FormatDateHelper {
	
	private FormatDateHelper() {}

	private static final String FORMAT_DEFAULT = "dd/MM/yyyy";
	private static final String FORMAT_DATAHORA = "dd/MM/yyyy hh:mm:ss";
	private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");
	
	private SimpleDateFormat formatoPadraoDaData;
	
	FormatDateHelper(SimpleDateFormat simpleDateFormat) {
		
		formatoPadraoDaData = new SimpleDateFormat(FORMAT_DEFAULT, LOCALE_PT_BR);
	}
	
	public static FormatDateHelper getInstance() {
		
		return new FormatDateHelper();
	}
	
	
	public String converterDataParaCaracter(final Date date) throws PedidoException {
			
			return converterDataParaCaracter(date, FORMAT_DEFAULT);
	}

	public String converterDataParaCaracter(final Date date, final String value) throws PedidoException {

		try {
			inserirFormatoPadraoDaData(value);
			return formatoPadraoDaData.format(date).toString();
		} catch (final Exception e) {
			throw new ConverterDataParaCaracterException();
		}
	}

	public boolean isMesmadata(final Date date1, final Date date2) throws PedidoException {

		final Date primeiraData = formatarDataParaPadrao(date1, FORMAT_DATAHORA);
		final Date segundaData = formatarDataParaPadrao(date2, FORMAT_DATAHORA);
		return primeiraData.compareTo(segundaData) == 0;
	}
	
	public boolean isData1MenorQueData2(final Date date1, final Date date2)
			throws PedidoException {

		final Date primeiraData = formatarDataParaPadrao(date1, FORMAT_DATAHORA);
		final Date segundaData = formatarDataParaPadrao(date2, FORMAT_DATAHORA);
		return primeiraData.compareTo(segundaData) <= 0;
	}

	public Date formatarDataParaPadrao(final String date, final String value) throws PedidoException {

		try {
			inserirFormatoPadraoDaData(value);
			return formatoPadraoDaData.parse(date);
		} catch (final Exception e) {
			throw new FormatarDataParaPadraoException();
		}
	}
	
	public Date formatarDataParaPadrao(final Date date, final String value) throws PedidoException {

		try {
			inserirFormatoPadraoDaData(value);
			return formatoPadraoDaData.parse(formatoPadraoDaData.format(date));
		} catch (final Exception e) {
			throw new FormatarDataParaPadraoException();
		}
	}

	private void inserirFormatoPadraoDaData(final String formato) {
		
		formatoPadraoDaData = new SimpleDateFormat(formato, LOCALE_PT_BR);
	}
	
}
