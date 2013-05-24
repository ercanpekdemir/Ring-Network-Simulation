/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import java.util.Scanner;

/**
 * 
 * @author gepgep
 */
public class Project1 {

	static Scanner input = new Scanner(System.in);
	static double simTime;
	static int numberOfNode;
	static double tx, tp;
	static DoublyLinkedList list;
	static int numberOfFlow;
	static Flows[] flows = new Flows[100];

	static FEL_Stack stack = new FEL_Stack();

	static double totalTransmissiontime;
	public static void main(String[] s) {
		// boolean b=true;
		// LinkedList<Packet> l=new LinkedList<Packet>();
		// l.add(new Packet(4,34,43,new Node(),new Node(),true,5,l));
		// while(b){
		//
		// try {
		// stack.push(new Events(4,"s",new Node(),l.pop()));
		// } catch (NoSuchElementException e) {
		// System.out.println("queue bos");
		// b=false;
		//
		// }
		// System.out.println("devam");
		// }

		initiateSimulation();

		Events temp = new Events();

		while (stack.firstEvent != null) {

			temp = stack.pop();
			simTime = temp.eventTime;

			if (temp.eventType.contentEquals("D")) {
				System.out.println("Pkt " + temp.p.packetNo + " (flow"
						+ temp.p.flowNo + ") has been transmitted from node"
						+ temp.n.nodeId + " at time " + simTime);
				
			
				// check the flow direc. of packet
				// same packet will have arr event
				if (flows[temp.p.flowNo].clockwise) {
					// change the queue inf of packet
					

					stack.push(new Events(simTime + temp.n.prop, "A",
							temp.n.next, temp.p));
				} else {
					// change the queue inf of packet
					

					stack.push(new Events(simTime + temp.n.prop, "A",
							temp.n.prev, temp.p));

				}

				// check the queue of the packet
				// if queue empty, no dep event
				try {
					if (flows[temp.p.flowNo].clockwise){
						totalTransmissiontime += 1000
								* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
								/ (flows[temp.p.flowNo].messageSize );
						
						stack.push(new Events(
								simTime
										+1000
										* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
										/ (flows[temp.p.flowNo].messageSize ),
								"D", temp.n, temp.n.prevQueue.pop()));// ayni queue degil
					}
					else{
						totalTransmissiontime += 1000
								* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
								/ (flows[temp.p.flowNo].messageSize );
						
						stack.push(new Events(
								simTime
										+1000
										* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
										/ (flows[temp.p.flowNo].messageSize ),
								"D", temp.n, temp.n.nextQueue.pop()));// ayni queue degil
					}

				} catch (NoSuchElementException e) {
					if (flows[temp.p.flowNo].clockwise)
						temp.n.setServerIdle_next(true);
					else
						temp.n.setServerIdle_prev(true);
				}
			}

			if (temp.eventType.contentEquals("A")) {
				System.out.println("Pkt " + temp.p.packetNo + " (flow"
						+ temp.p.flowNo + ") has arrived to node"
						+ temp.n.nodeId + " at time " + simTime);
				// if the node not the same with packet's dest. node
				// keep going with packet
				if (flows[temp.p.flowNo].destination.nodeId != temp.n.nodeId) {

					if (flows[temp.p.flowNo].clockwise) {

						if (temp.n.isServerIdle_next()) {
							totalTransmissiontime += 1000
									* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
									/ (flows[temp.p.flowNo].messageSize );
							
							temp.n.setServerIdle_next(false);
							stack.push(new Events(
									simTime
											+ 1000
											* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
											/ (flows[temp.p.flowNo].messageSize ),
									"D", temp.n, temp.p));

						} else {
							temp.n.prevQueue.add(temp.p);
						}

					} else {

						if (temp.n.isServerIdle_prev()) {
							temp.n.setServerIdle_prev(false);
							
							totalTransmissiontime += 1000
									* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
									/ (flows[temp.p.flowNo].messageSize );
							
							stack.push(new Events(
									simTime
											+ 1000
											* temp.n.nextDataRate* flows[temp.p.flowNo].numberOfPacket
											/ (flows[temp.p.flowNo].messageSize ),
									"D", temp.n, temp.p));

						} else {
							temp.n.nextQueue.add(temp.p);
						}

					}
				}

			}

		}
		System.out.println("Total transmission time "+totalTransmissiontime);

	}

	private static void initiateSimulation() {

		list = new DoublyLinkedList();

		int source, destination, numberOFBottleneck, numberOfPacket;
		double dataRate, messageSize;

		System.out.print("Please enter the number of nodes in the network: ");
		numberOfNode = input.nextInt();
		System.out.print("Please enter the data rate of each link: ");
		tx = input.nextDouble();
		System.out.print("Please enter the propagation delay of each link: ");
		tp = input.nextDouble();

		// establish network
		for (int i = 1; i <= numberOfNode; i++) {
			list.addNode(i);
		}
		// set initial data rate and prop delay
		for (int i = 1; i <= numberOfNode; i++) {
			list.getNode(i).setnextDataRate(tx);
			list.getNode(i).setPrevDateRate(tx);
			list.getNode(i).setProp(tp);
		}

		System.out.print("Please enter the number of bottleneck links: ");
		numberOFBottleneck = input.nextInt();
		// set custom data rates for bottleneck
		for (int i = 1; i <= numberOFBottleneck; i++) {
			System.out.print("Please enter bottleneck link " + i
					+ " and its data rate: ");
			source = input.nextInt();
			destination = input.nextInt();
			dataRate = input.nextDouble();

			list.getNode(source).setnextDataRate(dataRate);
			list.getNode(destination).setPrevDateRate(dataRate);
		}

		// number of flow
		System.out.print("Please enter the number of flows in the network: ");
		numberOfFlow = input.nextInt();

		// define each flows
		for (int i = 1; i <= numberOfFlow; i++) {
			System.out.print("Enter data for flow " + i
					+ " (source, destination, message size, packet #): ");
			// flows.add(new LinkedList<Packet>());
			source = input.nextInt();
			destination = input.nextInt();
			messageSize = input.nextDouble();
			numberOfPacket = input.nextInt();
			flows[i] = new Flows(findPath(numberOfNode, source, destination),
					list.getNode(source), list.getNode(destination),
					messageSize, numberOfPacket);

			// initially, add packet to source node queue ..
			for (int j = 1; j <= numberOfPacket; j++) {

				if (findPath(numberOfNode, source, destination))
					flows[i].source.prevQueue.addLast(new Packet(j,
							flows[i].source, flows[i].destination, findPath(
									numberOfNode, source, destination), i));
				else
					flows[i].source.nextQueue.addLast(new Packet(j,
							flows[i].source, flows[i].destination, findPath(
									numberOfNode, source, destination), i));

			}
		}

		// add initial dep. event into the stack
		for (int i = 1; i <= numberOfFlow; i++) {

			if (flows[i].clockwise) {
				stack.push(new Events(1000 * flows[i].source.nextDataRate* flows[i].numberOfPacket
						/ (flows[i].messageSize ),
						"D", flows[i].source, flows[i].source.prevQueue.pop()));

			}

			else
				stack.push(new Events(1000 * flows[i].source.prevDateRate* flows[i].numberOfPacket
						/ (flows[i].messageSize ),
						"D", flows[i].source, flows[i].source.nextQueue.pop()));

		}

	}

	public static boolean findPath(int totalNodes, int senderNode,
			int receiverNode) {

		DoublyLinkedList mylist = new DoublyLinkedList();
		for (int i = 1; i <= totalNodes; i++) {
			mylist.addNode(i);
		}
		// find correct path

		boolean clockwise;
		if (senderNode < receiverNode) {
			if ((receiverNode - senderNode) <= totalNodes - receiverNode
					+ senderNode) {

				clockwise = true;
			} else {

				clockwise = false;
			}
		} else {
			if (senderNode - receiverNode <= totalNodes + receiverNode
					- senderNode) {

				clockwise = false;
			} else {

				clockwise = true;
			}
		}
		return clockwise;

	}

}
