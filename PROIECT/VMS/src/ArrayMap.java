
import java.util.*; //pentru AbstarctMap/Lists, etc.


public class ArrayMap<K,V> extends AbstractMap<K,V>{
    
    //implementare generica ArrayMapEntry pe baza laboratorului
    class ArrayMapEntry<K,V> implements Map.Entry<K,V>{
        
        public K key;
        public V value;
        
        public ArrayMapEntry(K k, V v){
            this.key = k;
            this.value = v;
        }
        
        @Override
        public K getKey(){
            return this.key;
        }

        @Override
        public V getValue(){
            return this.value;
        }

        @Override
        public V setValue(V v){
            V aux = this.value;
            this.value = v;
            return aux;
        }
        
        @Override
        public String toString(){
            return "Key:" + getKey() + "; Value:" + getValue();
        }
        
        @Override
        public boolean equals(Object o){
           
            if(!(o instanceof ArrayMapEntry))
	        return false;
	    
           ArrayMapEntry<K,V> obj = (ArrayMapEntry<K,V>) o;
	   
           return (this.getKey() == null ? obj.getKey() == null 
                   : this.getKey().equals(obj.getKey())) &&
                    (this.getValue() == null ? obj.getValue() == null 
                     : this.getValue().equals(obj.getValue()));
        }

        @Override
        public int hashCode(){
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^
                    (this.getValue() == null ? 0 : this.getValue().hashCode());
        }
        
    }
    
    public ArrayList<ArrayMapEntry<K,V>> list = null;
    
    public ArrayMap(){
        this.list = new ArrayList<>();
    }
    
    @Override
    public V put(K key, V value){
        
        Map.Entry<K,V> entry = null;
        int i;
        
        for(i = 0; i < list.size(); i++){
            entry = list.get(i);
            if(key.equals(entry.getKey()))
                break;
        }
        
        V prev_value = null;
        
        if(i < list.size()){
            prev_value = entry.getValue();
            entry.setValue(value);
        }
        
        else{
            list.add(new ArrayMapEntry(key, value));
        }
        
        return prev_value;
    }
    
    @Override
    public boolean containsKey(Object key){
        
        Map.Entry<K,V> entry = null;
        int i;
        
        for(i = 0; i < list.size(); i++){
            entry = list.get(i);
            if (key.equals(entry.getKey()))
                return true;
        }
        
        return false;
    }
    
    @Override
    public V get(Object key){
        
        Map.Entry<K,V> entry = null;
        int i;
        
        for(i = 0; i < list.size(); i++){
            entry = list.get(i);
            if(key.equals(entry.getKey()))
                return entry.getValue();
        }
        
        return null;
    }
    
    @Override
    public int size(){
        return list.size();
    }
    
    @Override
    public Set entrySet() {
        
        Set<Map.Entry<K,V>> set = new HashSet<>();
	set.addAll(list);
	return set;
    }
    
    @Override
    public String toString (){
	
        String s = "";
	for(int i = 0; i < list.size(); ++i){
	    s += list.get(i);
	}
	
        return s;
    }
    
}
