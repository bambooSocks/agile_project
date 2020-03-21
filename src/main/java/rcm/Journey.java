package rcm;

import java.util.LinkedList;

public class Journey {
	private int id;
	private String originPort;
	private String destinationPort;
	private String content;
	private String currentPosition;
	
	private Container container;
	private Client client;
	
	private LinkedList<ContainerStatus> history;

	public Journey(String originPort, String destinationPort, String content, 
				   Container container, Client client) {
		this.originPort = originPort;
		this.destinationPort = destinationPort;
		this.content = content;
		this.container = container;
		this.client = client;
	}
	
	
}
