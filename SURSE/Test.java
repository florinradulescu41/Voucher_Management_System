
import java.io.*; //pentru File + exceptii
import java.text.*; //pentru SimpleDateFormat.parse + exceptii
import java.time.*; //pentru LocalDateTime
import java.util.*; //pentru Scanner si Date

public class Test {                 //contine clasa main; pentru rulare
    
    
    public static void main(String args[]) 
            throws FileNotFoundException, ParseException, IOException{
                                    //posibile exceptii
        
        //stergere fisier vechi pentru a pune unul nou in locul lui
        File of = new File("test00\\myoutput.txt");
        of.delete();
        
        File nf = new File("test00\\myoutput.txt");
        if(nf.createNewFile()){
            System.out.println("File created:" + nf.getName());
        }
        
        //creare un writer care sa poata puna rezultatul in fisier
        FileWriter mw = new FileWriter(
                "test00\\myoutput.txt");
        
        //outputul va fi salvat in writer
        String output = "";
        
        //fereastra pentru afisarea interfetei
        Window w = new Window("VMS Menu");
        
        //folosire singleton pattern pentru a se instantia un singur obiect VMS
        VMS vms = VMS.getInstance();
        
        //citire campanii
        System.out.println("Citire campanii");
        File text = new File(
                "test00\\input\\campaigns.txt");
     
        //creare instanta scanner pentru a citi din fisier
        Scanner scnr = new Scanner(text);
     
        //citire fiecare linie din fisier folosind clasa scanner
        String nr_campanii = scnr.nextLine();
        
        //citire numar campanii
        System.out.println("Nr de campanii este " + nr_campanii);
        
        //citire data curenta a aplicatiei - atribuita lui now
        Date now = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm").parse(scnr.nextLine());
        System.out.println("Data curenta este " + now);
        
        //citire campanii din fisier
        while(scnr.hasNextLine()){         //pana la terminarea fisierului

            String line = scnr.nextLine();      //citire pe fiecare linie
            String[] array = line.split(";");   /*impartirea datelor dupa ;
                                                si adaugarea lor in array*/
            
            Campaign nc = new Campaign(
                    Integer.valueOf(array[0]), array[1], array[2], 
                     new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(array[3]), 
                      new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(array[4]), 
                       Integer.valueOf(array[5]), array[6]);
                        /*constructie campanie folosind datele din fisier
                        incluse si impartite in array*/
                        
            if(nc.start_date.after(now)){       /*decidere status campanie
                                                in functie de data curenta
                                                si datele campaniei*/
                nc.status = nc.status.NEW;
            }
            if(nc.start_date.equals(now)||nc.start_date.before(now)){
                nc.status = nc.status.STARTED;
            }
            if(nc.end_date.equals(now)||nc.end_date.before(now)){
                nc.status = nc.status.EXPIRED;
            }

           vms.addCampaign(nc);          /*adaugare fiecare campanie construita
                                         in colectia de campanii a vms*/
            
        }                   //incheiere while la terminarea liniilor din fisier
        
        //citire useri la fel ca citirea campaniilor, din fisierul specific
        System.out.println("Citire useri");
        text = new File(
                "test00\\input\\users.txt");
     
        //folosire tot metoda cu scanner
        scnr = new Scanner(text);
     
        //citire fiecare linie
        String nr_useri = scnr.nextLine();
        System.out.println("Nr de useri este " + nr_useri);
        
        //pana la terminare fisier
        while(scnr.hasNextLine()){

            String line = scnr.nextLine();
            String[] array = line.split(";");
            
            User u = new User(Integer.valueOf(array[0]), array[1],
                               array[3], array[2]);
            
            if(array[4].equals("ADMIN")){
                u.setAdmin();
            }
            if(array[4].equals("GUEST")){
                u.setGuest();
            }
            
            vms.addUser(u);     //adaugare user in colectie din vms
            
        }
        
        //citire evenimente
        
        System.out.println("Citire eventuri");
        text = new File(
                "test00\\input\\events.txt");
        
        scnr = new Scanner(text);
        
        Date app_date = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm").parse(scnr.nextLine());
        System.out.println("Data aplicatiei este " + app_date);
        String nr_events = scnr.nextLine();
        System.out.println("Nr de evenimente este " + nr_events);
        
        while(scnr.hasNextLine()){
            
            String line = scnr.nextLine();
            String[] array = line.split(";");
            
            Integer cui = Integer.valueOf(array[0]); /* id-ul utilizatorului
                                                     care executa comanda*/
            
            String function = array[1];          //comanda va apela o functie
            
            if (function.equals("addCampaign") && vms.isUserAdmin(cui - 1)){
                Campaign nc = new Campaign(
                    Integer.valueOf(array[2]), array[3], array[4],
                     new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(array[5]), 
                      new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(array[6]), 
                       Integer.valueOf(array[7]), array[8]);
                        /* exemplu: pentru functia addCampaign se va construi o
                        campanie noua cu parametrii dati si se va adauga in
                        colectia de campanii a vms*/

                if(nc.start_date.after(now)){
                    nc.status = nc.status.NEW;
                }
                if(nc.start_date.equals(now)||nc.start_date.before(now)){
                    nc.status = nc.status.STARTED;
                }
                if(nc.end_date.equals(now)||nc.end_date.before(now)){
                    nc.status = nc.status.EXPIRED;
                }
               vms.addCampaign(nc);
               
            }
            
            if (function.equals("editCampaign") && vms.isUserAdmin(cui - 1)){
                
                /*practic, se creeaza o noua campanie si se inlocuieste
                    cu ce existenta*/
                
                Integer cci = Integer.valueOf(array[2]); 
                                                    //id-ul campaniei de editat
                Campaign nc = new Campaign(         //nc e noua campanie
                    Integer.valueOf(array[2]), array[3], array[4],
                     new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(array[5]), 
                      new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(array[6]), 
                       Integer.valueOf(array[7]));
                
                vms.updateCampaign(cci - 1, nc);
                    /*campania cu id-ul n este salvata in lista de campanii
                    din vms pe pozitia n-1*/
            }
            
            if (function.equals("cancelCampaign") && vms.isUserAdmin(cui - 1)){
                Integer cci = Integer.valueOf(array[2]);
                vms.cancelCampaign(cci - 1); //functie specifica
            }
            
            if (function.equals("generateVoucher") && vms.isUserAdmin(cui - 1)){
                Integer cci = Integer.valueOf(array[2]);
                vms.getCampaign(cci - 1).generateVoucher(
                        array[3], array[4], Integer.valueOf(array[5]));
            }
            
            if (function.equals("redeemVoucher")){
                /*aceste linii sunt comentate deoarece functia redeemVoucher
                    nu functioneza si va da eroare*/
                //Integer cci = Integer.valueOf(array[2]);
                //vms.getCampaign(cci - 1).redeemVoucher(array[3], now);
            }
            
            if (function.equals("getVouchers") && (!vms.isUserAdmin(cui - 1))){
                
                System.out.println(
                 vms.getUser(cui - 1).getVouchers().toString());
                
                //variabila de tip string output retine lista de afisat
                output = vms.getUser(cui - 1).getVouchers().toString();
                //scriere in fisier a liniei de output generata
                mw.write(output);
            }
            
            if (function.equals("getObservers") && vms.isUserAdmin(cui -1)){
                System.out.println(
                 vms.getCampaign(Integer.valueOf(array[2])).getObservers());
                //aceste linii sunt comentate fiidnca genereza erori
//                output = vms.getCampaign(
//                        Integer.valueOf(array[2])).getObservers().toString();
                mw.write(output);
            }
            
            
        }
      
        System.out.println("END OF RUN!");
        
        //inchidere writer
        mw.close();

    }
}
