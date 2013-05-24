package finalproject;

public class Packet {
	int flowNo;
	int packetNo;

	Node source;
	Node destination;
	boolean clockwise;
	

	Packet(int packetNo, Node source, Node destination, boolean clockwise,
			int flowNo) {
		super();
		this.packetNo = packetNo;

		this.source = source;
		this.destination = destination;
		this.clockwise = clockwise;
		this.flowNo = flowNo;
		
	}



}
