package jwd.finalExam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Banka {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;

	@Column
	private Double sredstva;

	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Racun> racuni = new ArrayList<Racun>();

	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tipracuna> tipovi = new ArrayList<Tipracuna>();
	
	public void addRacun(Racun racun) {
		this.racuni.add(racun);
		if(!racun.getBanka().equals(this)) {
			racun.setBanka(this);
		}
	}
	
	public void addTip(Tipracuna tip) {
		this.tipovi.add(tip);
		if(!tip.getBanka().equals(this)) {
			tip.setBanka(this);
		}
	}

	public Banka(String naziv, Double sredstva) {
		super();
		this.naziv = naziv;
		this.sredstva = sredstva;
	}

	public Banka() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getSredstva() {
		return sredstva;
	}

	public void setSredstva(Double sredstva) {
		this.sredstva = sredstva;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

	public List<Tipracuna> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<Tipracuna> tipovi) {
		this.tipovi = tipovi;
	}
	
	

}
