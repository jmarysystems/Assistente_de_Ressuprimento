/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema_submenus;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import jpa.JPAUtil;
import sistema.Sistema_Home;

/**
 *
 * @author ana
 */
public class Submenu3 extends javax.swing.JPanel {

    Sistema_Home Sistema_Home;
        
    public Submenu3( Sistema_Home Sistema_Home2 ) {
        Sistema_Home = Sistema_Home2;
        
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btExercicios3 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        btMatematica = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        btExercicios4 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        btExercicios5 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        btExercicios1 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        btExercicios2 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();

        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jToolBar2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setPreferredSize(new java.awt.Dimension(658, 71));

        jSeparator7.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator7);

        btExercicios3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/home.png"))); // NOI18N
        btExercicios3.setText("Home");
        btExercicios3.setFocusable(false);
        btExercicios3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExercicios3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExercicios3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExercicios3MousePressed(evt);
            }
        });
        jToolBar2.add(btExercicios3);

        jSeparator13.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator13);

        btMatematica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/fonema_e_letra_estudar.png"))); // NOI18N
        btMatematica.setText("Matemática");
        btMatematica.setFocusable(false);
        btMatematica.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMatematica.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btMatematica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btMatematicaMousePressed(evt);
            }
        });
        jToolBar2.add(btMatematica);

        jSeparator14.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator14);

        btExercicios4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/fonema_e_letra_estudar.png"))); // NOI18N
        btExercicios4.setText("Física");
        btExercicios4.setEnabled(false);
        btExercicios4.setFocusable(false);
        btExercicios4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExercicios4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExercicios4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExercicios4MousePressed(evt);
            }
        });
        jToolBar2.add(btExercicios4);

        jSeparator15.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator15);

        btExercicios5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/fonema_e_letra_estudar.png"))); // NOI18N
        btExercicios5.setText("Química");
        btExercicios5.setEnabled(false);
        btExercicios5.setFocusable(false);
        btExercicios5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExercicios5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExercicios5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExercicios5MousePressed(evt);
            }
        });
        jToolBar2.add(btExercicios5);

        jSeparator10.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator10);

        btExercicios1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/exercicios.png"))); // NOI18N
        btExercicios1.setText("Exercícios");
        btExercicios1.setEnabled(false);
        btExercicios1.setFocusable(false);
        btExercicios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExercicios1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExercicios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExercicios1MousePressed(evt);
            }
        });
        jToolBar2.add(btExercicios1);

        jSeparator11.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator11);

        btExercicios2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistema_imagens/desafios.png"))); // NOI18N
        btExercicios2.setText("Desafios");
        btExercicios2.setEnabled(false);
        btExercicios2.setFocusable(false);
        btExercicios2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExercicios2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(btExercicios2);

        jSeparator12.setSeparatorSize(new java.awt.Dimension(12, 0));
        jToolBar2.add(jSeparator12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btMatematicaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btMatematicaMousePressed
        /*if( btMatematica.isEnabled() == true ){
            try{
                String materia = "MATEMÁTICA";
            
                materias.Materia Materia = new materias.Materia();
                List<materias.Materia> ListMateria = new ArrayList();
                Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MATERIA WHERE MATERIA LIKE ?", materias.Materia.class );
                q.setParameter(1, materia );
            
                ListMateria = q.getResultList();
                for (int i = 0; i < ListMateria.size(); i++){ Materia = ListMateria.get(i);
                    if( Materia.getMateria().equals( materia ) ){ 
                    
                        Sistema_Home.Home.ControleTabs.AddTabsAoHome("Matemática", "/sistema_imagens/livroTp.gif", new sistema_designer.JPHtmlPrincipal( Sistema_Home.Home, materia ) );
                        break;
                    }            
                }
                     
            } catch( Exception e ){}             
        }*/ 
    }//GEN-LAST:event_btMatematicaMousePressed

    private void btExercicios3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExercicios3MousePressed
        try{
            Sistema_Home.Home.tabHome.setSelectedIndex( 0 );
        } catch( Exception e ){}        
    }//GEN-LAST:event_btExercicios3MousePressed

    private void btExercicios1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExercicios1MousePressed
        
    }//GEN-LAST:event_btExercicios1MousePressed

    private void btExercicios4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExercicios4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExercicios4MousePressed

    private void btExercicios5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExercicios5MousePressed
        // TODO add your handbtMatematicare:
    }//GEN-LAST:event_btExercicios5MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExercicios1;
    private javax.swing.JButton btExercicios2;
    private javax.swing.JButton btExercicios3;
    private javax.swing.JButton btExercicios4;
    private javax.swing.JButton btExercicios5;
    private javax.swing.JButton btMatematica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}
