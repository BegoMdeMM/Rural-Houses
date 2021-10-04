package principal;

/*import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;*/

public class User {
	
	private Integer numClient;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public User() {
		super();
	}
	public User (Integer numClient, String nombre, String ape1, String ape2 ){
		setnumClient(numClient);
		setNombreCli(nombre);
		setApe1Cli(ape1);
		setApe2Cli(ape2);
		//this.res=new Vector<Reserve>();
	}
	public Integer getnumClient(){return this.numClient;}
	public String getNombreCli(){return this.nombre;}
	public String getApe1Cli(){return this.apellido1;}
	public String getApe2Cli(){return this.apellido2;}
	/*public List<Reserve> getRes(Date firstDay,  Date lastDay){
		List<Reserve> availableRes= new Vector<Reserve>();
		Iterator<Reserve> e=res.iterator();
		Reserve re;
		while (e.hasNext()){
			re=e.next();
			if((re.getFirstDay().compareTo(firstDay)>=0) && (re.getLastDay().compareTo(lastDay)<=0))
				availableRes.add(re);
		}
		return availableRes;
	}*/
	public void setnumClient(Integer numClient){this.numClient=numClient;}
	public void setNombreCli(String nombre){this.nombre=nombre;}
	public void setApe1Cli(String ape1){this.apellido1=ape1;}
	public void setApe2Cli(String ape2){this.apellido2=ape2;}
	
	//public void setRes(List<Reserve> res){this.res=res;}
	
	/*public Reserve createReserve(int resNum, Date firsDate, Date lastDate, int price, RuralHouse selectedHouse ){
		Reserve res=null;
		System.out.println(resNum);
		res=new Reserve(resNum, firsDate, lastDate,  price, selectedHouse);
		if (this.res==null) this.res=new Vector<Reserve>();
		this.res.add(res);
		return res;
	}*/
/*	public Reserve overlapsWith( Date firstDay,  Date lastDay) {
			
			Iterator<Reserve> e=res.iterator();
			Reserve re=null;
			while (e.hasNext()){
				re=e.next();
				if ( (re.getFirstDay().compareTo(lastDay)<0) && (re.getLastDay().compareTo(firstDay)>0))
					return re;
			}
			return null;
			
		}*/
	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (numClient != other.numClient)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numClient;
		return result;
	}*/
	
	
}
