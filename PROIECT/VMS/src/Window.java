
import javax.swing.*; //pentru JFrame si alte J*
import java.awt.*;  //pentru layout-uri
import java.awt.event.*; //pentru ActionListener, etc
import java.util.*;

public class Window extends JFrame implements ActionListener{
    
    public Window(String titlu){    //constructor fereastra
        
        super(titlu);
        
        setDefaultCloseOperation(JFrame . EXIT_ON_CLOSE);
                         //ca sa putem parasi si inchide fereastra apasand pe X
        JButton bload = new JButton("Load data");
        JTextField tu = new JTextField("Usename");
        JTextField tp = new JTextField("Password");
        JButton blogin = new JButton("Login");
        
        bload.addActionListener(this);
        bload.setActionCommand("b");
        
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout(120, 30));

        p.add(bload, BorderLayout.NORTH);
        p.add(tu, BorderLayout.EAST);
        p.add(tp, BorderLayout.WEST);
        p.add(blogin, BorderLayout.SOUTH);
        
        add(p);
        pack();
        setVisible(true);  
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if(action.equals("b")){
            }    
            
        }
}
