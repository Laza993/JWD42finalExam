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
public class Transakcija {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Racun racunUplatioc;
	@ManyToOne(fetch = FetchType.EAGER)
	private Racun racunPrimaoc;
	@Column
	private Double iznos;
	@Column
	private Boolean stateOfTransaction;
	
	
	
	
	public Transakcija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transakcija(Racun racunUplatioc, Racun racunPrimaoc, Double iznos, Boolean stateOfTransaction) {
		super();
		this.racunUplatioc = racunUplatioc;
		this.racunPrimaoc = racunPrimaoc;
		this.iznos = iznos;
		this.stateOfTransaction = stateOfTransaction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Racun getRacunUplatioc() {
		return racunUplatioc;
	}

	public void setRacunUplatioc(Racun racunUplatioc) {
		this.racunUplatioc = racunUplatioc;
		if(!racunUplatioc.getTransakcijeSlanje().contains(this)) {
			racunUplatioc.addTransakcijeSlanje(this);
		}
	}

	public Racun getRacunPrimaoc() {
		return racunPrimaoc;
	}

	public void setRacunPrimaoc(Racun racunPrimaoc) {
		this.racunPrimaoc = racunPrimaoc;
		if(!racunPrimaoc.getTransakcijePrijem().contains(this)) {
			racunPrimaoc.addTransakcijePrijem(this);
		}
	}

	public Double getIznos() {
		return iznos;
	}

	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}

	public Boolean getStateOfTransaction() {
		return stateOfTransaction;
	}

	public void setStateOfTransaction(Boolean stateOfTransaction) {
		this.stateOfTransaction = stateOfTransaction;
	}
	
}
