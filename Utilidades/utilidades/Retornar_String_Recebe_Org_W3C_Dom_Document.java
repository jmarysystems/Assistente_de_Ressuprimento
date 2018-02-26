/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author pc
 */
public class Retornar_String_Recebe_Org_W3C_Dom_Document {
    
    public static String Retornar_String( org.w3c.dom.Document Document ) {
        String s = "";
          //String para org.w3c.dom.Document
          //org.w3c.dom.Document Document2; Document2.setTextContent( "" ); 
        
        //Retornar Apenas letras 
        //s = Document.getDocumentElement().getTextContent();
        try{
            DOMSource domSource = new DOMSource( Document );
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return  s = writer.toString();
        }
        catch( Exception e ){ 
            return "";
        }
    }
    
}
