package l;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.List;
import java.util.ArrayList;
import l.Simulator;
import l.SimulatorRF;
import l.SimulatorRW;
import l.SimulatorRB;
import l.SimulatorRH;
import l.SimulatorFW;
import l.SimulatorFB;
import l.SimulatorFH;
import l.SimulatorWB;
import l.SimulatorWH;
import l.SimulatorBH;
import l.SimulatorRFW;
import l.SimulatorRFB;
import l.SimulatorRFH;
import l.SimulatorRWB;
import l.SimulatorRWH;
import l.SimulatorRBH;
import l.SimulatorFWB;
import l.SimulatorFWH;
import l.SimulatorFBH;
import l.SimulatorWBH;
import l.SimulatorRFWB;
import l.SimulatorRFWH;
import l.SimulatorRFBH;
import l.SimulatorRWBH;
import l.SimulatorFWBH;
import javax.swing.ImageIcon;
import javax.swing.*;

/**
 * Illustrate the GUI of the simulator.
 * 
 * @author Jeremy Chu
 * @version 2021.06.10
 */
public class GUI extends JFrame{
    
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private Simulator simulator;
    private Animal animal;
    private List<Animal> animals;
    private FieldStats fieldstats;
    private Field field;
    private SimulatorView view;
    
    //species2 = {"RF","RW","RB","RH","FW","FB","FH","WB","WH","BH"};
    private SimulatorRF simulatorRF;
    private SimulatorRW simulatorRW;
    private SimulatorRB simulatorRB;
    private SimulatorRH simulatorRH;
    private SimulatorFW simulatorFW;
    private SimulatorFB simulatorFB;
    private SimulatorFH simulatorFH;
    private SimulatorWB simulatorWB;
    private SimulatorWH simulatorWH;
    private SimulatorBH simulatorBH;
    //species3 = {"RFW","RFB","RFH","RWB","RWH","RBH","FWB","FWH","FBH","WBH"};
    private SimulatorRFW simulatorRFW;
    private SimulatorRFB simulatorRFB;
    private SimulatorRFH simulatorRFH;
    private SimulatorRWB simulatorRWB;
    private SimulatorRWH simulatorRWH;
    private SimulatorRBH simulatorRBH;
    private SimulatorFWB simulatorFWB;
    private SimulatorFWH simulatorFWH;
    private SimulatorFBH simulatorFBH;
    private SimulatorWBH simulatorWBH;
    //species4 = {"RFWB","RFWH","RFBH","RWBH","FWBH"};
    private SimulatorRFWB simulatorRFWB;
    private SimulatorRFWH simulatorRFWH;
    private SimulatorRFBH simulatorRFBH;
    private SimulatorRWBH simulatorRWBH;
    private SimulatorFWBH simulatorFWBH;
     
    
    JComboBox comboBox1;
    JComboBox comboBox2;
    JComboBox comboBox3;
    JComboBox comboBox4;
    
    JButton button;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6, button7, button8, button9, button10;
    JButton reset_butt;
    JButton nextstep_butt;
    JButton longstep_butt;
    JButton run_butt;
    
    JTextField depthtxt;
    JTextField widthtxt;
    JTextField steplatertxt;
    int depth = 100; //Default depth of the field
    int width = 100; //Default width of the field
    int steplater = 1; //Default of many step later on
    int version = 0; //Default version of the simulation (All species)
    
    public GUI() {
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        
        panel1.setBackground(Color.lightGray);
        panel2.setBackground(Color.gray);
        panel3.setBackground(Color.cyan);
        
        

        String[] species2 = {"","Rabbits & Foxes","Rabbits & Wolves","Rabbits & Bears","Rabbits & Hunters","Foxes & Wolves",
                                "Foxes & Bears","Foxes & Hunters","Wolves & Bears","Wolves & Hunters","Bears & Hunters"};
        String[] species3 = {"","<html>Rabbits & Foxes<br /> & Wolves</html>","<html>Rabbits & Foxes<br /> & Bears</html>",
                                "<html>Rabbits & Foxes<br /> & Hunters</html>","<html>Rabbits & Wolves<br /> & Bears</html>",
                                "<html>Rabbits & Wolves<br /> & Hunters</html>","<html>Rabbits & Bears<br /> & Hunters</html>",
                                "<html>Foxes & Wolves<br /> & Bears</html>","<html>Foxes & Wolves<br /> & Hunters</html>",
                                "<html>Foxes & Bears<br /> & Hunters</html>","<html>Wolves & Bears<br /> & Hunters</html>"};
        String[] species4 = {"","<html>Rabbits & Foxes<br /> & Wolves & Bears</html>","<html>Rabbits & Foxes<br /> & Wolves & Hunters</html>",
                                "<html>Rabbits & Foxes<br /> & Bears & Hunters</html>","<html>Rabbits & Wolves<br /> & Bears & Hunters</html>",
                                "<html>Foxes & Wolves<br /> & Bears & Hunters</html>"};
        String[] species5 = {"","ALL"};
        
        comboBox1 = new JComboBox(species2);
        comboBox1.addActionListener(new Combobox1Handler());
        comboBox1.setEnabled(false);
        comboBox2 = new JComboBox(species3);
        comboBox2.addActionListener(new Combobox2Handler());
        comboBox2.setEnabled(false);
        comboBox3 = new JComboBox(species4);
        comboBox3.addActionListener(new Combobox3Handler());
        comboBox3.setEnabled(false);
        comboBox4 = new JComboBox(species5);
        comboBox4.addActionListener(new Combobox4Handler());
        comboBox4.setEnabled(false);
        
        
        JLabel label1 = new JLabel("Select");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setFont(new Font("Verdana", Font.BOLD, 30));
        JLabel label2 = new JLabel("options t");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setFont(new Font("Verdana", Font.BOLD, 30));
        JLabel label3 = new JLabel("o run sim");
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        label3.setFont(new Font("Verdana", Font.BOLD, 30));
        JLabel label4 = new JLabel("ulation");
        label4.setHorizontalAlignment(SwingConstants.LEFT);
        label4.setFont(new Font("Verdana", Font.BOLD, 30));
        JLabel label5 = new JLabel(" Select species");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Verdana", Font.BOLD, 17));
        JLabel label6 = new JLabel("in the field: ");
        label6.setFont(new Font("Verdana", Font.BOLD, 17));
        JLabel label7 = new JLabel("");
        //JLabel label8 = new JLabel("");
        JLabel label8 = new JLabel("<html><font size='4'color=gray> Rabbits</font> <font size='4'color=orange> Foxes</font><font size='4'color=blue> Wolves</font><font size='4'color=red> Bears</font><font size='4'color=black> Hunters</font></html>");
        JLabel label9 = new JLabel("Species selected:");
        label9.setHorizontalAlignment(SwingConstants.RIGHT);
        label9.setVerticalAlignment(SwingConstants.BOTTOM);
        label9.setFont(new Font("Verdana", Font.BOLD, 14));
        JLabel label10 = new JLabel("");
        JLabel label11 = new JLabel("");
        JLabel label12 = new JLabel("");
        JLabel label13 = new JLabel("");
        JLabel label14 = new JLabel("");
        JLabel label15 = new JLabel("");
        JLabel label16 = new JLabel(" Set field size: ");
        label16.setHorizontalAlignment(SwingConstants.CENTER);
        label16.setFont(new Font("Verdana", Font.BOLD, 17));
        JLabel label17 = new JLabel("");
        JLabel label18 = new JLabel("");
        JLabel label19 = new JLabel("");
        JLabel label20 = new JLabel("");
        JLabel label21 = new JLabel("depth:");
        label21.setFont(new Font("Verdana", Font.PLAIN, 15));
        JLabel label22 = new JLabel("Width:");
        label22.setFont(new Font("Verdana", Font.PLAIN, 15));
        JLabel label23 = new JLabel("");
        JLabel label24 = new JLabel("");
        JLabel label25 = new JLabel("");
        JLabel label26 = new JLabel("");
        JLabel label27 = new JLabel("");
        JLabel label28 = new JLabel("");
        JLabel label29 = new JLabel("");
        JLabel label30 = new JLabel(" Simulate optio");
        label30.setHorizontalAlignment(SwingConstants.RIGHT);
        label30.setFont(new Font("Verdana", Font.BOLD, 17));
        JLabel label31 = new JLabel("ns:");
        label31.setHorizontalAlignment(SwingConstants.LEFT);
        label31.setFont(new Font("Verdana", Font.BOLD, 17));
        JLabel label32 = new JLabel("");
        JLabel label33 = new JLabel("");
        JLabel label34 = new JLabel("");
        JLabel label35 = new JLabel("");
        JLabel label36 = new JLabel("");
        JLabel label37 = new JLabel("");
        JLabel label38 = new JLabel("");
        JLabel label39 = new JLabel("");
        JLabel label40 = new JLabel("");
        JLabel label41 = new JLabel("");
        JLabel label42 = new JLabel("Show result after ");
        label42.setHorizontalAlignment(SwingConstants.RIGHT);
        label42.setFont(new Font("Verdana", Font.PLAIN, 15));
        JLabel label43 = new JLabel(" step");
        label43.setHorizontalAlignment(SwingConstants.LEFT);
        label43.setFont(new Font("Verdana", Font.PLAIN, 15));
        JLabel label44 = new JLabel("");
        JLabel label45 = new JLabel("");
        JLabel label46 = new JLabel("");
        JLabel label47 = new JLabel("");
        
        ImageIcon image1 = new ImageIcon("rabbit_icon.png");
        image1.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);
        ImageIcon image2 = new ImageIcon("fox_icon.png");
        image2.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon("wolf_icon.png");
        image3.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);
        ImageIcon image4 = new ImageIcon("bear_icon.png");
        image4.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);
        ImageIcon image5 = new ImageIcon("hunter_icon.png");
        image5.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);

        button1 = new JButton("Two species");
        button1.setFont(new Font("Arial", Font.BOLD, 18));
        button1.addActionListener(new Button1Handler());
        button2 = new JButton("Three species");
        button2.setFont(new Font("Arial", Font.BOLD, 18));
        button2.addActionListener(new Button2Handler());
        button3 = new JButton("Four species");
        button3.setFont(new Font("Arial", Font.BOLD, 18));
        button3.addActionListener(new Button3Handler());
        button4 = new JButton("All");
        button4.setFont(new Font("Arial", Font.BOLD, 18));
        button4.addActionListener(new Button4Handler());
        button5 = new JButton("Reset");
        button5.setFont(new Font("Arial", Font.BOLD, 18));
        button5.addActionListener(new Button5Handler());
        
        
        
        button6 = new JButton("Rabbits",image1);
        button6.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button6.setForeground(Color.gray);
        button6.setBackground(Color.white);
        button6.setBounds(20,50,60,0);
        int offset6 = button6.getInsets().left;
        button6.setIcon(resizeIcon(image1, button6.getWidth() - offset6, button6.getHeight() - offset6));
        button6.setEnabled(false);
        
        button7 = new JButton("Foxes",image2);
        button7.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button7.setForeground(Color.orange);
        button7.setBackground(Color.white);
        button7.setBounds(20,50,60,0);
        int offset7 = button7.getInsets().left;
        button7.setIcon(resizeIcon(image2, button7.getWidth() - offset7, button7.getHeight() - offset7));
        button7.setEnabled(false);
        
        button8 = new JButton("Wolves",image3);
        button8.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button8.setForeground(Color.blue);
        button8.setBackground(Color.white);
        button8.setBounds(20,50,60,0);
        int offset8 = button8.getInsets().left;
        button8.setIcon(resizeIcon(image3, button8.getWidth() - offset8, button8.getHeight() - offset8));
        button8.setEnabled(false);
        
        button9 = new JButton("Bears",image4);
        button9.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button9.setForeground(Color.red);
        button9.setBackground(Color.white);
        button9.setBounds(20,50,60,0);
        int offset9 = button9.getInsets().left;
        button9.setIcon(resizeIcon(image4, button9.getWidth() - offset9, button9.getHeight() - offset9));
        button9.setEnabled(false);
        
        button10 = new JButton("Hunters",image5);
        button10.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button10.setForeground(Color.black);
        button10.setBackground(Color.white);
        button10.setBounds(20,50,60,0);
        int offset10 = button10.getInsets().left;
        button10.setIcon(resizeIcon(image5, button10.getWidth() - offset10, button10.getHeight() - offset10));
        button10.addActionListener(new Button5Handler());
        button10.setEnabled(false);       
        
        button = new JButton("Generate Field");
        button.addActionListener(new ButtonHandler());
        button.setEnabled(false);
        
        reset_butt = new JButton("Reset");
        reset_butt.addActionListener(new SimulateOption());
        reset_butt.setEnabled(false);
        
        nextstep_butt = new JButton("Next step");
        nextstep_butt.addActionListener(new SimulateOption());
        nextstep_butt.setEnabled(false);
        
        longstep_butt = new JButton("Next hundred steps");
        longstep_butt.addActionListener(new SimulateOption());
        longstep_butt.setEnabled(false);
        
        run_butt = new JButton("Run");
        run_butt.addActionListener(new SimulateOption());
        run_butt.setEnabled(false);
        
        depthtxt = new JTextField();
        depthtxt.setText("100");
        depthtxt.setHorizontalAlignment(SwingConstants.CENTER);
        depthtxt.setEnabled(false);
        widthtxt = new JTextField();
        widthtxt.setText("100"); 
        widthtxt.setHorizontalAlignment(SwingConstants.CENTER);
        widthtxt.setEnabled(false);
        steplatertxt = new JTextField();
        steplatertxt.setText("0"); 
        steplatertxt.setHorizontalAlignment(SwingConstants.CENTER);
        steplatertxt.setEnabled(false);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setTitle("Predator and Prey simulator");
        
        frame.setVisible(true);
        
        panel1.setLayout(new GridLayout(7,4,0,0));
        panel1.add(label1);         panel1.add(label2);         panel1.add(label3);           panel1.add(label4);     
        panel1.add(label5);         panel1.add(label6);         panel1.add(label7);           panel1.add(label8);
        panel1.add(button1);        panel1.add(button2);        panel1.add(button3);          panel1.add(button4);
        panel1.add(comboBox1);      panel1.add(comboBox2);      panel1.add(comboBox3);        panel1.add(comboBox4);
        panel1.add(label9);         panel1.add(label10);        panel1.add(label11);          panel1.add(label12);
        panel1.add(label13);        panel1.add(button6);        panel1.add(button7);          panel1.add(button8);
        panel1.add(label14);        panel1.add(button9);        panel1.add(button10);         panel1.add(button5);
        
        panel2.setLayout(new GridLayout(4,4,5,0));
        panel2.add(label16);        panel2.add(label17);        panel2.add(label18);          panel2.add(label19); 
        panel2.add(label20);        panel2.add(label21);        panel2.add(label22);          panel2.add(label23); 
        panel2.add(label24);        panel2.add(depthtxt);       panel2.add(widthtxt);         panel2.add(label25);
        panel2.add(label26);        panel2.add(label27);        panel2.add(label28);          panel2.add(label29); 
        
        panel3.setLayout(new GridLayout(6,4,0,5));
        panel3.add(label30);        panel3.add(label31);        panel3.add(label32);          panel3.add(label33); 
        panel3.add(label34);        panel3.add(label35);        panel3.add(label36);          panel3.add(label37); 
        panel3.add(button);         panel3.add(reset_butt);     panel3.add(nextstep_butt);    panel3.add(longstep_butt);
        panel3.add(label38);        panel3.add(label39);        panel3.add(label40);          panel3.add(label41);
        panel3.add(label42);        panel3.add(steplatertxt);   panel3.add(label43);          panel3.add(run_butt);
        panel3.add(label44);        panel3.add(label45);        panel3.add(label46);          panel3.add(label47);
        
        
        frame.add(panel1,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel3,BorderLayout.SOUTH);
        
        frame.pack();
        frame.setBounds(0,0,650,690);
    }

    public static void main(String[] args){
        new GUI();
    }
    
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }
    
    private class Button1Handler implements ActionListener{//Two Species
        public void actionPerformed(ActionEvent e){
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            comboBox1.setEnabled(true);
            comboBox2.setEnabled(false);
            comboBox3.setEnabled(false);
            comboBox4.setEnabled(false);
        }
    }
    private class Button2Handler implements ActionListener{//Three Species
        public void actionPerformed(ActionEvent e){
            button1.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            comboBox1.setEnabled(false);
            comboBox2.setEnabled(true);
            comboBox3.setEnabled(false);
            comboBox4.setEnabled(false);
        }
    }
    private class Button3Handler implements ActionListener{//Four Species
        public void actionPerformed(ActionEvent e){
            button1.setEnabled(false);
            button2.setEnabled(false);
            button4.setEnabled(false);
            comboBox1.setEnabled(false);
            comboBox2.setEnabled(false);
            comboBox3.setEnabled(true);
            comboBox4.setEnabled(false);
        }
    }
    private class Button4Handler implements ActionListener{//All Species
        public void actionPerformed(ActionEvent e){
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            comboBox1.setEnabled(false);
            comboBox2.setEnabled(false);
            comboBox3.setEnabled(false);
            comboBox4.setEnabled(true);
        }
    }
    private class Button5Handler implements ActionListener{//Reset Species
        public void actionPerformed(ActionEvent e){
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            comboBox1.setEnabled(false);
            comboBox2.setEnabled(false);
            comboBox3.setEnabled(false);
            comboBox4.setEnabled(false);
            comboBox1.setSelectedIndex(0);
            comboBox2.setSelectedIndex(0);
            comboBox3.setSelectedIndex(0);
            comboBox4.setSelectedIndex(0);
            button6.setEnabled(false); //rabbit icon
            button7.setEnabled(false); //fox icon
            button8.setEnabled(false); //wolf icon
            button9.setEnabled(false); //bear icon
            button10.setEnabled(false); //hunter icon
            button.setEnabled(false);
            reset_butt.setEnabled(false);
            nextstep_butt.setEnabled(false);
            longstep_butt.setEnabled(false);
            run_butt.setEnabled(false);
            depthtxt.setEnabled(false);
            widthtxt.setEnabled(false);
            steplatertxt.setEnabled(false);
        }
    }
    private class Combobox1Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species2 = {"RF","RW","RB","RH","FW","FB","FH","WB","WH","BH"}
            if(comboBox1.getSelectedIndex() != 0){
                if(comboBox1.getSelectedIndex() == 1){//RF
                    version = 1;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                }
                else if(comboBox1.getSelectedIndex() == 2){//RW
                    version = 2;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button8.setEnabled(true); //wolf icon
                }
                else if(comboBox1.getSelectedIndex() == 3){//RB
                    version = 3;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox1.getSelectedIndex() == 4){//RH
                    version = 4;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox1.getSelectedIndex() == 5){//FW
                    version = 5;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                }
                else if(comboBox1.getSelectedIndex() == 6){//FB
                    version = 6;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox1.getSelectedIndex() == 7){//FH
                    version = 7;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox1.getSelectedIndex() == 8){//WB
                    version = 8;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox1.getSelectedIndex() == 9){//WH
                    version = 9;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button8.setEnabled(true); //wolf icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox1.getSelectedIndex() == 10){//BH
                    version = 10;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                button.setEnabled(true);
                depthtxt.setEnabled(true);
                widthtxt.setEnabled(true);
            }
            else{
                //reset selected species first
                button6.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                button9.setEnabled(false);
                button10.setEnabled(false);
            }
        }

    }
    private class Combobox2Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species3 = {"RFW","RFB","RFH","RWB","RWH","RBH","FWB","FWH","FBH","WBH"};
            if(comboBox2.getSelectedIndex() != 0){
                if(comboBox2.getSelectedIndex() == 1){//RFW
                    version = 11;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                }
                else if(comboBox2.getSelectedIndex() == 2){//RFB
                    version = 12;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox2.getSelectedIndex() == 3){//RFH
                    version = 13;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox2.getSelectedIndex() == 4){//RWB
                    version = 14;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox2.getSelectedIndex() == 5){//RWH
                    version = 15;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button8.setEnabled(true); //wolf icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox2.getSelectedIndex() == 6){//RBH
                    version = 16;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox2.getSelectedIndex() == 7){//FWB
                    version = 17;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox2.getSelectedIndex() == 8){//FWH
                    version = 18;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox2.getSelectedIndex() == 9){//FBH
                    version = 19;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox2.getSelectedIndex() == 10){//WBH
                    version = 20;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                button.setEnabled(true);
                depthtxt.setEnabled(true);
                widthtxt.setEnabled(true);
            } 
            else{
                //reset selected species first
                button6.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                button9.setEnabled(false);
                button10.setEnabled(false);
            }
        }
    }
    private class Combobox3Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species4 = {"RFWB","RFWH","RFBH","RWBH","FWBH"};
            if(comboBox3.getSelectedIndex() != 0){
                if(comboBox3.getSelectedIndex() == 1){//RFWB
                    version = 21;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                }
                else if(comboBox3.getSelectedIndex() == 2){//RFWH
                    version = 22;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox3.getSelectedIndex() == 3){//RFBH
                    version = 23;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox3.getSelectedIndex() == 4){//RWBH
                    version = 24;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button6.setEnabled(true); //rabbit icon
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                else if(comboBox3.getSelectedIndex() == 5){//FWBH
                    version = 25;
                    //reset selected species first
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    button10.setEnabled(false);
                    //selected species
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                button.setEnabled(true);
                depthtxt.setEnabled(true);
                widthtxt.setEnabled(true);
            }
            else{
                //reset selected species first
                button6.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                button9.setEnabled(false);
                button10.setEnabled(false);
            }
        }
    }
    private class Combobox4Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species5 = {"All"};
            if(comboBox4.getSelectedIndex() != 0){
                if(comboBox4.getSelectedIndex() == 1){
                    version = 0;
                    button6.setEnabled(true); //rabbit icon
                    button7.setEnabled(true); //fox icon
                    button8.setEnabled(true); //wolf icon
                    button9.setEnabled(true); //bear icon
                    button10.setEnabled(true); //hunter icon
                }
                button.setEnabled(true);
                depthtxt.setEnabled(true);
                widthtxt.setEnabled(true);
            }
            else{
                //reset selected species first
                button6.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                button9.setEnabled(false);
                button10.setEnabled(false);
            }
        }
    }  
    
    public class ButtonHandler implements ActionListener{//Generate field
        public void actionPerformed(ActionEvent e){
            String depthstr = depthtxt.getText();
            String widthstr = widthtxt.getText();
            depth = Integer.parseInt(depthstr);
            width = Integer.parseInt(widthstr);
            switch(version) {
                case 0:
                    Simulator.Simulator(depth,width);  
                    break;
                case 1:
                    SimulatorRF.SimulatorRF(depth,width);
                    break;
                case 2:
                    SimulatorRW.SimulatorRW(depth,width);
                    break;
                case 3:
                    SimulatorRB.SimulatorRB(depth,width);
                    break;
                case 4:
                    SimulatorRH.SimulatorRH(depth,width);
                    break;
                case 5:
                    SimulatorFW.SimulatorFW(depth,width);
                    break;
                case 6:
                    SimulatorFB.SimulatorFB(depth,width);
                    break;
                case 7:
                    SimulatorFH.SimulatorFH(depth,width);
                    break;
                case 8:
                    SimulatorWB.SimulatorWB(depth,width);
                    break;
                case 9:
                    SimulatorWH.SimulatorWH(depth,width);
                    break;
                case 10:
                    SimulatorBH.SimulatorBH(depth,width);
                    break;
                case 11:
                    SimulatorRFW.SimulatorRFW(depth,width);
                    break;
                case 12:
                    SimulatorRFB.SimulatorRFB(depth,width);
                    break;
                case 13:
                    SimulatorRFH.SimulatorRFH(depth,width);
                    break;
                case 14:
                    SimulatorRWB.SimulatorRWB(depth,width);
                    break;
                case 15:
                    SimulatorRWH.SimulatorRWH(depth,width);
                    break;
                case 16:
                    SimulatorRBH.SimulatorRBH(depth,width);
                    break;
                case 17:
                    SimulatorFWB.SimulatorFWB(depth,width);
                    break;
                case 18:
                    SimulatorFWH.SimulatorFWH(depth,width);
                    break;
                case 19:
                    SimulatorFBH.SimulatorFBH(depth,width);
                    break;
                case 20:
                    SimulatorWBH.SimulatorWBH(depth,width);
                    break;
                case 21:
                    SimulatorRFWB.SimulatorRFWB(depth,width);
                    break;
                case 22:
                    SimulatorRFWH.SimulatorRFWH(depth,width);
                    break;
                case 23:
                    SimulatorRFBH.SimulatorRFBH(depth,width);
                    break;
                case 24:
                    SimulatorRWBH.SimulatorRWBH(depth,width);
                    break;
                case 25:
                    SimulatorFWBH.SimulatorFWBH(depth,width);
                    break;
            }  
            reset_butt.setEnabled(true);
            nextstep_butt.setEnabled(true);
            longstep_butt.setEnabled(true);
            steplatertxt.setEnabled(true);
            run_butt.setEnabled(true);
        }
    }      
    public class SimulateOption implements ActionListener{//Simulate Options
        public void actionPerformed(ActionEvent e){
            String steplaterstr = steplatertxt.getText();
            int steplater = Integer.parseInt(steplaterstr);
            switch(version) {
                case 0:
                    if(e.getSource() == reset_butt){
                        Simulator.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        Simulator.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        Simulator.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        Simulator.simulate(steplater);
                    }
                    break;
                case 1:
                    if(e.getSource() == reset_butt){
                        SimulatorRF.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRF.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRF.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRF.simulate(steplater);
                    }
                    break;
                case 2:
                    if(e.getSource() == reset_butt){
                        SimulatorRW.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRW.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRW.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRW.simulate(steplater);
                    }
                    break;
                case 3:
                    if(e.getSource() == reset_butt){
                        SimulatorRB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRB.simulate(steplater);
                    }
                    break;
                case 4:
                    if(e.getSource() == reset_butt){
                        SimulatorRH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRH.simulate(steplater);
                    }
                    break;
                case 5:
                    if(e.getSource() == reset_butt){
                        SimulatorFW.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFW.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFW.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFW.simulate(steplater);
                    }
                    break;
                case 6:
                    if(e.getSource() == reset_butt){
                        SimulatorFB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFB.simulate(steplater);
                    }
                    break;
                case 7:
                    if(e.getSource() == reset_butt){
                        SimulatorFH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFH.simulate(steplater);
                    }
                    break;
                case 8:
                    if(e.getSource() == reset_butt){
                        SimulatorWB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorWB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorWB.simulate(steplater);
                    }
                    break;
                case 9:
                    if(e.getSource() == reset_butt){
                        SimulatorWH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorWH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorWH.simulate(steplater);
                    }
                    break;
                case 10:
                    if(e.getSource() == reset_butt){
                        SimulatorBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorBH.simulate(steplater);
                    }
                    break;
                case 11:
                    if(e.getSource() == reset_butt){
                        SimulatorRFW.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRFW.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRFW.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRFW.simulate(steplater);
                    }
                    break;
                case 12:
                    if(e.getSource() == reset_butt){
                        SimulatorRFB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRFB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRFB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRFB.simulate(steplater);
                    }
                    break;
                case 13:
                    if(e.getSource() == reset_butt){
                        SimulatorRFH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRFH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRFH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRFH.simulate(steplater);
                    }
                    break;
                case 14:
                    if(e.getSource() == reset_butt){
                        SimulatorRWB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRWB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRWB.simulate(steplater);
                    }
                    break;
                case 15:
                    if(e.getSource() == reset_butt){
                        SimulatorRWH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRWH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRWH.simulate(steplater);
                    }
                    break;
                case 16:
                    if(e.getSource() == reset_butt){
                        SimulatorRBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRBH.simulate(steplater);
                    }
                    break;
                case 17:
                    if(e.getSource() == reset_butt){
                        SimulatorFWB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFWB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFWB.simulate(steplater);
                    }
                    break;
                case 18:
                    if(e.getSource() == reset_butt){
                        SimulatorFWH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFWH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFWH.simulate(steplater);
                    }
                    break;
                case 19:
                    if(e.getSource() == reset_butt){
                        SimulatorFBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFBH.simulate(steplater);
                    }
                    break;
                case 20:
                    if(e.getSource() == reset_butt){
                        SimulatorWBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorWBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorWBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorWBH.simulate(steplater);
                    }
                    break;
                case 21:
                    if(e.getSource() == reset_butt){
                        SimulatorRFWB.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRFWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRFWB.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRFWB.simulate(steplater);
                    }
                    break;
                case 22:
                    if(e.getSource() == reset_butt){
                        SimulatorRFWH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRFWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRFWH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRFWH.simulate(steplater);
                    }
                    break;
                case 23:
                    if(e.getSource() == reset_butt){
                        SimulatorRFBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRFBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRFBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRFBH.simulate(steplater);
                    }
                    break;
                case 24:
                    if(e.getSource() == reset_butt){
                        SimulatorRWBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorRWBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorRWBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorRWBH.simulate(steplater);
                    }
                    break;
                case 25:
                    if(e.getSource() == reset_butt){
                        SimulatorFWBH.reset();
                        reset_butt.setEnabled(true);
                        nextstep_butt.setEnabled(true);
                        longstep_butt.setEnabled(true);
                        run_butt.setEnabled(true);
                    }
                    else if(e.getSource() == nextstep_butt){
                        SimulatorFWBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        SimulatorFWBH.runLongSimulation();
                    }
                    else if(e.getSource() == run_butt){
                        SimulatorFWBH.simulate(steplater);
                    }
                    break;
            }  
        }
    }      
}

