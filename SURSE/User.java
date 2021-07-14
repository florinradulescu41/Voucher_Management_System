
import java.util.*; //pentru ArrayList, etc


public class User {
    
    public Integer userID;
    public String name;
    public String email;
    public String password;
    enum UserType{
        ADMIN, GUEST
    }
    
    public UserType type;
    
    public void setGuest(){     //functii de modificare a tipului
        this.type = type.GUEST;
    }
    public void setAdmin(){
        this.type = type.ADMIN;
    }
    public UserVoucherMap uvm = new UserVoucherMap();   //dictionar
    
    public String getVouchers(){    //intoarce voucherele userului
        Integer i;
        List l = new ArrayList();
        for(i = 0; i < this.uvm.list.size(); i++){
            Voucher o = (Voucher) this.uvm.list.get(i);
            l.add(o);
        }
        
        return l.toString();
    }
    
    //colectia de notificari a userului
    public Collection user_notifications = new <Notification>Vector();
    
    public void addNotification(Notification n){    //adaugare notificare
        user_notifications.add(n);
    }
        
    public void update(Notification n){ 
        this.user_notifications.add(n);
    }
    
    public User(Integer uid, String un, String uem, String psw){
        this.userID = uid;
        this.name = un;
        this.email = uem;
        this.password = psw;
        this.type = this.type.GUEST;        //initializare tip cu guest
    }
    
    public User(){
        
    }
    
    public String toString(){               //pentru afisare
        return "userID:" + this.userID + "; name:" + this.name + "; email:" +
                this.email + "; type:" + this.type;
    }
}
