package entity;

import java.util.ArrayList;
import java.util.List;

public class Vet {
	private int id;
	private String name;
	private List<Speciality> specs = new ArrayList<Speciality>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Speciality> getSpecs() {
		return specs;
	}
	public void setSpecs(List<Speciality> specs) {
		this.specs = specs;
	}
	
	
	
}
