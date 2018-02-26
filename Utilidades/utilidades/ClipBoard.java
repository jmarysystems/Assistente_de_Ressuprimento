/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author pc
 */
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

public class ClipBoard {
    
    /** Creates a new instance of ClipBoard */
public ClipBoard() {
}

public String getClipboardContents() {
    String result = "";
    Clipboard clipboard =
                                Toolkit.getDefaultToolkit().getSystemClipboard();
    //odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);
    boolean hasTransferableText = (contents != null) &&
            contents.isDataFlavorSupported(DataFlavor.stringFlavor);

    if ( hasTransferableText ) {
        try {
            result =
                   (String)contents.getTransferData(DataFlavor.stringFlavor);
        }
        catch (UnsupportedFlavorException ex){
            //highly unlikely since we are using a standard DataFlavor
            System.out.println(ex);
            ex.printStackTrace();
        }
        catch (IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    return result;
}
    
}
