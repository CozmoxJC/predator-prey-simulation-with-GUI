import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.List;
import java.util.ArrayList;



/**
 * Illustrate the GUI of the simulator.
 * 
 * @author Jeremy Chu
 * @version 2021.06.02
 */
public class GUI extends JFrame
{
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
    JButton reset_butt;
    JButton nextstep_butt;
    JButton longstep_butt;
    
    JTextField depthtxt;
    JTextField widthtxt;
    int depth = 100;
    int width = 100;
    int version = 0;
    
    public GUI() {
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        //panel3 = new JPanel();
        
        panel1.setBackground(Color.lightGray);
        panel2.setBackground(Color.gray);
        //panel3.setBackground(Color.blue);
        
        

        String[] species2 = {"","Rabbits & Foxes","Rabbits & Wolves","Rabbits & Bears","Rabbits & Hunters","Foxes & Wolves",
                                "Foxes & Bears","Foxes & Hunters","Wolves & Bears","Wolves & Hunters","Bears & Hunters"};
        String[] species3 = {"","Rabbits & Foxes & Wolves","Rabbits & Foxes & Bears","Rabbits & Foxes & Hunters","Rabbits & Wolves & Bears",
                                "Rabbits & Wolves & Hunters","Rabbits & Bears & Hunters","Foxes & Wolves & Bears","Foxes & Wolves & Hunters",
                                "Foxes & Bears & Hunters","Wolves & Bears & Hunters"};
        String[] species4 = {"","Rabbits & Foxes & Wolves & Bears","Rabbits & Foxes & Wolves & Hunters","Rabbits & Foxes & Bears & Hunters",
                                "Rabbits & Wolves & Bears & Hunters","Foxes & Wolves & Bears & Hunters"};
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
        
        
        
        JLabel label1 = new JLabel("");
        JLabel label2 = new JLabel("Choose Options to generate Simulation");
        JLabel label3 = new JLabel("");
        JLabel label4 = new JLabel("Choose species in the field: ");
        JLabel label5 = new JLabel("");
        JLabel label6 = new JLabel("<html><font size='4'color=gray> Rabbits</font> <font size='4'color=orange> Foxes</font><font size='4'color=blue> Wolves</font><font size='4'color=red> Bears</font><font size='4'color=black> Hunters</font></html>");
        JLabel label7 = new JLabel("");
        JLabel label17 = new JLabel("");
        JLabel label18 = new JLabel("");
        JLabel label8 = new JLabel("Simulate:");
        JLabel label9 = new JLabel("");
        JLabel label10 = new JLabel("");
        JLabel label11 = new JLabel("depth:");
        JLabel label12 = new JLabel("Width:");
        JLabel label13 = new JLabel("");
        JLabel label14 = new JLabel("Reset");
        JLabel label15 = new JLabel("Next step");
        JLabel label16 = new JLabel("Next hundred step");


        button1 = new JButton("Two species");
        button1.addActionListener(new Button1Handler());
        button2 = new JButton("Three species");
        button2.addActionListener(new Button2Handler());
        button3 = new JButton("Four species");
        button3.addActionListener(new Button3Handler());
        button4 = new JButton("All");
        button4.addActionListener(new Button4Handler());
        button5 = new JButton("Reset");
        button5.addActionListener(new Button5Handler());
        
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
        
        depthtxt = new JTextField();
        depthtxt.setText("100");
        depthtxt.setEnabled(false);
        widthtxt = new JTextField();
        widthtxt.setText("100"); 
        widthtxt.setEnabled(false);
        
        
        panel1.setLayout(new GridLayout(7,5,10,0));
        panel1.add(label1);         panel1.add(label2);         panel1.add(label3);
        panel1.add(label4);         panel1.add(label5);         panel1.add(label6);
        panel1.add(button1);        panel1.add(button2);        panel1.add(button3);
        panel1.add(comboBox1);      panel1.add(comboBox2);      panel1.add(comboBox3);
        panel1.add(button4);        panel1.add(label7);         panel1.add(label18);
        panel1.add(comboBox4);      panel1.add(label17);        panel1.add(button5);
        panel2.setLayout(new GridLayout(5,4,10,0));
        panel2.add(label8);         panel2.add(label9);         panel2.add(label10);
        panel2.add(label11);        panel2.add(label12);        panel2.add(label13);
        panel2.add(depthtxt);       panel2.add(widthtxt);       panel2.add(button);
        panel2.add(reset_butt);     panel2.add(nextstep_butt);  panel2.add(longstep_butt);
        //panel3.setLayout(new GridLayout(5,4,10,0));
        
        frame.add(panel1,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.SOUTH);
        //frame.add(panel3,BorderLayout.SOUTH);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setTitle("Predator and Prey simulator");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new GUI();
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
        }
    }
    private class Combobox1Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species2 = {"RF","RW","RB","RH","FW","FB","FH","WB","WH","BH"}
            if(comboBox1.getSelectedIndex() == 1){//RF
                version = 1;
            }
            else if(comboBox1.getSelectedIndex() == 2){//RW
                version = 2;
            }
            else if(comboBox1.getSelectedIndex() == 3){
                version = 3;
            }
            else if(comboBox1.getSelectedIndex() == 4){
                version = 4;
            }
            else if(comboBox1.getSelectedIndex() == 5){
                version = 5;
            }
            else if(comboBox1.getSelectedIndex() == 6){
                version = 6;
            }
            else if(comboBox1.getSelectedIndex() == 7){
                version = 7;
            }
            else if(comboBox1.getSelectedIndex() == 8){
                version = 8;
            }
            else if(comboBox1.getSelectedIndex() == 9){
                version = 9;
            }
            else if(comboBox1.getSelectedIndex() == 10){
                version = 10;
            }
            button.setEnabled(true);
            depthtxt.setEnabled(true);
            widthtxt.setEnabled(true);
        }
    }
    private class Combobox2Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species3 = {"RFW","RFB","RFH","RWB","RWH","RBH","FWB","FWH","FBH","WBH"};
            if(comboBox2.getSelectedIndex() == 1){
                version = 11;
            }
            else if(comboBox2.getSelectedIndex() == 2){
                version = 12;
            }
            else if(comboBox2.getSelectedIndex() == 3){
                version = 13;
            }
            else if(comboBox2.getSelectedIndex() == 4){
                version = 14;
            }
            else if(comboBox2.getSelectedIndex() == 5){
                version = 15;
            }
            else if(comboBox2.getSelectedIndex() == 6){
                version = 16;
            }
            else if(comboBox2.getSelectedIndex() == 7){
                version = 17;
            }
            else if(comboBox2.getSelectedIndex() == 8){
                version = 18;
            }
            else if(comboBox2.getSelectedIndex() == 9){
                version = 19;
            }
            else if(comboBox2.getSelectedIndex() == 10){
                version = 20;
            }
            button.setEnabled(true);
            depthtxt.setEnabled(true);
            widthtxt.setEnabled(true);
        }
    }
    private class Combobox3Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species4 = {"RFWB","RFWH","RFBH","RWBH","FWBH"};
            if(comboBox3.getSelectedIndex() == 1){
                version = 21;
            }
            else if(comboBox3.getSelectedIndex() == 2){
                version = 22;
            }
            else if(comboBox3.getSelectedIndex() == 3){
                version = 23;
            }
            else if(comboBox3.getSelectedIndex() == 4){
                version = 24;
            }
            else if(comboBox3.getSelectedIndex() == 5){
                version = 25;
            }
            button.setEnabled(true);
            depthtxt.setEnabled(true);
            widthtxt.setEnabled(true);
        }
    }
    private class Combobox4Handler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //species5 = {"All"};
            if(comboBox4.getSelectedIndex() == 1){
                version = 0;
            }
            button.setEnabled(true);
            depthtxt.setEnabled(true);
            widthtxt.setEnabled(true);
        }
    }  
    private class ButtonHandler implements ActionListener{//Generate field
        public void actionPerformed(ActionEvent e){
            String depthstr = depthtxt.getText();
            String widthstr = widthtxt.getText();
            int depth = Integer.parseInt(depthstr);
            int width = Integer.parseInt(widthstr);
            switch(version) {
                case 0:
                    Simulator set = new Simulator();
                    set.Simulator(depth,width);
                    break;
                case 1:
                    SimulatorRF setRF = new SimulatorRF();
                    setRF.SimulatorRF(depth,width);
                    break;
                case 2:
                    SimulatorRW setRW = new SimulatorRW();
                    setRW.SimulatorRW(depth,width);
                    break;
                case 3:
                    SimulatorRB setRB = new SimulatorRB();
                    setRB.SimulatorRB(depth,width);
                    break;
                case 4:
                    SimulatorRH setRH = new SimulatorRH();
                    setRH.SimulatorRH(depth,width);
                    break;
                case 5:
                    SimulatorFW setFW = new SimulatorFW();
                    setFW.SimulatorFW(depth,width);
                    break;
                case 6:
                    SimulatorFB setFB = new SimulatorFB();
                    setFB.SimulatorFB(depth,width);
                    break;
                case 7:
                    SimulatorFH setFH = new SimulatorFH();
                    setFH.SimulatorFH(depth,width);
                    break;
                case 8:
                    SimulatorWB setWB = new SimulatorWB();
                    setWB.SimulatorWB(depth,width);
                    break;
                case 9:
                    SimulatorWH setWH = new SimulatorWH();
                    setWH.SimulatorWH(depth,width);
                    break;
                case 10:
                    SimulatorBH setBH = new SimulatorBH();
                    setBH.SimulatorBH(depth,width);
                    break;
                case 11:
                    SimulatorRFW setRFW = new SimulatorRFW();
                    setRFW.SimulatorRFW(depth,width);
                    break;
                case 12:
                    SimulatorRFB setRFB = new SimulatorRFB();
                    setRFB.SimulatorRFB(depth,width);
                    break;
                case 13:
                    SimulatorRFH setRFH = new SimulatorRFH();
                    setRFH.SimulatorRFH(depth,width);
                    break;
                case 14:
                    SimulatorRWB setRWB = new SimulatorRWB();
                    setRWB.SimulatorRWB(depth,width);
                    break;
                case 15:
                    SimulatorRWH setRWH = new SimulatorRWH();
                    setRWH.SimulatorRWH(depth,width);
                    break;
                case 16:
                    SimulatorRBH setRBH = new SimulatorRBH();
                    setRBH.SimulatorRBH(depth,width);
                    break;
                case 17:
                    SimulatorFWB setFWB = new SimulatorFWB();
                    setFWB.SimulatorFWB(depth,width);
                    break;
                case 18:
                    SimulatorFWH setFWH = new SimulatorFWH();
                    setFWH.SimulatorFWH(depth,width);
                    break;
                case 19:
                    SimulatorFBH setFBH = new SimulatorFBH();
                    setFBH.SimulatorFBH(depth,width);
                    break;
                case 20:
                    SimulatorWBH setWBH = new SimulatorWBH();
                    setWBH.SimulatorWBH(depth,width);
                    break;
                case 21:
                    SimulatorRFWB setRFWB = new SimulatorRFWB();
                    setRFWB.SimulatorRFWB(depth,width);
                    break;
                case 22:
                    SimulatorRFWH setRFWH = new SimulatorRFWH();
                    setRFWH.SimulatorRFWH(depth,width);
                    break;
                case 23:
                    SimulatorRFBH setRFBH = new SimulatorRFBH();
                    setRFBH.SimulatorRFBH(depth,width);
                    break;
                case 24:
                    SimulatorRWBH setRWBH = new SimulatorRWBH();
                    setRWBH.SimulatorRWBH(depth,width);
                    break;
                case 25:
                    SimulatorFWBH setFWBH = new SimulatorFWBH();
                    setFWBH.SimulatorFWBH(depth,width);
                    break;
            }  
            reset_butt.setEnabled(true);
            nextstep_butt.setEnabled(true);
            longstep_butt.setEnabled(true);
        }
    }      
    private class SimulateOption implements ActionListener{//Generate field
        public void actionPerformed(ActionEvent e){
            switch(version) {
                case 0:
                    Simulator set = new Simulator();
                    set.Simulator(depth,width);
                    if(e.getSource() == reset_butt){
                        set.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        set.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        set.runLongSimulation();
                    }
                    break;
                case 1:
                    SimulatorRF setRF = new SimulatorRF();
                    setRF.SimulatorRF(depth,width);
                    if(e.getSource() == reset_butt){
                        setRF.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRF.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRF.runLongSimulation();
                    }
                    break;
                case 2:
                    SimulatorRW setRW = new SimulatorRW();
                    setRW.SimulatorRW(depth,width);
                    if(e.getSource() == reset_butt){
                        setRW.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRW.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRW.runLongSimulation();
                    }
                    break;
                case 3:
                    SimulatorRB setRB = new SimulatorRB();
                    setRB.SimulatorRB(depth,width);
                    if(e.getSource() == reset_butt){
                        setRB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRB.runLongSimulation();
                    }
                    break;
                case 4:
                    SimulatorRH setRH = new SimulatorRH();
                    setRH.SimulatorRH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRH.runLongSimulation();
                    }
                    break;
                case 5:
                    SimulatorFW setFW = new SimulatorFW();
                    setFW.SimulatorFW(depth,width);
                    if(e.getSource() == reset_butt){
                        setFW.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFW.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFW.runLongSimulation();
                    }
                    break;
                case 6:
                    SimulatorFB setFB = new SimulatorFB();
                    setFB.SimulatorFB(depth,width);
                    if(e.getSource() == reset_butt){
                        setFB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFB.runLongSimulation();
                    }
                    break;
                case 7:
                    SimulatorFH setFH = new SimulatorFH();
                    setFH.SimulatorFH(depth,width);
                    if(e.getSource() == reset_butt){
                        setFH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFH.runLongSimulation();
                    }
                    break;
                case 8:
                    SimulatorWB setWB = new SimulatorWB();
                    setWB.SimulatorWB(depth,width);
                    if(e.getSource() == reset_butt){
                        setWB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setWB.runLongSimulation();
                    }
                    break;
                case 9:
                    SimulatorWH setWH = new SimulatorWH();
                    setWH.SimulatorWH(depth,width);
                    if(e.getSource() == reset_butt){
                        setWH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setWH.runLongSimulation();
                    }
                    break;
                case 10:
                    SimulatorBH setBH = new SimulatorBH();
                    setBH.SimulatorBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setBH.runLongSimulation();
                    }
                    break;
                case 11:
                    SimulatorRFW setRFW = new SimulatorRFW();
                    setRFW.SimulatorRFW(depth,width);
                    if(e.getSource() == reset_butt){
                        setRFW.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRFW.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRFW.runLongSimulation();
                    }
                    break;
                case 12:
                    SimulatorRFB setRFB = new SimulatorRFB();
                    setRFB.SimulatorRFB(depth,width);
                    if(e.getSource() == reset_butt){
                        setRFB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRFB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRFB.runLongSimulation();
                    }
                    break;
                case 13:
                    SimulatorRFH setRFH = new SimulatorRFH();
                    setRFH.SimulatorRFH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRFH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRFH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRFH.runLongSimulation();
                    }
                    break;
                case 14:
                    SimulatorRWB setRWB = new SimulatorRWB();
                    setRWB.SimulatorRWB(depth,width);
                    if(e.getSource() == reset_butt){
                        setRWB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRWB.runLongSimulation();
                    }
                    break;
                case 15:
                    SimulatorRWH setRWH = new SimulatorRWH();
                    setRWH.SimulatorRWH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRWH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRWH.runLongSimulation();
                    }
                    break;
                case 16:
                    SimulatorRBH setRBH = new SimulatorRBH();
                    setRBH.SimulatorRBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRBH.runLongSimulation();
                    }
                    break;
                case 17:
                    SimulatorFWB setFWB = new SimulatorFWB();
                    setFWB.SimulatorFWB(depth,width);
                    if(e.getSource() == reset_butt){
                        setFWB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFWB.runLongSimulation();
                    }
                    break;
                case 18:
                    SimulatorFWH setFWH = new SimulatorFWH();
                    setFWH.SimulatorFWH(depth,width);
                    if(e.getSource() == reset_butt){
                        setFWH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFWH.runLongSimulation();
                    }
                    break;
                case 19:
                    SimulatorFBH setFBH = new SimulatorFBH();
                    setFBH.SimulatorFBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setFBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFBH.runLongSimulation();
                    }
                    break;
                case 20:
                    SimulatorWBH setWBH = new SimulatorWBH();
                    setWBH.SimulatorWBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setWBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setWBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setWBH.runLongSimulation();
                    }
                    break;
                case 21:
                    SimulatorRFWB setRFWB = new SimulatorRFWB();
                    setRFWB.SimulatorRFWB(depth,width);
                    if(e.getSource() == reset_butt){
                        setRFWB.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRFWB.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRFWB.runLongSimulation();
                    }
                    break;
                case 22:
                    SimulatorRFWH setRFWH = new SimulatorRFWH();
                    setRFWH.SimulatorRFWH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRFWH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRFWH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRFWH.runLongSimulation();
                    }
                    break;
                case 23:
                    SimulatorRFBH setRFBH = new SimulatorRFBH();
                    setRFBH.SimulatorRFBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRFBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRFBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRFBH.runLongSimulation();
                    }
                    break;
                case 24:
                    SimulatorRWBH setRWBH = new SimulatorRWBH();
                    setRWBH.SimulatorRWBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setRWBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setRWBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setRWBH.runLongSimulation();
                    }
                    break;
                case 25:
                    SimulatorFWBH setFWBH = new SimulatorFWBH();
                    setFWBH.SimulatorFWBH(depth,width);
                    if(e.getSource() == reset_butt){
                        setFWBH.reset();
                    }
                    else if(e.getSource() == nextstep_butt){
                        setFWBH.simulateOneStep();
                    }
                    else if(e.getSource() == longstep_butt){
                        setFWBH.runLongSimulation();
                    }
                    break;
            }  
            reset_butt.setEnabled(true);
            nextstep_butt.setEnabled(true);
            longstep_butt.setEnabled(true);
        }
    }      
}
