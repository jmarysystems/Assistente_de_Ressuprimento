/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importar_exportar_search;

/**
 *
 * @author pc
 */
public class BookInfoEventos {
    
    public int    ID;    
        public String MATERIA; 

        public BookInfoEventos(int ID2, String MATERIA2 ) {
            ID                 = ID2     ;    
            MATERIA            = MATERIA2; 
        }
        
        @Override
        public String toString() {
            return MATERIA;
        }
    
}
