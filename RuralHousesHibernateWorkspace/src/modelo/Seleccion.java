package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name ="seleccion", eager=true)
@RequestScoped
public class Seleccion {
	public String getQueryAvailability(){
		return "query";}
	
	public String getGrabarAvailability(){
		return "set";}
	
	public String getSetReserve(){
		return "res";
	}
	
	//12/15
	public String getReseserAvailability(){
		principal.FacadeImplementationWS.getInstance().close();
		return "back";
	}

}
