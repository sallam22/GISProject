package com.invoice;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {
     private JButton btn;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JTextField userName;
    private JPasswordField password;
    private JTextArea textArea;
    private JTable table;
    private JTable table1;
    private JMenuBar mb;
    private JMenuItem openItem;
    private JMenuItem openItem1;

    private JMenu FileMenu;
    String clipText=new String("");
    String []cols={"No.","Date ","Customer","Total"};
    String [] []data={
            {"","","",""}


    };

    String []cols1={"No.","Item Name","Item Price","Count","Item Total"};
    String [] []data1={
            {"1","apple","100","1","100"},
            {"1","apple","100","1","100"},
            {"1","apple","100","1","100"},

    };
    public MyFrame()  {
        super("invoice sales");
        setLayout(new FlowLayout());
        // add table1
        table=new JTable(data,cols);
        add(new JScrollPane(table));
        // add table 2
        table1=new JTable(data1,cols1);
        add(new JScrollPane(table1));
        // set button1 for create invoice
        btn =new JButton("Create New Invoice ");
        add(btn);
        btn.addActionListener(this);
        //set button 2 for delete invoice
        btn2 =new JButton("Delete Invoice ");
        add(btn2);
        btn2.addActionListener(this);
        // set button 3 for save
        btn3 =new JButton("Save ");
        add(btn3);
        btn3.addActionListener(this);
        // set button 4 for cancel
        btn4 =new JButton("Cancel ");
        add(btn4);
        btn4.addActionListener(this);
        /*
       // add text field
        userName=new JTextField(15);
        add(new JLabel("User Name"));
        add(userName);
        //add password field
        password=new JPasswordField(15);
        add(new JLabel("Password"));
        add(password);
        //add text area field
        textArea=new JTextArea(5,15);
        add(new JLabel("address"));
        add(new JScrollPane(textArea));
       */

        //menu bar
        mb=new JMenuBar();
        FileMenu=new JMenu("File");
        openItem=new JMenuItem("LoadFile");
        openItem.addActionListener(this);

        openItem1=new JMenuItem("SaveFile");
        openItem1.addActionListener(this);
        setJMenuBar(mb);
        mb.add(FileMenu);
        FileMenu.add(openItem);
        FileMenu.add(openItem1);

        // size and location of frame
        setSize(950,1000);
        setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
           if(e.getSource().equals(btn)) {
              // String name= userName.getText();




               JOptionPane.showMessageDialog(this,table);
               //JOptionPane.showMessageDialog(this,table, "information", JOptionPane.INFORMATION_MESSAGE);
           } else if (e.getSource().equals(btn2)) {
               String address= textArea.getText();
               String msg2= " address is " + address;
               JOptionPane.showMessageDialog(this,msg2, "information", JOptionPane.CANCEL_OPTION);

           }
           else if(e.getSource().equals(openItem)){
               openFile();
           }
           else if(e.getSource().equals(openItem1)){
               saveFile();
           }
           else {
               System.out.println("tested");
           }


    }




    // methods
   private void openFile(){

        JFileChooser fc=new JFileChooser();
        int result =fc.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            String path=fc.getSelectedFile().getPath();
            FileInputStream fis =null;
            try {
             fis=  new FileInputStream(path);
                int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b);
                textArea.setText(new String(b));

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();}
           finally {
                try{

                    fis.close();
                }
                catch (IOException e){}

            }

        }
   }
  private void saveFile() {

      JFileChooser fc = new JFileChooser();
      int result = fc.showSaveDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
          String path = fc.getSelectedFile().getPath();
          FileOutputStream fos = null;
          try {
              fos = new FileOutputStream(path);
              byte[] b = textArea.getText().getBytes();
              fos.write(b);
          }
          catch (FileNotFoundException e)
          {
              e.printStackTrace();
          }
          catch (IOException e)
          {
              e.printStackTrace();}
          finally {
              try{

                  fos.close();
              }
              catch (IOException e){}

          }

      }
  }
}
