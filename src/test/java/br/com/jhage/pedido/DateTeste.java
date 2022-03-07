package br.com.jhage.pedido;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class DateTeste {
	
	
	@Test
	public void teste(){
		
//		Convert java.util.Date to java.time.LocalDate
		Date date = new Date();
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		System.out.println("Convert java.util.Date to java.time.LocalDate :::" + res);
		
//		Convert java.util.Date to java.time.LocalTime
		Date time = new Date();
		instant = Instant.ofEpochMilli(time.getTime());
		LocalTime ress = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
		System.out.println("Convert java.util.Date to java.time.LocalTime :::" + ress);
		
//		Convert java.time.LocalDateTime to java.util.Date
		LocalDateTime ldt = LocalDateTime.now();
		instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		Date resss = Date.from(instant);
		System.out.println("Convert java.time.LocalDateTime to java.util.Date :::" + resss);
		
//		Convert java.time.LocalDate to java.util.Date
		LocalDate ld = LocalDate.now();
		instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date ressss = Date.from(instant);
		System.out.println("Convert java.time.LocalDate to java.util.Date :::" + ressss);
		
//		Convert java.time.LocalTime to java.util.Date
		LocalTime lt = LocalTime.now();
		instant = lt.atDate(LocalDate.of(2013, 4, 14)).
		        atZone(ZoneId.systemDefault()).toInstant();
		Date times = Date.from(instant);
		System.out.println("Convert java.time.LocalTime to java.util.Date :::" + times);
	}

}
