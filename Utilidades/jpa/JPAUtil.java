package jpa;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utilidades.DB_Bean;
import utilidades.JOPM;

public class JPAUtil implements Serializable {
    
    public static EntityManager em() { EntityManager em = null; try{

            final String PERSISTENCE_UNIT_NAME = "todos";
            EntityManagerFactory factory;
        
            Map pmap = new HashMap();
            pmap.put("javax.persistence.jdbc.driver", DB_Bean.driverAlone );  
            pmap.put("javax.persistence.jdbc.url", DB_Bean.urlAlone );  
            pmap.put("javax.persistence.jdbc.user", DB_Bean.usuario );  
            pmap.put("javax.persistence.jdbc.password", DB_Bean.senha );
            pmap.put("eclipselink.jdbc.native-sql", "true");
            //pmap.put("eclipselink.logging.level", "FINE");
            factory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME,pmap);  
            em = factory.createEntityManager();   
                                    
        } catch( Exception e ){ JOPM JOPM = new JOPM( 2, "emUtil(), \n"
                + e.getMessage() + "\n", "JPAUtil" ); }
    
        return em;
    }
}