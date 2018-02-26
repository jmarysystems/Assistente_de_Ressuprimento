/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario_new;

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
public class Usuario_New_Inicio {
        
    Usuario_New Estabelecimento_New;//Materias_Home; 
    
    public Usuario_New_Inicio( Usuario_New Estabelecimento_New2 ) {
        
        Estabelecimento_New = Estabelecimento_New2;

        inicio();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Usuario_New_Inicio( Usuario_New Estabelecimento_New2, usuario_0.Usuario Estabelecimento ) {
        
        Estabelecimento_New = Estabelecimento_New2;
          
        setarDadosDoBancoNoJPanel( Estabelecimento );
        
        //Eventos
        try{
            MouseListener[] MouseListener = Estabelecimento_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Estabelecimento_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Estabelecimento_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar(Estabelecimento_New.Estabelecimento, true );
                }
            });    
        }catch(Exception e ){} 
        Estabelecimento_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            
                        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Subcategorias_New_Inicio" ); } } }.start();                 
    }
    
    //Salvar
    public void salvar( usuario_0.Usuario Usuario, boolean alterar ) {  
        if( validacoes() == true ) {
            
            if( Estabelecimento_New.tfNome_Completo.getText().trim().length() > 6 ) {
                
                if( Estabelecimento_New.tfLogin.getText().trim().length() > 4 ) {
                    
                    if( Estabelecimento_New.pfSenha.getPassword().length > 6 ) {
                        
                        if( Estabelecimento_New.pfRepetirSenha.getPassword().length > 6 ) {
                        
                            char[] senha1 = Estabelecimento_New.pfSenha.getPassword();
                            char[] senha2 = Estabelecimento_New.pfRepetirSenha.getPassword();
                            if (senha1.length == senha2.length) {
                            
                                confirmarSalvar( Usuario, alterar ); 
                            }
                            else{ sem3(); }
                        }
                    }
                    else{ sem2(); }
                }
                else{ sem2(); }
                
            }
            else{ sem1(); }
        }
    } 
    
    public void confirmarSalvar( usuario_0.Usuario Usuario, boolean alterar ) { 
        Usuario = pegarDadosDoJPanelParaSalvar( Usuario, alterar );
        
        if( verificarRepeticao( Usuario ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Usuario, JPAUtil.em());
            Usuario usuario_cadastrado = (Usuario) DAOGenericoJPA.gravanovoOUatualizar();
            
            Estabelecimento_New.Estabelecimento_Home.Inicio.cancelar();
        }
        else{
            
            repetido();
        }
    }
   
   private void sem1() {
       JOPM JOptionPaneMod = new JOPM( 2, "NOME COMPLETO, "
                        + "\nO Campo NOME COMPLETO não pode ser nulo"
                        + "\nInforme o NOME COMPLETO do usuário"
                        + "\n", "NOME COMPLETO" );
   }
   
   private void sem2() {
       JOPM JOptionPaneMod = new JOPM( 2, "PARA CADASTRAR UM USUÁRIO, "
                        + "\nO Campo LOGIN não pode ter menos que 5 caracteres"
                        + "\nO Campo SENHA não pode ter menos que 7 caracteres"
                        + "\nO Campo REPETIR SENHA não pode ter menos que 7 caracteres"
                        + "\n", "PARA CADASTRAR UM USUÁRIO" );
   }
   
   private void sem3() {
       JOPM JOptionPaneMod = new JOPM( 2, "CONFIRMAÇÃO DA SENHA DIFERENTE, "
                        + "\nO Campo SENHA e CONFIRMAÇÃO DA SENHA não pode ser DIFERENTE"
                        + "\nInforme a SENHA e CONFIRMAÇÃO DA SENHA IGUAIS"
                        + "\n", "CONFIRMAÇÃO DA SENHA DIFERENTE" );
   }
       
   private void repetido() {
       JOPM JOptionPaneMod = new JOPM( 2, "USUÁRIO JÁ CADASTRADO, "
                        + "\nO LOGIN informado já existe cadastrado"
                        + "\nInforme um LOGIN ainda não cadastrado"
                        + "\n", "LOGIN" );
   }
   
   private boolean verificarRepeticao( usuario_0.Usuario Usuario ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM SCHEMAJMARY.USUARIO", usuario_0.Usuario.class );
            List<usuario_0.Usuario> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Usuario.getId() ){
                    
                    if( list.get(i).getLogin().equals( Usuario.getLogin() ) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private usuario_0.Usuario pegarDadosDoJPanelParaSalvar( usuario_0.Usuario Usuario, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                
                Usuario.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Usuario.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{
            
            Usuario.setImagemLogotipo( Estabelecimento_New.icone_do_estabelecimento );
        }catch(Exception e ){} 
        
        try{ 
            
            Usuario.setNomecompleto(Estabelecimento_New.tfNome_Completo.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Usuario.setPermitiracesso( Estabelecimento_New.chPermitirAcessar.isSelected() );
        }catch(Exception e ){}
        
        try{ 
            
            Usuario.setLogin(Estabelecimento_New.tfLogin.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        
        try{ 
            
            String senha = Estabelecimento_New.pfSenha.getText();
            System.out.println( "Senha - " + senha );
            Usuario.setSenha( senha ); 
        }catch(Exception e ){}

        
        //RETORNO
        return Usuario;
    }
    
    
    
    
    
    
    String imagemLogotipo = "";
    public void setarDadosDoBancoNoJPanel( usuario_0.Usuario Usuario ) {  
        try{ imagemLogotipo = Usuario.getImagemLogotipo(); }catch(Exception e ){}  
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Usuario, JPAUtil.em());            
            Usuario usuario_que_cadastrou = (Usuario) DAOGenericoJPA.getBean( Usuario.getIdUsuario(), Usuario.class );
                
            Estabelecimento_New.tfCadastrado_Por.setText( usuario_que_cadastrou.getLogin().toUpperCase() ); 
                                                             
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Usuario.getDataCadastro() );

            Estabelecimento_New.tfData_Cadastro.setText( data_formatada );                  
        }catch(Exception e ){} 
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            if( !imagemLogotipo.equals("") ){
            
                String home    = System.getProperty("user.dir");
                String endimgs = home + System.getProperty("file.separator") + "Logo_Estabelecimentos";
            
                File f  = new File( endimgs + System.getProperty("file.separator") + imagemLogotipo );
            
                Image image = null;
                try {  
                    image = ImageIO.read(f);  
                
                    Estabelecimento_New.lbIcon.setIcon(new ImageIcon(image.getScaledInstance(
                        100, 100, Image.SCALE_DEFAULT)));
                
                    Estabelecimento_New.icone_do_estabelecimento = f.getName();
                } catch (IOException ex) { System.out.println( "c2222 "); ex.printStackTrace();  }
            }
        } catch( Exception e ){ } } }.start();
        
        
        
        try{ 
            
            Estabelecimento_New.tfNome_Completo.setText( Usuario.getNomecompleto() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento_New.chPermitirAcessar.setSelected( Usuario.getPermitiracesso() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento_New.tfLogin.setText( Usuario.getLogin() );
        }catch(Exception e ){}
        
        
        try{ 
            
            char[] senhaCorreta = Usuario.getSenha().toCharArray(); 
            System.out.println( "Usuario.getSenha() - " + Usuario.getSenha() );
            Estabelecimento_New.pfSenha.setText( Usuario.getSenha() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento_New.pfRepetirSenha.setText( Usuario.getSenha() );
        }catch(Exception e ){}
        
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Estabelecimento_New.tfLogin       .setEditable(b);       

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
