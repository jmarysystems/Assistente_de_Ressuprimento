/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.InputStream;
import javax.swing.JOptionPane;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author pc
 */
public class Recebe_StringHTML_Devolve_Txt_Da_ID {
    
    public static String conteudo_Id( String html, String id ) {
        String retorno = "";
        
        try {
            
            InputStream inputStream = new ByteArrayInputStream( html.getBytes() );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse( inputStream );
            //doc.getDocumentElement().normalize();
            
            if ( doc.hasChildNodes() ) {
                
                retorno = printNote( doc.getChildNodes(), id );
            }
            else{
                System.out.println( "... doc.hasChildNodes() ..." );
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog( null, "Recebe_StringHTML_Devolve_Txt_Da_ID\n - Método: conteudo_Id() \n"+ e.getMessage() ); 
        }
        
        return retorno;        
    }
    
    private static String printNote( NodeList nodeList, String id ) {
        boolean b      = false;
        String retorno = ""; 
        
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);
 
            // make sure it's element node.
            if ( ( tempNode.getNodeType() == Node.ELEMENT_NODE ) && b == false ) {
                                                      
		// get node name and value
                //System.out.println("<" + tempNode.getNodeName() + ">");

		//System.out.println("Conteúdo = " + tempNode.getTextContent());
 
		if ( ( tempNode.hasAttributes() ) && b == false ) {
 
			// get attributes names and values
			NamedNodeMap nodeMap = tempNode.getAttributes();
 
			for (int i = 0; i < nodeMap.getLength(); i++) {
 
				Node node = nodeMap.item(i);
				//System.out.println("attr name : " + node.getNodeName());
				//System.out.println("attr value : " + node.getNodeValue());
                                if ( ( node.getNodeValue().equals( id ) ) && b == false ) {
                                    b = true;
                                    retorno = tempNode.getTextContent();
                                    //System.out.println( retorno );
                                    break;
                                } 
			}
 
		}
 
		if ( ( tempNode.hasChildNodes() ) && b == false ) {
 
			// loop again if has child nodes
			retorno = printNote( tempNode.getChildNodes(), id ); 
		}
 
		//System.out.println("</" + tempNode.getNodeName() + ">"); 
                
	}
 
    }
         
    return retorno;
  }
    
    public static void main(String argv[]) {
 
        try {
        
            String t = "<html><body>ccccccc<div id=\"id1\">idxxlllllx</div></body></html>";
         
            String r = conteudo_Id( t, "id1" );
            System.out.println( r );
        
        } catch (Exception e) {
	    e.printStackTrace();
        }
  }
 
}
