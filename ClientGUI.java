/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShip5;


import BattleShip4.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mal
 */
public class ClientGUI {
    public JFrame frame;
    public JPanel panel;
    private JPanel panel1;
    private DefaultTableModel tmodel;
    private JTable table;
    private JScrollPane scrollPane;
     private DefaultTableModel tmodel1;
    private JTable table1;
    private JScrollPane scrollPane1;
    private JButton playBtn;
    private JPanel centerPanel;
    private JLabel msg;
    public static void main(String[] args) {
        ClientGUI g = new ClientGUI();
    }
    public ClientGUI() {
        frame = new JFrame("BattleShip");
        frame.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 1600, 1200);
        centerPanel = new JPanel(new GridLayout(2,1));
        frame.add(centerPanel, BorderLayout.CENTER);
        panel = new JPanel();
        msg = new JLabel();
        msg.setText("Placer dine skibe øverst. Skyd mod fjendens skibe i nederste");
        playBtn = new JButton("Next Turn");
        panel1 = new JPanel();
        panel.setLayout(new FlowLayout());
        centerPanel.add(panel);
        centerPanel.add(panel1);
        //frame.add(panel1);
        setupTables();
        //JLabel j = (JLabel)tmodel.getValueAt(8, 3);
        
        frame.add(msg, BorderLayout.NORTH);
        frame.add(playBtn, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getPlayBtn() {
        return playBtn;
    }

    public void setPlayBtn(JButton playBtn) {
        this.playBtn = playBtn;
    }
    
    private void setupTables() {
        String columnNames[] = { "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        String dataValues[][] =
		{
			{ "1", "", "", "", "", "", "", "", "", "", "" },
			{ "2", "", "", "", "", "", "", "", "", "", "" },
			{ "3", "", "", "", "", "", "", "", "", "", "" },
			{ "4", "", "", "", "", "", "", "", "", "", "" },
			{ "5", "", "", "", "", "", "", "", "", "", "" },
			{ "6", "", "", "", "", "", "", "", "", "", "" },
			{ "7", "", "", "", "", "", "", "", "", "", "" },
			{ "8", "", "", "", "", "", "", "", "", "", "" },
			{ "9", "", "", "", "", "", "", "", "", "", "" },
			{ "10", "", "", "", "", "", "", "", "", "", "" },
		};		
        tmodel = new DefaultTableModel(dataValues ,columnNames);
        table = new JTable(tmodel);
        CustomCellRenderer1 renderer1 = new CustomCellRenderer1();
        CustomCellRenderer renderer = new CustomCellRenderer();
        
        
        table.setDefaultRenderer(Object.class, renderer1);
        table.setBounds(16, 16, 420, 301);
        table.setRowHeight(28);
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // kun en celle kan vælges
        //http://stackoverflow.com/questions/6523974/shrink-jscroll-pane-to-same-height-as-jtable/6524224#6524224
	Dimension d = table.getPreferredSize();	
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(d.width,table.getRowHeight()*11+1));
        scrollPane.setBounds(16, 16, 420, 301);
        //scrollPane.setBorder(BorderFactory.createCompoundBorder(border, 
        //BorderFactory.createEmptyBorder(0,0,0,0)));
        //frame.getContentPane().add(scrollPane);
        panel.add(scrollPane);
        
        tmodel1 = new DefaultTableModel(dataValues ,columnNames);
        table1 = new JTable(tmodel);
        table1.setDefaultRenderer(Object.class, renderer);
        table1.setBounds(16, 16, 420, 301);
		table1.setRowHeight(28);
		table1.setCellSelectionEnabled(true);
                table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Dimension d1 = table.getPreferredSize();	
		scrollPane1 = new JScrollPane(table1);
                scrollPane1.setPreferredSize(new Dimension(d.width,table.getRowHeight()*11+1));
		scrollPane1.setBounds(16, 16, 420, 301);
		//scrollPane.setBorder(BorderFactory.createCompoundBorder(border, 
	        //BorderFactory.createEmptyBorder(0,0,0,0)));
		//frame.getContentPane().add(scrollPane);
                //panel.add(scrollPane);
                panel1.add(scrollPane1);
    }
    public void updateBoard(String[][] update) {
        clearTable();
        //update the gameplate, called by BattleShipClient
        SwingUtilities.invokeLater(new Runnable() {
            //https://community.oracle.com/thread/1350100

            @Override
            public void run() {
                //String[] x= { "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
                for(int i = 0; i < update.length; i++) {
                    tmodel.addRow(update[i]);
                }
                
            }
        });
        
    }
    public void clearTable() {
       for( int i = tmodel.getRowCount() - 1; i >= 0; i-- ) {
        tmodel.removeRow(i);
        }
    }
    public void updateBoardx(HashMap<String, String[]> map) {
        for(Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            this.tmodel.addRow(new Object[] {value});
            
        }
    }
    public boolean hit(String[][] playerPlate, String[][] gamePlate) {
        return false;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public DefaultTableModel getTmodel() {
        return tmodel;
    }

    public void setTmodel(DefaultTableModel tmodel) {
        this.tmodel = tmodel;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getTmodel1() {
        return tmodel1;
    }

    public void setTmodel1(DefaultTableModel tmodel1) {
        this.tmodel1 = tmodel1;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }
    
    
    
}