
import java.util.Date;
public class Event implements Comparable<Event> {
    String title;
    String date;
    String time;
    String location;
    
    LinkedList <String> contacts_names;
    boolean EventOrapp;  //this flag will be true if the object is event and false if its appointment

    public Event() {
        this.title = "";
        this.date = "";
        this.time = "";
        this.location = "";
        this.EventOrapp = true;
        this.contacts_names = new LinkedList<String> ();
    }
    
    public Event(String title, String date, String time, String location, boolean t, String contact) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.EventOrapp =t;
        this.contacts_names = new LinkedList<String> ();
        contacts_names.insert(contact);
    }

    public boolean addContact (String contact)
    {
        if ((this.EventOrapp == true) || ((this.EventOrapp == false)&&(contacts_names.size == 0)))
            return contacts_names.insert(contact);
        
        System.out.println("Could not add more than contact for an appoinment");
        return false;
    }
    
    public boolean removeContact(String contact)
    {
            String name = contacts_names.remove(contact);
            if ( name != null)
                return true; 
            return false;
    }

    @Override
    public String toString() {
        String EventT = (this.EventOrapp == true)? "Event ": "Appoinment ";     
        String str = "\nEvent title: " + title +
                    "\nEvent date and time (MM/DD/YYYY HH:MM): " + date + time +
                   "\nEvent location: " + location + "\n" +
                   "\nType: " + EventT + "\n" +
                    "\nContacts names:   \n" + contacts_names.toString();
                
          return str;
    }

        public void display() {
            String Eventorapp  ;  
            if(EventOrapp==true)
            Eventorapp="Event";
            else
            Eventorapp="Appoinment ";
           
        String str = "\nEvent title: " + title +
                    "\nEvent date and time (MM/DD/YYYY HH:MM): " + date + time +
                   "\nEvent location: " + location + "\n" +
                   "\nType: " + Eventorapp + "\n" +
                    "\nContacts names:   \n"  + contacts_names.toString();
                
            System.out.println(str);
    }

    
    @Override
    public int compareTo(Event obj) {
        try {
            return (this.title.compareToIgnoreCase(obj.title));
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public boolean compareToSameEvent(Event obj) {
        try {
            return ((this.title.compareToIgnoreCase(obj.title) == 0) && 
                    (this.date.compareTo(obj.date) == 0) &&
                    (this.time.compareToIgnoreCase(obj.time) == 0) && (this.EventOrapp == obj.EventOrapp));
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
}
