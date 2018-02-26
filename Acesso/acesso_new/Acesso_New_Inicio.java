/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acesso_new;

import acesso.Acesso;
import home_usuario_logado.Bean_Usuario_Logado;
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
public class Acesso_New_Inicio {
        
    Acesso_New Acesso_New;//Materias_Home; 
    
    public Acesso_New_Inicio( Acesso_New Estabelecimento_New2 ) {
        
        Acesso_New = Estabelecimento_New2;

        inicio();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Acesso_New_Inicio( Acesso_New Estabelecimento_New2, acesso.Acesso Estabelecimento ) {
        
        Acesso_New = Estabelecimento_New2;
          
        setarDadosDoBancoNoJPanel( Estabelecimento );
        
        //Eventos
        try{
            MouseListener[] MouseListener = Acesso_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Acesso_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Acesso_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Acesso_New.Estabelecimento, true );
                }
            });    
        }catch(Exception e ){} 
        Acesso_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            
                        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Subcategorias_New_Inicio" ); } } }.start();                 
    }
    
    //Salvar
    public void salvar( acesso.Acesso Acesso, boolean alterar ) {  
        if( validacoes() == true ) {
                
            if( Acesso_New.tfComando.getText().trim().length() > 3 ) {
                    
                if( Acesso_New.tfDescricao.getText().trim().length() > 3 ) {
                        
                    confirmarSalvar( Acesso, alterar ); 
                }
                else{ sem2(); }
            }
            else{ sem1(); }
        }
    } 
    
    public void confirmarSalvar( acesso.Acesso Acesso, boolean alterar ) { 
        Acesso = pegarDadosDoJPanelParaSalvar( Acesso, alterar );
        
        if( verificarRepeticao( Acesso ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Acesso, JPAUtil.em());
            Acesso acesso_cadastrado = (Acesso) DAOGenericoJPA.gravanovoOUatualizar();
            
            Acesso_New.Estabelecimento_Home.Inicio.cancelar();
        }
        else{
            
            repetido();
        }
    }
   
   private void sem1() {
       JOPM JOptionPaneMod = new JOPM( 2, "COMANDO, "
                        + "\nO Campo COMANDO não pode ser nulo"
                        + "\nInforme o COMANDO com 4 dígitos no mínimo"
                        + "\n", "COMANDO" );
   }
   
   private void sem2() {
       JOPM JOptionPaneMod = new JOPM( 2, "PARA CADASTRAR UM COMANDO, "
                        + "\nO Campo COMANDO não pode ter menos que 4 caracteres"
                        + "\nO Campo DESCRIÇÃO não pode ter menos que 4 caracteres"
                        + "\n", "PARA CADASTRAR UM COMANDO" );
   }
   
   private void repetido() {
       JOPM JOptionPaneMod = new JOPM( 2, "COMANDO JÁ CADASTRADO, "
                        + "\nO COMANDO informado já existe cadastrado"
                        + "\nInforme um COMANDO ainda não cadastrado"
                        + "\n", "COMANDO" );
   }
   
   private boolean verificarRepeticao( acesso.Acesso Acesso ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ACESSO", acesso.Acesso.class );
            List<acesso.Acesso> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Acesso.getId() ){
                    
                    if( list.get(i).getComandodoacesso().equals( Acesso.getComandodoacesso() ) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private acesso.Acesso pegarDadosDoJPanelParaSalvar( acesso.Acesso Acesso, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Acesso.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Acesso.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{ 
            
            Acesso.setComandodoacesso( Acesso_New.tfComando.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Acesso.setDescricaodoacesso(Acesso_New.tfDescricao.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
                
        //RETORNO
        return Acesso;
    }
    
    String imagemLogotipo = "";
    public void setarDadosDoBancoNoJPanel( acesso.Acesso Acesso ) {  
  
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Acesso, JPAUtil.em());            
            Usuario usuario_do_acesso = (Usuario) DAOGenericoJPA.getBean( Acesso.getIdUsuario(), Usuario.class );
                
            Acesso_New.tfCadastrado_Por.setText( usuario_do_acesso.getLogin().toUpperCase() );    
                                                                      
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Acesso.getDataCadastro() );

            Acesso_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        try{ Acesso_New.tfComando.setText( Acesso.getComandodoacesso() ); }catch(Exception e ){}
        try{ Acesso_New.tfDescricao.setText( Acesso.getDescricaodoacesso() ); }catch(Exception e ){}
        
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Acesso_New.tfComando       .setEditable(b);       

        }catch(Exception e ){} 
    }
    
    public boolean validacoes(){
        boolean retorno = true;
        
        // validações
        try{
            

        }catch(Exception e ){} 
        
        return retorno;
    }
    
}
