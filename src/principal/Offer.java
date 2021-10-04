package principal;

import java.io.*;
import java.util.Date;	

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)

public class Offer implements Serializable {
	
	private Integer idUsuario;
	private Integer offerNumber;
	private Date firstDay; // Dates are stored as java.util.Date objects instead of java.sql.Date objects
	private Date lastDay;  // because, they are not well stored in db4o as java.util.Date objects
	private float price;
	private Integer houseNumber;
	//private User usu= new User();
	// This is coherent because objects of java.sql.Date are objects of java.util.Date 
	@XmlIDREF
	private RuralHouse ruralHouse;

	public Offer(){}
	public Offer(Integer idUsuario, int offerNumber, Date firstDay, Date lastDay, float price, RuralHouse ruralHouse){
		  this.firstDay=firstDay;
		  this.lastDay=lastDay;
		  this.price=price;
		  this.ruralHouse=ruralHouse;
		  this.offerNumber=offerNumber;
		  this.houseNumber = ruralHouse.getHouseNumber();
		  this.idUsuario = idUsuario;
	}

	/**
	 * Get the house number of the offer
	 * 
	 * @return the house number
	 */
	
	/*public void setUsuario(User usu){this.usu=usu;}
	public User getUsuario(){return usu;}*/
	public Integer getIdUsuario(){return idUsuario;}
	public void setIdUsuario(Integer idUsuario){this.idUsuario=idUsuario;}
	public RuralHouse getRuralHouse() {return this.ruralHouse;}
	
	public Integer getHouseNumber() { return houseNumber; }
	public void setHouseNumber(Integer houseNumber) { this.houseNumber = houseNumber; }

	
	/**
	 * Set the house number to a offer
	 * 
	 * @param house number
	 */
	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}


	/**
	 * Get the offer number
	 * 
	 * @return offer number
	 */
	public int getOfferNumber() {
		return this.offerNumber;
	}
	
	public void setOfferNumber(Integer offerNumber) { this.offerNumber = offerNumber; }

	/**
	 * Get the first day of the offer
	 * 
	 * @return the first day
	 */
	public Date getFirstDay() {
		return this.firstDay;
	}

	/**
	 * Set the first day of the offer
	 * 
	 * @param firstDay
	 *            The first day
	 */
	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	/**
	 * Get the last day of the offer
	 * 
	 * @return the last day
	 */
	public Date getLastDay() {
		return this.lastDay;
	}

	/**
	 * Set the last day of the offer
	 * 
	 * @param lastDay
	 *            The last day
	 */
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	/**
	 * Get the price
	 * 
	 * @return price
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Set the price
	 * 
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/*public String toString(){
		return offerNumber+";"+firstDay.toString()+";"+lastDay.toString()+";"+price;
	}*/
	public String toString(){
		return idUsuario+offerNumber+";"+firstDay.toString()+";"+lastDay.toString()+";"+price;
	}
}