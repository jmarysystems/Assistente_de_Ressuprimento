/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import utilidades_imagens.Imagens;

/**
 *
 * @author AnaMariana
 */
public class JpImagem extends javax.swing.JPanel {

    /**
     * Creates new form PnTabI9
     */
    String enderecoImagem = "jmlogo2.png";
    
    public JpImagem( String enderecoImagem2 ) { 
        enderecoImagem = enderecoImagem2;
        initComponents();
    }
            
    @Override
    protected void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D) g.create();  
        
        try{
            Class<Imagens> clazzHome = Imagens.class;
            ImageIcon fundo = new ImageIcon( clazzHome.getResource( enderecoImagem ));  
            
            g2d.drawImage(fundo.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);  
            g2d.dispose();  
        }catch(Exception e){}
    }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
