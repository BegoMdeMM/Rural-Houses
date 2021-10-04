package principal;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reserve implements Serializable{
	/*se almacenan las selecciones de las*/
	//de la ofeta cojo el numero de oferta para que no se utilice de nuevo;
	private Date firstDay;
	private Date lastDay;
	private int price;
	private Integer numRH;
	private Integer numRes;
	@XmlIDREF
	private RuralHouse ruralHouse;
	

	public Reserve( int numRes, Date firstDay, Date lastDay, int price, RuralHouse ruralHouse){
		setFirstDay(firstDay);
		setLastDay(lastDay);
		setPrice(price);
		setNumRes(numRes);		
		setRuralHouse(ruralHouse);
		setNumRH(ruralHouse.getHouseNumber());
	}
	public Reserve(){}
	public Reserve( Date entrada, Date salida, int precio,Integer numRH){}
	
	public Date getFirstDay() { return firstDay;	  }
	public void setFirstDay(Date firstDay) {this.firstDay = firstDay; }
	public Date getLastDay() {return lastDay;  }
	public void setLastDay(Date lastDay) { this.lastDay = lastDay;  }
	public int getPrice() {return price;  }
	public void setPrice(int price) { this.price = price;  }
	public RuralHouse getRuralHouse (){return this.ruralHouse;}
	public void setRuralHouse(RuralHouse ruralHouse) { this.ruralHouse = ruralHouse; }
	public Integer getNumRH (){return this.numRH;}
	public void setNumRH(Integer numRH) { this.numRH = numRH; }
	public Integer getNumRes (){return this.numRes;}
	public void setNumRes(Integer numRes) { this.numRes = numRes; }
	
	
	
	
	public String toString(){
		return numRes+";"+firstDay.toString()+";"+lastDay.toString()+";"+price+";"+ruralHouse.getCity().toString();
	}
}
