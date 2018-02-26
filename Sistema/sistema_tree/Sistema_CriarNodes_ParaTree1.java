
package sistema_tree;

import sistema_designer.JPHtmlPrincipal;
import sistema_designer.JPHtmlPrincipal_Inicio;
import sistema_iconestrees.IconesTrees;
import home_controle.BookInfo;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Sistema_CriarNodes_ParaTree1 {
    
    JPHtmlPrincipal JPHtmlPrincipal;        
    DefaultMutableTreeNode tree_Materia = new DefaultMutableTreeNode( "Fonema" );

    String home                = System.getProperty("user.dir");
    String comp                = System.getProperty("file.separator") + "Files_Html" + System.getProperty("file.separator") + "Materia" + System.getProperty("file.separator");
    
    public Sistema_CriarNodes_ParaTree1( JPHtmlPrincipal JPHtml ) {
        
        JPHtmlPrincipal = JPHtml;
        verificarMateria();
    }

    private void verificarMateria(){ 
        try {/* 
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
                
                DefaultTreeModel treeModel = new DefaultTreeModel( tree_Materia );
                JPHtmlPrincipal.tree1.setModel(treeModel); 
            }
            else{
                
                paginaIncial(); matematica_Simbologia_Administrador_CriarNodes( true );
            }*/
        } catch ( Exception e ) {
            paginaIncial(); matematica_Simbologia_Administrador_CriarNodes( true );
        }
    }
    
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
            }         
            
        } catch ( Exception e ) {}
    }*/
    
    ////////////////////////////////////////////////////////////////////////////
    private void paginaIncial(){ 
        try {
            JPHtmlPrincipal_Inicio setarJEditorPane_Inicio = new JPHtmlPrincipal_Inicio( getClass().getResource("/sistema_Html/fonema_e_letra.html").toString(), JPHtmlPrincipal );
            JPHtmlPrincipal.bookImprimir = "/sistema_Html/fonema_e_letra.html";
        } catch ( Exception e ) {}
    }
            
    private void matematica_Simbologia_Administrador_CriarNodes( boolean adicionarList ) {
        
        BookInfo fonemaRoot = new BookInfo( "Português", "/sistema/Fonema_e_Letra_Home.html", true, "", 0, IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );
            tree_Materia = new DefaultMutableTreeNode( fonemaRoot );
            
        BookInfo fonema_1 = new BookInfo( "Fonema", "/sistema_Html/fonema_indice.html", true, "", 0, IconesTrees.root, IconesTrees.pastaFechada, IconesTrees.pastaAberta, IconesTrees.nod, IconesTrees.nodPastaAberta );    
            DefaultMutableTreeNode fonema_11 = new DefaultMutableTreeNode( fonema_1 );
            tree_Materia.add( fonema_11 );  
        
        //Simbologia_CriarNodes_ParaTree1 Simbologia_ParaTree1 = new Simbologia_CriarNodes_ParaTree1( JPHtmlPrincipal, tree_Materia, adicionarList ); 
        
        /* JTree Customization node */
        DefaultTreeModel treeModel = new DefaultTreeModel( tree_Materia );
        JPHtmlPrincipal.tree1.setModel(treeModel); 
    }
}