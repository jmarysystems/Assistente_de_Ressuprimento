/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle_acesso_new;

import acesso.Acesso;
import controle_acesso.Controleacesso;
import estabelecimento.Estabelecimento;
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
public class Controle_Acesso_New_Inicio {
        
    Controle_Acesso_New Controle_Acesso_New;//Materias_Home; 
    
    public Controle_Acesso_New_Inicio( Controle_Acesso_New Estabelecimento_New2 ) {
        
        Controle_Acesso_New = Estabelecimento_New2;

        inicio();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Controle_Acesso_New_Inicio( Controle_Acesso_New Estabelecimento_New2, controle_acesso.Controleacesso Estabelecimento ) {
        
        Controle_Acesso_New = Estabelecimento_New2;
          
        setarDadosDoBancoNoJPanel( Estabelecimento );
        
        //Eventos
        try{
            MouseListener[] MouseListener = Controle_Acesso_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Controle_Acesso_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Controle_Acesso_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Controle_Acesso_New.Controleacesso, true );
                }
            });    
        }catch(Exception e ){} 
        Controle_Acesso_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            
                        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Subcategorias_New_Inicio" ); } } }.start();                 
    }
    
    //Salvar
    public void salvar( controle_acesso.Controleacesso Controleacesso, boolean alterar ) {  
        if( validacoes() == true ) {
                
            if( Controle_Acesso_New.tfUsuario.getText().trim().length() > 3 ) {
                    
                if( Controle_Acesso_New.tfComando.getText().trim().length() > 3 ) {
                        
                    confirmarSalvar( Controleacesso, alterar ); 
                }
                else{ sem2(); }
            }
            else{ sem1(); }
        }
    } 
    
    public void confirmarSalvar( controle_acesso.Controleacesso Controleacesso, boolean alterar ) { 
        Controleacesso = pegarDadosDoJPanelParaSalvar( Controleacesso, alterar );
        
        if( verificarRepeticao( Controleacesso ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Controleacesso, JPAUtil.em());
            Controleacesso controleacesso_cadastrado = (Controleacesso) DAOGenericoJPA.gravanovoOUatualizar();
            
            Controle_Acesso_New.Controle_Acesso_Home.Inicio.cancelar();
        }
        else{
            
            repetido();
        }
    }
   
   private void sem1() {
       JOPM JOptionPaneMod = new JOPM( 2, "USUARIO, "
                        + "\nO Campo USUARIO não pode ser nulo"
                        + "\nInforme o USUARIO para prosseguir"
                        + "\n", "USUARIO" );
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
                        + "\nPara este Usuário"
                        + "\nInforme um COMANDO ainda não cadastrado"
                        + "\n", "COMANDO" );
   }
   
   private boolean verificarRepeticao( controle_acesso.Controleacesso Controleacesso ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.CONTROLEACESSO", controle_acesso.Controleacesso.class );
            List<controle_acesso.Controleacesso> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Controleacesso.getId() ){
                    
                    if( list.get(i).getIdAcesso()          == Controleacesso.getIdAcesso()  &&
                        list.get(i).getIdUsuario()         == Controleacesso.getIdUsuario() &&
                        list.get(i).getIdEstabelecimento() == Controleacesso.getIdEstabelecimento()   ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private controle_acesso.Controleacesso pegarDadosDoJPanelParaSalvar( controle_acesso.Controleacesso Controleacesso, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Controleacesso.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Controleacesso.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{ 
            
            Controleacesso.setIdUsuario(Controle_Acesso_New.Usuario.getId() );
        }catch(Exception e ){}
        
        try{ 
            
            Controleacesso.setIdAcesso(Controle_Acesso_New.Acesso.getId() );
        }catch(Exception e ){}
        
        try{ 
            
            Controleacesso.setIdEstabelecimento( Controle_Acesso_New.Estabelecimento.getId() );
        }catch(Exception e ){}
        
                
        //RETORNO
        return Controleacesso;
    }
    
    String imagemLogotipo = "";
    public void setarDadosDoBancoNoJPanel( controle_acesso.Controleacesso Controleacesso ) {  
  
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Controleacesso, JPAUtil.em());            
            Usuario usuario_do_acesso = (Usuario) DAOGenericoJPA.getBean( Controleacesso.getIdUsuario(), Usuario.class );
                
            Controle_Acesso_New.tfCadastrado_Por.setText( usuario_do_acesso.getLogin().toUpperCase() );    
                                                                      
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Controleacesso.getDataCadastro() );

            Controle_Acesso_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        try{
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Controleacesso, JPAUtil.em());            
            
            Usuario usuario = (Usuario) DAOGenericoJPA.getBean( Controleacesso.getIdUsuario(), Usuario.class );
                       
            Controle_Acesso_New.tfUsuario.setText( usuario.getNomecompleto() );
        } catch( Exception e ){}
        
        try{
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Controleacesso, JPAUtil.em());            
            
            Acesso Acesso = (Acesso) DAOGenericoJPA.getBean( Controleacesso.getIdAcesso(), Acesso.class );
                     
            Controle_Acesso_New.tfComando.setText( Acesso.getComandodoacesso() );
        } catch( Exception e ){}
        
        try{
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Controleacesso, JPAUtil.em());            
            
            Estabelecimento Estabelecimento = (Estabelecimento) DAOGenericoJPA.getBean( Controleacesso.getIdEstabelecimento(), Estabelecimento.class );
                     
            Controle_Acesso_New.tfEstabelecimento.setText( Estabelecimento.getNomeFantasia() + " - CNPJ: " + Estabelecimento.getCnpj() );
        } catch( Exception e ){}
        
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Controle_Acesso_New.tfUsuario       .setEditable(b);       

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
