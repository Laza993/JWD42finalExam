package jwd.finalExam.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Racun {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String ImePrezime;
	@Column
	private String JMBG;
	@Column
	private String brRacuna;
	@Column
	private Double stanje;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Tipracuna tip;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Banka banka;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "racunUplatioc")
	private List<Transakcija> transakcijeSlanje;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "racunPrimaoc")
	private List<Transakcija> transakcijePrijem;
	
	
	
	
	public Racun(String imePrezime, String JMBG, String brRacuna, Double stanje, Tipracuna tip, Banka banka) {
		super();
		ImePrezime = imePrezime;
		this.JMBG = JMBG;
		this.brRacuna = brRacuna;
		this.stanje = stanje;
		this.tip = tip;
		this.banka = banka;
	}
	public Racun() {
		super();
		this.ImePrezime = "undefined";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImePrezime() {
		return ImePrezime;
	}
	public void setImePrezime(String imePrezime) {
		ImePrezime = imePrezime;
	}

	public String getBrRacuna() {
		return brRacuna;
	}
	public void setBrRacuna(String brRacuna) {
		this.brRacuna = brRacuna;
	}
	public Double getStanje() {
		return stanje;
	}
	public void setStanje(Double stanje) {
		this.stanje = stanje;
	}
	public Tipracuna getTip() {
		return tip;
	}
	public void setTip(Tipracuna tip) {
		this.tip = tip;
		if(!tip.getRacuni().contains(this)) {
			tip.getRacuni().add(this);
		}
	}
	public Banka getBanka() {
		return banka;
	}
	public void setBanka(Banka banka) {
		this.banka = banka;
		if(!banka.getRacuni().contains(this)) {
			banka.getRacuni().add(this);
		}
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	
	
	public List<Transakcija> getTransakcijeSlanje() {
		return transakcijeSlanje;
	}

	public List<Transakcija> getTransakcijePrijem() {
		return transakcijePrijem;
	}
	
	public void addTransakcijeSlanje(Transakcija isplata) {
		this.transakcijeSlanje.add(isplata);
		if(isplata.getRacunUplatioc() != this) {
			isplata.setRacunUplatioc(this);
		}
	}
	public void addTransakcijePrijem(Transakcija uplata) {
		this.transakcijePrijem.add(uplata);
		if(uplata.getRacunPrimaoc() != this) {
			uplata.setRacunPrimaoc(this);
		}
	}
	
	
	

	
	
}
