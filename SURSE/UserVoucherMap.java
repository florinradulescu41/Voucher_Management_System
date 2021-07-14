
import java.util.*;


public class UserVoucherMap<K,V> extends ArrayMap<Integer, List<Voucher>>{
    
    //cheia este reprezentata de id-ul campaniilor
    //valoarea este reprezentata de colectia de vouchere din fiecare campanie
    
    public boolean addVoucher(Voucher v){   //adaugare voucher
        
        List<Voucher> voucher_list = this.get(v.campaignID);
        
        if(voucher_list == null){
            voucher_list = new ArrayList<>();
        }
        
        if(!voucher_list.contains(v)){
            voucher_list.add(v);
            put(v.campaignID, voucher_list);
            
            return true;
        }
        
        return false;
    }
    
}
