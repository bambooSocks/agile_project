package rcm;

import java.util.LinkedList;

public class LogisticsCompany {

	private int id;
	private String name;
	private String address;
	private String refPerson;
	private String email;
	LinkedList<Container> containers;

	public LogisticsCompany(String name, String address, String refPerson, String email) {
		super();
		this.name = name;
		this.address = address;
		this.refPerson = refPerson;
		this.email = email;
	}
	
	
	public Response updateLocation(Container container) {
	    
	    if(this.containers.contains(container)) {
	        
	        // TO DO implement user input for x and y
	        Double x = 1212.0;
	        Double y= 2313.0;
	        container.setLocation(x,y);
	        
	        return new Response(402, "Location changed");
	    }else {
	        return new Response(333, "Location not changed");
	        
	    }
    }
	
}
