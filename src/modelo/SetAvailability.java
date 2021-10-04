package modelo;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import principal.FacadeImplementationWS;
import principal.RuralHouse;
import principal.BadDates;
import principal.OverlappingOfferExists;

@ManagedBean(name="setAvailability")
@RequestScoped

public class SetAvailability {
		private Date entrada;
		private Date salida;
		private int precio;
		private String usuario;
		private List<RuralHouse> allRuralHouses;
		private FacadeImplementationWS facadeInstance;
		private RuralHouse selectedHouse;

		public SetAvailability(){
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
	  
	/*public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		}*/
	  public int getPrecio(){return this.precio;}
	  public void setPrecio(int valor){this.precio=valor;}
	  public String getCancelar(){return "back";} //VOLVER HACIA SELECCION
	  public String getAceptar(){
		  //SACAR EN UN LABEL LA VARIABLE RET
		  if (!facadeInstance.getLogin().getName().equals("admin")) return "";
		  
		String ret = "Error al añadir las fechas. Compruebe que entrada sea menor que salida";
		if ((selectedHouse!=null && entrada != null && salida != null && entrada.compareTo(salida)<0) && precio > 0){
			ret = "Oferta para el día " + DateUtils.formatDay(entrada) + " con salida el día " + DateUtils.formatDay(salida); //SACAR EN UN LABEL YA MISMO
			try {
				this.facadeInstance.createOffer(selectedHouse, entrada, salida, precio);
			} catch (OverlappingOfferExists e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadDates e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//mando la nueva reserva a reservar/seleccion
		
		//Reserve res=new Reserve(numRes, entrada, salida, precio,selectedHouse);
		return ret;
	}//Con este se graba en la base de datos

}
