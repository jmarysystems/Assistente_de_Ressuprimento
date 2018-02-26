/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pais_new;

import pais_0.Pais;
import home_usuario_logado.Bean_Usuario_Logado;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import usuario_0.Usuario;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Pais_New_Inicio {
        
    Pais_New Pais_New;//Materias_Home; 
    
    public Pais_New_Inicio( Pais_New Estabelecimento_New2 ) {
        
        Pais_New = Estabelecimento_New2;

        inicio();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Pais_New_Inicio( Pais_New Estabelecimento_New2, pais_0.Pais Estabelecimento ) {
        
        Pais_New = Estabelecimento_New2;
          
        setarDadosDoBancoNoJPanel( Estabelecimento );
        
        //Eventos
        try{
            MouseListener[] MouseListener = Pais_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Pais_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Pais_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Pais_New.Estabelecimento, true );
                }
            });    
        }catch(Exception e ){} 
        Pais_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            
                        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Subcategorias_New_Inicio" ); } } }.start();                 
    }
    
    //Salvar
    public void salvar( pais_0.Pais Estabelecimento, boolean alterar ) {  
        if( validacoes() == true ) {
            
            if( Pais_New.tfCodigo.getText().trim().length() > 1 ) {    
                
                if( Pais_New.tfNome_PTBR.getText().trim().length() > 1 ) {
                    
                    if( Pais_New.tfNome_PTBR.getText().trim().length() > 1 ) {
                        
                        confirmarSalvar( Estabelecimento, alterar ); 
                    }
                    else{ sem2(); }
                }
                else{ sem2(); }
                
            }
            else{ sem1(); }
        }
    } 
    
    public void confirmarSalvar( pais_0.Pais Pais, boolean alterar ) { 
        Pais = pegarDadosDoJPanelParaSalvar( Pais, alterar );
        
        if( verificarRepeticao( Pais ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Pais, JPAUtil.em());
            Pais estabelecimento_cadastrado = (Pais) DAOGenericoJPA.gravanovoOUatualizar();
             
            Pais_New.Estabelecimento_Home.Inicio.cancelar();
        }
        else{
            
            repetido();
        }
    }
   
   private void sem1() {
       JOPM JOptionPaneMod = new JOPM( 2, "CÓDIGO, "
                        + "\nO Campo CÓDIGO não pode ser nulo ou inválido"
                        + "\nInforme um CÓDIGO válido"
                        + "\n", "CÓDIGO" );
   }
   
   private void sem2() {
       JOPM JOptionPaneMod = new JOPM( 2, "PARA CADASTRAR UM PAÍS, "
                        + "\nO Campo CÓDIGO não pode ser nulo"
                        + "\nO Campo NOME PTBR não pode ser nulo"
                        + "\n", "PARA CADASTRAR UM PAIS" );
   }
   
   private void repetido() {
       JOPM JOptionPaneMod = new JOPM( 2, "PAÍS JÁ CADASTRADO, "
                        + "\nO PAÍS informado já existe cadastrado"
                        + "\nInforme um PAÍS ainda não cadastrado"
                        + "\n", "PAÍS" );
   }
   
   private boolean verificarRepeticao( pais_0.Pais Pais ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.PAIS", pais_0.Pais.class );
            List<pais_0.Pais> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Pais.getId() ){
                    
                    if( list.get(i).getNomePtbr().equals( Pais.getNomePtbr()) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private pais_0.Pais pegarDadosDoJPanelParaSalvar( pais_0.Pais Pais, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Pais.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Pais.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{
            
            Pais.setImagemLogotipo(Pais_New.icone_do_estabelecimento );
        }catch(Exception e ){} 
        
        try{ 
            
            Pais.setCodigo( Integer.parseInt( Pais_New.tfCodigo.getText() ) );
        }catch(Exception e ){}
        
        try{ 
            
            Pais.setNomePtbr( Pais_New.tfNome_PTBR.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Pais.setNomeEn( Pais_New.tfNome_ENG.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Pais.setSigla2( Pais_New.tfSigla2.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Pais.setSigla3( Pais_New.tfSigla3.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        //RETORNO
        return Pais;
    }
    
    String imagemLogotipo = "";
    public void setarDadosDoBancoNoJPanel( pais_0.Pais Pais ) {  
        try{ imagemLogotipo = Pais.getImagemLogotipo(); }catch(Exception e ){}  
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Pais, JPAUtil.em());            
            Usuario usuario_do_estabelecimento = (Usuario) DAOGenericoJPA.getBean( Pais.getIdUsuario(), Usuario.class );
                
            Pais_New.tfCadastrado_Por.setText( usuario_do_estabelecimento.getLogin().toUpperCase() );    
                                                                          
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Pais.getDataCadastro() );

            Pais_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            if( !imagemLogotipo.equals("") ){
            
                String home    = System.getProperty("user.dir");
                String endimgs = home + System.getProperty("file.separator") + "Logo_Estabelecimentos";
            
                File f  = new File( endimgs + System.getProperty("file.separator") + imagemLogotipo );
            
                Image image = null;
                try {  
                    image = ImageIO.read(f);  
                
                    Pais_New.lbIcon.setIcon(new ImageIcon(image.getScaledInstance(
                        100, 100, Image.SCALE_DEFAULT)));
                
                    Pais_New.icone_do_estabelecimento = f.getName();
                } catch (IOException ex) { System.out.println( "c2222 "); ex.printStackTrace();  }
            }
        } catch( Exception e ){ } } }.start();
        
        try{ Pais_New.tfCodigo.setText( String.valueOf( Pais.getCodigo() ) ); }catch(Exception e ){}
        try{ Pais_New.tfNome_PTBR.setText( Pais.getNomePtbr() ); }catch(Exception e ){}
        
        try{ Pais_New.tfNome_ENG.setText( Pais.getNomeEn() ); }catch(Exception e ){}
        try{ Pais_New.tfSigla2.setText( Pais.getSigla2() ); }catch(Exception e ){}
        try{ Pais_New.tfSigla3.setText( Pais.getSigla3() ); }catch(Exception e ){}

        //OUTROS FIM 
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Pais_New.tfCodigo       .setEditable(b);       

        }catch(Exception e ){} 
    }
    
    public boolean validacoes(){
        boolean retorno = true;
        
        // validações
        try{
            /*
            if ( !Pais_New.tfEmail.getText().trim().equals("") ) {
            
                if( ValidarEmail.validar(Pais_New.tfEmail.getText().trim() ) == true ){
                }
                else{
                    
                    retorno = false;
                
                    Pais_New.tfEmail.setText( "" );
                    JOPM JOptionPaneMod = new JOPM( 2, "EMAIL, "
                        + "\nO Campo EMAIL não se está se referindo a um EMAIL válido"
                        + "\nInforme um EMAIL válido"
                        + "\n", "EMAIL" );
                    Pais_New.tfEmail.requestFocus();
                }
            }*/
        }catch(Exception e ){} 
        
        return retorno;
    }
    
}
