package jwd.finalExam.web.dto;


public class BankaDTO {
	private Long id;
	private String naziv;
	private Double sredstva;
	
	
	
	public BankaDTO() {
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
	
	
}
