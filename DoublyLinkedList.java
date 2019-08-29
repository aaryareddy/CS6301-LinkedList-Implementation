package axp170019;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import axp170019.SinglyLinkedList.Entry;
import axp170019.SinglyLinkedList.SLLIterator;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
    static class Entry<E> extends SinglyLinkedList.Entry<E> {
	Entry<E> prev;
	Entry(E x, Entry<E> next, Entry<E> prev) {
	    super(x, next);
	    this.prev = prev;
	}
    }
    
    
    public DoublyLinkedList() {
    	super();
        head = new Entry<>(null,null,null);
        
        tail = head;
    }
    
    public Iterator<T> iterator() { return new DLLIterator(); }
    
    protected class DLLIterator extends SLLIterator implements Iterator<T> {
  
    	DLLIterator() {
    	    super(); 	    
    	    
    	}

    	public boolean hasPrev() {
    	    return ((Entry<T>) cursor).prev != null;
    	}
    	
    	public T prev() {
    	    prev = (Entry<T>)cursor;
    	    cursor = ((Entry<T>)cursor).prev;
    	    ready = true;
    	    return cursor.element;
    	}

    	public void add(T x) {
    		add(new Entry<>(x, null,null));
    	}

    	// add new elements at middle
    	public void add(Entry<T> ent) {
    		if(cursor==null) {
    			// edge case
    			tail.next =ent;
    			ent.prev=(Entry<T>) tail;
    			tail=tail.next;
    			cursor = tail;
    	    	size++;
    		}
    		else {
    			
    	    	ent.next = (Entry<T>)cursor;
    	    	ent.prev =  ((Entry<T>)cursor).prev;
    	    	((Entry<T>)cursor).prev.next = ent;
    	    	((Entry<T>)cursor).prev=ent;
    	    	size++;
    		}
    	}    
    	
    	
    	
    

    	// Removes the current element (retrieved by the most recent next())
    	// Remove can be called only if next has been called and the element has not been removed
    	public void remove() {
    	    if(!ready) {
    		throw new NoSuchElementException();
    	    }
    	    
    	    prev.next = ((Entry<T>)cursor).next;
    	    if(cursor!=tail) {
    	    	((Entry<T>)cursor.next).prev= ((Entry<T>)cursor).prev;
    	    }
    	    // Handle case when tail of a list is deleted
    	    if(cursor == tail) {
    	    tail = prev;
    	    } 
    	    
    	    cursor = prev;
    	   
    	   // prev=((Entry<T>)cursor).prev;
    	    ready = false;  // Calling remove again without calling next will result in exception thrown
    	    size--;
    	}
        }  // end of class DLLIterator
    
    // Add new elements to the end of the list
    
    
    public void add(T x) {
    	add(new Entry<>(x, null,(Entry <T>)tail));
        }


    public static void main(String[] args) throws NoSuchElementException {
        int n = 12;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(Integer.valueOf(i));
        }
        lst.printList();

        DoublyLinkedList<Integer>.DLLIterator dl = (DoublyLinkedList<Integer>.DLLIterator)lst.iterator();
	Scanner in = new Scanner(System.in);
	
	whileloop:
	while(in.hasNext()) {
	    int com = in.nextInt();
	    switch(com) {
	    case 1:  // Move to next element and print it and add
		if (dl.hasNext()) {
		    System.out.println(dl.next());
			dl.add(in.nextInt());
			lst.printList();
		} else {
		    lst.add(in.nextInt());
		    dl.next();
		}
		break;
	    case 2:  
	    	// check has previou
	    	// Remove element
		dl.remove();
		lst.printList();
		break;
	    case 3:  // Move to next element and print it
			if (dl.hasNext()) {
			    System.out.println(dl.next());
				lst.printList();
			} else {
			    break whileloop;
			}
			break;
	    case 4:
	    	if (dl.hasPrev()) {
			    System.out.println(dl.prev());
				lst.printList();
			} else {
			    break whileloop;
			}
			break;
	    default:  // Exit loop
		 break whileloop;
	    }
	}
        lst.printList();
        
    }
}
