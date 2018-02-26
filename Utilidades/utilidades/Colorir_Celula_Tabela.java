/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import gb_evento.Eventoprodutos;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Cleilson
 */
public class Colorir_Celula_Tabela extends DefaultTableCellRenderer {

    List<Eventoprodutos> listEventoprodutosFull = new ArrayList();
    public Colorir_Celula_Tabela( List<Eventoprodutos> listEventoprodutosFull2 ){
        
        listEventoprodutosFull = listEventoprodutosFull2;
    }

    /*
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String bd = ""; try { bd = String.valueOf( table.getValueAt( row, 0 ) ); } catch( Exception e ){}
        System.out.println( "bd  -  " + bd );
        
        for (int it = 0; it < listEventoprodutosFull.size(); it++){
            
            if( listEventoprodutosFull.get(it).getItem().equals(bd)  ){

                label.setBackground(Color.RED);     
                
                break;
            }
        }
        
        return label;
    }*/
    
    @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                //super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                Component c = super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);

                //A coluna do status é 3
                //Coluna Status
                String bd = ""; try { bd = String.valueOf( table.getValueAt( row, 0 ) ); } catch( Exception e ){}
                
                String b = "";
                for (int it = 0; it < listEventoprodutosFull.size(); it++){
                    
                    if( listEventoprodutosFull.get(it).getItem().equals(bd)  ){
                        
                        b = bd;
                    }
                }
                //Coloca cor em todas as linhas,COLUNA(3) que tem o valor "Aberto"
                if (bd != null && bd.equals( b )) {//Se Status for igual a "Aberto"
                    c.setBackground(Color.green);//Preenche a linha de vermelho
                    c.setForeground(Color.BLACK);//E a fonte de branco
                } else {
                    boolean sel = isSelected;
                    if (sel == true) {
                        c.setBackground(getBackground());
                        c.setForeground(getForeground());
                    } else {//Se Status não for "Aberto" 
                        c.setBackground(Color.WHITE);//Preenche a linha de branco
                        c.setForeground(new Color(51, 51, 51));//E a fonte de preto
                    }
                }
                return this;
            }



}
