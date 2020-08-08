package jwd.finalExam.web.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RacunDTO {
	private Long id;
	private String ImePrezime;
	
	@Size(max = 13)
	private String JMDB;
	@NotEmpty
	private String brRacuna;
	
	private Double stanje;
	
	private String tipNaziv;
	private Long tipId;
	
	private String bankaNaziv;
	private Long bankaId;
	
	
	
	
	
	public RacunDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getJMDB() {
		return JMDB;
	}
	public void setJMDB(String jMDB) {
		JMDB = jMDB;
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
	public String getTipNaziv() {
		return tipNaziv;
	}
	public void setTipNaziv(String tipNaziv) {
		this.tipNaziv = tipNaziv;
	}
	public Long getTipId() {
		return tipId;
	}
	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}
	public String getBankaNaziv() {
		return bankaNaziv;
	}
	public void setBankaNaziv(String bankaNaziv) {
		this.bankaNaziv = bankaNaziv;
	}
	public Long getBankaId() {
		return bankaId;
	}
	public void setBankaId(Long bankaId) {
		this.bankaId = bankaId;
	}
	
	
}
