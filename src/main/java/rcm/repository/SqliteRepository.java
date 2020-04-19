package rcm.repository;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import rcm.Container;
import rcm.LogisticsCompany;


public class SqliteRepository implements Repository {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Database");
	EntityManager em = emf.createEntityManager();
/*
	@Override
	public void createUser(LogisticsCompany po) throws IOException {
		withinTransaction(() -> {
			em.persist(po);
		});
	}

	@Override
	public LogisticsCompany readUser(int key) throws IOException {
		try {
			TypedQuery<LogisticsCompany> query = em.createQuery("SELECT u FROM LogisticsCompany u WHERE u.Id=:key", LogisticsCompany.class)
					.setParameter("key", key);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
*/
	private void withinTransaction(UnitFunction f) {
		em.getTransaction().begin();
		f.unitFunction();
		em.getTransaction().commit();
	}

	interface UnitFunction {
		public void unitFunction();
	}

	@Override
	public void clearDatabase() {
		withinTransaction(() -> {
			//em.createQuery("DELETE FROM LogisticsCompany").executeUpdate();
			em.createQuery("DELETE FROM Container").executeUpdate();
		});
	}
	
	
	@Override
    public void createContainer(Container po) throws IOException {
        withinTransaction(() -> {
            em.persist(po);
        });
    }

    @Override
    public Container readContainer(int key) throws IOException {
        try {
            TypedQuery<Container> query = em.createQuery("SELECT u FROM Container u WHERE u.id=:key", Container.class)
                    .setParameter("key", key);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



}
