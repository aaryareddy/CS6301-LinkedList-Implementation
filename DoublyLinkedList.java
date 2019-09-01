//axp170019 - Aarya Vardhan Reddy Paakaala
//sxs180104 - Shivaprakash Sivagurunathan

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
        head = new Entry<>(null,null,null);
        tail = head;
    }
    
    public Iterator<T> iterator() { return new DLLIterator(); }
    
    protected class DLLIterator extends SLLIterator implements Iterator<T> {
  
    	DLLIterator() {
    	    super(); 	    
    	    
    	}
    
//Check if there is an element previous to the current position of the cursor
    	public boolean hasPrev() {
    	    return ((Entry<T>) cursor).prev != null;
    	}
//Prints the previous element
    	public T prev() {
    	    prev = (Entry<T>)cursor;
    	    cursor = ((Entry<T>)cursor).prev;
    	    ready = true;
    	    return cursor.element;
    	}

    	public void add(T x) {
    		add(new Entry<>(x, null,null));
    	}

//add element before the element returned by call to the next method is printed
    	public void add(Entry<T> ent) { 
    		if(cursor==null) {
    			//add element to the end of the list
    			tail.next =ent;
    			ent.prev=(Entry<T>) tail;
    			tail=tail.next;
    		}
    		else {
    			//add element to the location before the element returned by call to the next method
    			((Entry<T>)cursor).prev=ent;
    			ent.next=(Entry<T>)cursor;
    			ent.prev=(Entry<T>)prev;
    			((Entry<T>)prev).next=ent;
    		}
    		size++;
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
    	    ready = false;  // Calling remove again without calling next will result in exception thrown
    	    size--;
    	}
        }  // end of class DLLIterator
    
    // Add new elements to the end of the list
    public void add(T x) {
    	add(new Entry<>(x, null,(Entry <T>)tail));
        }


    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
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
	    case 1:  // Move to next element, print it and add the entered element
		if (dl.hasNext()) {
		    System.out.println(dl.next());
			dl.add(in.nextInt());
			lst.printList();
		} else {
		    lst.add(in.nextInt());
		    dl.next();
		    lst.printList();
		}
		break;
	    case 2: // Check if an element before the cursor exists if true print the previous element 
	    	if (dl.hasPrev()) {
			    System.out.println(dl.prev());
				lst.printList();
			} else {
			    break whileloop;
			}
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
	    	// Remove the element at cursor
			dl.remove();
			lst.printList();
			break;
	    default:  // Exit loop
		 break whileloop;
	    }
	}
        lst.printList();  
    }
}
