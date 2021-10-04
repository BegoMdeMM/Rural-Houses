package principal;

import java.util.List;
import java.util.Date;


//import domain.Booking;
import principal.Offer;
import principal.RuralHouse;
import principal.BadDates;
import principal.OverlappingOfferExists;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ApplicationFacadeInterfaceWS  {
	
	
	/*@WebMethod User createUser(String name, String pass )throws  OverlappingOfferExists, BadDates;*/
	
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */


	@WebMethod Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws  OverlappingOfferExists, BadDates;
	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	
	
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	@WebMethod public List<RuralHouse> getAllRuralHouses();
	
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */

	@WebMethod public List<Offer> getOffersByDate( RuralHouse rh, Date firstDay,  Date lastDay) ;
	
	
	@WebMethod public void initializeBD();

	
}
