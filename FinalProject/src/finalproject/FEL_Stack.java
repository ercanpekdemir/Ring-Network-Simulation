package finalproject;



/**
 *
 * @author gepgep
 */
public class FEL_Stack {

    Events firstEvent;
    Events lastEvent;
    int top;
    int flowNo;
    
    FEL_Stack() {
		super();
	}

	public FEL_Stack(int flowNo) {
		super();
		this.flowNo = flowNo;
	}

	static int dprtNO;
    static int arrvlNO;
    static int numberOfEvent;
    
	/**
	 * @return the numberOfEvent
	 */

	public boolean push(Events newEvent) {
        numberOfEvent++;
		if(newEvent.eventType.contentEquals("A"))
        	arrvlNO++;
        if(newEvent.eventType.contentEquals("D"))
        	dprtNO++;

        top++;
        if (isEmpity()) {
            firstEvent = newEvent;
            lastEvent = newEvent;
            return true;
        }
        if (newEvent.eventTime <= firstEvent.eventTime) {
            newEvent.next = firstEvent;
            firstEvent.prev = newEvent;
            firstEvent = newEvent;
            return true;
        }
        if (newEvent.eventTime >= lastEvent.eventTime) {
            lastEvent.next = newEvent;
            newEvent.prev = lastEvent;
            lastEvent = newEvent;
            return true;
        }
        if (newEvent.eventTime >= firstEvent.eventTime && newEvent.eventTime <= lastEvent.eventTime) {
            Events current = firstEvent.next;
            Events previous = firstEvent;
            while (newEvent.eventTime >= current.eventTime) {
                previous = current;
                current = current.next;
            }
            previous.next = newEvent;
            newEvent.prev = previous;
            newEvent.next = current;
            current.prev = newEvent;
        }
                
        return true;
    }

    public Events pop() {
    	numberOfEvent--;
    	top--;
        Events temp = firstEvent;
        if (!isEmpity()) {
            firstEvent = firstEvent.next;
        }
        return temp;
    }

    public boolean isEmpity() {
        return firstEvent == null;
    }

    public Events getFirstEvent() {
        return firstEvent;
    }
    
    
}
