/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cep;

import br.com.jmary.home.Home;
import estabelecimento_new.Estabelecimento_New;
import javax.swing.JTabbedPane;

/**
 *
 * @author pc
 */
public class Cep_Home extends javax.swing.JPanel {

    public Home                                   Home;
    public JTabbedPane                            tabHome;
    
    public Cep_Home_Inicio       Inicio;
            
    /**
     * Creates new form Fonema_E_Letra_Home
     * @param Home2
     */
    public Cep_Home( Home Home2 ) {
        initComponents();
        
        Home                       = Home2;
        tabHome                    = new JTabbedPane();
                                        
        Inicio = new Cep_Home_Inicio( this );
                
    }
    
    //////////////////////////////////////////////////////    
    public Cep_Home( Home Home2, JTabbedPane tabHome2 ) {
        initComponents();
        
        Home                       = Home2;
        tabHome                    = tabHome2;
                                
        Inicio = new Cep_Home_Inicio( this, tabHome );
                
    }
    //////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////
    public Estabelecimento_New Estabelecimento_New;
    
    public Cep_Home( Home Home2, Estabelecimento_New Estabelecimento2 ) {
        initComponents();
        
        Home                       = Home2;
        tabHome                    = new JTabbedPane();
        
        Estabelecimento_New        = Estabelecimento2;
                                
        Inicio = new Cep_Home_Inicio( this, Estabelecimento2 );
                
    }
    //////////////////////////////////////////////////////
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHome = new javax.swing.JPanel();

        javax.swing.GroupLayout pnHomeLayout = new javax.swing.GroupLayout(pnHome);
        pnHome.setLayout(pnHomeLayout);
        pnHomeLayout.setHorizontalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        pnHomeLayout.setVerticalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel pnHome;
    // End of variables declaration//GEN-END:variables
    
}
