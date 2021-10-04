package principal;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import principal.DataAccess;

//import domain.Booking;
import principal.Login;
import principal.Offer;
import principal.RuralHouse;
import principal.BadDates;
import principal.OverlappingOfferExists;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS  implements ApplicationFacadeInterfaceWS {

	/**
	 * 
	 */

	//Vector<Owner> owners;
	//Vector<RuralHouse> ruralHouses;
	//DataAccessInterface dB4oManager;
	private static FacadeImplementationWS myInstance = null;
	DataAccess da;
	

	private FacadeImplementationWS()  {
		da = new DataAccess();
		
		da.initializeDB();
		
	}
	
	public static void CreateInstance() { myInstance = new FacadeImplementationWS(); }
	
	public static FacadeImplementationWS getInstance() { return myInstance; }
	
	//yo
	public Login check(String nombre, String pass){
		return da.check(nombre, pass);
	}
	public Login checkLogin(String nombre, String pass){
		return da.checkLogin(nombre, pass);														// Revisar estos que creo que hay que modificar
	}
	
	public Login getLogin() { return this.da.getLogin(); }
	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws OverlappingOfferExists, BadDates {
		
		return da.createOffer(ruralHouse, firstDay, lastDay, price);
	
	}
	
	public boolean createReserve(Integer idUsuario, RuralHouse ruralHouse, Date firstDay, Date lastDay){
		return da.createReserve(idUsuario, ruralHouse, firstDay, lastDay);
	}
	
	public List<RuralHouse> getAllRuralHouses()  {
		System.out.println("Start: getAllRuralHouses");
		
		

		return da.getAllRuralHouses();

	}
	
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */

	@WebMethod public List<Offer> getOffersByDate( RuralHouse rh, Date firstDay,  Date lastDay) {
		
		return da.getOffers(rh, firstDay, lastDay);
	}	
		
		
	
	public void close() {
		
		da.close();
	}


	 public void initializeBD(){
		 
		 da.initializeDB();
		 
	} 

	}

