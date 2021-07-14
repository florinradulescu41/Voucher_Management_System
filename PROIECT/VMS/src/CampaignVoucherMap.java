
import java.util.*;


public class CampaignVoucherMap<K,V> extends ArrayMap<String, List<Voucher>>{
    
    //cheia este emailul asignat voucherului adaugat
    //valoarea este o lista de vouchere asignata emailului
    //adaugarea valorii se face dupa cheie
    
    public boolean addVoucher(Voucher v){
        
        List<Voucher> voucher_list = this.get(v.email);   //cheia este emailul
        
        if(voucher_list == null){               //creare lista daca nu exista
            voucher_list = new ArrayList<>();
        }
        
        if(!voucher_list.contains(v)){      //daca nu exista voucherul in lista
            voucher_list.add(v);            //se adauga in lista
            put(v.email, voucher_list);     /*lista se actualizaeaza
                                              in campaignvouchermap dupa cheie*/
            
            return true;                    //daca s-a adaugat cu succes
        }
        
        return false;                       //daca nu s-a putut adauga
    }
}
