package finalproject;

public class Flows {
	boolean clockwise;
	Node source;
	Node destination;
	double messageSize;
	int numberOfPacket;

	Flows(boolean clockwise, Node source, Node destination, double messageSize,
			int numberOfPacket) {
		super();
		this.clockwise = clockwise;
		this.source = source;
		this.destination = destination;
		this.messageSize = messageSize;
		this.numberOfPacket = numberOfPacket;
	}
	/**
	 * @return the clockwise
	 */
	public boolean isClockwise() {
		return clockwise;
	}
	/**
	 * @param clockwise the clockwise to set
	 */
	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}
	/**
	 * @return the source
	 */
	public Node getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Node source) {
		this.source = source;
	}
	/**
	 * @return the destination
	 */
	public Node getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Node destination) {
		this.destination = destination;
	}
}
