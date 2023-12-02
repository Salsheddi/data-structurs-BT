
public class Event implements Comparable<Event> {
    String title;
    String date;
    String time;
    String location;
    LinkedListADT <String> contactsnames;
    boolean EventOrapp;  //this flag will be true if the object is event and false if its appointment

    public Event() {
        this.title = "";
        this.date = "";
        this.time = "";
        this.location = "";
        this.EventOrapp = true;
        this.contactsnames = new LinkedListADT<String> ();
    }
    
    public Event(String title, String date, String time, String location, boolean type, String contact) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.EventOrapp =type;
        this.contactsnames = new LinkedListADT<String> ();
        contactsnames.insert(contact);
    }

    public boolean addContact (String contact)
    {
        if ((this.EventOrapp == true) || ((this.EventOrapp == false)&&(contactsnames.size == 0)))
            return contactsnames.insert(contact);
        
        System.out.println(" can not add more than a contact for an appoinment");
        return false;
    }
    
    public boolean removeContact(String contact)
    {
            String name = contactsnames.remove(contact);
            if ( name != null)
                return true; 
            return false;
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
                    "\nContacts names:   \n"  + contactsnames.toString();
                
            System.out.println(str);
    }

    
    @Override
    public int compareTo(Event obj) {
        try {
            return (this.title.compareToIgnoreCase(obj.title));
        }
        catch (Exception e)
        {
            throw new UnsupportedOperationException("Unsupported");

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
            
             throw new UnsupportedOperationException("Unsupported");
            }
    }
    
    @Override
    public String toString() {
          String Eventorapp  ;  
            if(EventOrapp==true)
            Eventorapp="Event";
            else
            Eventorapp="Appoinment ";    
        String str = "\nEvent title: " + title +
                    "\nEvent date and time (MM/DD/YYYY HH:MM): " + date + time +
                   "\nEvent location: " + location + "\n" +
                   "\nType: " + Eventorapp + "\n" +
                    "\nContacts names:" + contactsnames.toString();
                
          return str;
    }
    
}
