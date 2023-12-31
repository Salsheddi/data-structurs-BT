
public class LinkedListADT<T extends Comparable<T>> {
    

    private Node<T> head;
    private Node<T> current;
    int size;
    
    public LinkedListADT () {
        size = 0;
        head = current = null;
    }
    public boolean empty () {
        return head == null;
    }
    public boolean last () {
        return current.next == null;
    }
    public boolean full () {
            return false;
    }
    public void findFirst () {
            current = head;
    }
    public void findNext () {
            current = current.next;
    }
    public T retrieve () {
            return current.data;
    }
    public void update (T val) {
            current.data = val;
    }
    
    public boolean insert (T val) {
           
            Node<T> tmp;
            if (empty()) {
                    current = head = new Node (val);
            }
            else {
                if ( head.getData().compareTo(val) >0)
                {
                    tmp = new Node(val);
                    tmp.setNext(head);
                    head = tmp;
                }
                else
                {
                    Node<T> prev = null;
                    current = head;
                    
                    while (( current != null ) && (current.getData().compareTo(val) <= 0))
                    {
                        prev = current;
                        current = current.getNext();
                    }
                    tmp = new Node (val);
                    if ( current != null)
                    {
                        tmp.next = current;
                        prev.next = tmp;
                        current = tmp;
                    }
                    else
                        current = prev.next =tmp;
                }
            }
            size++;
            return true;
    }

    public boolean search (T val)
    {
        if (head == null)
            return false;
        
        Node<T> node  = head;
        while ((node != null) && (node.getData().compareTo(val) != 0))
            node = node.getNext();
        if ((node != null) && (node.getData().compareTo(val) == 0))
        {
            current = node;
            return true;
        }
        return false;
    }
            
    public T remove (T val) {
            
        if (search (val) == false)
         return null;

        T data = current.getData();
        
        if (current == head) {
                head = head.next;
        }
        else {
                Node tmp = head;

                while (tmp.next != current)
                        tmp = tmp.next;
               tmp.next = current.next;
        }
        if (current.next == null)
                current = head;
        else
                current = current.next;
        size -- ;
        return data;    
    }

     @Override
    public String toString() 
    {
           Node<T>  p = head;
            String str = "" ;
            
            while ( p != null)
            {
                str += p.getData().toString();
                str +=  "\n";
                p = p .next;
            }
            return str;
        }
}