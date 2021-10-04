package principal;

public class Login {
	private Integer idUsuario;
	private String name;	
	private String password;
	private boolean isNew;
	
	
	public Login(){}
	public Login(Integer idUsuario, String name, String password, boolean _isNew){
		isNew = _isNew;
		setIdUsuario(idUsuario);setName(name);setPassword(password);
	}
	
	public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; } 

	public Integer getIdUsuario() { return this.idUsuario; }
	
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
	
	public boolean getIsNew() { return isNew; }
	

}
