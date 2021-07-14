
import java.util.*; //pentru ArrayList, etc


public class VMS {
    
    //singleton -- pt constructor -- in main fac un vms = vms.getInstance
    private static final VMS INSTANCE = new VMS();
    
    private VMS(){
        
    }
    
    public static VMS getInstance(){
        return INSTANCE;
    }
    
    //colectie de campanii si de useri de tip ArrayList
    public ArrayList campaigns = new <Campaign>ArrayList();
    public ArrayList users = new <User>ArrayList();
    
    public String getCampaigns(){       //intoarce campaniile
        return this.campaigns.toString();
    }
    
    //verifica statusurile campaniilor
    public String verifyStatus(){
        String s = "";
        for(int id = 0; id < this.campaigns.size(); id++){
        Object o = this.campaigns.get(id);
        Campaign c = (Campaign) o;
        s = s + c.viewStatus();
        }
        return s;
    }
    
    public Campaign getCampaign(Integer id){   //intoarcere campanie cu id dat
        Object o = this.campaigns.get(id);
        Campaign c = (Campaign) o;
        return c;
    }
    
    public void addCampaign(Campaign campaign){     //adauga campanie
        this.campaigns.add(campaign);
    }
    
    public void updateCampaign(Integer id, Campaign campaign){
                      //inlocuire campania cu id dat cu o campanie cu date noi
        Object o = this.campaigns.get(id);
        Campaign c = (Campaign) o;
        if(c.status.equals(c.status.NEW) || c.status.equals(c.status.STARTED)){
            c = campaign;
        }
    }
    
    public void cancelCampaign(Integer id){     //anulare campanie
        Object o = this.campaigns.get(id);
        Campaign c = (Campaign) o;
        c.cancelCampaign();
    }
    
    public String getUsers(){       //intoarce utilizatorii
        return this.users.toString();
    }
    
    public User getUser(Integer id){    //intoarce utilizator cu id dat
        Object o = this.users.get(id);
        User u = (User) o;
        return u;
    }
    
    public void addUser(User user){         //adauga utilizator
        this.users.add(user);
    }
    
    public boolean isUserAdmin(Integer id){    
                                        //verifica daca userul este admin
        Object o = this.users.get(id);
        User u = (User) o;
        if (u.type.equals(u.type.ADMIN)){
            return true;
        }
        return false;
    }
}
