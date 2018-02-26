/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package municipio_new2;

import municipio.Municipio;
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
import uf_new_0.BookInfoPais;
import usuario_0.Usuario;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Municipio_New_Inicio {
        
    Municipio_New Municipio_New;//Materias_Home; 
    
    public Municipio_New_Inicio( Municipio_New Estabelecimento_New2 ) {
        
        Municipio_New = Estabelecimento_New2;

        inicioCBS();
        inicio();
    }
    
    private void inicioCBS(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );         
            Color c = new Color( 255, 255, 255 );
        
            Municipio_New.cbPais     .setBackground( c );  
            Municipio_New.cbUf       .setBackground( c );  
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicioCBS(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();       
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Municipio_New_Inicio( Municipio_New Municipio_New2, municipio.Municipio Municipio ) {
        
        Municipio_New = Municipio_New2;
          
        setarDadosDoBancoNoJPanel( Municipio );
        
        setarCBPais     ( true, Municipio ); 
        setarCBUf       ( true, Municipio );
        //Eventos
        try{
            MouseListener[] MouseListener = Municipio_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Municipio_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Municipio_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Municipio_New.Municipio, true );
                }
            });    
        }catch(Exception e ){} 
        Municipio_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            setarCBPais(false, new municipio.Municipio());
            
            //setarCBUf(false, new municipio.Municipio());
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();                 
    }
    
    //setar CB Matéria
    public void setarCBPais(boolean alterar, municipio.Municipio Municipio) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.PAIS", pais_0.Pais.class );          
            List<pais_0.Pais> list = q.getResultList();
            
            if( Municipio_New.cbPais.getItemCount() > 0 ) { Municipio_New.cbPais.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Municipio_New.cbPais.addItem( new BookInfoUf( 
                    list.get(i).getId(),list.get(i).getNomePtbr()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Municipio_New.cbPais.getItemCount(); i++){
                
                    BookInfoPais obj = (BookInfoPais) Municipio_New.cbPais.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Categoria.getIdMateria(): " + Categoria.getIdMateria() );
                    
                    if( obj.ID == Municipio.getIdPais() ){
                    
                        Municipio_New.cbPais.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBPais(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    //setar CB Matéria
    public void setarCBUf(boolean alterar, municipio.Municipio Municipio) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.UF WHERE ID_PAIS = ?", uf_0.Uf.class );
            q.setParameter( 1, Municipio.getIdPais() );            
            List<uf_0.Uf> list = q.getResultList();
                    //System.out.println( "setarCBUf 1" );
            if( Municipio_New.cbUf.getItemCount() > 0 ) { Municipio_New.cbUf.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Municipio_New.cbUf.addItem( new BookInfoUf( 
                    list.get(i).getId(),list.get(i).getNome()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Municipio_New.cbUf.getItemCount(); i++){
                
                    BookInfoUf obj = (BookInfoUf) Municipio_New.cbUf.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Subcategoria.getIdCategoria(): " + Subcategoria.getIdCategoria() );
                    if( obj.ID == Municipio.getIdUf() ){
                    
                        Municipio_New.cbUf.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBMateria(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    } 
    
    //Salvar
    public void salvar( municipio.Municipio Municipio, boolean alterar ) {  
        if( validacoes() == true ) {
            
            if( Municipio_New.tfCodigoIBGE.getText().trim().length() > 1 ) {    
                
                if( Municipio_New.tfNome_PTBR.getText().trim().length() > 1 ) {
                    
                    if( Municipio_New.cbPais.getItemCount() > 0 ) {
                        
                        if( Municipio_New.cbUf.getItemCount() > 0 ) {
                            
                            confirmarSalvar( Municipio, alterar ); 
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
    
    public void confirmarSalvar( municipio.Municipio Municipio, boolean alterar ) { 
        Municipio = pegarDadosDoJPanelParaSalvar( Municipio, alterar );
        
        if( verificarRepeticao( Municipio ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Municipio, JPAUtil.em());
            Municipio estabelecimento_cadastrado = (Municipio) DAOGenericoJPA.gravanovoOUatualizar();
             
            Municipio_New.Municipio_Home.Inicio.cancelar();
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
   
   private boolean verificarRepeticao( municipio.Municipio Municipio ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.MUNICIPIO", municipio.Municipio.class );
            List<municipio.Municipio> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Municipio.getId() ){
                    
                    if( list.get(i).getNome().equals( Municipio.getNome() ) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private municipio.Municipio pegarDadosDoJPanelParaSalvar( municipio.Municipio Municipio, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Municipio.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Municipio.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{ 
            
            Municipio.setNome(Municipio_New.tfNome_PTBR.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Municipio.setCodigoIbge(Municipio_New.tfCodigoIBGE.getText() );
        }catch(Exception e ){}
                        
        try{ 
            
            Municipio.setCodigoReceitaFederal( Municipio_New.tfCodigoReceitaFederal.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Municipio.setCodigoEstadual( Municipio_New.tfCodigoEstadual.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            BookInfoUf obj = (BookInfoUf) Municipio_New.cbPais.getSelectedItem();
            Municipio.setIdPais(obj.ID);
        }catch(Exception e ){}
        
        try{ 
            
            BookInfoUf obj = (BookInfoUf) Municipio_New.cbUf.getSelectedItem();
            Municipio.setIdUf(obj.ID);
        }catch(Exception e ){}
        
        //RETORNO
        return Municipio;
    }
    
    public void setarDadosDoBancoNoJPanel( municipio.Municipio Municipio ) {  
 
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Municipio, JPAUtil.em());            
            Usuario usuario_do_estabelecimento = (Usuario) DAOGenericoJPA.getBean( Municipio.getIdUsuario(), Usuario.class );
                
            Municipio_New.tfCadastrado_Por.setText( usuario_do_estabelecimento.getLogin().toUpperCase() );    
                                                                          
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Municipio.getDataCadastro() );

            Municipio_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        try{ Municipio_New.tfNome_PTBR.setText( Municipio.getNome() ); }catch(Exception e ){}
        
        try{ Municipio_New.tfCodigoIBGE.setText( String.valueOf( Municipio.getCodigoIbge() ) ); }catch(Exception e ){}
        
        
        try{ Municipio_New.tfCodigoReceitaFederal.setText( Municipio.getCodigoReceitaFederal() ); }catch(Exception e ){}
        
        try{ Municipio_New.tfCodigoEstadual.setText( Municipio.getCodigoEstadual() ); }catch(Exception e ){}
        
        inicioCBS();

        //OUTROS FIM 
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Municipio_New.tfCodigoIBGE       .setEditable(b);       

        }catch(Exception e ){} 
    }
    
    public boolean validacoes(){
        boolean retorno = true;
        
        // validações
        try{
            /*
            if ( !Municipio_New.tfEmail.getText().trim().equals("") ) {
            
                if( ValidarEmail.validar(Municipio_New.tfEmail.getText().trim() ) == true ){
                }
                else{
                    
                    retorno = false;
                
                    Municipio_New.tfEmail.setText( "" );
                    JOPM JOptionPaneMod = new JOPM( 2, "EMAIL, "
                        + "\nO Campo EMAIL não se está se referindo a um EMAIL válido"
                        + "\nInforme um EMAIL válido"
                        + "\n", "EMAIL" );
                    Municipio_New.tfEmail.requestFocus();
                }
            }*/
        }catch(Exception e ){} 
        
        return retorno;
    }
    
}
