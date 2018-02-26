package municipio_new2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AnaMariana
 */
public class BookInfoUf {
    
        public int    ID;    
        public String MATERIA; 

        public BookInfoUf(int ID2, String MATERIA2 ) {
            ID                 = ID2     ;    
            MATERIA            = MATERIA2; 
        }
        
        @Override
        public String toString() {
            return MATERIA;
        }

}
