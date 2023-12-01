
import java.util.Scanner;


public class Phonebook {

   
    public static Scanner input = new Scanner (System.in);
    public static BST <String, Contact> contacts = new BST <String, Contact>();
    public static BST <String, Event> events = new BST <String, Event>();
    


    

    public static int mainmenu ()
    {
        System.out.println("Please choose an option:");
        System.out.println("1. Add a contact");
        System.out.println("2. Search for a contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. Schedule an event");
        System.out.println("5. Print event details");
        System.out.println("6. Print contacts by first name");
        System.out.println("7. Print all events alphabetically");
        System.out.println("8. Exit");
        System.out.println("\nEnter your choice: ");
        int choice = input.nextInt();
        
        return choice;
    }
    
    public static int submenu()
    {
        System.out.println("Enter search criteria:");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email Address");
        System.out.println("4. Address");
        System.out.println("5. Birthday");
        System.out.println("\nEnter your choice: ");
        int choice = input.nextInt();
        return choice;
    }

    public static int submenu5()
    {
        System.out.println("Enter search criteria:");
        System.out.println("1. contact name");
        System.out.println("2. Event tittle");
        System.out.println("\nEnter your choice: ");
        int choice = input.nextInt();
        return choice;
    }
    
    //1. Add a contact
    public static void AddContact(){
        Contact c = new Contact();
        System.out.println("Enter the contact\'s name: ");
        input.nextLine();
        c.name = input.nextLine();
        
        if (!contacts.empty() && contacts.findkey(c.name))
        {
                System.out.println("Contact already exists");
                return;
        }
        System.out.print("Enter the contact's phone number:");
        c.phonenumber = input.nextLine();
        
        if (!contacts.empty() && (contacts.SearchPhone(c.phonenumber)))
        {
            System.out.println("Contact already exists");
            return;
        }
        System.out.print("Enter the contact's email address: ");
        c.emailaddress = input.nextLine();
        
        System.out.print("Enter the contact's address: ");
        c.address = input.nextLine();
        
        System.out.print("Enter the contact's birthday: ");
        c.birthday = input.nextLine();
        
        System.out.print("Enter any notes for the contact: ");
        c.notes = input.nextLine();
        
        if (contacts.insert(c.name, c))
            System.out.println("Contact added successfully!");
    }

    //2. Search for a contact
    public static void SearchContact(){
        int choice = submenu();
        if (contacts.empty())
            System.out.println("Contact not founded!");
        else
        {
            switch ( choice )
           {
               case 1:
               {
                    System.out.print("Enter the contact\'s name: ");
                    input.nextLine();
                    String name = input.nextLine();
                    
                    if (!contacts.empty() && contacts.findkey(name))
                    {
                        System.out.println("Contact founded!");
                        
                        System.out.println(contacts.retrieve().toString());
                        break;
                    }
                    System.out.println("Contact not founded!");
               }
               break;

               case 2:
               {
                   System.out.print("Enter the contact's phone number:");
                   input.nextLine();
                    String phonenumber = input.nextLine();
                   
                    if (!contacts.empty() && contacts.SearchPhone(phonenumber))
                    {
                        System.out.println("Contact founded!");
                        
                        System.out.println(contacts.retrieve());
                        break;
                    }
                    System.out.println("Contact not founded!");
               }
               break;

               case 3:
               {
                   System.out.print("Enter the contact's email address: ");
                   input.nextLine();
                    String emailaddress = input.nextLine();
                   
                    if (!contacts.empty())
                    {
                        contacts.SearchEmail(emailaddress);
                        System.out.println("Contact founded!");
                        break;
                    }
                    System.out.println("Contacts not founded!");
               }                
               break;

               case 4:
               {
                   System.out.print("Enter the contact's address: ");
                   input.nextLine();
                    String address = input.nextLine();
                    if (!contacts.empty() )
                    {
                        contacts.SearchAddress(address);
                        System.out.println("Contact founded!");
                        break;
                    }
                    System.out.println("Contact founded!");
               }                
               break;

               case 5:
               {
                   System.out.print("Enter the contact's Birthday: ");
                   input.nextLine();
                    String birthday = input.nextLine();
                    if (!contacts.empty() )
                    {
                        contacts.SearchBirthday(birthday);
                        System.out.println("Contact founded!");
                        break;
                    }
                    System.out.println("Contact not founded!");
               }
           }
        }            
    }
    
    //3. Delete a contact
    public static void DeleteContact()
    {
        Contact c = new Contact();
        
        System.out.print("Enter the contact\'s name: ");
        input.nextLine();
        c.name = input.nextLine();
       
        if (contacts.empty())
            System.out.println("Contact not founded!");
        else
        {
            
            if ( ! contacts.findkey(c.name))
                System.out.println("Contact not founded!");
            else
            {
                c = contacts.retrieve();
                contacts.removeKey(c.name);
                if (! c.events.empty())
                {
                    c.events.findFirst();
                    for ( int i = 0 ; i < c.events.size ; i++)
                    {
                        Event e = c.events.retrieve();
                        if (events.findkey(e.title))
                        {
                            Event Update_Event = events.retrieve();
                            Update_Event.removeContact(c.name);
                            if (Update_Event.contactsnames.empty())
                            {
                                events.removeKey(e.title);
                                System.out.println("Delete event, No cantact particapate");
                            }
                            else
                                events.update(Update_Event.title,Update_Event);
                            
                        }
                        c.events.findNext();
                    }
                }
                System.out.println("Contact Deleted Successfully !");
                System.out.println(c);
            }    
        }        
    }
    
    //4. Schedule an event
    public static void ScheduleEvent()
    {
        Contact c = new Contact();
        Event e = new Event();
        
        boolean event_Updated = false;
        boolean Added_Event_To_Contact = false;
        
        System.out.print("Enter event title: ");
        input.nextLine();
        e.title = input.nextLine();
        
        System.out.print("Enter contact name: ");
        c.name = input.nextLine();
        
        if ( ! contacts.empty() && contacts.findkey(c.name) == true)
        {
            System.out.print("Enter event date and time (MM/DD/YYYY HH:MM): ");
            e.date = input.nextLine();
            
            System.out.print("Enter event location: ");
            input.nextLine();
            e.location = input.nextLine();
            
            System.out.print("Enter the type of event you want to schedule: (E = Event, A = Appoinment) ");
            char t = input.next().charAt(0);
            if (t == 'E' || t =='e')
                e.EventOrapp = true;
            else
                e.EventOrapp = false;
            
            c = contacts.retrieve();
            Added_Event_To_Contact = c.addEvent(e);
            if (Added_Event_To_Contact)
            {
                // event added to contact
                contacts.update(c.name,c);
                if (!events.empty() && events.findkey(e.title))
                {
                    Event eventFound = events.retrieve();
                    if (eventFound.EventOrapp && (eventFound.EventOrapp == e.EventOrapp) 
                            && (eventFound.date.compareTo(e.date)== 0 ) 
                            && (eventFound.time.compareTo(e.time)== 0 ))
                    {
                        eventFound.contactsnames.insert(c.name);
                        events.update(eventFound.title,eventFound);
                        event_Updated = true;
                    }
                    else if (! eventFound.EventOrapp && (eventFound.EventOrapp == e.EventOrapp) 
                            && (eventFound.date.compareTo(e.date)== 0 ) 
                            && (eventFound.time.compareTo(e.time)== 0 ))
                    {
                        System.out.println("Could not add more than contact for an appoinment");
                        return;                        
                    }
                }
                if (! event_Updated)  
                {
                        e.contactsnames.insert(c.name);
                        events.insert(e.title, e);
                }
                    System.out.println("Event scheduled successfully!   ");
            }
            else
                System.out.println("Contact has conflict Event !  ");
        }
        else
            System.out.println("Cantcat not found !");
        
    }
    
    //5. Print event details
    public static void PrintEvent(){
        int choice = submenu5();
        switch ( choice )
        {
            case 1:
            {
                Contact c = new Contact();
                System.out.print("Enter the contact name :  ");
                input.nextLine();
                c.name = input.nextLine();
                        
                if (! contacts.empty() )
                {
                  if (contacts.findkey(c.name) == true)
                  {
                    System.out.println("Contact found !");
                    c = contacts.retrieve();

                    c.events.findFirst();
                    for (int i = 0 ; i < c.events.size ; i++)
                    {
                        Event e = c.events.retrieve();
                        
                        if (!events.empty() && events.findkey(e.title))
                            System.out.println(events.retrieve().toString());
                        c.events.findNext();
                    }
                    if (c.events.empty())
                        System.out.println("No events found for this contact !");
                     }
                else
                    System.out.println("Contact not founded !");
                }
                else
                    System.out.println("Contact not founded !");
            }
            break;

            case 2:
            {
                Event e = new Event();
                System.out.print("Enter the event title:  ");
                input.nextLine();
                e.title = input.nextLine();
                
                if (! events.empty() && events.findkey(e.title))
                {
                    System.out.println("Event founded!");
                    //System.out.println(events.retrieve());
                    Event ee = events.retrieve();
                    System.out.println(ee);
                }
                else
                    System.out.println("Event not founded !");
            }
            break;
        }
    }
    
    //6. Print contacts by first name
    public static void PrintContactsFirstName(){
       
        System.out.print("Enter the first name:");
        input.nextLine();
        String fname = input.nextLine();
        
        if (contacts.empty())
            System.out.println("No Contacts found !");
        else
            contacts.SearchSameFirstName(fname);
    }
    
    //7. Print all events alphabetically // O(n)
    public static void PrintAllEvents(){
        if (!events.empty())
            System.out.println(events.toString());
        else
            System.out.println("No events founded !");
    }
        
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Welcome to BST Phonebook!");
        int choice;
        do {
            choice = mainmenu();
            switch (choice)
            {
                case 1:
                    AddContact();
                    break;
                
                case 2:
                    SearchContact();
                    break;
                
                case 3:
                    DeleteContact();
                    break;
                
                case 4:
                    ScheduleEvent();
                    break;
                
                case 5:
                    PrintEvent();
                    break;
                    
                case 6:
                    PrintContactsFirstName();
                    break;
                    
                case 7:
                    PrintAllEvents();
                    break;
                    
                case 8:
                    System.out.println("Goodbye!");
                    break;
                default :
                    System.out.println("Bad choice! Try again");
            }
            System.out.println("\n\n");
        }while (choice != 8);
    }
        
}
