package principal;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.mysql.jdbc.Connection;

//import domain.Booking;
import principal.Offer;
import principal.RuralHouse;
import modelo.HibernateUtil;

public class DataAccess  {
	

	private static SessionFactory s;
	private static Connection c;
	private static List<RuralHouse> rhs;
	private static Login log;

	public DataAccess()  {
		
		s = HibernateUtil.getSessionFactory();
		
		try {
			c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ruralhouse");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}


	
	public void initializeDB(){
		
		Session ss = s.getCurrentSession();
		
		rhs = new Vector<RuralHouse>();
		
		ss.beginTransaction();
		
		List l =  ss.createQuery("FROM principal.RuralHouse").list();
		
		for (Object rh : l){
			rhs.add((RuralHouse)rh);
		}
	}
	
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {

		Session ss = s.getCurrentSession();
		
		ss.beginTransaction();
		
		List l =  ss.createQuery("FROM principal.Offer WHERE houseNumber='" + ruralHouse.getHouseNumber()+"'").list();
		
		Offer off = null;
		
		boolean bussy = false;
		
		for (Object o : l){
			Offer of = (Offer)o;
			if (!(of.getFirstDay().compareTo(lastDay)>0 || of.getLastDay().compareTo(firstDay)<0)){
				bussy=true;
			}
		}
		
		if (!bussy){
			l =  ss.createQuery("SELECT offerNumber FROM Offer").list();
			
			int noffer = 0;
			
			if (l.size() > 0){
				
				noffer = (Integer) l.get(l.size()-1);
			}
			
			System.out.println(log.getIdUsuario() + " " + noffer + " " + firstDay.toString() + " " + price);
			
			off=ruralHouse.createOffer(-1, ++noffer,firstDay, lastDay, price);
			
			ss.save(off);
			
			ss.getTransaction().commit();
	
		}
		return off;
}
	
	public boolean createReserve(Integer idUsuario, RuralHouse ruralHouse, Date firstDay, Date lastDay){
		Session ss = s.getCurrentSession();
		boolean ret = false;
		ss.beginTransaction();
		
		List l =  ss.createQuery("FROM principal.Offer WHERE idUsuario='-1' AND houseNumber='" + ruralHouse.getHouseNumber()+"'").list();
		
		boolean bussy = false;
		
		for (Object o : l){
			Offer of = (Offer)o;
			if (!(of.getFirstDay().compareTo(lastDay)>0 || of.getLastDay().compareTo(firstDay)<0)){
				bussy=true;
			}
		}
		
		if (bussy){
			ret = true;
			Offer of = (Offer)l.get(0);
			of.setIdUsuario(idUsuario);
			System.out.println(idUsuario);
			System.out.println(of.getOfferNumber());
			ss.update(of);
			//ss.createSQLQuery("UPDATE OFFER SET idUsuario='" + log.getIdUsuario() + "' WHERE offerNumber='" + of.getOfferNumber() + "'").executeUpdate();
			ss.getTransaction().commit();
		}
		return ret;
	}
	
	public Login check(String nombre, String pass){
		Session ss = s.getCurrentSession();
		ss.beginTransaction();
		List l =  ss.createQuery("FROM principal.Login WHERE password='" + pass +"'" + " AND name='" + nombre+ "'").list();
		Login _log = null;
		if (l.isEmpty()){
			_log = new Login(null,null,null,true);
		}else{
			_log = new Login(null,null,null,false);
		}
		return _log;
	}
	public Login checkLogin(String nombre, String pass){
		Session ss = s.getCurrentSession();
		ss.beginTransaction();
		List l =  ss.createQuery("FROM principal.Login WHERE name='" + nombre+ "'").list();
		Integer id =  ((java.lang.Long) ss.createQuery("SELECT COUNT(*) FROM principal.Login").list().get(0)).intValue();
		if (l.isEmpty()){
			System.out.println("no existe");
			log = new Login(++id,nombre,pass,true);
			ss.save(log);
			ss.getTransaction().commit();
		}else{
			System.out.println("existe");
			id =  (Integer) ss.createQuery("SELECT idUsuario FROM principal.Login WHERE password='" + pass +"'" + " AND name='" + nombre+ "'").list().get(0);
			log = new Login(id,nombre,pass,false);
		}
		return log;
	}
	
	public Login getLogin() { return log; }
	
	private RuralHouse getRuralHouse(Integer id){
		RuralHouse rh = null;
		for (RuralHouse r : rhs)
			if (r.getHouseNumber().compareTo(id)==0)
				rh = r;
		return rh;
	}
	
	public List<RuralHouse> getAllRuralHouses() { return rhs; }
	
	 public List<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) {
		 
		 Session ss = s.getCurrentSession();
		 
		 ss.beginTransaction();
		
		 List<Offer> ol = new Vector<Offer>();
		 
		List l =  ss.createQuery("FROM principal.Offer WHERE idUsuario='-1' AND houseNumber='" + rh.getHouseNumber()+"'").list();
		
		for (Object o : l){
			Offer of = (Offer)o;
			if (!(of.getFirstDay().compareTo(lastDay)>0 || of.getLastDay().compareTo(firstDay)<0)){
				ol.add((Offer)o);
			}
		}
		return ol;
	 }
	 



	public void close(){
		s.close();
		System.out.println("DataBase closed");
	}
}
