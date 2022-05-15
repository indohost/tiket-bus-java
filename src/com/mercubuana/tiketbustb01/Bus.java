package com.mercubuana.tiketbustb01;

import java.io.Serializable;

public class Bus implements Serializable{
	String namaBus;
	char destinasi;
	int kursi;
	int sisaKursi;
	
	public Bus(String namaBus, char destinasi, int kursi, int sisaKursi) {
		super();
		this.namaBus = namaBus;
		this.destinasi = destinasi;
		this.kursi = kursi;
		this.sisaKursi = sisaKursi;
	}

	public String getNamaBus() {
		return namaBus;
	}

	public void setNamaBus(String namaBus) {
		this.namaBus = namaBus;
	}

	public char getDestinasi() {
		return destinasi;
	}

	public void setDestinasi(char destinasi) {
		this.destinasi = destinasi;
	}

	public int getKursi() {
		return kursi;
	}

	public void setKursi(int kursi) {
		this.kursi = kursi;
	}

	public int getSisaKursi() {
		return sisaKursi;
	}

	public void setSisaKursi(int sisaKursi) {
		this.sisaKursi = sisaKursi;
	}
	
	public String toString() {
		return namaBus;
	}
}
