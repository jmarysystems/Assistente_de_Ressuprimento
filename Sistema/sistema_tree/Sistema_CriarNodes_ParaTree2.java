package sistema_tree;

import sistema_designer.JPHtmlPrincipal;
import sistema_iconestrees.IconesTrees;
import home_controle.BookInfo;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Sistema_CriarNodes_ParaTree2 {
    
    JPHtmlPrincipal JPHtmlPrincipal;    
    DefaultMutableTreeNode tree_Materia = new DefaultMutableTreeNode( "Português" );
    
    String tipoNoBanco;
    int    idNoBanco;
    
    String home                = System.getProperty("user.dir");
    String comp                = System.getProperty("file.separator") + "Files_Html" + System.getProperty("file.separator") + "Materia" + System.getProperty("file.separator");
    
    public Sistema_CriarNodes_ParaTree2( JPHtmlPrincipal JPHtml, String tipoNoBanco2, int idNoBanco2 ) {
        
        JPHtmlPrincipal = JPHtml;
        tipoNoBanco     = tipoNoBanco2;
        idNoBanco       = idNoBanco2;
        
        //verificarTipoNoBanco();
    }
            
    /*private void verificarTipoNoBanco() {
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); DefaultTreeModel treeModel;
        
            switch( tipoNoBanco ){
                
                case "MATERIA":    
                    verificarMateria(); 
                    
                    treeModel = new DefaultTreeModel( tree_Materia );
                    JPHtmlPrincipal.tree2.setModel(treeModel); 
                break;
                case "CATEGORIA":  
                    materias.Materia Materia = new materias.Materia();
            
                    List<materias.Materia> ListMateria = new ArrayList();
                    Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MATERIA WHERE MATERIA LIKE ?", materias.Materia.class );
                    q.setParameter(1, JPHtmlPrincipal.materia );
            
                    ListMateria = q.getResultList();
                    for (int i = 0; i < ListMateria.size(); i++){ Materia = ListMateria.get(i); }
                    
                    ////////////////////////////////////////////////////////////
                    categorias.Categoria Categoria = new categorias.Categoria();
            
                    List<categorias.Categoria> ListCategoria = new ArrayList();
                    Query q2 = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.CATEGORIA WHERE ID = ?", categorias.Categoria.class );
                    q2.setParameter( 1, idNoBanco );
            
                    ListCategoria = q2.getResultList();
                    for (int i = 0; i < ListCategoria.size(); i++){ 
                        Categoria = ListCategoria.get(i); 
                        String endCategoria = home + comp + Materia.getMateria() + System.getProperty("file.separator") + Categoria.getCategoria() + System.getProperty("file.separator") + Categoria.getCategoriapaginahtml(); 
                        
                        BookInfo rootCategoria = new BookInfo( Categoria.getCategoria(), new File(endCategoria).toURI().toURL().toString(), false, "CATEGORIA", Categoria.getId(), IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );    
                        tree_Materia = new DefaultMutableTreeNode( rootCategoria );
                        
                        setarSubcategorias( Materia, Categoria, tree_Materia );
                    
                        treeModel = new DefaultTreeModel( tree_Materia );
                        JPHtmlPrincipal.tree2.setModel(treeModel);                                                      
                        break;
                    }                                       
                    ////////////////////////////////////////////////////////////
                                
                break; 
                case "SUBCATEGORIA":
                    Materia = new materias.Materia();
            
                    ListMateria = new ArrayList();
                    q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MATERIA WHERE MATERIA LIKE ?", materias.Materia.class );
                    q.setParameter(1, JPHtmlPrincipal.materia );
            
                    ListMateria = q.getResultList();
                    for (int i = 0; i < ListMateria.size(); i++){ Materia = ListMateria.get(i); }   
                    
                    ////////////////////////////////////////////////////////////
                    subcategoria.Subcategoria Subcategoria = new subcategoria.Subcategoria();
            
                    List<subcategoria.Subcategoria> ListSubcategoria = new ArrayList();
                    q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.SUBCATEGORIA WHERE ID = ?", subcategoria.Subcategoria.class );
                    q.setParameter( 1, idNoBanco );
            
                    ListSubcategoria = q.getResultList();
                    for (int i = 0; i < ListSubcategoria.size(); i++){ Subcategoria = ListSubcategoria.get(i);
                    
                        String nomePastaDaCategoria = retornarNomeCategoria( Subcategoria.getIdCategoria() );
            
                        String endSubcategoria = home + comp + Materia.getMateria() + System.getProperty("file.separator") + nomePastaDaCategoria + System.getProperty("file.separator") + Subcategoria.getSubcategoria() + System.getProperty("file.separator") + Subcategoria.getSubcategoriapaginahtml(); 
                                        
                        BookInfo rootSubcategoria = new BookInfo( Subcategoria.getSubcategoria(), new File(endSubcategoria).toURI().toURL().toString(), false, "SUBCATEGORIA", Subcategoria.getId(), IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );      
                        tree_Materia = new DefaultMutableTreeNode( rootSubcategoria );
                        
                        setarAssunto( Subcategoria, tree_Materia );
                    
                        treeModel = new DefaultTreeModel( tree_Materia );
                        JPHtmlPrincipal.tree2.setModel(treeModel);                                                      
                        break;
                    }
                    ////////////////////////////////////////////////////////////
                break;    
                    
                default: Fonema_e_Letra_Administrador_CriarNodes(); break;   
            }
                       
        } catch( Exception e ){ Fonema_e_Letra_Administrador_CriarNodes(); } } }.start();          
    }*/
    
    /*private void verificarMateria() {
        try { 
            materias.Materia Materia = new materias.Materia();
            
            List<materias.Materia> ListMateria = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MATERIA WHERE MATERIA LIKE ?", materias.Materia.class );
            q.setParameter(1, JPHtmlPrincipal.materia );
            
            ListMateria = q.getResultList();
            for (int i = 0; i < ListMateria.size(); i++){ Materia = ListMateria.get(i); 
                
                String endMateria = home + comp + Materia.getMateria() + System.getProperty("file.separator") + ListMateria.get(i).getMateriapaginahtml(); 
                
                BookInfo rootMateria = new BookInfo( Materia.getMateria(), new File(endMateria).toURI().toURL().toString(), false, "MATERIA", Materia.getId(), IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );
                tree_Materia = new DefaultMutableTreeNode( rootMateria );
                
                JPHtmlPrincipal_Inicio setarJEditorPane_Inicio = new JPHtmlPrincipal_Inicio( new File(endMateria).toURI().toURL().toString(), JPHtmlPrincipal );
            }
            
            if( Materia.getMateria().equals( JPHtmlPrincipal.materia ) ){                
                
                setarCategorias( Materia );                
            }
            else{
                
                Fonema_e_Letra_Administrador_CriarNodes();
            }
        } catch ( Exception e ) {}               
    }*/
    
    /*private void setarCategorias( materias.Materia Materia ){ 
        try {         
            categorias.Categoria Categoria = new categorias.Categoria();
            
            List<categorias.Categoria> ListCategoria = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.CATEGORIA WHERE ID_MATERIA = ?", categorias.Categoria.class );
            q.setParameter( 1, Materia.getId() );
            
            ListCategoria = q.getResultList();
            for (int i = 0; i < ListCategoria.size(); i++){ Categoria = ListCategoria.get(i);
            
                String endCategoria = home + comp + Materia.getMateria() + System.getProperty("file.separator") + Categoria.getCategoria() + System.getProperty("file.separator") + Categoria.getCategoriapaginahtml(); 
                
                BookInfo categoria_1 = new BookInfo( Categoria.getCategoria(), new File(endCategoria).toURI().toURL().toString(), false, "CATEGORIA", Categoria.getId(), IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );    
                DefaultMutableTreeNode categoria = new DefaultMutableTreeNode( categoria_1 );
                tree_Materia.add( categoria );    
                
                setarSubcategorias( Materia, Categoria, categoria );
            }         
            
        } catch ( Exception e ) {}
    }*/
    
    /*private void setarSubcategorias( materias.Materia Materia, categorias.Categoria Categoria, DefaultMutableTreeNode categoria ){ 
        try {         
            subcategoria.Subcategoria Subcategoria = new subcategoria.Subcategoria();
            
            List<subcategoria.Subcategoria> ListSubcategoria = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.SUBCATEGORIA WHERE ID_MATERIA = ? AND ID_CATEGORIA = ?", subcategoria.Subcategoria.class );
            q.setParameter( 1, Categoria.getIdMateria() );
            q.setParameter( 2, Categoria.getId() );
            
            ListSubcategoria = q.getResultList();
            for (int i = 0; i < ListSubcategoria.size(); i++){ Subcategoria = ListSubcategoria.get(i);
            
                String endSubcategoria = home + comp + Materia.getMateria() + System.getProperty("file.separator") + Categoria.getCategoria() + System.getProperty("file.separator") + Subcategoria.getSubcategoria() + System.getProperty("file.separator") + Subcategoria.getSubcategoriapaginahtml(); 
                
                BookInfo subcategoria_1 = new BookInfo( Subcategoria.getSubcategoria(), new File(endSubcategoria).toURI().toURL().toString(), false, "SUBCATEGORIA", Subcategoria.getId(), IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );    
                DefaultMutableTreeNode subcategoria = new DefaultMutableTreeNode( subcategoria_1 );
                categoria.add( subcategoria );  
                
                setarAssunto( Subcategoria, subcategoria );
            }         
            
        } catch ( Exception e ) {}
    }*/
    
    /*private void setarAssunto( subcategoria.Subcategoria Subcategoria, DefaultMutableTreeNode subcategoria ){ 
        try {         
            assunto.Assunto Assunto = new assunto.Assunto();
            
            List<assunto.Assunto> ListAssunto = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.ASSUNTO WHERE ID_MATERIA = ? AND ID_CATEGORIA = ? AND ID_SUBCATEGORIA = ?", assunto.Assunto.class );
            q.setParameter( 1, Subcategoria.getIdMateria() );
            q.setParameter( 2, Subcategoria.getIdCategoria() );
            q.setParameter( 3, Subcategoria.getId() );
            
            ListAssunto = q.getResultList();
            for (int i = 0; i < ListAssunto.size(); i++){ Assunto = ListAssunto.get(i);
            
                String nomePastaDaMateria   = retornarNomeMateria( Subcategoria.getIdMateria() );
                String nomePastaDaCategoria = retornarNomeCategoria( Subcategoria.getIdCategoria() );
            
                String endAssunto = home + comp + nomePastaDaMateria + System.getProperty("file.separator") + nomePastaDaCategoria + System.getProperty("file.separator") + Subcategoria.getSubcategoria() + System.getProperty("file.separator") + Assunto.getAssunto() + System.getProperty("file.separator") + Assunto.getAssuntopaginahtml(); 
                
                BookInfo assunto_1 = new BookInfo( Assunto.getAssunto(), new File(endAssunto).toURI().toURL().toString(), false, "ASSUNTO", Assunto.getId(), IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );    
                DefaultMutableTreeNode assunto = new DefaultMutableTreeNode( assunto_1 );
                subcategoria.add( assunto );         
            }         
            
        } catch ( Exception e ) {}
    }*/
        
    ////////////////////////////////////////////////////////////////////////////
    public void Fonema_e_Letra_Administrador_CriarNodes() {
        
        BookInfo fonemaRoot = new BookInfo( "Fonema", "/sistema_Html/fonema.html", true, "", 0, IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );
            tree_Materia = new DefaultMutableTreeNode( fonemaRoot );
        
        BookInfo fonema_1 = new BookInfo( "Fonema e Letra", "/sistema_Html/fonema_e_letra.html", true, "", 0, IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );
            DefaultMutableTreeNode fonema_11 = new DefaultMutableTreeNode( fonema_1 );
            tree_Materia.add( fonema_11 );  
        
        /* JTree Customization node */
        DefaultTreeModel treeModel = new DefaultTreeModel( tree_Materia );
        JPHtmlPrincipal.tree2.setModel(treeModel); 
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /*private String retornarNomeMateria( int idMateria ){ String retorno = "";
        
        try{
            
            List<materias.Materia> ListMateria = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.MATERIA WHERE ID = ?", materias.Materia.class );
            q.setParameter(1, idMateria );
            
            ListMateria = q.getResultList();
            for (int i = 0; i < ListMateria.size(); i++){ 
                
                retorno = ListMateria.get(i).getMateria();
            }
        } catch( Exception e ){}
        
        return retorno;
    }*/
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    /*private String retornarNomeCategoria( int idCategoria ){ String retorno = "";
        
        try{
            
            List<categorias.Categoria> ListCategoria = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.CATEGORIA WHERE ID = ?", categorias.Categoria.class );
            q.setParameter(1, idCategoria );
            
            ListCategoria = q.getResultList();
            for (int i = 0; i < ListCategoria.size(); i++){ 
                
                retorno = ListCategoria.get(i).getCategoria();
            }
        } catch( Exception e ){}
        
        return retorno;
    }*/
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    /*private String retornarNomeSubcategoria( int idSubcategoria ){ String retorno = "";
        
        try{
            
            List<subcategoria.Subcategoria> ListSubcategoria = new ArrayList();
            Query q = JPAUtil.em().createNativeQuery( "SELECT * FROM SCHEMAJMARY.SUBCATEGORIA WHERE ID = ?", subcategoria.Subcategoria.class );
            q.setParameter(1, idSubcategoria );
            
            ListSubcategoria = q.getResultList();
            for (int i = 0; i < ListSubcategoria.size(); i++){ 
                
                retorno = ListSubcategoria.get(i).getSubcategoria();
            }
        } catch( Exception e ){}
        
        return retorno;
    }*/
    ////////////////////////////////////////////////////////////////////////////

}