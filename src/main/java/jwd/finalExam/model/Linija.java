package jwd.finalExam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Linija {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private int brMesta;
	@Column
	private double cena;
	@Column
	private String vremePolaska;
	@Column(nullable = false)
	private String destinacija;
	@ManyToOne(fetch = FetchType.EAGER)
	private Prevoznik prevoznik;
	
	public Linija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Linija(int brMesta, double cena, String vremePolaska, String destinacija, Prevoznik prevoznik) {
		super();
		this.brMesta = brMesta;
		this.cena = cena;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrMesta() {
		return brMesta;
	}

	public void setBrMesta(int brMesta) {
		this.brMesta = brMesta;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if(!prevoznik.getLinije().contains(this)) {
			prevoznik.getLinije().add(this);
		}
	}
	
	

}
