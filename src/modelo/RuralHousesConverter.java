package modelo;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import principal.RuralHouse;
@FacesConverter("ruralHouseConverter")

public class RuralHousesConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		if (arg2==null) return null;
		ValueExpression vex =
				arg0.getApplication().getExpressionFactory()
                        .createValueExpression(arg0.getELContext(),
                                "#{setAvailability}", SetAvailability.class);

		SetAvailability houses = (SetAvailability)vex.getValue(arg0.getELContext());
		RuralHouse house = null;
		try{
		 house = houses.getRuralHouse(Integer.valueOf(arg2));
		}catch(Exception ex){}
        return house;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		// TODO Auto-generated method stub
		if (arg2 == null) return null;
		 return ((RuralHouse)arg2).getHouseNumber().toString();
		
	}

}
