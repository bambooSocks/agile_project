package rcm;

import java.util.LinkedList;

public class Journey {
    private static int idGlobal;
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

    public void setID() {
        this.id = idGlobal;
        idGlobal ++;
        
    }
    
    public int getID() {
        return this.id; 
        
    }
    
    public static void main(String[] args) {
        Journey j1 = new Journey(null,null,null,null,null);  
        Journey j2 = new Journey(null,null,null,null,null); 
        System.out.println(j1.getID());
        System.out.println(j2.getID());
        
    } 


}
