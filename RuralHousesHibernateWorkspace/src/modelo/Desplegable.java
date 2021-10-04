package modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import principal.FacadeImplementationWS;
import principal.Offer;
import principal.RuralHouse;

@ManagedBean(name="desplegable")
@RequestScoped


public class Desplegable {
	private Date entrada;
	private int precio;
	private String usuario;
	private List<Offer> offers;
	private int noches, nhouse, noffer;
	private Date casaEntrada, casaSalida;
	private RuralHouse selectedHouse;
	private List<RuralHouse> allRuralHouses;
	private FacadeImplementationWS facadeInstance;

	public Desplegable(){
	this.facadeInstance = principal.FacadeImplementationWS.getInstance();
	this.allRuralHouses = this.facadeInstance.getAllRuralHouses();
	this.usuario = this.facadeInstance.getLogin().getName();
	}
	
	public String getUsuario() { return this.usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; } 

	public List<RuralHouse> getAllRuralHouses() { return this.allRuralHouses; }
	public FacadeImplementationWS getFacadeInstance() { return this.facadeInstance; }

	public void setSelectedHouse(RuralHouse selectedHouse) { this.selectedHouse = selectedHouse; }
	public RuralHouse getSelectedHouse() { return selectedHouse; }

	public List<Offer> getOffers() { return this.offers; }

	public void setNoches(int noches) { this.noches = noches; }
	public int getNoches() { return this.noches; }

	public RuralHouse getRuralHouse(Integer id){
		if (id == null) return null;
		for (RuralHouse house : this.allRuralHouses){
			if (id.compareTo(house.getHouseNumber())==0)
				return house;
		}
		return null;
	}

	 public Date getEntrada() {

	    return entrada;

	  }

	 public void setEntrada(Date entrada) {

	    this.entrada = entrada;

	  }

	public String getPrintEntrada() {

	    if (entrada == null) {

	      return("No date selected.");

	    } else {

	      String message =

	        String.format(DateUtils.formatDay(entrada));
	      return(message);

	    }

	  }

	public String getCasaEntrada() {

	    if (casaEntrada == null) {

	      return("No date selected.");

	    } else {

	      String message =

	        String.format(DateUtils.formatDay(casaEntrada));
	      return(message);

	    }

	  }

	public String getPrintCasaSalida() {

	    if (casaSalida == null) {

	      return("No date selected.");

	    } else {

	      String message =

	        String.format(DateUtils.formatDay(casaSalida));
	      return(message);

	    }

	  }


	public String getCancelar() { return "back"; }

	public String getAceptar() {
		//SI LAS NOCHES O LA FECHA O LA CASA NO HAN SIDO BIEN SELECCIONADAS
		//AVISAR POR UN LABEL..
		if (selectedHouse != null && noches > 0 && entrada != null){
			Calendar c = Calendar.getInstance();
			c.setTime(entrada);
			c.add(Calendar.DATE, noches-1);
			Date salida = c.getTime();
			this.offers = this.facadeInstance.getOffersByDate(selectedHouse, entrada, salida);
			this.selectedHouse = null;
			this.entrada = null;
			this.noches = 0;
		}
		return "";
	}

}
