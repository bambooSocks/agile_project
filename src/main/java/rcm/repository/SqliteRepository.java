package rcm.repository;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;

public class SqliteRepository implements Repository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Database");
    EntityManager em = emf.createEntityManager();

    @Override
    public void createLogisticsCompany(LogisticsCompany po) throws IOException {
        withinTransaction(() -> {
            em.persist(po);
        });
    }

    @Override
    public LogisticsCompany readLogisticsCompany(String key) throws IOException {
        try {
            TypedQuery<LogisticsCompany> query = em
                    .createQuery("SELECT u FROM LogisticsCompany u WHERE u.email=:key", LogisticsCompany.class)
                    .setParameter("key", key);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

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
            em.createQuery("DELETE FROM LogisticsCompany").executeUpdate();
            em.createQuery("DELETE FROM Container").executeUpdate();
            em.createQuery("DELETE FROM Client").executeUpdate();
            em.createQuery("DELETE FROM Journey").executeUpdate();
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

    @Override
    public void createClient(Client po) throws IOException {
        withinTransaction(() -> {
            em.persist(po);
        });
    }

    @Override
    public Client readClient(String key) throws IOException {
        try {
            TypedQuery<Client> query = em.createQuery("SELECT u FROM Client u WHERE u.email=:key", Client.class)
                    .setParameter("key", key);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void createJourney(Journey po) throws IOException {
        withinTransaction(() -> {
            em.persist(po);
        });

    }

    @Override
    public Journey readJourney(int key) throws IOException {
        try {
            TypedQuery<Journey> query = em.createQuery("SELECT u FROM Journey u WHERE u.id=:key", Journey.class)
                    .setParameter("key", key);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updateCompany(LogisticsCompany logisticsCompany) {
        withinTransaction(() -> {
            em.merge(logisticsCompany);
        });
    }

}
