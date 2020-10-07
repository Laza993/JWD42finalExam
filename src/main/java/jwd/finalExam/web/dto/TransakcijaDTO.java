package jwd.finalExam.web.dto;


public class TransakcijaDTO {
	private Long id;
	private String racunUplatiocBroj;
	private Long racunUplatiocId;
	
	private String racunPrimaocBroj;
	private Long racunPrimaocId;
	
	private Double iznos;
	private Boolean stateOfTransaction;
	
	
	
	public Long getId() {
		return id;
	}

	
	public Long getRacunUplatiocId() {
		return racunUplatiocId;
	}
	public void setRacunUplatiocId(Long racunUplatiocId) {
		this.racunUplatiocId = racunUplatiocId;
	}

	
	public Long getRacunPrimaocId() {
		return racunPrimaocId;
	}
	public void setRacunPrimaocId(Long racunPrimaocId) {
		this.racunPrimaocId = racunPrimaocId;
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
	public String getRacunUplatiocBroj() {
		return racunUplatiocBroj;
	}
	public void setRacunUplatiocBroj(String racunUplatiocBroj) {
		this.racunUplatiocBroj = racunUplatiocBroj;
	}
	public String getRacunPrimaocBroj() {
		return racunPrimaocBroj;
	}
	public void setRacunPrimaocBroj(String racunPrimaocBroj) {
		this.racunPrimaocBroj = racunPrimaocBroj;
	}
	
	
}
