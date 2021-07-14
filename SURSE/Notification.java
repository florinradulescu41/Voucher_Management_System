
import java.util.*; //pentru Date si ArrayList


public class Notification {         

    enum NotificationType{
        EDIT, CANCEL
    }
    
    public NotificationType type;
    public Date date;
    public Integer campaignID;
    
    //lista de coduri primite de catre utilizator
    public Collection list_of_codes = new ArrayList();
    
    public void addVoucher(Voucher v){
        this.list_of_codes.add(v.code);
    }
    
    public Notification(Date d, Integer cid){
        this.date = d;
        this.campaignID = cid;
    }
    
    public void edit(){         //tip notificare
        this.type = type.EDIT;
    }
    
    public void cancel(){
        this.type = type.CANCEL;
    }
    
    public Notification(){
        
    }
    
    @Override
    public String toString(){           //pentru afisare
        return "date:" + this.date + ";campaignID:" + this.campaignID +
                ";type:" + this.type;
    }
}
