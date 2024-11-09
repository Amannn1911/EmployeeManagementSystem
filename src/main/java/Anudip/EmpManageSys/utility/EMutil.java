package Anudip.EmpManageSys.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMutil {
    private static EntityManagerFactory emf;
    
    static {
    	emf = Persistence.createEntityManagerFactory("studentUnit");
    }
    public static EntityManager providerEntityManager() {
		return emf.createEntityManager();
    	
    }
}
