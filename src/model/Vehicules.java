package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllVehicules", query = "select v from Vehicules v"),
        @NamedQuery(name = "findAllVehByBrand", query = "select v from Vehicules v where upper(v.brand)=:brand ")
})
public class Vehicules implements Serializable {

    private int idVehicule;
    private String brand;
    private String vehiType;
    private String imma;
    private String power;
    private Collection<Journeys> journeys;
    private Users users;

    @Id
    @Column(name = "idVehicule")
    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "vehiType")
    public String getVehiType() {
        return vehiType;
    }

    public void setVehiType(String vehiType) {
        this.vehiType = vehiType;
    }

    @Basic
    @Column(name = "imma")
    public String getImma() {
        return imma;
    }

    public void setImma(String imma) {
        this.imma = imma;
    }

    @Basic
    @Column(name = "power")
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicules vehicules = (Vehicules) o;
        return idVehicule == vehicules.idVehicule &&
                Objects.equals(brand, vehicules.brand) &&
                Objects.equals(vehiType, vehicules.vehiType) &&
                Objects.equals(imma, vehicules.imma) &&
                Objects.equals(power, vehicules.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVehicule, brand, vehiType, imma, power);
    }

    @OneToMany(mappedBy = "vehicules")
    public Collection<Journeys> getJourneys() {
        return journeys;
    }

    public void setJourneys(Collection<Journeys> journeys) {
        this.journeys = journeys;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Vehicules() {
    }

    public Vehicules(String brand, String vehiType, String imma, String power) {
        this.brand = brand;
        this.vehiType = vehiType;
        this.imma = imma;
        this.power = power;
    }
}
