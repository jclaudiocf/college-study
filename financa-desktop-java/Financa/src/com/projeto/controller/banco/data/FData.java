package com.projeto.controller.banco.data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class FData {
	
	private LocalDateTime fData;
	private final String FORMATO_DATA_US = "yyyy-MM-dd";
	
	public FData() {
		fData = LocalDateTime.now();
	}
	
	public FData(LocalDateTime aData) {
		// TODO não pode ser null, criar verificação
		fData = aData;
	}
	
	public LocalDateTime getData() {
		return fData;
	}
	
	public String getDataFMT() {
		return DateTimeFormatter.ofPattern(FORMATO_DATA_US).toString();
	}
	
	public Date getDataSQL() {
		DateTimeFormatter xFormatacao = DateTimeFormatter.ofPattern(FORMATO_DATA_US);
		return Date.valueOf(fData.format(xFormatacao).toString());
	}
	
	public static LocalDateTime getDataMinima() {
		return LocalDateTime.of(1990, 1, 1, 0, 0);
	}
	
	public static LocalDateTime getDataMaxima() {
		return LocalDateTime.of(2500, 1, 1, 0, 0);
	}
	
	public Timestamp getDataHoraSQL() {
		return Timestamp.valueOf(fData);
	}
	
	public static LocalDateTime toLocalDateTime(Date aData) {
		Instant instant = Instant.ofEpochMilli(aData.getTime());
	    LocalDateTime xDataHora = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return xDataHora;
    }
}
