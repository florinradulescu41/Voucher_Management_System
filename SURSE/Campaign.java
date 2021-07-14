
import java.time.*; //pentru LocalDateTime
import java.util.*; //pentru Date si List


public class Campaign {
    
    public Integer campaignID; //nu am facut unic
    public String name;
    public String description;
    public Date start_date;
    public Date end_date;
    public Integer total_vouchers;
    public Integer current_vouchers;
    enum CampaignStatusType{            //enumerare
        NEW, STARTED, CANCELLED, EXPIRED
    }
    public CampaignStatusType status;
    public List<User> observers; //lista de observeri de tip User
    
    public Campaign(){
        
    }
    
    public Campaign(Integer cid, String n, String d, Date sd, Date ed,
            Integer tv, String st){
        this.campaignID = cid;
        this.name = n;
        this.description = d;
        this.start_date = sd;
        this.end_date = ed;
        this.total_vouchers = tv;
        this.current_vouchers = tv; //la inceput sunt egale
        
        this.status = this.status.NEW; /*initializez cu NEW, se va schimba
                                        pe parcurs in functie de date */
        this.strategyType = st;
    }
    
    public Campaign(Integer cid, String n, String d, Date sd, Date ed,
            Integer tv){        //acelasi constructor, dar fara strategyType
        this.campaignID = cid;
        this.name = n;
        this.description = d;
        this.start_date = sd;
        this.end_date = ed;
        this.total_vouchers = tv;
        this.current_vouchers = tv;
        this.status = this.status.NEW;
    }
    
    public void startCampaign(){            //functii de modificare status
        this.status = this.status.STARTED;
    }
    
    public void cancelCampaign(){
        this.status = this.status.CANCELLED;
    }
    
    public void endCampaign(){
        this.status = this.status.EXPIRED;
    }
    
    @Override
    public String toString(){           //pentru afisare a membrilor campaniei
        return "CampaignID:" + this.campaignID + "; name:" + this.name 
                + "; description:" + this.description + "; std"
                + this.start_date + "; end" + this.end_date +
                "; tv" + this.total_vouchers + "; stt" + this.strategyType;
    }
    
    public String viewStatus(){        //fucntie de verificare status campanie
        return "CampaignID:" + this.campaignID + "; status:" + this.status;
    }
    
    public CampaignVoucherMap cvm = new CampaignVoucherMap();    //dictionar
    
    public String strategyType;   //nu au fost implementate tipuri de strategii
    
    public List getVouchers(){  /*intoarce o lista cu toate 
                                    voucherele din campanie*/
        Integer i;
        List l = new ArrayList();
        for(i = 0; i < this.cvm.list.size(); i++){
            Voucher o = (Voucher) this.cvm.list.get(i);
            l.add(o);
        }
        
        return l;
    }
    
    public Voucher getVoucher(String code){     //intoarce voucher cu codul dat
        Integer i;
        Integer j;
        for(i = 0; i < this.cvm.list.size(); i++){
            List v_list = (List) this.cvm.list.get(i);
                                                /* intai se formeaza lista de
                                                vouchere, apoi se cauta in ea*/
            for (j = 0; j< v_list.size(); j ++){
                Voucher v = (Voucher) v_list.get(j);
                if (v.code.equals(code)){
                    return v;
                }
            }
        }
        
        return null;
    }
    
    public void generateVoucher(String email, String voucherType, float value){
                                                //genereaza voucher
        
        if (voucherType.equals("GiftVoucher")){
                                               //generare pe baza tipului
            Voucher v = new GiftVoucher(value);
            v.email = email;
            v.campaignID = this.campaignID;
            Integer c = cvm.list.size();
            v.code = c.toString();             /*se genereaza cod in functie
                                                de pozitia in lista*/
            this.cvm.addVoucher(v);            //se dauga in campaignvouchermap
        
        }
        
        if (voucherType.equals("LoyaltyVoucher")){
            Voucher v = new LoyaltyVoucher(value);
            v.email = email;
            v.campaignID = this.campaignID;
            Integer c = cvm.list.size();
            v.code = c.toString();
            this.cvm.addVoucher(v);
        
        }
        
    }
    
    public void redeemVoucher(String code, Date date){      //folosire voucher
        Voucher v = getVoucher(code);
        v.date_of_usage = date;
        v.hasBeenUsed();            //marcare voucher status ca folosit
        
    }
    
    public List<User> getObservers() {          //intoarce lista de observatori
        return observers;
    }
    
    public void addObserver(User user){         //adauga observator
        observers.add(user);
    }
    
    public void removeObserver(User user){      //sterge observator
        observers.remove(user);
    }
    
    public void notifyAllObservers(Notification notification){  
                                                //notifica toti observatorii
        Integer i;
        for (i = 0; i < observers.size(); i ++){     //pentru fiecare din lista
            observers.get(i).update(notification);     //apelare fucntie update
        }
    }
    
}
