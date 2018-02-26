/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banco_de_dados;

import banco_de_dados_tabelas.ANALISEDEVOLUTIVA;
import banco_de_dados_tabelas.ANALISE_EVENTOS;
import banco_de_dados_tabelas.Acesso;
import banco_de_dados_tabelas.AcessoPovoar;
import banco_de_dados_tabelas.Cep;
import banco_de_dados_tabelas.CepPovoar;
import banco_de_dados_tabelas.ControleAcessoPovoar;
import banco_de_dados_tabelas.Controle_Acesso;
import banco_de_dados_tabelas.EnderecoEspacoGondula;
import banco_de_dados_tabelas.Estabelecimento;
import banco_de_dados_tabelas.EstabelecimentoPovoar;
import banco_de_dados_tabelas.Fornecedor;
import banco_de_dados_tabelas.GBATIVOSEMVENDA;
import banco_de_dados_tabelas.GBCORINGA;
import banco_de_dados_tabelas.GBDDEDISP;
import banco_de_dados_tabelas.GBDESCRICAOGRUPO;
import banco_de_dados_tabelas.GBDESCRICAOSETOR;
import banco_de_dados_tabelas.GBDIASSEMVENDA;
import banco_de_dados_tabelas.GBDiretoLoja;
import banco_de_dados_tabelas.GBEANSECUNDARIO;
import banco_de_dados_tabelas.GBELENCO;
import banco_de_dados_tabelas.GBEntrouCdOntem;
import banco_de_dados_tabelas.GBEstoqueCdB001;
import banco_de_dados_tabelas.GBEstoqueCdB184;
import banco_de_dados_tabelas.GBEstoqueCdB289;
import banco_de_dados_tabelas.GBEstoqueLojaB141;
import banco_de_dados_tabelas.GBEvento;
import banco_de_dados_tabelas.GBEventoProdutos;
import banco_de_dados_tabelas.GBMRP;
import banco_de_dados_tabelas.GBOOPORTSEMANA;
import banco_de_dados_tabelas.GBOportunidadeIdentificada;
import banco_de_dados_tabelas.GBPEDIDOSATIVOS;
import banco_de_dados_tabelas.GBPONTOEXTRA;
import banco_de_dados_tabelas.GBPRODUTOPORFORNECEDOR;
import banco_de_dados_tabelas.GBPRODUTOPORFORNECEDORCD;
import banco_de_dados_tabelas.GBProdutos;
import banco_de_dados_tabelas.GBQUANTIDADEVENDIDA;
import banco_de_dados_tabelas.GBRA;
import banco_de_dados_tabelas.GBSUPLY;
import banco_de_dados_tabelas.GBULTIMAENTRADADATA;
import banco_de_dados_tabelas.GBZRIS;
import banco_de_dados_tabelas.INVENTARIOMB52;
import banco_de_dados_tabelas.INVENTARIOZLMR06;
import banco_de_dados_tabelas.LOF;
import banco_de_dados_tabelas.LOJAS_A_MB52;
import banco_de_dados_tabelas.LOJAS_A_ZDADOSLOG_CD;
import banco_de_dados_tabelas.LOJAS_A_ZDADOSLOG_LOJA;
import banco_de_dados_tabelas.LOJAS_A_ZVTRA_SORT;
import banco_de_dados_tabelas.Municipio;
import banco_de_dados_tabelas.MunicipioPovoar;
import banco_de_dados_tabelas.Pais;
import banco_de_dados_tabelas.PaisPovoar;
import banco_de_dados_tabelas.Teste;
import banco_de_dados_tabelas.TestePovoar;
import banco_de_dados_tabelas.Uf;
import banco_de_dados_tabelas.UfPovoar;
import banco_de_dados_tabelas.Usuario;
import banco_de_dados_tabelas.UsuarioPovoar;
import banco_de_dados_tabelas.VerificarBanco;
import banco_de_dados_view.View_Zris_Disponilidade_Baixa;
import utilidades.JOPM;

/**
 *
 * @author Ana
 */
public class ControleThread_db extends Thread { 
    
    String endereco_db;
    
    public ControleThread_db( String endereco_db2 ){   
        endereco_db = endereco_db2;
    } 
            
    public void run(){  
        synchronized ( this ) {
            
            verificarsedbexiste(); 
        }  
    }  
    
    public void verificarsedbexiste(){  
        synchronized(this) {  
            if ( VerificarBanco.criar() == true ) {
    
                
            } else{ 
            
                a(); b(); c(); d(); e(); f();  
                usuario(); usuariopovoar(); 
                estabelecimento(); estabelecimentoPovoar();
                h(); i(); j(); k();  
                
                pais(); paisPovoar();
                uf(); ufPovoar();
                municipio(); municipioPovoar();
                cep(); cepPovoar();
                
                gbEvento(); gbEventoProdutos();
                
                gbProduto();
                gbEstoqueLojaB141();
                
                gbEntrouCdOntem(); gbEstoqueCdB184(); gbPEDIDOSATIVOS(); gbPRODUTOPORFORNECEDOR(); gbRA(); gbSUPLY(); gbZRIS(); gbDIASSEMVENDA();
                gbMRP();
                
                disp();
                
                gbPontoExtra(); gbB001(); gbB289();
                
                gbOPorSem();
                
                fornecedorGB();
                
                diretoLojaGB();
                
                gbElenco(); 
                
                gbDescricao();
                gbDescricaoSetor();
                
                enderecoEspacoGondula();
                
                eanSecundario();
                
                inventarioMb52();
                inventarioZlmr06();
                
                opIdentificada();
                
                produtoPorFornecedorCD();
                ultimaEntradaData();
                coringa();
                
                devolutiva();
                
                view_Zris_Disponilidade_Baixa();
                
                analiseEvento();
                
                lojas_a_mb52();                
                lojas_a_zvtra_sortimento();                
                lojas_a_zdadoslog_loja();
                lojas_a_zdadoslog_CD();
                
                lof();
                
                qtdVendida();
            }          
            
        }  
    } 
  
    public void a(){  
        synchronized(this) {  
            if ( CriarBancoDeDados.criar( endereco_db ) == true ) {              
                
            } else{ 
            
                JOPM JOptionPaneMod = new JOPM( 2, "Não foi possível estabelecer \n"
                + "Comunicação com o banco de dados, \n"
                + "Verificar se a pasta lib está junto com o arquivo JAR"        
                + "\n", "ControleThread_db" );
            }          
        }  
    }  
    
    public void b(){  
        synchronized(this){      
            if ( RequererAutenticacao.criar() == true ) {
                System.out.println( "RequererAutenticacao" );              
                
            } else{ 
                
            } 
        }
    }
  
    public void c(){  
        synchronized(this){ 
            if ( CriarSchema.criar() == true ) {
                System.out.println( "CriarSchema" );
                
            } else{ 
           
            } 
        }
    } 
    
    public void d(){  
        synchronized(this){          
            if ( TestarUsuarioESenha.criar() == true ) {
                System.out.println( "TestarUsuarioESenha" );
                                
            } else{ 
                
            }   
        }  
    }
    
    public void e(){  
        synchronized(this){   
            if ( Teste.criar() == true ) {
                System.out.println( "Teste" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void f(){  
        synchronized(this){       
            if ( TestePovoar.criar() == true ) {
                System.out.println( "TestePovoar" );
                
            } else{ 
            }  
        }  
    }    
                
    public void usuario(){  
        synchronized(this){   
            if ( Usuario.criar() == true ) {
                System.out.println( "Usuario" );
                                              
            } else{ 
            }  
        }  
    }        
    
    public void usuariopovoar(){  
        synchronized(this){   
            if ( UsuarioPovoar.criar() == true ) {
                System.out.println( "UsuarioPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void estabelecimento(){  
        synchronized(this){   
            if ( Estabelecimento.criar() == true ) {
                System.out.println( "Estabelecimento" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void estabelecimentoPovoar(){  
        synchronized(this){   
            if ( EstabelecimentoPovoar.criar() == true ) {
                System.out.println( "estabelecimentoPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void h(){  
        synchronized(this){   
            if ( Acesso.criar() == true ) {
                System.out.println( "Acesso" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void i(){  
        synchronized(this){   
            if ( AcessoPovoar.criar() == true ) {
                System.out.println( "AcessoPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void j(){  
        synchronized(this){   
            if ( Controle_Acesso.criar() == true ) {
                System.out.println( "Controle_Acesso" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void k(){  
        synchronized(this){   
            if ( ControleAcessoPovoar.criar() == true ) {
                System.out.println( "ControleAcessoPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void pais(){  
        synchronized(this){   
            if ( Pais.criar() == true ) {
                System.out.println( "Pais" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void paisPovoar(){  
        synchronized(this){   
            if ( PaisPovoar.criar() == true ) {
                System.out.println( "PaisPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void uf(){  
        synchronized(this){   
            if ( Uf.criar() == true ) {
                System.out.println( "Uf" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void ufPovoar(){  
        synchronized(this){   
            if ( UfPovoar.criar() == true ) {
                System.out.println( "UfPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void municipio(){  
        synchronized(this){   
            if ( Municipio.criar() == true ) {
                System.out.println( "Municipio" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void municipioPovoar(){  
        synchronized(this){   
            if ( MunicipioPovoar.criar() == true ) {
                System.out.println( "MunicipioPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void cep(){  
        synchronized(this){   
            if ( Cep.criar() == true ) {
                System.out.println( "Cep" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void cepPovoar(){  
        synchronized(this){   
            if ( CepPovoar.criar() == true ) {
                System.out.println( "CepPovoar" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void gbEvento(){  
        synchronized(this){   
            if ( GBEvento.criar() == true ) {
                System.out.println( "gbEvento" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void gbEventoProdutos(){  
        synchronized(this){   
            if ( GBEventoProdutos.criar() == true ) {
                System.out.println( "GBEventoProdutos" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void gbProduto(){  
        synchronized(this){   
            if ( GBProdutos.criar() == true ) {
                System.out.println( "GBProdutos" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void gbEstoqueLojaB141(){  
        synchronized(this){   
            if ( GBEstoqueLojaB141.criar() == true ) {
                System.out.println( "GBEstoqueLojaB141" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void gbATIVOSEMVENDA(){  
        synchronized(this){   
            if ( GBATIVOSEMVENDA.criar() == true ) {
                System.out.println( "gbATIVOSEMVENDA" );                                              
            } else{}  
    } }
    
    public void gbEntrouCdOntem(){  
        synchronized(this){   
            if ( GBEntrouCdOntem.criar() == true ) {
                System.out.println( "GBEntrouCdOntem" );                                              
            } else{}  
    } }
    
    public void gbEstoqueCdB184(){  
        synchronized(this){   
            if ( GBEstoqueCdB184.criar() == true ) {
                System.out.println( "GBEstoqueCdB184" );                                              
            } else{}  
    } }
    
    public void gbPEDIDOSATIVOS(){  
        synchronized(this){   
            if ( GBPEDIDOSATIVOS.criar() == true ) {
                System.out.println( "GBPEDIDOSATIVOS" );                                              
            } else{}  
    } }
    
    public void gbPRODUTOPORFORNECEDOR(){  
        synchronized(this){   
            if ( GBPRODUTOPORFORNECEDOR.criar() == true ) {
                System.out.println( "GBPRODUTOPORFORNECEDOR" );                                              
            } else{}  
    } }
    
    public void gbRA(){  
        synchronized(this){   
            if ( GBRA.criar() == true ) {
                System.out.println( "GBRA" );                                              
            } else{}  
    } }
    
    public void gbSUPLY(){  
        synchronized(this){   
            if ( GBSUPLY.criar() == true ) {
                System.out.println( "GBSUPLY" );                                              
            } else{}  
    } }
    
    public void gbZRIS(){  
        synchronized(this){   
            if ( GBZRIS.criar() == true ) {
                System.out.println( "GBZRIS" );                                              
            } else{}  
    } }
    
    public void gbDIASSEMVENDA(){  
        synchronized(this){   
            if ( GBDIASSEMVENDA.criar() == true ) {
                System.out.println( "GBDIASSEMVENDA" );                                              
            } else{}  
    } }
    
    public void gbMRP(){  
        synchronized(this){   
            if ( GBMRP.criar() == true ) {
                System.out.println( "GBMRP" );                                              
            } else{}  
    } }
    
    public void fornecedorGB(){  
        synchronized(this){   
            if ( Fornecedor.criar() == true ) {
                System.out.println( "Fornecedor" );
                                              
            } else{ 
            }  
        }  
    }
    
    public void gbPontoExtra(){  
        synchronized(this){   
            if ( GBPONTOEXTRA.criar() == true ) {
                System.out.println( "GBPONTOEXTRA" );                                              
            } else{}  
    } }
    
    public void gbB001(){  
        synchronized(this){   
            if ( GBEstoqueCdB001.criar() == true ) {
                System.out.println( "GBEstoqueCdB001" );                                              
            } else{}  
    } }
    
    public void gbB289(){  
        synchronized(this){   
            if ( GBEstoqueCdB289.criar() == true ) {
                System.out.println( "GBESTOQUECDB289" );                                              
            } else{}  
    } }
    
    public void gbOPorSem(){  
        synchronized(this){   
            if ( GBOOPORTSEMANA.criar() == true ) {
                System.out.println( "GBOOPORTSEMANA" );                                              
            } else{}  
    } }
    
    public void disp(){  
        synchronized(this){   
            if ( GBDDEDISP.criar() == true ) {
                System.out.println( "GBDDEDISP" );                                              
            } else{}  
    } }
    
    public void diretoLojaGB(){  
        synchronized(this){   
            if ( GBDiretoLoja.criar() == true ) {
                System.out.println( "GBDiretoLoja" );                                              
            } else{}  
    } }
    
    public void gbElenco(){  
        synchronized(this){   
            if ( GBELENCO.criar() == true ) {
                System.out.println( "GBELENCO" );                                              
            } else{}  
    } }
    
    public void gbDescricao(){  
        synchronized(this){   
            if ( GBDESCRICAOGRUPO.criar() == true ) {
                System.out.println( "GBDESCRICAOGRUPO" );                                              
            } else{}  
    } }  
            
    public void gbDescricaoSetor(){  
        synchronized(this){   
            if ( GBDESCRICAOSETOR.criar() == true ) {
                System.out.println( "GBDESCRICAOSETOR" );                                              
            } else{}  
    } }        
    
    public void enderecoEspacoGondula(){  
        synchronized(this){   
            if ( EnderecoEspacoGondula.criar() == true ) {
                System.out.println( "ENDERECOESPACOGONDULA" );                                              
            } else{}  
    } } 
                       
    public void eanSecundario(){  
        synchronized(this){   
            if ( GBEANSECUNDARIO.criar() == true ) {
                System.out.println( "GBEANSECUNDARIO" );                                              
            } else{}  
    } } 
                    
    public void inventarioMb52(){  
        synchronized(this){   
            if ( INVENTARIOMB52.criar() == true ) {
                System.out.println( "INVENTARIOMB52" );                                              
            } else{}  
    } } 
    
    public void inventarioZlmr06(){  
        synchronized(this){   
            if ( INVENTARIOZLMR06.criar() == true ) {
                System.out.println( "INVENTARIOZLMR06" );                                              
            } else{}  
    } } 
    
    public void opIdentificada(){  
        synchronized(this){   
            if ( GBOportunidadeIdentificada.criar() == true ) {
                System.out.println( "GBOportunidadeIdentificada" );                                              
            } else{}  
    } } 
    
    public void produtoPorFornecedorCD(){  
        synchronized(this){   
            if ( GBPRODUTOPORFORNECEDORCD.criar() == true ) {
                System.out.println( "GBPRODUTOPORFORNECEDORCD" );                                              
            } else{}  
    } } 
    
    public void ultimaEntradaData(){  
        synchronized(this){   
            if ( GBULTIMAENTRADADATA.criar() == true ) {
                System.out.println( "GBULTIMAENTRADADATA" );                                              
            } else{}  
    } }
    
    public void coringa(){  
        synchronized(this){   
            if ( GBCORINGA.criar() == true ) {
                System.out.println( "GBCORINGA" );                                              
            } else{}  
    } }
    
    public void devolutiva(){  
        synchronized(this){   
            if ( ANALISEDEVOLUTIVA.criar() == true ) {
                System.out.println( "ANALISEDEVOLUTIVA" );                                              
            } else{}  
    } }
    
    public void view_Zris_Disponilidade_Baixa(){  
        synchronized(this){   
            if ( View_Zris_Disponilidade_Baixa.criar() == true ) {
                System.out.println( "View_Zris_Disponilidade_Baixa" );                                              
            } else{}  
    } }
    
    public void analiseEvento(){  
        synchronized(this){   
            if ( ANALISE_EVENTOS.criar() == true ) {
                System.out.println( "ANALISE_EVENTOS" );                                              
            } else{}  
    } }
        
    public void lojas_a_mb52(){  
        synchronized(this){   
            if ( LOJAS_A_MB52.criar() == true ) {
                System.out.println( "LOJAS_A_MB52" );                                              
            } else{}  
    } }    

    public void lojas_a_zvtra_sortimento(){  
        synchronized(this){   
            if ( LOJAS_A_ZVTRA_SORT.criar() == true ) {
                System.out.println( "LOJAS_A_ZVTRA_SORT" );                                              
            } else{}  
    } }   
    
    public void lojas_a_zdadoslog_loja(){  
        synchronized(this){   
            if ( LOJAS_A_ZDADOSLOG_LOJA.criar() == true ) {
                System.out.println( "LOJAS_A_ZDADOSLOG_LOJA" );                                              
            } else{}  
    } }  
    
    public void lojas_a_zdadoslog_CD(){  
        synchronized(this){   
            if ( LOJAS_A_ZDADOSLOG_CD.criar() == true ) {
                System.out.println( "LOJAS_A_ZDADOSLOG_CD" );                                              
            } else{}  
    } } 
    
    public void lof(){  
        synchronized(this){   
            if ( LOF.criar() == true ) {
                System.out.println( "LOF" );                                              
            } else{}  
    } } 
    
    public void qtdVendida(){  
        synchronized(this){   
            if ( GBQUANTIDADEVENDIDA.criar() == true ) {
                System.out.println( "GBQUANTIDADEVENDIDA" );                                              
            } else{}  
    } }
        
    /******************Executar Teste*************************************       
     * @param args************************  opIdentificada();
    public static void main(String[] args) {            
          
        ControleThread t1 = new ControleThread(); 
        
        t1.setName("Thread1");   
        
        t1.start();  
    }
    /******************Executar Teste*************************************/
    
}
