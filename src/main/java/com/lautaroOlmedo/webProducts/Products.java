package com.lautaroOlmedo.webProducts;

import java.util.Date;

public class Products {
	
	// ---------- ---------- ---------- CONSTRUCTORS ---------- ---------- ----------
	
	public Products(String cArt, String section, String nArt, double price, boolean imported, String oCountry, Date date) {
		
		this.cArt = cArt;
		this.section = section;
		this.nArt = nArt;
		this.oCountry = oCountry;
		this.price = price;
		this.date = date;
		this.imported = imported;
	}
	
	
	
	public Products(String section, String nArt,boolean imported, String oCountry, double price, Date date) {
	
		this.section = section;
		this.nArt = nArt;
		this.oCountry = oCountry;
		this.price = price;
		this.date = date;
		this.imported = imported;
	}

	// ---------- ---------- ---------- GETTERS & SETTERS METHODS ---------- ---------- ----------

	public String getcArt() {
		return cArt;
	}



	public void setcArt(String cArt) {
		this.cArt = cArt;
	}



	public String getSection() {
		return section;
	}



	public void setSection(String section) {
		this.section = section;
	}



	public String getnArt() {
		return nArt;
	}



	public void setnArt(String nArt) {
		this.nArt = nArt;
	}



	public String getoCountry() {
		return oCountry;
	}



	public void setoCountry(String oCountry) {
		this.oCountry = oCountry;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}
	
	public Boolean getImported() {
		return imported;
	}
	
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
	// ---------- ---------- ---------- METHODS ---------- ---------- ----------
	
	@Override
	public String toString() {
		return "Products [cArt=" + cArt + ", section=" + section + ", nArt=" + nArt + ", oCountry=" + oCountry
				+ ", price=" + price + ", date=" + date + "]";
	}

	// ---------- ---------- ---------- VARIABLES ---------- ---------- ----------
	
	private String cArt, section, nArt, oCountry;
	private double price;
	private Date date;
	private boolean imported;

}
