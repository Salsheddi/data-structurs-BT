public class BST<K extends Comparable<K>,T> {
    BSTNode<K,T> root, current;

   
    public BST() {
            root = current = null;
    }

    public boolean empty() {
            return root == null;
    }

    public boolean full() {
            return false;
    }

    public T retrieve () {
            return current.data;
    }

    public boolean findkey(K tkey) {
            BSTNode<K,T>  p = root;
            BSTNode<K,T>  q = root;

            if(empty())
                    return false;

            while(p != null) {
                    q = p;
                    if(p.key.compareTo(tkey) == 0) {
                            current = p;
                            return true;
                    }
                    else if(tkey.compareTo(p.key) < 0)
                            p = p.left;
                    else
                            p = p.right;
            }
            current = q;
            return false;
    }



    public boolean insert(K k, T val) {
            BSTNode<K,T>  p;
            BSTNode<K,T>  q = current;

            if(findkey(k)) {
                    current = q;  // findkey() modified current
                    return false; // key already in the BST
            }

            p = new BSTNode<K,T> (k, val);
            if (empty()) {
                    root = current = p;
                    return true;
            }
            else {
                    // current is pointing to parent of the new key
                    if (k.compareTo(current.key) < 0)
                            current.left = p;
                    else
                            current.right = p;
                    current = p;
                    return true;
            }
    }



    
    public boolean remove_key (K tkey){
            Boolean removed = new Boolean(false);
            BSTNode<K,T>  p;
            p = remove_aux(tkey, root, removed);
            current = root = p;
            return removed;
    }
    
    private BSTNode<K,T>  remove_aux(K key, BSTNode<K,T>  p, Boolean flag) {
            BSTNode<K,T>  q, child = null;
            if(p == null)
                    return null;
            if(key.compareTo(p.key) <0)
                    p.left = remove_aux(key, p.left, flag); //go left
            else if(key.compareTo(p.key) >0)
                    p.right = remove_aux(key, p.right, flag); //go right
            else {
                    flag = true;
                    if (p.left != null && p.right != null){ //two children
                            q = find_min(p.right);
                            p.key = q.key;
                            p.data = q.data;
                            p.right = remove_aux(q.key, p.right, flag);
                    }
            else {
                            if (p.right == null) //one child
                                    child = p.left;
                            else if (p.left == null) //one child
                                    child = p.right;
                            return child;
                    }
            }
            return p;
    }

    private BSTNode<K,T>  find_min(BSTNode<K,T>  p)
    {
            if(p == null)
                    return null;

            while(p.left != null){
                    p = p.left;
            }

            return p;
    }
    
    public boolean update(K key, T data)
    {
                remove_key(current.key);
                return insert(key, data);
    }

    
   
    
    @Override
    public String toString() 
    {
        String str = "";
        if ( root == null)
            return str;
        str = recin_order_Traversal ( root , str );
        return str;
    }
     //In order traversal
    private String recin_order_Traversal (BSTNode <K, T> p ,String str)
    {
        if (p == null)
            return "";
        else
        {
            str = recin_order_Traversal(p.left , str);
            str += p.data.toString() + "    ";
            str += recin_order_Traversal(p.right, str);
        }
        return str;
    }

   
    public boolean SearchPhone(String phone)
    {
        return SearchPhone_rec (root, phone);
    }
    private boolean SearchPhone_rec (BSTNode <K, T> p, String phone)
    {
        if (p == null)
            return false;
        else    if (((Contact)p.data).compareToPhone(phone) == 0)
        {
            current = p;
            
            return true;
        }
        
        return (SearchPhone_rec(p.left , phone) || SearchPhone_rec(p.right, phone));
    }
    

    
   
    public void SearchEmail(String email)
    {
        SearchEmail_rec (root, email);
    }
    private void SearchEmail_rec (BSTNode <K, T> p, String email)
    {
        if (p == null)
            return;
        
        else    if (((Contact)p.data).compareToEmail(email) == 0)
            System.out.println(p.data);
        
        SearchEmail_rec(p.left , email);
        SearchEmail_rec(p.right, email);
    }
    
    
    public void SearchBirthday(String birthday)
    {
        SearchBirthday_rec (root, birthday);
    }
    private void SearchBirthday_rec (BSTNode <K, T> p, String birthday)
    {
        if (p == null)
            return ;
        else    if (((Contact)p.data).compareToBirthday(birthday) == 0)
            System.out.println(p.data);
        
        SearchBirthday_rec(p.left , birthday);
        SearchBirthday_rec(p.right, birthday);
    }
     
    public void SearchAddress(String address)
    {
        SearchAddress_rec (root, address);
    }
    private void SearchAddress_rec (BSTNode <K, T> p, String address)
    {
        if (p == null)
            return ;
        else    if (((Contact)p.data).compareToAddress(address) == 0)
            System.out.println(p.data);
        
        SearchAddress_rec(p.left , address);
        SearchAddress_rec(p.right, address);
    }
    
    

       public boolean SearchSameEvent(Event event)
    {
        return SearchSameEvent_rec (root, event);
    }
    private boolean SearchSameEvent_rec (BSTNode <K, T> p, Event event)
    {
        if (p == null)
            return false;
        else    if (((Event)p.data).compareToSameEvent(event))
        {
            current = p;
            return true;
        }
        return (SearchSameEvent_rec(p.left , event) || SearchSameEvent_rec(p.right, event));
    }
    
    public void SearchSameFirstName(String name)
    {
        SearchSameFirstName_rec (root, name);
    }
    private void SearchSameFirstName_rec (BSTNode <K, T> p, String name)
    {
        if (p == null)
            return ;
        else    if (((Contact)p.data).compareFirstName(name) == 0)
            System.out.println(p.data);
       
        SearchSameFirstName_rec(p.left , name);
        SearchSameFirstName_rec(p.right, name);
    }

    
}
