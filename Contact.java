
public class Contact implements Comparable<Contact> {
    String name;
    String phoneNumber;
    String emailAddress;
    String address;
    String birthday; 
    String notes;
    LinkedListADT<Event> events ; 
   
    public Contact() {
        this.name = "";
        this.phoneNumber = "";
        this.emailAddress = "";
        this.address = "";
        this.birthday = null;
        this.notes = "";
        events = new LinkedListADT<Event>();
    }

    public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
        events = new LinkedListADT<Event>();
    }
    public boolean addEvent( Event e)
    {
        if ((e.EventOrapp) || (!e.EventOrapp && e.contactsnames.size==0))
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

    public boolean removeEvent( String eventTitle)
    {
        if (events.empty())
            return false;
       Event temp = new Event();
       temp.title = eventTitle;
        if (events.search(temp))
        {
            events.remove(temp);
            return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(Contact con) {
        try {
            return (this.name.compareToIgnoreCase(con.name));
        }
        catch (Exception e)
        {
             
            throw new UnsupportedOperationException("Unsupported");
        }
    }


    public String getName() {
        return name;
        }
    
        public String getPhoneNumber() {
        return phoneNumber;
        }
    
        public String getEmailAddress() {
        return emailAddress;
        }
    
        public String getAddress() {
        return address;
        }
    
        public LinkedListADT<Event> getEvents()
        {
            return events;
        }
        
        public String getBirthday() {
            return birthday;
        }
    
        public String getNotes() {
        return notes;
        }
    
        public void setName(String name) {
        this.name = name;
        }
    
        public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        }
    
        public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        }
    
        public void setAddress(String address) {
        this.address = address;
        }
    
        public void setBirthday(String birthday) {
        this.birthday = birthday;
        }
    
        public void setNotes(String notes) {
        this.notes = notes;
        }

        
public int compareToBirthday(String birthday) {
        try {
            return (this.birthday.compareTo(birthday) ) ;
        }
        catch (Exception e)
        {
            
            throw new UnsupportedOperationException("Unsupported");
        }
    }
    
    public int compareFirstName(String name) {
        try {
            String [] arr = this.name.split(" ");
            return (arr[0].compareToIgnoreCase(name) ) ;
        }
        catch (Exception e)
        {
        
            throw new UnsupportedOperationException("Unsupported");
        }
    }



    
    public int compareToPhone(String Phone) {
        try {
            return (this.phoneNumber.compareToIgnoreCase(Phone));
        }
        catch (Exception e)
        {
             
            throw new UnsupportedOperationException("Unsupported");
        }
    }

    public int compareToEmail(String email) {
        try {
            return (this.emailAddress.compareToIgnoreCase(email));
        }
        catch (Exception e)
        {
             
            throw new UnsupportedOperationException("Unsupported");
        }
    }

    public int compareToAddress(String address) {
        try {
            return (this.address.compareToIgnoreCase(address));
        }
        catch (Exception e)
        {
            throw new UnsupportedOperationException("Unsupported");
        }
    }

    


    @Override
    public String toString() {
        return "\nName: " + name +
                    "\nPhone Number: " + phoneNumber +
                    "\nEmail Address: " + emailAddress +
                    "\nAddress: " +  address +
                    "\nBirthday: " + birthday +
                    "\nNotes: " + notes + 
                    "\nEvents : " + events.toString();

    }
}
