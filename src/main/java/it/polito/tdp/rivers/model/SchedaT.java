package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class SchedaT {
	
	private LocalDate prima;
	private LocalDate ultima;
	private River river;
	private double media;
	private int tot;
	
	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public SchedaT(LocalDate prima, LocalDate ultima, River river, double media, Integer tot) {
		super();
		this.prima = prima;
		this.ultima = ultima;
		this.river =river;
		this.media = media;
		this.tot=tot;
	}

	public LocalDate getPrima() {
		return prima;
	}

	public void setPrima(LocalDate prima) {
		this.prima = prima;
	}

	public LocalDate getUltima() {
		return ultima;
	}

	public void setUltima(LocalDate ultima) {
		this.ultima = ultima;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}
}
