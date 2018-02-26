/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utilidades_imagens.Imagens;

/**
 *
 * @author ana
 */
public class JOPM {
    
    public JOPM( int j, String mensagem ) {
        padrao( mensagem );  
    }
    
    public JOPM( int j, String mensagem2, String mensagemsemicone21, Icon icon ) {
        iconcustomizado( mensagem2, mensagemsemicone21, icon );
    }
    
    public JOPM( int j, String mensagem2, String mensagem21 ) {
        switch( j ){
            case 1: aviso   ( mensagem2, mensagem21 ); break;  
            case 2: erroicon( mensagem2, mensagem21 ); break; 
            case 3: semicon ( mensagem2, mensagem21 ); break;       
        }
    }
    
    public JOPM( String metodo, String mensagem, String classe ){ Class<Imagens> clazzHome = Imagens.class;
        //custom title, custom icon
        JOptionPane.showMessageDialog( null,
                metodo + "\n"
                + "\nExceção gerada:\n"
                + mensagem + "\n"
                + "\nOK Para Prosseguir",
                "Class: " + classe,
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
    }
    
    public JOPM(String mensagem){ Class<Imagens> clazzHome = Imagens.class;
        //custom title, custom icon
        JOptionPane.showMessageDialog( null,
                "\n"
                + "\n"+ mensagem + "..." +"\n"
                + "\n"
                + "\nAguarde a Finalização",
                mensagem + "...",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon( clazzHome.getResource("carregando.gif")) );
    }
    
    public void fechar() throws Throwable{
        
        try{ this.finalize(); } catch( Exception e ){}
    }
        
    private void padrao( String mensagem ){
        //default title and icon
        JOptionPane.showMessageDialog( null, mensagem );	
    }
    
    private void aviso( String mensagem, String aviso ){
        //custom title, warning icon
        JOptionPane.showMessageDialog( null, mensagem,
                aviso,
                JOptionPane.WARNING_MESSAGE);	
    }
       
    private void erroicon( String mensagem, String erro ){
        //custom title, error icon
        JOptionPane.showMessageDialog( null,
                mensagem,
                erro,
                JOptionPane.ERROR_MESSAGE);	
    }
    
    private void semicon( String mensagem, String mensagemsemicone ){
        //custom title, no icon
        JOptionPane.showMessageDialog( null,
                mensagem,
                mensagemsemicone,
                JOptionPane.PLAIN_MESSAGE);
    }
    
    private void iconcustomizado( String mensagem, String mensagemsemicone, Icon icon ){
        //custom title, custom icon
        JOptionPane.showMessageDialog( null,
                mensagem,
                mensagemsemicone,
                JOptionPane.INFORMATION_MESSAGE,
                icon);
    }
        
}
