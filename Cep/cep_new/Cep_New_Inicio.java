/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cep_new;

import cep.Cep;
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
public class Cep_New_Inicio {
        
    Cep_New Cep_New;//Materias_Home; 
    
    public Cep_New_Inicio( Cep_New Estabelecimento_New2 ) {
        
        Cep_New = Estabelecimento_New2;

        inicioCBS();
        inicio();
    }
    
    private void inicioCBS(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );         
            Color c = new Color( 255, 255, 255 );
        
            Cep_New.cbPais      .setBackground( c );  
            Cep_New.cbUf        .setBackground( c ); 
            Cep_New.cbMunicipio .setBackground( c ); 
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicioCBS(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();       
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Cep_New_Inicio( Cep_New Municipio_New2, cep.Cep Cep ) {
        
        Cep_New = Municipio_New2;
          
        setarDadosDoBancoNoJPanel( Cep );
        
        setarCBPais     ( true, Cep ); 
        setarCBUf       ( true, Cep );
        setarCBMunicipio( true, Cep );
        //Eventos
        try{
            MouseListener[] MouseListener = Cep_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Cep_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Cep_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Cep_New.Municipio, true );
                }
            });    
        }catch(Exception e ){} 
        Cep_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            setarCBPais(false, new cep.Cep());
            
            //setarCBUf(false, new municipio.Cep());
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();                 
    }
    
    //setar CB Matéria
    public void setarCBPais(boolean alterar, cep.Cep Cep) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.PAIS", pais_0.Pais.class );          
            List<pais_0.Pais> list = q.getResultList();
            
            if( Cep_New.cbPais.getItemCount() > 0 ) { Cep_New.cbPais.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Cep_New.cbPais.addItem( new uf_new_0.BookInfoPais( 
                    list.get(i).getId(),list.get(i).getNomePtbr()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Cep_New.cbPais.getItemCount(); i++){
                
                    uf_new_0.BookInfoPais obj = (uf_new_0.BookInfoPais) Cep_New.cbPais.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Categoria.getIdMateria(): " + Categoria.getIdMateria() );
                    
                    if( obj.ID == Cep.getIdPais() ){
                    
                        Cep_New.cbPais.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBPais(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    //setar CB Matéria
    public void setarCBUf(boolean alterar, cep.Cep Cep) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.UF WHERE ID_PAIS = ?", uf_0.Uf.class );
            q.setParameter( 1, Cep.getIdPais() );            
            List<uf_0.Uf> list = q.getResultList();
                    //System.out.println( "setarCBUf 1" );
            if( Cep_New.cbUf.getItemCount() > 0 ) { Cep_New.cbUf.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Cep_New.cbUf.addItem( new municipio_new2.BookInfoUf( 
                    list.get(i).getId(),list.get(i).getNome()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Cep_New.cbUf.getItemCount(); i++){
                
                    municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Cep_New.cbUf.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Subcategoria.getIdCategoria(): " + Subcategoria.getIdCategoria() );
                    if( obj.ID == Cep.getIdUf() ){
                    
                        Cep_New.cbUf.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBMateria(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    //setar CB Assunto
    public void setarCBMunicipio( boolean alterar, cep.Cep Cep ) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MUNICIPIO WHERE ID_UF = ?", municipio.Municipio.class );
            q.setParameter( 1, Cep.getIdUf() );            
            List<municipio.Municipio> list = q.getResultList();
                    //System.out.println( "setarCBMunicipio 1" );
            if( Cep_New.cbMunicipio.getItemCount() > 0 ) { Cep_New.cbMunicipio.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Cep_New.cbMunicipio.addItem( new municipio_new2.BookInfoUf( 
                    list.get(i).getId(),list.get(i).getNome()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Cep_New.cbMunicipio.getItemCount(); i++){
                
                    municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Cep_New.cbMunicipio.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Subcategoria.getIdCategoria(): " + Subcategoria.getIdCategoria() );
                    if( obj.ID == Cep.getIdMunicipio() ){
                    
                        Cep_New.cbMunicipio.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBMateria(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    } 
    
    //Salvar
    public void salvar( cep.Cep Cep, boolean alterar ) {  
        if( validacoes() == true ) {
            
            if( Cep_New.tfBairro.getText().trim().length() > 1 ) {    
                
                if( Cep_New.tfCep.getText().trim().length() > 1 ) {
                    
                    if( Cep_New.cbPais.getItemCount() > 0 ) {
                        
                        if( Cep_New.cbUf.getItemCount() > 0 ) {
                            
                            if( Cep_New.cbMunicipio.getItemCount() > 0 ) {
                            
                                confirmarSalvar( Cep, alterar ); 
                            }
                            else{ sem2(); }
                        }
                        else{ sem2(); }
                    }
                    else{ sem2(); }
                }
                else{ sem2(); }
                
            }
            else{ sem1(); }
        }
    } 
    
    public void confirmarSalvar( cep.Cep Cep, boolean alterar ) { 
        Cep = pegarDadosDoJPanelParaSalvar( Cep, alterar );
        
        if( verificarRepeticao( Cep ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Cep, JPAUtil.em());
            Cep estabelecimento_cadastrado = (Cep) DAOGenericoJPA.gravanovoOUatualizar();
             
            Cep_New.Municipio_Home.Inicio.cancelar();
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
   
   private boolean verificarRepeticao( cep.Cep Cep ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.CEP", cep.Cep.class );
            List<cep.Cep> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Cep.getId() ){
                    
                    if( list.get(i).getCep().equals( Cep.getCep() ) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private cep.Cep pegarDadosDoJPanelParaSalvar( cep.Cep Cep, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Cep.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Cep.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{ 
            
            Cep.setCep( Cep_New.tfCep.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Cep.setLogradouro( Cep_New.tfLogradouro.getText().trim().toUpperCase() );
        }catch(Exception e ){}
                        
        try{ 
            
            Cep.setComplemento( Cep_New.tfComplemento.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Cep.setBairro( Cep_New.tfBairro.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            uf_new_0.BookInfoPais obj = (uf_new_0.BookInfoPais) Cep_New.cbPais.getSelectedItem();
            //System.out.println( "Pais: " + obj.ID );
            Cep.setIdPais(obj.ID);
        }catch(Exception e ){}
        
        try{ 
            
            municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Cep_New.cbUf.getSelectedItem();
            //System.out.println( "Estado: " + obj.ID );
            Cep.setIdUf(obj.ID);
        }catch(Exception e ){}
        
        try{ 
            
            municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Cep_New.cbMunicipio.getSelectedItem();
            //System.out.println( "Municipio: " + obj.ID );
            Cep.setIdMunicipio(obj.ID);
        }catch(Exception e ){}
        
        //RETORNO
        return Cep;
    }
    
    public void setarDadosDoBancoNoJPanel( cep.Cep Municipio ) {  
 
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Municipio, JPAUtil.em());            
            Usuario usuario_do_estabelecimento = (Usuario) DAOGenericoJPA.getBean( Municipio.getIdUsuario(), Usuario.class );
                
            Cep_New.tfCadastrado_Por.setText( usuario_do_estabelecimento.getLogin().toUpperCase() );    
                                                                          
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Municipio.getDataCadastro() );

            Cep_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        try{ Cep_New.tfCep.setText( Municipio.getCep() ); }catch(Exception e ){}
        
        try{ Cep_New.tfLogradouro.setText( Municipio.getLogradouro() ); }catch(Exception e ){}
        
        
        try{ Cep_New.tfComplemento.setText( Municipio.getComplemento() ); }catch(Exception e ){}
        
        try{ Cep_New.tfBairro.setText( Municipio.getBairro() ); }catch(Exception e ){}
        
        inicioCBS();

        //OUTROS FIM 
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Cep_New.tfBairro       .setEditable(b);       

        }catch(Exception e ){} 
    }
    
    public boolean validacoes(){
        boolean retorno = true;
        
        // validações
        try{
            /*
            if ( !Cep_New.tfEmail.getText().trim().equals("") ) {
            
                if( ValidarEmail.validar(Cep_New.tfEmail.getText().trim() ) == true ){
                }
                else{
                    
                    retorno = false;
                
                    Cep_New.tfEmail.setText( "" );
                    JOPM JOptionPaneMod = new JOPM( 2, "EMAIL, "
                        + "\nO Campo EMAIL não se está se referindo a um EMAIL válido"
                        + "\nInforme um EMAIL válido"
                        + "\n", "EMAIL" );
                    Cep_New.tfEmail.requestFocus();
                }
            }*/
        }catch(Exception e ){} 
        
        return retorno;
    }
    
}
