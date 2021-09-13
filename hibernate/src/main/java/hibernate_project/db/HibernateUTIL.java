package hibernate_project.db;

import hibernate_project.entity.Address;
import hibernate_project.entity.City;
import hibernate_project.entity.Country;
import hibernate_project.entity.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateUTIL {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private final static Logger logger = LoggerFactory.getLogger(HibernateUTIL.class);
    private Session session = getSessionFactory().openSession();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        logger.debug("SessionFactory: getSessionFactory");
        return sessionFactory;
    }

    public static void shutdown() {
        logger.debug("Shutdown session");
        getSessionFactory().close();
    }

    public void saveAddressesToDB(ArrayList<Address> addresses) {
        session.beginTransaction();
        for (Address address : addresses) {
            session.save(address);
        }
        session.getTransaction().commit();
    }

    public List<City> selectAllFromCityTable() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> rootEntity = cq.from(City.class);
        CriteriaQuery<City> all = cq.select(rootEntity);

        TypedQuery<City> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Country> selectAllFromCountriesTable() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> rootEntity = cq.from(Country.class);
        CriteriaQuery<Country> all = cq.select(rootEntity);

        TypedQuery<Country> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Region> selectAllFromRegionsTable() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Region> cq = cb.createQuery(Region.class);
        Root<Region> rootEntity = cq.from(Region.class);
        CriteriaQuery<Region> all = cq.select(rootEntity);

        TypedQuery<Region> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public List<City> selectByIndexFromCity(int index) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> rootEntity = cq.from(City.class);
        CriteriaQuery<City> byIndex = cq.where(cb.equal(rootEntity.get("index"), index));
        TypedQuery<City> allQuery = session.createQuery(byIndex);
        return allQuery.getResultList();
    }

    public List<City> selectByNameFromCity(String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> rootEntity = cq.from(City.class);
        CriteriaQuery<City> byName = cq.where(cb.like(rootEntity.get("name"), "%" + name + "%"));
        TypedQuery<City> allQuery = session.createQuery(byName);
        return allQuery.getResultList();
    }

    public List<Country> selectByIndexFromCountry(int index) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> rootEntity = cq.from(Country.class);
        CriteriaQuery<Country> byIndex = cq.where(cb.equal(rootEntity.get("index"), index));
        TypedQuery<Country> allQuery = session.createQuery(byIndex);
        return allQuery.getResultList();
    }

    public List<Country> selectByNameFromCountry(String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> rootEntity = cq.from(Country.class);
        CriteriaQuery<Country> byName = cq.where(cb.like(rootEntity.get("name"), "%" + name + "%"));
        TypedQuery<Country> allQuery = session.createQuery(byName);
        return allQuery.getResultList();
    }

    public List<Region> selectByIndexFromRegion(int index) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Region> cq = cb.createQuery(Region.class);
        Root<Region> rootEntity = cq.from(Region.class);
        CriteriaQuery<Region> byIndex = cq.where(cb.equal(rootEntity.get("index"), index));
        TypedQuery<Region> allQuery = session.createQuery(byIndex);
        return allQuery.getResultList();
    }

    public List<Region> selectByNameFromRegion(String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Region> cq = cb.createQuery(Region.class);
        Root<Region> rootEntity = cq.from(Region.class);
        CriteriaQuery<Region> byName = cq.where(cb.like(rootEntity.get("name"), "%" + name + "%"));
        TypedQuery<Region> allQuery = session.createQuery(byName);
        return allQuery.getResultList();
    }
}
