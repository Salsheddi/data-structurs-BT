
import java.util.Scanner;


public class Phonebook {

   
    public static Scanner input = new Scanner (System.in);
    public static BST <String, Contact> contacts = new BST <String, Contact>();
    public static BST <String, Event> events = new BST <String, Event>();
    
    
    //1. Add a contact to the phonebook
    public static void AddContact(){
        Contact contact = new Contact();
        input.nextLine();
        System.out.print("Enter the contact's name: ");
        String name = input.nextLine();
        contact.setName(name);

        if (!contacts.empty() && contacts.findkey(contact.name))
        {
                System.out.println("Contact already exists");
                return;
        }
        System.out.print("Enter the contact's phone number:");
        String phonenumber = input.next();
        contact.setPhoneNumber(phonenumber);
        
        if (!contacts.empty() && (contacts.SearchPhone(contact.phoneNumber)))
        {
            System.out.println("Contact already exists");
            return;
        }
        System.out.print("Enter the contact's email address: ");
        input.nextLine();
        contact.setEmailAddress( input.nextLine());
                
        System.out.print("Enter the contact's address: ");
        contact.setAddress(input.nextLine());
                
        System.out.print("Enter the contact's birthday: ");
        contact.setBirthday(input.nextLine());
                
        System.out.print("Enter any notes for the contact: ");
        contact.setNotes(input.nextLine());
        System.out.println("");
        
        if (contacts.insert(contact.name , contact))
        System.out.println("\n Contact added successfully!"); 
    }

    //2. Search for a contact in the phonebook
    public static void SearchforAContact(){
        int c = submenu1();
        if (contacts.empty())
            System.out.println("Contact not founded!");
        else
        {
            switch (c)
           {
               case 1:
               {
                    System.out.print("Enter the contact's name: ");
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
                    System.out.print("Enter the contact's phone number: ");
                    input.nextLine();
                    String phoneNumber = input.nextLine();
                   
                    if (!contacts.empty() && contacts.SearchPhone(phoneNumber))
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
                   System.out.print("Enter the contact's email: ");
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
        
        System.out.print("Enter the contact name: ");
        c.name = input.nextLine();
       
        if(contacts.empty())
            System.out.println("Contact not founded");
        else
        {
            
            if ( ! contacts.findkey(c.name))
                System.out.println("Contact not founded!");
            else
            {
                c = contacts.retrieve();
                contacts.remove_key(c.name);
                //Contact has been removed successfully!
                //now we delete the events
                if (! c.events.empty())
                {
                    c.events.findFirst();
                    for ( int i = 0 ; i < c.events.size ; i++)
                    {
                        Event e = c.events.retrieve();
                        if (events.findkey(e.title))
                        {
                            Event temp_Event = events.retrieve();
                            temp_Event.removeContact(c.name);
                            if (temp_Event.contactsnames.empty())
                            {
                                events.remove_key(e.title);
                                System.out.println("No othen cantact so the event has been deleted");
                            }
                            else
                                events.update(temp_Event.title,temp_Event);
                            
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
    public static void ScheduleAnEvent()
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
            e.time = input.nextLine();
            System.out.print("Enter event location: ");
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
    public static void PrintEventdetails(){
        int choice = submenu2();
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
    public static void PrintContactsbyFirstName(){
       
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
        
    public static int mainmenu ()
    {
        System.out.println("Please choose an option:");
        System.out.println("1. Add a contact");
        System.out.println("2. Search for a contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. Schedule an event/appointment");
        System.out.println("5. Print event details");
        System.out.println("6. Print contacts by first name");
        System.out.println("7. Print all events alphabetically");
        System.out.println("8. Exit");
        System.out.println("\nEnter your choice: ");
        int c = input.nextInt();
        
        return c;
    }
    
    public static int submenu1()
    {
        System.out.println("Enter search criteria:");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email Address");
        System.out.println("4. Address");
        System.out.println("5. Birthday");
        System.out.println("\nEnter your choice: ");
        int c= input.nextInt();
        return c;
    }

    public static int submenu2()
    {
        System.out.println("Enter search criteria:");
        System.out.println("1. contact name");
        System.out.println("2. Event tittle");
        System.out.println("\nEnter your choice: ");
        int choice = input.nextInt();
        return choice;
    }
    public static void main(String[] args) {
        
        
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
                    SearchforAContact();
                    break;
                
                case 3:
                    DeleteContact();
                    break;
                
                case 4:
                    ScheduleAnEvent();
                    break;
                
                case 5:
                    PrintEventdetails();
                    break;
                    
                case 6:
                    PrintContactsbyFirstName();
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
