
public interface Subject {              //interfata subiect - Campaign
    
    public void addObserver(User user);
    public void removeObserver(User user);
    public void notifyAllObservers(Notification notification);
    
}
