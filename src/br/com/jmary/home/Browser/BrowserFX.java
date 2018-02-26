/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jmary.home.Browser;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import utilidades.JOPM;

/**
 *
 * @author AnaMariana
 */
public class BrowserFX extends JFXPanel{
    String url;
    public WebViewPane WebViewPane;
    
    public BrowserFX( String url2 ) {
        url = url2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                    
            // create JavaFX scene
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    createScene();
                }
            });      
        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "BrowserFX( String url2 ), \n"
                + e.getMessage() + "\n", "BrowserFX" ); } } }.start();      
    }

    public BrowserFX(String url2, boolean b) {
        url = url2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                    
            // create JavaFX scene
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    createScene("");
                }
            });      
        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "BrowserFX( String url2 ), \n"
                + e.getMessage() + "\n", "BrowserFX" ); } } }.start(); 
    }
    
    public BrowserFX(String url2, boolean b, boolean b2) {
        url = url2;
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
                    
            // create JavaFX scene
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    createScene("",true);
                }
            });      
        
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "BrowserFX( String url2 ), \n"
                + e.getMessage() + "\n", "BrowserFX" ); } } }.start(); 
    }

    private void createScene() { 
        WebViewPane = new WebViewPane( url );
        this.setScene(new Scene( WebViewPane ));
    } 
    
    private void createScene(String str) { 
        WebViewPane = new WebViewPane( url, true );
        this.setScene(new Scene( WebViewPane ));
    }
    
    private void createScene(String str, boolean b) { 
        WebViewPane = new WebViewPane( url, true, true );       
        this.setScene(new Scene( WebViewPane ));       
    }

}
