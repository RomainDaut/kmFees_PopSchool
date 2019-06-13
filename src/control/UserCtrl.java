package control;
import metier.Service;
import model.*;
import util.JPAUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name="ctrl")
@SessionScoped
public class UserCtrl implements Serializable {
    private Users ulog;
    private List<Users> users;
    private Users newUser;
    private List<Address> address;
    private Address newAddress;
    private List<Vehicules> vehicules;
    private Vehicules newVehicule;
    private Journeys newJourney;
    private String vehiJourney;
    private String vehiType;
    private Address addressA;
    private Address addressB;
    private int idAddressA;
    private int idAddressB;
    private int idVehicule;


    public UserCtrl() {
        ulog = new Users();
    }

    //region GetterSetter

    public Users getUlog() {
        return ulog;
    }
    public void setUlog(Users ulog) {
        this.ulog = ulog;
    }
    public Address getNewAddress() {
        return newAddress;
    }
    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }
    public List<Address> getAddress() {
        return this.address = Service.getSingleton().findAllAddressByUser(ulog);
    }
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    public List<Vehicules> getVehicules() {
        return this.vehicules=Service.getSingleton().findAllVehi();
    }
    public void setVehicules(List<Vehicules> vehicules) {
        this.vehicules = vehicules;
    }
    public Vehicules getNewVehicule() {
        return newVehicule;
    }
    public void setNewVehicule(Vehicules newVehicule) {
        this.newVehicule = newVehicule;
    }
    public String getVehiType() {
        return vehiType;
    }
    public void setVehiType(String vehiType) {
        this.vehiType = vehiType;
    }
    public Journeys getNewJourney() {
        return newJourney;
    }
    public void setNewJourney(Journeys newJourney) {
        this.newJourney = newJourney;
    }
    public Address getAddressA() {
        return addressA;
    }
    public void setAddressA(Address addressA) { this.addressA = addressA;
    }
    public Address getAddressB() { return addressB;
    }
    public void setAddressB(Address addressB) { this.addressB = addressB;
    }
    public String getVehiJourney() {
        return vehiJourney;
    }
    public void setVehiJourney(String vehiJourney) {
        this.vehiJourney = vehiJourney;
    }
    public Users getNewUser() {
        return newUser;
    }
    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }
    public List<Users> getUsers() {
        return this.users = Service.getSingleton().findAllUsers();
    }
    public void setUsers(List<Users> users) {
        this.users = users;
    }
    public int getIdAddressA() {
        return idAddressA;
    }
    public void setIdAddressA(int idAddressA) {
        this.idAddressA = idAddressA;
    }
    public int getIdAddressB() {
        return idAddressB;
    }
    public void setIdAddressB(int idAddressB) {
        this.idAddressB = idAddressB;
    }
    public int getIdVehicule() {
        return idVehicule;
    }
    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }
    //endregion

    public String laAdReg(){
        return "AddressReg";
    }
    public String laVeReg(){
        return "VehiculeReg";
    }
    public String laJoReg(){
        return "JourneyReg";
    }
    public String laUsReg() {
        newUser = new Users();
        return "CreateNewUser";
    }

    public String doLogin(){
        newAddress = new Address();
        newVehicule = new Vehicules();
        newJourney = new Journeys();
        EntityManager em = JPAUtil.getEm();
        em.getTransaction().begin();
        Users u = em.createNamedQuery("Users.findUserByEmail&Password", Users.class)
                .setParameter("email", ulog.getEmail().toLowerCase())
                .setParameter("password", ulog.getPassword())
                .getSingleResult();

        if (u.getEmail().toLowerCase().equals(ulog.getEmail().toLowerCase())
                && u.getPassword().equals(ulog.getPassword())) {
            ulog = u;
            em.close();
        return "pages/UserMenu";
        }
        else if (u.getEmail().equals("") && u.getPassword().equals("")){
            em.close();
            return "/index";
        }
        else
            em.close();
        return "pages/unknownUser";
    }
    public String doAddressReg() {
        newAddress.setUsers(ulog);
        Service.getSingleton().createAddress(newAddress);
        address = Service.getSingleton().findAllAddressByUser(ulog);
        return "UserMenu";
    }
    public String dovehiReg() {
        newVehicule.setUsers(ulog);
        Service.getSingleton().createVehicule(newVehicule);
        return "UserMenu";
    }
    public String doJourneyReg(int adA, int adB, int veJ){
        Address a = Service.getSingleton().AddressAJourney(adA);
        Address b = Service.getSingleton().AddressBJourney(adB);
        Vehicules veh = Service.getSingleton().VehiculeJourney(veJ);
        newJourney.setAddressA(a);
        newJourney.setAddressB(b);
        newJourney.setVehicules(veh);
        Service.getSingleton().createJourney(newJourney);
        return "UserMenu";
    }
    public String doRegNewUser(){
        Service.getSingleton().createUser(newUser);
        return "UserMenu";
    }
    }
