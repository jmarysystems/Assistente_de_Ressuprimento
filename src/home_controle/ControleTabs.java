/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package home_controle;

import br.com.jmary.home.Home;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import utilidades.JOPM;

/**
 *
 * @author Ana
 */
public class ControleTabs {
    
    public ControleTabs( Home HomeR ) {
        Home = HomeR.getHome();
    }
    
    Home Home;

    public int qtdComponentes = 0;
    
    public void AddTabsAoHome( String nomeTab, String imgURLIcon, Component comp ) {
        try{ 
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIcon ));
            
            JScrollPane sc = new JScrollPane();
            sc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            sc.setViewportView( comp );
                    
            Home.getHome().tabHome.addTab( "  "+nomeTab, icon, sc, nomeTab ); 
                    
            qtdComponentes+=1;
            Home.getHome().tabHome.setTabComponentAt( qtdComponentes, new ButtonTabComponent( Home.getHome().tabHome, nomeTab, icon, Home.getHome() ) );                                        
            Home.getHome().tabHome.setSelectedIndex( qtdComponentes );
                    
            //removerTabHome();
        }catch( Exception e ){ JOptionPane.showMessageDialog(null,"ControleTabs - AddTabsAoHome\ns"+e); }
    }
    
    public void removerTabSelecionado() {
        
        Home.getHome().tabHome.remove( Home.getHome().tabHome.getSelectedComponent() );
        qtdComponentes-=1;
    }
    
    
            
    // Setando ImageIcon Diferente pelo endereço não pela classe
    JTabbedPane paneSem;
    String nomeTabSem;
    String imgURLIconSem;
    Component compSem;
    public void AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ) {
        paneSem = paneSem2; nomeTabSem = nomeTabSem2; imgURLIconSem = imgURLIconSem2; compSem = compSem2;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );            
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIconSem ) );  
            
            JScrollPane sc = new JScrollPane();
            sc.setBorder( null );
            sc.setViewportView( compSem );
            
            int qtdComponentesControl = paneSem.getTabCount();

            sc.setSize( 100, 19 );
            sc.setPreferredSize(new java.awt.Dimension( 100, 33 ) );
            
            paneSem.addTab( "  "+nomeTabSem, icon, sc, nomeTabSem );              
            paneSem.setTabComponentAt( qtdComponentesControl, new ButtonTabComponentSemControle( paneSem, nomeTabSem, icon ) ); 
            paneSem.setSelectedIndex ( qtdComponentesControl );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ), \n"
                + e.getMessage() + "\n", "ControleTabs" ); } } }.start();    
    }
    
    // Setando ImageIcon Diferente Sem sc pelo endereço não pela classe
    JTabbedPane paneSem2;
    String nomeTabSem2;
    String imgURLIconSem2;
    Component compSem2;
    public void AddTabSemSCControleNovo( JTabbedPane paneSem3, String nomeTabSem3, String imgURLIconSem3, Component compSem3 ) {
        paneSem2 = paneSem3; nomeTabSem2 = nomeTabSem3; imgURLIconSem2 = imgURLIconSem3; compSem2 = compSem3;
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );            
            
            ImageIcon icon = new ImageIcon(getClass().getResource( imgURLIconSem2 ) );  
            
            int qtdComponentesControl = paneSem2.getTabCount();
            
            paneSem2.addTab( "  "+nomeTabSem2, icon, compSem2, nomeTabSem2 );              
            paneSem2.setTabComponentAt( qtdComponentesControl, new ButtonTabComponentSemControle( paneSem2, nomeTabSem2, icon ) ); 
            paneSem2.setSelectedIndex ( qtdComponentesControl );
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "AddTabSemControleNovo( JTabbedPane paneSem2, String nomeTabSem2, String imgURLIconSem2, Component compSem2 ), \n"
                + e.getMessage() + "\n", "ControleTabs" ); } } }.start();    
    }
    
    public void removerTabSemControleSelecionado(JTabbedPane pane) {
        
        pane.remove( pane.getSelectedComponent() );
    }
    
}
