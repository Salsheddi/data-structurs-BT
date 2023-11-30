
public class Contact implements Comparable<Contact> {
    String name;
    String phonenumber;
    String emailaddress;
    String address;
    String birthday; 
    String notes;
    LinkedList<Event> events ; 
   
    public Contact() {
        this.name = "";
        this.phonenumber = "";
        this.emailaddress = "";
        this.address = "";
        this.birthday = null;
        this.notes = "";
        events = new LinkedList<Event>();
    }

    public Contact(String name, String phonenumber, String emailaddress, String address, String birthday, String notes) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.emailaddress = emailaddress;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
        events = new LinkedList<Event>();
    }

    @Override
    public String toString() {
        return "\nName: " + name +
                    "\nPhone Number: " + phonenumber +
                    "\nEmail Address: " + emailaddress +
                    "\nAddress: " +  address +
                    "\nBirthday: " + birthday +
                    "\nNotes: " + notes + 
                    "\nEvents : " + events.toString();

    }

    public boolean addEvent( Event e)
    {
        if ((e.EventOrapp) || (!e.EventOrapp && e.contacts_names.size==0))
        {
                if (! events.empty())
                {
                    events.findFirst();
                    for ( int i = 0 ; i < events.size ; i++)
                    {
                        if ((events.retrieve().date.compareTo(e.date) == 0) 
                                && (events.retrieve().time.compareTo(e.time) == 0))
                            return false;
                    }
              }
            events.insert(e);
            return true;
        }
        return false;
    }

    public boolean removeEvent( String eTitle)
    {
        if (events.empty())
            return false;
       Event val = new Event();
       val.title = eTitle;
        if (events.search(val))
        {
            events.remove(val);
            return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(Contact o) {
        try {
            return (this.name.compareToIgnoreCase(o.name));
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    
    public int compareToPhone(String Phone) {
        try {
            return (this.phonenumber.compareToIgnoreCase(Phone));
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public int compareToEmail(String emailaddress) {
        try {
            return (this.emailaddress.compareToIgnoreCase(emailaddress));
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public int compareToAddress(String address) {
        try {
            return (this.address.compareToIgnoreCase(address));
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public int compareToBirthday(String birthday) {
        try {
            return (this.birthday.compareTo(birthday) ) ;
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public int compareFirstName(String name) {
        try {
            String [] all = this.name.split(" ");
            return (all[0].compareToIgnoreCase(name) ) ;
        }
        catch (Exception e)
        {
             //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
