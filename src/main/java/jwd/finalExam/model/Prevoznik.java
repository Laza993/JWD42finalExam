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
public class Prevoznik {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column()
	private String address;
	@Column(nullable = false, unique = true)
	private String PIB;
	
	@OneToMany(mappedBy = "prevoznik", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Linija> linije = new ArrayList<Linija>();
	
	public List<Linija> getLinije() {
		return linije;
	}
	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}
	public void addLinija(Linija linija) {
		this.linije.add(linija);
		if(linija.getPrevoznik() != null && !linija.getPrevoznik().equals(this)) {
			linija.setPrevoznik(this);
		}
	}
	public Prevoznik(String name, String address, String pIB) {
		super();
		this.name = name;
		this.address = address;
		PIB = pIB;
	}
	public Prevoznik() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prevoznik(Long id, String name, String address, String pIB) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		PIB = pIB;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPIB() {
		return PIB;
	}
	public void setPIB(String pIB) {
		PIB = pIB;
	}
	
	
}
