package finalproject;


public class Events {

    double eventTime;
    String eventType;
    
    Packet p;
    Node n;
    
    
    Events prev, next;
    public Events() {
    }



	Events(double eventTime, String eventType,  Node n,Packet p) {
		super();
		this.eventTime = eventTime;
		this.eventType = eventType;	
		this.n = n;
		this.p = p;
	}



	public String getEventType() {
		return eventType;
	}

    public double getEventTime() {
        return eventTime;
    }

}
