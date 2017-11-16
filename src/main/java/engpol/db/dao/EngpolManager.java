package engpol.db.dao;

import engpol.db.model.Engpol;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class EngpolManager {
    private final SessionFactory factory;

    public EngpolManager() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public void insert(Engpol engpol) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.persist(engpol);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public  Engpol find(long id) {
        Session session = factory.openSession();
        Engpol engpol = session.find(Engpol.class, id);
        session.close();
        factory.close();
        return engpol;
    }

    public void delete(Engpol engpol) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(engpol);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public List<Engpol> getAllEngpols() {
        String query = "select e from Engpol e";
        Session session = factory.openSession();
        Query<Engpol> result = session.createQuery(query,Engpol.class);
        List<Engpol> engpols = result.getResultList();
        session.close();
        factory.close();
        return engpols;
    }
    public List<Engpol> getEngpolsByEngWord(String engWord) {
        //TODO: Do it better
        String query = "select e from Engpol e where e.engWord like :engWord";
        Session session = factory.openSession();
        Query<Engpol> result = session.createQuery(query,Engpol.class).setParameter("engWord", "%"+engWord+"%");
        List<Engpol> engpols = result.getResultList();
        session.close();
        factory.close();
        return engpols;
    }
}
