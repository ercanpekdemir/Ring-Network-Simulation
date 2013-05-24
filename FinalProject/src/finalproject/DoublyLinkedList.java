/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author gepgep
 */
public class DoublyLinkedList {
        Node head;
        Node tail;
        int counter;
      
        public  void addNode(int nodeId){
            Node newNode=new Node(nodeId);
            if(isEmpity()) {
                head=newNode;
            }
            else {
                tail.next=newNode;
                newNode.prev=tail;
            }
            tail=newNode;
            
            tail.next=head;
            head.prev=tail;
            
            counter++;
        }
        public boolean isEmpity(){
            return tail==null;
        }
        public Node getNode(int nodeId){
            Node current=new Node();
            current=head;
            
            for(int i=1;i<=counter;i++) {
                if(current.nodeId==nodeId)
                    return current;
                current=current.next;
            }
            return null;                     
        }
    }
