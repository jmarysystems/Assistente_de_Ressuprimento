/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario_z_control;

/**
 *
 * @author ana
 */
public class Bean_Usuario {
    
    private static int ID                                   ;
                    
    private static boolean PERMITIRACESSO               = false;
    private static String  LOGIN                        = "";
    private static String  SENHA                        = "";
    
    private static String  NOMECOMPLETO                 = "";

    /**
     * @return the ID
     */
    public static int getID() {
        return ID;
    }

    /**
     * @param aID the ID to set
     */
    public static void setID(int aID) {
        ID = aID;
    }

    /**
     * @return the PERMITIRACESSO
     */
    public static boolean isPERMITIRACESSO() {
        return PERMITIRACESSO;
    }

    /**
     * @param aPERMITIRACESSO the PERMITIRACESSO to set
     */
    public static void setPERMITIRACESSO(boolean aPERMITIRACESSO) {
        PERMITIRACESSO = aPERMITIRACESSO;
    }

    /**
     * @return the LOGIN
     */
    public static String getLOGIN() {
        return LOGIN;
    }

    /**
     * @param aLOGIN the LOGIN to set
     */
    public static void setLOGIN(String aLOGIN) {
        LOGIN = aLOGIN;
    }

    /**
     * @return the SENHA
     */
    public static String getSENHA() {
        return SENHA;
    }

    /**
     * @param aSENHA the SENHA to set
     */
    public static void setSENHA(String aSENHA) {
        SENHA = aSENHA;
    }
    
    /**
     * @return the NOMECOMPLETO
     */
    public static String getNOMECOMPLETO() {
        return NOMECOMPLETO;
    }
    
    /**
     * @param aNOMECOMPLETO the SENHA to set
     */
    public static void setNOMECOMPLETO(String aNOMECOMPLETO) {
        NOMECOMPLETO = aNOMECOMPLETO;
    }

}
