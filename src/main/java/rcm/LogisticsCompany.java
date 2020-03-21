package rcm;

import java.util.LinkedList;

public class LogisticsCompany {

	private int id;
	private String name;
	private String address;
	private String refPerson;
	private String email;
	
	//optional
	LinkedList<Container> containers;

	public LogisticsCompany(String name, String address, String refPerson, String email) {
		super();
		this.name = name;
		this.address = address;
		this.refPerson = refPerson;
		this.email = email;
	}
	
}
