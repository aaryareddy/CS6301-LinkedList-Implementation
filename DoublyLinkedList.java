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
    
    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;
    
    public DoublyLinkedList() {
        head = new Entry<>(null,null,null);
        
        tail = head;
        size = 0;
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
    	    	ent.next = (Entry<T>)cursor;
    	    	ent.prev =  ((Entry<T>)cursor).prev;
    	    	((Entry<T>)cursor).prev.next = ent;
    	    	((Entry<T>)cursor).prev=ent;
    	    	size++;
    	}    
    	
    	
    	
    

    	// Removes the current element (retrieved by the most recent next())
    	// Remove can be called only if next has been called and the element has not been removed
    	public void remove() {
    	    if(!ready) {
    		throw new NoSuchElementException();
    	    }
    	    prev.next = cursor.next;
    	    // Handle case when tail of a list is deleted
    	    if(cursor == tail) {
    		tail = (Entry<T>)prev;
    	    }
    	    cursor = prev;
    	    ready = false;  // Calling remove again without calling next will result in exception thrown
    	    size--;
    	}
        }  // end of class DLLIterator
    
    // Add new elements to the end of the list
    
    
    public void add(T x) {
    	add(new Entry<>(x, null,null));
        }

        public void add(Entry<T> ent) {
        	System.out.println('a');
        	ent.prev= tail;
        	tail.next = ent;
        	tail = (Entry<T>) tail.next;
        	size++;
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
	    case 1:  // Move to next element and print it
		if (dl.hasNext()) {
		    System.out.println(dl.next());
			dl.add(in.nextInt());
			lst.printList();
		} else {
		    break whileloop;
		};
		break;
	    case 2:  
	    	// check has previou
	    	// Remove element
		dl.remove();
		lst.printList();
		break;
	    default:  // Exit loop
		 break whileloop;

	    }
	}
	lst.printList();
	lst.unzip();
        lst.printList();
        
    }
}
