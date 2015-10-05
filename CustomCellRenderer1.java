/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BattleShip5;

/**
 *
 * @author Mal
 */

import BattleShip4.*;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;


public class CustomCellRenderer1 implements TableCellRenderer{
        
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
        JTextField editor = new JTextField();

        if (value != null)
            editor.setText(value.toString());
            editor.setBackground((row % 2 == 0) ? Color.white : new Color(242, 241, 240));
            editor.setBackground(Color.BLUE);
            editor.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK) );
        if (table.isCellSelected(row, column))
         editor.setBackground(Color.GREEN);		
        return editor;
   }
        
}
