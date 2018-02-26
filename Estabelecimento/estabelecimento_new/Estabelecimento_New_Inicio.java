/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estabelecimento_new;

import cep_new.BookInfoMuni;
import estabelecimento.Estabelecimento;
import home_usuario_logado.Bean_Usuario_Logado;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;
import jpa.DAOGenericoJPA;
import jpa.JPAUtil;
import municipio.Municipio;
import uf_0.Uf;
import usuario_0.Usuario;
import utilidades.JOPM;
import utilidades.ValidarCnpj;
import utilidades.ValidarEmail;

/**
 *
 * @author pc
 */
public class Estabelecimento_New_Inicio {
        
    Estabelecimento_New Estabelecimento_New;//Materias_Home; 
    
    public Estabelecimento_New_Inicio( Estabelecimento_New Estabelecimento_New2 ) {
        
        Estabelecimento_New = Estabelecimento_New2;

        inicioCBS();
        inicio();
    }
    
    private void inicioCBS(){        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );         
            Color c = new Color( 255, 255, 255 );
        
            Estabelecimento_New.cbPais      .setBackground( c );  
            Estabelecimento_New.cbUf        .setBackground( c ); 
            Estabelecimento_New.cbMunicipio .setBackground( c ); 
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicioCBS(), \n"
                + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start();       
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public Estabelecimento_New_Inicio( Estabelecimento_New Estabelecimento_New2, estabelecimento.Estabelecimento Estabelecimento ) {
        
        Estabelecimento_New = Estabelecimento_New2;
          
        setarDadosDoBancoNoJPanel( Estabelecimento );
        
        setarCBPais     ( true, Estabelecimento ); 
        setarCBUf       ( true, Estabelecimento );
        setarCBMunicipio( true, Estabelecimento );
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
        
            setarCBPais(false, new estabelecimento.Estabelecimento());
                        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "inicio(), \n"
                + e.getMessage() + "\n", "Subcategorias_New_Inicio" ); } } }.start();                 
    }
    
    
    
    
    //setar CB Matéria
    public void setarCBPais(boolean alterar, estabelecimento.Estabelecimento Estabelecimento) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.PAIS", pais_0.Pais.class );          
            List<pais_0.Pais> list = q.getResultList();
            
            if( Estabelecimento_New.cbPais.getItemCount() > 0 ) { Estabelecimento_New.cbPais.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Estabelecimento_New.cbPais.addItem( new uf_new_0.BookInfoPais( 
                    list.get(i).getId(),list.get(i).getNomePtbr()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Estabelecimento_New.cbPais.getItemCount(); i++){
                
                    uf_new_0.BookInfoPais obj = (uf_new_0.BookInfoPais) Estabelecimento_New.cbPais.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Categoria.getIdMateria(): " + Categoria.getIdMateria() );
                    
                    if( obj.ID == Estabelecimento.getPais() ){
                    
                        Estabelecimento_New.cbPais.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBPais(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    //setar CB Matéria
    public void setarCBUf(boolean alterar, estabelecimento.Estabelecimento Estabelecimento) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.UF WHERE ID_PAIS = ?", uf_0.Uf.class );
            q.setParameter( 1, Estabelecimento.getPais() );            
            List<uf_0.Uf> list = q.getResultList();
                    //System.out.println( "setarCBUf 1" );
            if( Estabelecimento_New.cbUf.getItemCount() > 0 ) { Estabelecimento_New.cbUf.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Estabelecimento_New.cbUf.addItem( new municipio_new2.BookInfoUf( 
                    list.get(i).getId(),list.get(i).getNome()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Estabelecimento_New.cbUf.getItemCount(); i++){
                
                    municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Estabelecimento_New.cbUf.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Subcategoria.getIdCategoria(): " + Subcategoria.getIdCategoria() );
                    if( obj.ID == Estabelecimento.getEstado() ){
                    
                        Estabelecimento_New.cbUf.setSelectedIndex(i);
                        
                        try{
                            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Estabelecimento, JPAUtil.em());            
                            Uf Uf_X = (Uf) DAOGenericoJPA.getBean( Estabelecimento.getEstado(), uf_0.Uf.class );
                            Estabelecimento_New.tfCodigo_Ibge_Estado.setText( Uf_X.getCodigoIbge() );
                        } catch( Exception e ){}
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBMateria(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    }
    
    //setar CB Assunto
    public void setarCBMunicipio( boolean alterar, estabelecimento.Estabelecimento Estabelecimento ) {       
        try {
        //new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MUNICIPIO WHERE ID_UF = ?", municipio.Municipio.class );
            q.setParameter( 1, Estabelecimento.getEstado() );            
            List<municipio.Municipio> list = q.getResultList();
                    //System.out.println( "setarCBMunicipio 1" );
            if( Estabelecimento_New.cbMunicipio.getItemCount() > 0 ) { Estabelecimento_New.cbMunicipio.removeAllItems(); }
            
            for (int i = 0; i < list.size(); i++){ 
                Estabelecimento_New.cbMunicipio.addItem( new municipio_new2.BookInfoUf( 
                    list.get(i).getId(),list.get(i).getNome()
                    )
                );    
            }
            
            if( alterar == true ) {
                
                for (int i = 0; i < Estabelecimento_New.cbMunicipio.getItemCount(); i++){
                
                    municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Estabelecimento_New.cbMunicipio.getItemAt(i);
                    //System.out.println( "I: " + i + " - " +  "obj:" + obj.ID + " -- Subcategoria.getIdCategoria(): " + Subcategoria.getIdCategoria() );
                    if( obj.ID == Estabelecimento.getCidade() ){
                    
                        Estabelecimento_New.cbMunicipio.setSelectedIndex(i);
                        
                        try{
                            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA( Estabelecimento, JPAUtil.em());            
                            Municipio Municipio2 = (Municipio) DAOGenericoJPA.getBean( Estabelecimento.getCidade(), Municipio.class );
                            Estabelecimento_New.tfCodigo_Ibge_Cidade.setText( Municipio2.getCodigoIbge() );
                        } catch( Exception e ){}
                        break;
                    }
                }
            }
            
        } catch( Exception e ){}    
                        
        //} catch( Exception e ){ JOPM JOPM = new JOPM( 2, "setarCBMateria(), \n"
        //        + e.getMessage() + "\n", "Categorias_New_Inicio" ); } } }.start(); 
    } 
    
    
    
    
    //Salvar
    public void salvar( estabelecimento.Estabelecimento Estabelecimento, boolean alterar ) {  
        if( validacoes() == true ) {
            
            if( ValidarCnpj.CNPJ( Estabelecimento_New.tfCnpj.getText().trim() ) == true ) {
                
                if( Estabelecimento_New.tfRazao_Social.getText().trim().length() > 1 ) {
                    
                    if( Estabelecimento_New.tfNome_Fantasia.getText().trim().length() > 1 ) {
                        
                        if( Estabelecimento_New.cbPais.getItemCount() > 0 ) {
                        
                            if( Estabelecimento_New.cbUf.getItemCount() > 0 ) {
                            
                                if( Estabelecimento_New.cbMunicipio.getItemCount() > 0 ) {
                        
                                    confirmarSalvar( Estabelecimento, alterar ); 
                                }
                                else{ sem2(); }
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
    
    public void confirmarSalvar( estabelecimento.Estabelecimento Estabelecimento, boolean alterar ) { 
        Estabelecimento = pegarDadosDoJPanelParaSalvar( Estabelecimento, alterar );
        
        if( verificarRepeticao( Estabelecimento ) == false ){
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Estabelecimento, JPAUtil.em());
            Estabelecimento estabelecimento_cadastrado = (Estabelecimento) DAOGenericoJPA.gravanovoOUatualizar();
             
            Estabelecimento_New.Estabelecimento_Home.Inicio.cancelar();
        }
        else{
            
            repetido();
        }
    }
   
   private void sem1() {
       JOPM JOptionPaneMod = new JOPM( 2, "CNPJ, "
                        + "\nO Campo CNPJ não pode ser nulo ou inválido"
                        + "\nInforme o CNPJ com 14 dígitos válidos"
                        + "\n", "CNPJ" );
   }
   
   private void sem2() {
       JOPM JOptionPaneMod = new JOPM( 2, "PARA CADASTRAR UM ESTABELECIMENTO, "
                        + "\nO Campo CNPJ não pode ter menos que 14 caracteres"
                        + "\nO Campo RAZÃO SOCIAL não pode ter menos que 2 caracteres"
                        + "\nO Campo NOME FANTASIA não pode ter menos que 2 caracteres"
                        + "\n", "PARA CADASTRAR UM ESTABELECIMENTO" );
   }
   
   private void repetido() {
       JOPM JOptionPaneMod = new JOPM( 2, "CNPJ JÁ CADASTRADO, "
                        + "\nO CNPJ informado já existe cadastrado"
                        + "\nInforme um CNPJ ainda não cadastrado"
                        + "\n", "CNPJ" );
   }
   
   private boolean verificarRepeticao( estabelecimento.Estabelecimento Estabelecimento ) {  
       boolean retorno = false;
        try{
            
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ESTABELECIMENTO", estabelecimento.Estabelecimento.class );
            List<estabelecimento.Estabelecimento> list = q.getResultList();
            
            for (int i = 0; i < list.size(); i++){
                
                if( list.get(i).getId() != Estabelecimento.getId() ){
                    
                    if( list.get(i).getCnpj().equals( Estabelecimento.getCnpj() ) ){
                    
                        retorno = true;
                        break;                    
                    }
                }
            }

        }catch(Exception e ){} 
        
        return retorno;
    }
   
    private estabelecimento.Estabelecimento pegarDadosDoJPanelParaSalvar( estabelecimento.Estabelecimento Estabelecimento, boolean alterar ) {  
        
        // TODOS OS DADOS TEM DE SER SETADOS, POIS NO CASO ALTERAR O VALOR ANTERIOR NÃO FICA
        //DADOS BÁSICOS INÍCIO
        try{ 
            if( alterar == false ){
                                
                Estabelecimento.setIdUsuario( Bean_Usuario_Logado.ID ); 
            }            
        }catch(Exception e ){}    
        
        try{ 
            if( alterar == false ){
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dataHoje = gc.getTime();
                
                Estabelecimento.setDataCadastro( dataHoje ); 
            }            
        }catch(Exception e ){} 
        
        try{
            
            Estabelecimento.setImagemLogotipo( Estabelecimento_New.icone_do_estabelecimento );
        }catch(Exception e ){} 
        
        try{ 
            
            Estabelecimento.setRazaoSocial(Estabelecimento_New.tfRazao_Social.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setNomeFantasia(Estabelecimento_New.tfNome_Fantasia.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setCnpj(Estabelecimento_New.tfCnpj.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setInscricaoEstadual(Estabelecimento_New.tfInscricao_Estadual.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setInscricaoEstadualSt(Estabelecimento_New.tfInscricao_Estadual_ST.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setInscricaoMunicipal(Estabelecimento_New.tfInscricao_Municipal.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setInscricaoJuntaComercial(Estabelecimento_New.tfInscricao_Junta_Comercial.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
                
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            String dataDigitadaNoTF = Estabelecimento_New.tfData_Inscricao_Junta_Comercial.getText().trim();
                     
            Date date = formatter.parse( dataDigitadaNoTF ); 
                       
            Estabelecimento.setDataInscJuntaComercial( date );                          
        }catch(Exception e ){ Estabelecimento.setDataInscJuntaComercial( null );    }   
        
        try{ 
            
            Estabelecimento.setSuframa(Estabelecimento_New.tfSuframa.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setTipo(Estabelecimento_New.tfTipo.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
                
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                
            String dataDigitadaNoTF = Estabelecimento_New.tfData_Inicio_Atividades.getText().trim();
                
            Date date = formatter.parse( dataDigitadaNoTF ); 
                
            Estabelecimento.setDataInicioAtividades( date ); 
                        
        }catch(Exception e ){ Estabelecimento.setDataInicioAtividades( null ); }
        
        try{ 
            
            Estabelecimento.setObservacao(Estabelecimento_New.tfObs.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        //DADOS BÁSICOS FIM
        
        //CONTATO INÍCIO
        try{ 
            
            Estabelecimento.setContato(Estabelecimento_New.tfNome_Contato.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            Estabelecimento.setTelefone(Estabelecimento_New.tfTelefone.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setFone2(Estabelecimento_New.tfFone2.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setEmail(Estabelecimento_New.tfEmail.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setOutroemail(Estabelecimento_New.tfOutro_Email.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setFone3(Estabelecimento_New.tfFone3.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setFacebook(Estabelecimento_New.tfFacebook.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        //CONTATO FIM
        
        //FISCAL INÍCIO
        try{ 
            
            Estabelecimento.setCrt(Estabelecimento_New.tfCrt.getText().trim().toUpperCase() ); 
        }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento.setTipoRegime(Estabelecimento_New.tfTipo_Regime.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ 
            
            float aliquotaPis = Float.parseFloat( Estabelecimento_New.tfAliquota_Pis.getText().trim() );
            Estabelecimento.setAliquotaPis( aliquotaPis ); 
        }catch(Exception e ){}
        try{ 
            
            float aliquotaCofins = Float.parseFloat( Estabelecimento_New.tfAliquota_Cofins.getText().trim() );
            Estabelecimento.setAliquotaCofins( aliquotaCofins ); 
        }catch(Exception e ){}
        try{ 
            
            float aliquotaSat = Float.parseFloat( Estabelecimento_New.tfAliquota_Sat.getText().trim() );
            Estabelecimento.setAliquotaSat( aliquotaSat ); 
        }catch(Exception e ){}
        //FISCAL FIM
        
        //ENDEREÇO INÍCIO
        try{ 
            
            Estabelecimento.setCep(Estabelecimento_New.tfCep.getText().trim().toUpperCase() );
        }catch(Exception e ){}
        
        try{ Estabelecimento.setLogradouro(Estabelecimento_New.tfLogradouro.getText().trim().toUpperCase() ); }catch(Exception e ){}
        try{ Estabelecimento.setNumero( Estabelecimento_New.tfNumero.getText().trim().toUpperCase() ); }catch(Exception e ){}
        try{ Estabelecimento.setComplemento(Estabelecimento_New.tfComplemento.getText().trim().toUpperCase() ); }catch(Exception e ){}
        try{ Estabelecimento.setComplemento(Estabelecimento_New.tfComplemento.getText().trim().toUpperCase() ); }catch(Exception e ){}
        
        try{ Estabelecimento.setBairro(Estabelecimento_New.tfBairro.getText().trim().toUpperCase() ); }catch(Exception e ){}        
        try{ Estabelecimento.setCodigoIbgeCidade( Estabelecimento_New.tfCodigo_Ibge_Cidade.getText().trim() ); 
        }catch(Exception e ){} 
                
        try{ Estabelecimento.setCodigoIbgeUf( Estabelecimento_New.tfCodigo_Ibge_Estado.getText().trim() ); 
        }catch(Exception e ){} 
        
//////////////////////////////        
        try{             
            uf_new_0.BookInfoPais obj = (uf_new_0.BookInfoPais) Estabelecimento_New.cbPais.getSelectedItem();
            Estabelecimento.setPais(obj.ID);
        }catch(Exception e ){}
        
        try{           
            municipio_new2.BookInfoUf obj = (municipio_new2.BookInfoUf) Estabelecimento_New.cbUf.getSelectedItem();
            Estabelecimento.setEstado(obj.ID);
        }catch(Exception e ){}
        
        try{             
            BookInfoMuni obj = (BookInfoMuni) Estabelecimento_New.cbMunicipio.getSelectedItem();
            Estabelecimento.setCidade(obj.ID);
        }catch(Exception e ){}
/////////////////////////////        
        //ENDEREÇO FIM
        
        //OUTROS INÍCIO
        try{ Estabelecimento.setCodigoTerceiros( Estabelecimento_New.tfCodigo_Terceiros.getText().trim() ); }catch(Exception e ){} 
        try{ Estabelecimento.setCodigoGps( Estabelecimento_New.tfCodigo_Gps.getText().trim() );  }catch(Exception e ){} 
        
        try{ Estabelecimento.setCodigoCnaePrincipal(Estabelecimento_New.tfCodigo_Cnae.getText().trim().toUpperCase() ); }catch(Exception e ){}
        try{ Estabelecimento.setCei(Estabelecimento_New.tfCei.getText().trim().toUpperCase() ); }catch(Exception e ){}
        
        try{ Estabelecimento.setFpas( Estabelecimento_New.tfFpas.getText().trim() ); }catch(Exception e ){} 
        //OUTROS FIM
        
        //RETORNO
        return Estabelecimento;
    }
    
    String imagemLogotipo = "";
    public void setarDadosDoBancoNoJPanel( estabelecimento.Estabelecimento Estabelecimento ) {  
        try{ imagemLogotipo = Estabelecimento.getImagemLogotipo(); }catch(Exception e ){}  
        //DADOS BÁSICOS INÍCIO
        try{ 
            
            DAOGenericoJPA DAOGenericoJPA = new DAOGenericoJPA(Estabelecimento, JPAUtil.em());            
            Usuario usuario_do_estabelecimento = (Usuario) DAOGenericoJPA.getBean( Estabelecimento.getIdUsuario(), Usuario.class );
                
            Estabelecimento_New.tfCadastrado_Por.setText( usuario_do_estabelecimento.getLogin().toUpperCase() );    
                                                                          
        }catch(Exception e ){}                    
        try{ 
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String data_formatada = formatter.format( Estabelecimento.getDataCadastro() );

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
        
        try{ Estabelecimento_New.tfRazao_Social.setText( Estabelecimento.getRazaoSocial() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfNome_Fantasia.setText( Estabelecimento.getNomeFantasia() ); }catch(Exception e ){}
        try{ 
            if( !Estabelecimento.getCnpj().equals("") ){
                
                MaskFormatter mascara = new MaskFormatter("##.###.###/####-##");
                mascara.setValidCharacters("0123456789");                
                Estabelecimento_New.tfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascara ) ); 
            
                String cnpj = Estabelecimento.getCnpj();
                cnpj = cnpj.replaceAll(Pattern.compile("\\s").toString(), "");
                cnpj = cnpj.replaceAll(Pattern.compile("\\D").toString(), "");
                
                Estabelecimento_New.tfCnpj.setText( cnpj ); 
            }
        }catch(Exception e ){}
        
        try{ Estabelecimento_New.tfInscricao_Estadual.setText( Estabelecimento.getInscricaoEstadual() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfInscricao_Estadual_ST.setText( Estabelecimento.getInscricaoEstadualSt() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfInscricao_Municipal.setText( Estabelecimento.getInscricaoMunicipal() ); }catch(Exception e ){}
        
        try{ Estabelecimento_New.tfInscricao_Junta_Comercial.setText( Estabelecimento.getInscricaoJuntaComercial() ); }catch(Exception e ){}
        try{ 
                
            if( Estabelecimento.getDataInscJuntaComercial() != null ){

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String data_formatada = formatter.format( Estabelecimento.getDataInscJuntaComercial() );
            
                MaskFormatter mascaraTelefone = new MaskFormatter("##/##/####");
                mascaraTelefone.setValidCharacters("0123456789");                
                Estabelecimento_New.tfData_Inscricao_Junta_Comercial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) ); 
            
                Estabelecimento_New.tfData_Inscricao_Junta_Comercial.setText( data_formatada );   
            }
            
        }catch(Exception e ){}        
        try{ Estabelecimento_New.tfSuframa.setText( Estabelecimento.getSuframa() ); }catch(Exception e ){}
        
        try{ Estabelecimento_New.tfTipo.setText( Estabelecimento.getTipo() ); }catch(Exception e ){}
        try{    
                
            if( Estabelecimento.getDataInicioAtividades() != null ){
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String data_formatada = formatter.format( Estabelecimento.getDataInicioAtividades() );
            
                MaskFormatter mascaraTelefone = new MaskFormatter("##/##/####");
                mascaraTelefone.setValidCharacters("0123456789");                
                Estabelecimento_New.tfData_Inicio_Atividades.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) ); 

                Estabelecimento_New.tfData_Inicio_Atividades.setText( data_formatada ); 
            }
                
        }catch(Exception e ){ System.out.println("xxxxx "); e.printStackTrace(); }
        
        try{ Estabelecimento_New.tfObs.setText( Estabelecimento.getObservacao() ); }catch(Exception e ){}
        //DADOS BÁSICOS FIM
        
        //CONTATO INÍCIO
        try{ Estabelecimento_New.tfNome_Contato.setText( Estabelecimento.getContato() ); }catch(Exception e ){}

        try{ 
            if( !Estabelecimento.getTelefone().equals("") &&
                                       !Estabelecimento.getTelefone().equals("+    (    )      -") ){

                String formatada = Estabelecimento.getTelefone();
            
                MaskFormatter mascara = new MaskFormatter("+ ## ( ## ) #### - ####");
                mascara.setValidCharacters("0123456789");              
                Estabelecimento_New.tfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascara ) ); 
            
                Estabelecimento_New.tfTelefone.setText( formatada );               
            }            
        }catch(Exception e ){}
        
        try{ 
            if( !Estabelecimento.getFone2().equals("") && 
                                              !Estabelecimento.getFone2().equals("+    (    )       -") ){
                
                String formatada = Estabelecimento.getFone2();
            
                MaskFormatter mascara = new MaskFormatter("+ ## ( ## ) ##### - ####");
                mascara.setValidCharacters("0123456789");             
                Estabelecimento_New.tfFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascara ) ); 
            
                Estabelecimento_New.tfFone2.setText( formatada ); 
            }            
        }catch(Exception e ){}
        
        try{ Estabelecimento_New.tfEmail.setText( Estabelecimento.getEmail() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfOutro_Email.setText( Estabelecimento.getOutroemail() ); }catch(Exception e ){}
        try{
            if( !Estabelecimento.getFone3().equals("") && 
                                              !Estabelecimento.getFone3().equals("+    (    )       -") ){
                
                String data_formatada = Estabelecimento.getFone3();
            
                MaskFormatter mascaraTelefone = new MaskFormatter("+ ## ( ## ) ##### - ####");
                mascaraTelefone.setValidCharacters("0123456789");             
                Estabelecimento_New.tfFone3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascaraTelefone ) ); 
            
                Estabelecimento_New.tfFone3.setText( data_formatada ); 
            }
        }catch(Exception e ){}
        try{ Estabelecimento_New.tfFacebook.setText( Estabelecimento.getFacebook() ); }catch(Exception e ){}
        //CONTATO FIM
        
        //FISCAL INÍCIO
        try{ Estabelecimento_New.tfCrt.setText( Estabelecimento.getCrt() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfTipo_Regime.setText( Estabelecimento.getTipoRegime() ); }catch(Exception e ){}
        
        try{ 
            
            //if( Estabelecimento.getAliquotaPis() != null ){
                
                Estabelecimento_New.tfAliquota_Pis.setText( String.valueOf( Estabelecimento.getAliquotaPis() ) ); 
            //}
        }catch(Exception e ){}
        try{ 
            
            //if( Estabelecimento.getAliquotaCofins() != null ){
                
                Estabelecimento_New.tfAliquota_Cofins.setText( String.valueOf( Estabelecimento.getAliquotaCofins() ) );
            //}
        }catch(Exception e ){}
        try{ 
            
            //if( Estabelecimento.getAliquotaSat() != null ){
                
                Estabelecimento_New.tfAliquota_Sat.setText( String.valueOf( Estabelecimento.getAliquotaSat() ) );
            //}
        }catch(Exception e ){}
        //FISCAL FIM
        
        //ENDEREÇO INÍCIO
        try{ 
            if( !Estabelecimento.getCep().equals("") && 
                                            !Estabelecimento.getCep().equals("  .   -") ){
                
                MaskFormatter mascara = new MaskFormatter("##.###-###");
                mascara.setValidCharacters("0123456789");                
                Estabelecimento_New.tfCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory( mascara ) ); 
            
                String cep = Estabelecimento.getCep();
                cep = cep.replaceAll(Pattern.compile("\\s").toString(), "");
                cep = cep.replaceAll(Pattern.compile("\\D").toString(), "");
                
                Estabelecimento_New.tfCep.setText( cep ); 
            }
        }catch(Exception e ){}
        try{ Estabelecimento_New.tfLogradouro.setText( Estabelecimento.getLogradouro() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfNumero.setText( Estabelecimento.getNumero() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfComplemento.setText( Estabelecimento.getComplemento() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfComplemento.setText( Estabelecimento.getComplemento() ); }catch(Exception e ){}
        
        try{ Estabelecimento_New.tfBairro.setText( Estabelecimento.getBairro() ); }catch(Exception e ){}
        
        try{ 
            
            Estabelecimento_New.tfCodigo_Ibge_Cidade.setText( Estabelecimento.getCodigoIbgeCidade() );                      
        }catch(Exception e ){} 
        
        try{ 
                        
            Estabelecimento_New.tfCodigo_Ibge_Estado.setText( Estabelecimento.getCodigoIbgeUf() );
        }catch(Exception e ){} 
        
////////////////////////////////////////////////////////////////////////////////        
        inicioCBS();
////////////////////////////////////////////////////////////////////////////////        
        //ENDEREÇO FIM
        
        //OUTROS INÍCIO
        try{
            
            Estabelecimento_New.tfCodigo_Terceiros.setText( Estabelecimento.getCodigoTerceiros() );                       
        }catch(Exception e ){} 
        try{ 
            
            Estabelecimento_New.tfCodigo_Gps.setText( Estabelecimento.getCodigoGps() );                   
        }catch(Exception e ){} 
        
        try{ Estabelecimento_New.tfCodigo_Cnae.setText( Estabelecimento.getCodigoCnaePrincipal() ); }catch(Exception e ){}
        try{ Estabelecimento_New.tfCei.setText( Estabelecimento.getCei() ); }catch(Exception e ){}
        
        try{ 

            Estabelecimento_New.tfFpas.setText( Estabelecimento.getFpas() );                        
        }catch(Exception e ){} 
        //OUTROS FIM 
    }
    
    public void desabilitarComponentesDoJPanel( boolean b ) {          
        try{
            
            Estabelecimento_New.tfRazao_Social       .setEditable(b);       

        }catch(Exception e ){} 
    }
    
    public boolean validacoes(){
        boolean retorno = true;
        
        // validações
        try{
            
            if ( !Estabelecimento_New.tfEmail.getText().trim().equals("") ) {
            
                if( ValidarEmail.validar( Estabelecimento_New.tfEmail.getText().trim() ) == true ){
                }
                else{
                    
                    retorno = false;
                
                    Estabelecimento_New.tfEmail.setText( "" );
                    JOPM JOptionPaneMod = new JOPM( 2, "EMAIL, "
                        + "\nO Campo EMAIL não se está se referindo a um EMAIL válido"
                        + "\nInforme um EMAIL válido"
                        + "\n", "EMAIL" );
                    Estabelecimento_New.tfEmail.requestFocus();
                }
            }
        }catch(Exception e ){} 
        
        return retorno;
    }
    
}
