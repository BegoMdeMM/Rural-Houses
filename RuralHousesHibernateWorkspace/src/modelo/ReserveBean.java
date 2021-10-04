package modelo;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import principal.FacadeImplementationWS;
import principal.RuralHouse;

@ManagedBean(name="reserveBean")
@RequestScoped

public class ReserveBean {
	private Date entrada;
	private Date salida;
	private String usuario;
	private List<RuralHouse> allRuralHouses;
	private FacadeImplementationWS facadeInstance;
	private RuralHouse selectedHouse;
	private String reservado;

	public ReserveBean(){
		this.facadeInstance = principal.FacadeImplementationWS.getInstance();
		this.allRuralHouses = this.facadeInstance.getAllRuralHouses();
		this.usuario = this.facadeInstance.getLogin().getName();
	}
	public RuralHouse getRuralHouse(Integer id){//busco por id
		if (id == null) return null;
		for (RuralHouse house : this.allRuralHouses){
			if (id.compareTo(house.getHouseNumber())==0)
				return house;
		}
		return null;
	}
	
	public String getUsuario() { return this.usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; } 
	
	public List<RuralHouse> getAllRuralHouses() { return this.allRuralHouses; }
	public FacadeImplementationWS getFacadeInstance() { return this.facadeInstance; }
	public void setSelectedHouse(RuralHouse selectedHouse) { this.selectedHouse = selectedHouse; }
	public RuralHouse getSelectedHouse() { return selectedHouse; }
	public Date getEntrada() { return entrada;	  }
	public void setEntrada(Date entrada) {this.entrada = entrada; }
	public Date getSalida() {return salida;  }
	public void setSalida(Date salida) { this.salida = salida;  }
	public String getPrintEntrada() {
		if (entrada == null) {
			return("No date selected.");
		} else {
			String message =String.format("You choose '%s'.",DateUtils.formatDay(entrada));
			return(message);
		}
  }	 
  public String getPrintSalida() {
	    if (salida == null) {
	      return("No date selected.");
	    } else {
	      String message =String.format("You choose '%s'.",DateUtils.formatDay(salida));
	      return(message);
	    }
	  }
  public String getCancelar(){return "back";} //VOLVER HACIA SELECCION
  public String getAceptar(){
	  //SACAR EN UN LABEL LA VARIABLE RET
	  System.out.println("Llama");
	String ret = "Error al añadir las fechas. Compruebe que entrada sea menor que salida";
	if ((selectedHouse!=null && entrada != null && salida != null && entrada.compareTo(salida)<0)){
		System.out.println("Llama1");
			if (this.facadeInstance.createReserve(facadeInstance.getLogin().getIdUsuario(), selectedHouse, entrada, salida))
				reservado = "Reserva para: " + selectedHouse.getCity() + " para el día" + DateUtils.formatDay(entrada) + " con salida el día " + DateUtils.formatDay(salida);
			else
				reservado = "No existen ofertas para esta casa";
	}
	return ret;
}//Con este se graba en la base de datos
  
}
