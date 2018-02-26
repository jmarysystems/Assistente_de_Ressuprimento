/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author ana
 */
public class Retornar_Doc_Html_Recebe_String {

    /** Realiza a validação do CPF. 
     * 
     * @param str
     * @return 
     */

    public static HTMLDocument strToHtml( String str ) {

        return getHTMLDocument( str ); 
    }
    
    /** 
     * Método que recebe uma String e a converte para HTMLDocument. 
     * 
     * @param html 
     * @return documento HTML ou nulo 
     */  
    private static HTMLDocument getHTMLDocument( String html ) {  
        HTMLEditorKit editorKit = new HTMLEditorKit();  
        HTMLDocument document = (HTMLDocument) editorKit.createDefaultDocument();  
        document.putProperty("IgnoreCharsetDirective", Boolean.TRUE);  
        InputStream inputStream = new ByteArrayInputStream( html.getBytes() );  
        try {  
            editorKit.read(inputStream, document, 0);  
        } catch (IOException | BadLocationException ex) {  
            return null;  
        }  
        return document;  
    }
// Use este trecho para testar a classe 
//    }
    public static void main(String[] args) {
        String html = 
                "<html> "
                + "<title> JMary Systems </title> "
                + "<body>"
                + "Hello"
                + "</body>" 
                + "</html> ";
        
        //strToHtml( html );
        
        System.out.println( strToHtml( html ) );
    }
}
