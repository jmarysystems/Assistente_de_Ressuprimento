/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uf_new_0;

import uf_0.Uf;
import home_usuario_logado.Bean_Usuario_Logado;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Query;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import usuario_0.Usuario;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Uf_New_Inicio {
        
    Uf_New Uf_New;//Materias_Home; 
    
    public Uf_New_Inicio( Uf_New Estabelecimento_New2 ) {
        
        Uf_New = Estabelecimento_New2;

        inicioCBS();
        inicio();
    }
    
    private void inicioCBS(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );         
            Color c = new Color( 255, 255, 255 );
        
            Uf_New.cbPais     .setBackground( c );           
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicioCBS(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();       
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Uf_New_Inicio( Uf_New Estabelecimento_New2, uf_0.Uf Uf ) {
        
        Uf_New = Estabelecimento_New2;
          
        setarDadosDoBancoNoJPanel( Uf );
        
        setarCBPais( true, Uf ); 
        //Eventos
        try{
            MouseListener[] MouseListener = Uf_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Uf_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Uf_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Uf_New.Estabelecimento, true );
                }
            });    
        }catch(Exception e ){} 
        Uf_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            setarCBPais(false, new uf_0.Uf());
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();                 
    }
    
    //setar CB Matéria
    public void setarCBPais(boolean alterar, uf_0.Uf Uf) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.PAIS", pais_0.Pais.class );          
            List<pais_0.Pais> list = q.getResultList();
            
            if( Uf_New.cbPais.getItemCount() > 0 ) { Uf_New.cbPais.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Uf_New.cbPais.addItem( new BookInfoPais( 
                    list.get(i).getId(),list.get(i).getNomePtbr()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Uf_New.cbPais.getItemCount(); i++){
                
                    BookInfoPais obj = (BookInfoPais) Uf_New.cbPais.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Categoria.getIdMateria(): " + Categoria.getIdMateria() );
                    if( obj.ID == Uf.getIdPais() ){
                    
                        Uf_New.cbPais.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBPais(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    //Salvar
    public void salvar( uf_0.Uf Uf, boolean alterar ) {  
        if( validacoes() == true ) {
            
            if( Uf_New.tfCodigo.getText().trim().length() > 1 ) {    
                
                if( Uf_New.tfNome_PTBR.getText().trim().length() > 1 ) {
                    
                    if( Uf_New.cbPais.getItemCount() > 0 ) {
                        
                        confirmarSalvar( Uf, alterar ); 
                    }
                    else{ sem2(); }
                }
                else{ sem2(); }
                
            }
            else{ sem1(); }
        }
    } 
    
    public void confirmarSalvar( uf_0.Uf Pais, boolean alterar ) { 
        Pais = pegarDadosDoJPanelParaSalvar( Pais, alterar );
        
        if( verificarRepeticao( Pais ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Pais, JPAUtil.em());
            Uf estabelecimento_cadastrado = (Uf) DAOGenericoJPA.gravanovoOUatualizar();
             
            Uf_New.Uf_Home.Inicio.cancelar();
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
   
   private boolean verificarRepeticao( uf_0.Uf Uf ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.UF", uf_0.Uf.class );
            List<uf_0.Uf> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Uf.getId() ){
                    
                    if( list.get(i).getNome().equals( Uf.getNome() ) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private uf_0.Uf pegarDadosDoJPanelParaSalvar( uf_0.Uf Uf, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Uf.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Uf.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{ 
            
            Uf.setCodigoIbge( Uf_New.tfCodigo.getText() );
        }catch(Exception e ){}
        
        try{ 
            
            Uf.setNome( Uf_New.tfNome_PTBR.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Uf.setSigla( Uf_New.tfNome_ENG.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            BookInfoPais obj = (BookInfoPais) Uf_New.cbPais.getSelectedItem();
            Uf.setIdPais(obj.ID);
        }catch(Exception e ){}
        
        //RETORNO
        return Uf;
    }
    
    public void setarDadosDoBancoNoJPanel( uf_0.Uf Uf ) {  
 
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Uf, JPAUtil.em());            
            Usuario usuario_do_estabelecimento = (Usuario) DAOGenericoJPA.getBean( Uf.getIdUsuario(), Usuario.class );
                
            Uf_New.tfCadastrado_Por.setText( usuario_do_estabelecimento.getLogin().toUpperCase() );    
                                                                          
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Uf.getDataCadastro() );

            Uf_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        try{ Uf_New.tfCodigo.setText( String.valueOf( Uf.getCodigoIbge() ) ); }catch(Exception e ){}
        try{ Uf_New.tfNome_PTBR.setText( Uf.getNome() ); }catch(Exception e ){}
        
        try{ Uf_New.tfNome_ENG.setText( Uf.getSigla() ); }catch(Exception e ){}
        
        inicioCBS();

        //OUTROS FIM 
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Uf_New.tfCodigo       .setEditable(b);       

        }catch(Exception e ){} 
    }
    
    public boolean validacoes(){
        boolean retorno = true;
        
        // validações
        try{
            /*
            if ( !Uf_New.tfEmail.getText().trim().equals("") ) {
            
                if( ValidarEmail.validar(Uf_New.tfEmail.getText().trim() ) == true ){
                }
                else{
                    
                    retorno = false;
                
                    Uf_New.tfEmail.setText( "" );
                    JOPM JOptionPaneMod = new JOPM( 2, "EMAIL, "
                        + "\nO Campo EMAIL não se está se referindo a um EMAIL válido"
                        + "\nInforme um EMAIL válido"
                        + "\n", "EMAIL" );
                    Uf_New.tfEmail.requestFocus();
                }
            }*/
        }catch(Exception e ){} 
        
        return retorno;
    }
    
}
