package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import principal.FacadeImplementationWS;
import principal.Login;

@ManagedBean(name = "loginBean")
@RequestScoped

public class LoginBean {
	private Login log;
	private String name;	
	private String password;
	private String logintext;
	private FacadeImplementationWS facadeInstance;
	
	public LoginBean(){
		System.out.println("creado");
		if (this.facadeInstance!=null)
			this.facadeInstance.close();
		principal.FacadeImplementationWS.CreateInstance();
		this.facadeInstance = principal.FacadeImplementationWS.getInstance();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLogintext(String logintext) { this.logintext = logintext; }
	public String getLogintext() { return this.logintext; }
	
	//yo
	public String getCheck(){
		Login _log = this.facadeInstance.check(name, password);
		if (_log.getIsNew())
			this.logintext = "Usuario nuevo";
		else
			this.logintext = "Usuario existe";
		//mando la nueva reserva a reservar/seleccion
		return "";
	}
	
	public String getLogin(){ //SACAR EN UN LABEL LA VARIABLE RET
		String ret = "log";
		try{
			log = this.facadeInstance.checkLogin(name, password);
		}catch (Exception ex){
			ret = "error";
			this.logintext = "Contraseña inválida";
		}
		//mando la nueva reserva a reservar/seleccion;
		return ret;
	}//Con este se graba en la base de datos
}
