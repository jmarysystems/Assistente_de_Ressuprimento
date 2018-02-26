/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cep_new;

/**
 *
 * @author pc
 */
public class BookInfoMuni {
    
    public int    ID;    
        public String SUBCATEGORIA;

        public BookInfoMuni(int ID2, String SUBCATEGORIA2 ) {
            ID                 = ID2     ;    
            SUBCATEGORIA       = SUBCATEGORIA2; 
        }
        
        @Override
        public String toString() {
            return SUBCATEGORIA;
        }
    
}
