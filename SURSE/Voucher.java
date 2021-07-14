
import java.time.*;
import java.util.*;


public abstract class Voucher {     //clasa abstracta
    
    public Integer ID;
    public String code;
    enum VoucherStatusType{
        USED, UNUSED
    }
    public VoucherStatusType status;
    public Date date_of_usage;
    public String email;
    public Integer campaignID;
    
    public Voucher(){
        
    }
    
    public Voucher(Integer vid, String c, String e, Integer cid){
        this.ID = vid;
        this.code = c;
        this.email = e;
        this.campaignID = cid;
        this.status = this.status.UNUSED; //initializare ca nefolosit
    }
    
    public void hasBeenUsed(){          //modificare status
        this.status = this.status.USED;
    }
    
    public void reverseUsage(){
        this.status = this.status.UNUSED;
    }
    
}