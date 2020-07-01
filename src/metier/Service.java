package metier;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//region Singleton
public class Service implements Serializable {
    private static Service service = null;

    private Service() {
    }

    public static Service getSingleton() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }
//endregion

    public void createUser(Users user) {
        EntityManager em = JPAUtil.getEm();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void removeUser(Users user) {
        EntityManager em = JPAUtil.getEm();
        em.getTransaction().begin();
        em.remove(em.merge(user));
        em.getTransaction().commit();
        em.close();
    }

    public void updateUser(Users user) {
        EntityManager em = JPAUtil.getEm();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    public void createAddress(Address address) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(em.merge(address));
        em.getTransaction().commit();
        em.close();
    }

    public void removeAddress(Address address) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(address));
        em.getTransaction().commit();
        em.close();
    }

    public void createVehicule(Vehicules vehicule) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(vehicule);
        em.getTransaction().commit();
        em.close();
    }

    public void removeVehicule(Vehicules vehicule) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(vehicule));
        em.getTransaction().commit();
        em.close();
    }

    public void createJourney(Journeys journey) {
        EntityManager em = JPAUtil.getEm();
        em.getTransaction().begin();
        em.persist(journey);
        em.getTransaction().commit();
        em.close();
    }

    public void removeJourney(Journeys journey) {
        EntityManager em = JPAUtil.getEm();
        em.getTransaction().begin();
        em.remove(em.merge(journey));
        em.getTransaction().commit();
        em.close();
    }

    public List<Users> findAllUsers() {
        EntityManager em = JPAUtil.getEm();
        List usersall = new ArrayList();
        usersall = em.createNamedQuery("Users.findAllUsers", Users.class).getResultList();
        em.close();
        return usersall;
    }

    public Users findUserbyEmailandPass(String email, String password) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        Users user = em.createNamedQuery("Users.findUserByEmail&Password", Users.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        em.close();
        return user;
    }

    public Users findAllUsersPass(String password) {
        EntityManager em = JPAUtil.getEm();
        Users user = em.createNamedQuery("Users.findAllUsersPassword", Users.class)
                .setParameter("password", password)
                .getSingleResult();
        em.close();
        return user;
    }

    public List<Address> findAllAddressByUser(Users user) {
        EntityManager em = JPAUtil.getEm();
        List<Address> address = em.createNamedQuery("findAllAddressByUser", Address.class)
                .setParameter("user", user)
                .getResultList();
        em.close();
        return address;
    }

    public List<Vehicules> findAllVehiByUser(Users user) {
        EntityManager em = JPAUtil.getEm();
        List<Vehicules> vehicules = em.createNamedQuery("findAllVehiculesByUser", Vehicules.class)
                .setParameter("user", user)
                .getResultList();
        em.close();
        return vehicules;
    }

    public Address AddressJourney(int idAddress) {
        EntityManager em = JPAUtil.getEm();
        Address ad = em.find(Address.class, idAddress);
        em.close();
        return ad;
    }

    public Vehicules VehiculeJourney(int idVehicule) {
        EntityManager em = JPAUtil.getEm();
        Vehicules veJ = em.find(Vehicules.class, idVehicule);
        em.close();
        return veJ;
    }

}