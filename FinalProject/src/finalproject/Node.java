/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.util.LinkedList;

/**
 *
 * @author gepgep
 */
public class Node {
		LinkedList<Packet> nextQueue = new LinkedList<Packet>();
		LinkedList<Packet> prevQueue = new LinkedList<Packet>();
        int nodeId;
        double nextDataRate, prevDateRate;
        double prop;
        Node next,prev;
        boolean serverIdle_next=true;
        boolean serverIdle_prev=true;
        /**
		 * @return the serverIdle_next
		 */
		public boolean isServerIdle_next() {
			return serverIdle_next;
		}
		/**
		 * @param serverIdle_next the serverIdle_next to set
		 */
		public void setServerIdle_next(boolean serverIdle_next) {
			this.serverIdle_next = serverIdle_next;
		}
		/**
		 * @return the serverIdle_prev
		 */
		public boolean isServerIdle_prev() {
			return serverIdle_prev;
		}
		/**
		 * @param serverIdle_prev the serverIdle_prev to set
		 */
		public void setServerIdle_prev(boolean serverIdle_prev) {
			this.serverIdle_prev = serverIdle_prev;
		}
		
		public Node(){ 
        }
        public Node(int nodeNumber){
            this.nodeId=nodeNumber;
        }
		/**
		 * @return the nextQueue
		 */
		public LinkedList<Packet> getNextQueue() {
			return nextQueue;
		}
		/**
		 * @param nextQueue the nextQueue to set
		 */
		public void setNextQueue(LinkedList<Packet> nextQueue) {
			this.nextQueue = nextQueue;
		}
		/**
		 * @return the prevQueue
		 */
		public LinkedList<Packet> getPrevQueue() {
			return prevQueue;
		}
		/**
		 * @param prevQueue the prevQueue to set
		 */
		public void setPrevQueue(LinkedList<Packet> prevQueue) {
			this.prevQueue = prevQueue;
		}
		
		
		/**
		 * @return the nextDataRate
		 */
		public double getnextDataRate() {
			return nextDataRate;
		}
		/**
		 * @param nextDataRate the nextDataRate to set
		 */
		public void setnextDataRate(double nextDataRate) {
			this.nextDataRate = nextDataRate;
		}
		/**
		 * @return the prop
		 */
		public double getProp() {
			return prop;
		}
		/**
		 * @param prop the prop to set
		 */
		public void setProp(double prop) {
			this.prop = prop;
		}
		/**
		 * @return the prevDateRate
		 */
		public double getPrevDateRate() {
			return prevDateRate;
		}
		/**
		 * @param prevDateRate the prevDateRate to set
		 */
		public void setPrevDateRate(double prevDateRate) {
			this.prevDateRate = prevDateRate;
		}
        
    }