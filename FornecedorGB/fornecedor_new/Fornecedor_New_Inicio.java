/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fornecedor_new;

import java.awt.event.MouseListener;
import java.util.List;
import javax.persistence.Query;
import javax.swing.text.MaskFormatter;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import utilidades.JOPM;

/**
 *
 * @author pc
 */
public class Fornecedor_New_Inicio {
        
    Fornecedor_New Fornecedor_New;//Materias_Home; 
    
    public Fornecedor_New_Inicio( Fornecedor_New Fornecedor_New2 ) {
        
        Fornecedor_New = Fornecedor_New2;

        inicio();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Fornecedor_New_Inicio( Fornecedor_New Fornecedor_New2, fornecedor.Fornecedor Fornecedor ) {
        
        Fornecedor_New = Fornecedor_New2;
          
        setarDadosDoBancoNoJPanel( Fornecedor );
        
        //Eventos
        try{
            MouseListener[] MouseListener = Fornecedor_New.btSalvar.getMouseListeners();
            for ( MouseListener MouseListener1 : MouseListener) {
                Fornecedor_New.btSalvar.removeMouseListener( MouseListener1 );
            }
        
            Fornecedor_New.btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                 public void mousePressed(java.awt.event.MouseEvent evt) {

                    salvar( Fornecedor_New.Fornecedor, true );
                    alteracoesExternas( Fornecedor_New.Fornecedor );
                }
            });    
        }catch(Exception e ){} 
        Fornecedor_New.btSalvar.setEnabled(true);
        
    }
    ///////////////////////////////////////////////////////////////////////////
    
    private void inicio(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            
                        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Subcategorias_New_Inicio" ); } } }.start();                 
    }
    
    private void alteracoesExternas( fornecedor.Fornecedor Fornecedor ){
        try {           
            /*
            Query q2 = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.PRODUTOPORFORNECEDOR WHERE ID_FORNECEDOR = ?", produto_por_fornecedor.Produtoporfornecedor.class );
            q2.setParameter(1, Fornecedor.getId() ); 
            
            List<produto_por_fornecedor.Produtoporfornecedor> list = q2.getResultList();
            for (int i = 0; i < list.size(); i++){
                
                produto_por_fornecedor.Produtoporfornecedor Produtoporfornecedor = list.get(i);
                
                //if( !list.get(i).getNomedaembalagem().trim().equals( Produto.getNomedaembalagem().trim() ) ) {
                
                    Produtoporfornecedor.setNomeourazaosocial( Fornecedor.getNomeourazaosocial() );
                    Produtoporfornecedor.setNomefantasia     ( Fornecedor.getNomefantasia() );    
                                    
                    DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Produtoporfornecedor, JPAUtil.em() );
                    DAOGenericoJPA.gravanovoOUatualizar();
                //}
                //else{
                    
                //    break;
                //}
            }
            */
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "alteracoesExternas, \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); }         
    }
    
    //Salvar
    public void salvar( fornecedor.Fornecedor Fornecedor, boolean alterar ) {  
        if( Fornecedor_New.btSalvar.isEnabled() ) {
            
            if( Fornecedor_New.tfNomeouRazaoSocial.getText().trim().length() > 3 ) {
                
                //if( Fornecedor_New.tfNomeVendedor.getText().trim().length() > 3 ) {
                    
                    //if( Fornecedor_New.tfTelefoneVendedor.getText().trim().length() == 23 ) {
                        
                        //if( Fornecedor_New.rbPessoaFisica.isSelected() == true || Fornecedor_New.rbPessoaJuridica.isSelected() == true  ){
                        
                            if( Fornecedor_New.rbPessoaJuridica.isSelected() == true /*&& Fornecedor_New.tfCPFOuCNPJ.getText().trim().length() == 18*/ ) {
                            
                                confirmarsalvar( Fornecedor,alterar );
                            }
                            else if( Fornecedor_New.rbPessoaFisica.isSelected() == true /*&& Fornecedor_New.tfCPFOuCNPJ.getText().trim().length() == 14*/ ) {
                                
                                confirmarsalvar( Fornecedor,alterar );
                                
                            }else{ semCPF(); }
                        
                        //}else{ semPessoa(); }
                        
                    //}else{ semSenhaIgual(); }
                    
                //}else{ semSenha(); }                                             
                
            }
            else{ semLogin(); }
        }
    } 
    
    private void confirmarsalvar( fornecedor.Fornecedor Fornecedor, boolean alterar ) {  
        Fornecedor = pegarDadosDoJPanelParaSalvar( Fornecedor );
        
        if( verificarRepeticao( Fornecedor ) == false ){
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Fornecedor, JPAUtil.em());
            DAOGenericoJPA.gravanovoOUatualizar();
            Fornecedor_New.Fornecedor_Home.Inicio.cancelar();
                            
        }
        else{
            if( alterar == false ){
                repetido();
            }
            else{
                DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Fornecedor, JPAUtil.em());
                DAOGenericoJPA.gravanovoOUatualizar();
                Fornecedor_New.Fornecedor_Home.Inicio.cancelar();
            }
        }
    }
   
   private void semLogin() {
       JOPM JOptionPaneMod = new JOPM( 2, "NOME OU RAZÃO SOCIAL, "
                        + "\nO Campo NOME OU RAZÃO SOCIAL não pode ter menos que 4 caracteres"
                        + "\nInforme um NOME OU RAZÃO SOCIAL com no mínimo 4 caracteres"
                        + "\n", "NOME OU RAZÃO SOCIAL" );
   }
   private void semSenha() {
       JOPM JOptionPaneMod = new JOPM( 2, "NOME VENDEDOR, "
                        + "\nO Campo NOME VENDEDOR não pode ter menos que 4 caracteres"
                        + "\nInforme um NOME DO VENDEDOR com no mínimo 4 caracteres"
                        + "\n", "NOME VENDEDOR" );
   }
   private void semSenhaIgual() {
       JOPM JOptionPaneMod = new JOPM( 2, "TELEFONE DO VENDEDOR, "
                        + "\nO Campo TELEFONE DO VENDEDOR está inválido"
                        + "\nInforme um número de TELEFONE válido"
                        + "\n", "TELEFONE DO VENDEDOR" );
   }
   
   private void semPessoa() {
       JOPM JOptionPaneMod = new JOPM( 2, "TIPO DE PESSOA FÍSICA OU JURÍDICA, "
                        + "\nO TIPO DE PESSOA FÍSICA OU JURÍDICA está inválido"
                        + "\nSelecione o TIPO DE PESSOA FÍSICA OU JURÍDICA"
                        + "\n", "TIPO DE PESSOA FÍSICA OU JURÍDICA" );
   }
   
   private void semCPF() {
       JOPM JOptionPaneMod = new JOPM( 2, "CPF OU CNPJ, "
                        + "\nO campo CPF OU CNPJ está inválido"
                        + "\ninforme um CPF OU CNPJ válido"
                        + "\n", "CPF OU CNPJ" );
   }
   
   private void repetido() {
       JOPM JOptionPaneMod = new JOPM( 2, "NOME OU RAZÃO SOCIAL, "
                        + "\nO NOME OU RAZÃO SOCIAL informado já existe cadastrado"
                        + "\nInforme um NOME OU RAZÃO SOCIAL ainda não cadastrado"
                        + "\n", "NOME OU RAZÃO SOCIAL" );
   }
   
   private boolean verificarRepeticao( fornecedor.Fornecedor Fornecedor ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.FORNECEDOR", fornecedor.Fornecedor.class );
            List<fornecedor.Fornecedor> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getNomeourazaosocial().equals( Fornecedor.getNomeourazaosocial() ) ){
                    
                    retorno = true;
                    break;                    
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private fornecedor.Fornecedor pegarDadosDoJPanelParaSalvar( fornecedor.Fornecedor Fornecedor ) {          
        try{
            
            Fornecedor.setNomeourazaosocial ( Fornecedor_New.tfNomeouRazaoSocial.getText().trim().toUpperCase() );
            Fornecedor.setNomevendedor      (Fornecedor_New.tfNomeVendedor.getText().trim().toUpperCase() );
            Fornecedor.setTelefonevendedor  (Fornecedor_New.tfTelefoneVendedor.getText().trim() );
            
            Fornecedor.setNomefantasia      (Fornecedor_New.tfNomeFantasia.getText().trim().toUpperCase() );
            Fornecedor.setNomesupervisor    (Fornecedor_New.tfNomeSupervisor.getText().trim().toUpperCase() );
            Fornecedor.setTelefonesupervisor(Fornecedor_New.tfTelefoneSupervisor.getText().trim() );
            Fornecedor.setPrazodeentrega    (Fornecedor_New.tfPrazoDeEntrega.getText().trim().toUpperCase() );
            Fornecedor.setPrazodepagamento  (Fornecedor_New.tfPrazoDePagamento.getText().trim().toUpperCase() );
            
            if( Fornecedor_New.rbPessoaFisica.isSelected() == true ){
                Fornecedor.setTipodepessoa("FÍSICA");
            }
            else if( Fornecedor_New.rbPessoaJuridica.isSelected() == true ){
                Fornecedor.setTipodepessoa("JURÍDICA");
            }
            
            Fornecedor.setCnpj( Fornecedor_New.tfCPFOuCNPJ.getText().trim() );
            
            Fornecedor.setCd              (Fornecedor_New.tfCd.getText().trim().toUpperCase() );
            Fornecedor.setFrequencia      (Fornecedor_New.tfFrequencia.getText().trim().toUpperCase() );
            Fornecedor.setLeadtime        (Fornecedor_New.tfLeadTime.getText().trim().toUpperCase() );
            Fornecedor.setGeracaopedidosra(Fornecedor_New.tfGeracaoPedidosRA.getText().trim().toUpperCase() );
            Fornecedor.setNomepromotor    (Fornecedor_New.tfNomePromotor.getText().trim().toUpperCase() );
            Fornecedor.setTelefonepromotor(Fornecedor_New.tfTelefonePromotor.getText().trim().toUpperCase() );
            Fornecedor.setEmailpromotor   (Fornecedor_New.tfEmailPromotor.getText().trim().toUpperCase() );


        }catch(Exception e ){} 
        
        return Fornecedor;
    }
    
    public void setarDadosDoBancoNoJPanel( fornecedor.Fornecedor Fornecedor ) {          
        try{
            
            Fornecedor_New.tfNomeouRazaoSocial.setText       ( Fornecedor.getNomeourazaosocial() );
            Fornecedor_New.tfNomeVendedor.setText       ( Fornecedor.getNomevendedor() );    
                        
            try{
                String fone = Fornecedor.getTelefonevendedor().trim().replace("+", "").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");         
                MaskFormatter mascaraTelefone = new MaskFormatter("+ ## ( ## ) #### - ####");
                mascaraTelefone.setValidCharacters("0123456789");                
                Fornecedor_New.tfTelefoneVendedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) ); 
                Fornecedor_New.tfTelefoneVendedor.setText( fone );
            }catch(Exception e ){}   
            
            Fornecedor_New.tfNomeFantasia.setText      ( Fornecedor.getNomefantasia() );
            Fornecedor_New.tfNomeSupervisor.setText    ( Fornecedor.getNomesupervisor() );  
            
            try{
                String fone = Fornecedor.getTelefonesupervisor().trim().replace("+", "").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");         
                MaskFormatter mascaraTelefone = new MaskFormatter("+ ## ( ## ) #### - ####");
                mascaraTelefone.setValidCharacters("0123456789");                
                Fornecedor_New.tfTelefoneSupervisor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) ); 
                Fornecedor_New.tfTelefoneSupervisor.setText( fone );
            }catch(Exception e ){}     
            
            Fornecedor_New.tfPrazoDeEntrega.setText    ( Fornecedor.getPrazodeentrega() );        
            Fornecedor_New.tfPrazoDePagamento.setText  ( Fornecedor.getPrazodepagamento() );
            
            try{ Fornecedor_New.tfCd.setText(Fornecedor.getCd());                              }catch(Exception e ){} 
            try{ Fornecedor_New.tfFrequencia.setText(Fornecedor.getFrequencia());              }catch(Exception e ){} 
            try{ Fornecedor_New.tfLeadTime.setText(Fornecedor.getLeadtime());                  }catch(Exception e ){} 
            try{ Fornecedor_New.tfGeracaoPedidosRA.setText(Fornecedor.getGeracaopedidosra());  }catch(Exception e ){} 
            try{ Fornecedor_New.tfNomePromotor.setText(Fornecedor.getNomepromotor());          }catch(Exception e ){} 
            try{ Fornecedor_New.tfTelefonePromotor.setText(Fornecedor.getTelefonepromotor());  }catch(Exception e ){} 
            try{ Fornecedor_New.tfEmailPromotor.setText(Fornecedor.getEmailpromotor());        }catch(Exception e ){} 
            
            
            if( Fornecedor.getTipodepessoa().equals("FÍSICA") ){
                Fornecedor_New.rbPessoaFisica.setSelected(true);
                
                try{
                    String str = Fornecedor.getCnpj().trim().replace("-", "").replace(".", "");  
                    MaskFormatter mascara = new MaskFormatter("###.###.###-##");
                    mascara.setValidCharacters("0123456789");
                    
                    Fornecedor_New.tfCPFOuCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascara ) ); 
                    Fornecedor_New.tfCPFOuCNPJ.setText( str );
                }catch(Exception e ){} 
            }
            else if( Fornecedor.getTipodepessoa().equals("JURÍDICA") ){
                Fornecedor_New.rbPessoaJuridica.setSelected(true); 
                
                try{
                    String str = Fornecedor.getCnpj().trim().replace("-", "").replace(".", "").replace("/", "");  
                    MaskFormatter mascaraTelefone = new MaskFormatter("##.###.###/####-##");
                    mascaraTelefone.setValidCharacters("0123456789");
                    
                    Fornecedor_New.tfCPFOuCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) ); 
                    Fornecedor_New.tfCPFOuCNPJ.setText( str );
                }catch(Exception e ){} 
            }            
            
            

        }catch(Exception e ){} 
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Fornecedor_New.tfNomeouRazaoSocial       .setEditable(b);
            Fornecedor_New.tfNomeVendedor       .setEditable(b);    
            Fornecedor_New.tfTelefoneVendedor.setEditable(b);
            Fornecedor_New.tfNomeFantasia.setEditable(b);
            Fornecedor_New.tfNomeSupervisor.setEditable(b);
            Fornecedor_New.tfTelefoneSupervisor.setEditable(b);
            Fornecedor_New.tfPrazoDeEntrega.setEditable(b);
            Fornecedor_New.tfPrazoDePagamento.setEditable(b);        

        }catch(Exception e ){} 
    }
    
}
