package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="findAllAddressByUser", query = "select a from Address a where a.users= :user "),
})
public class Address implements Serializable {
    private int idAddress;
    private String numberStreet;
    private String postcode;
    private String city;
    private Users users;
    private Collection<Journeys> journeysA;
    private Collection<Journeys> journeysB;

    @Id
    @Column(name = "idAddress")
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    @Basic
    @Column(name = "numberStreet")
    public String getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(String numberStreet) {
        this.numberStreet = numberStreet;
    }

    @Basic
    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return idAddress == address.idAddress &&
                Objects.equals(numberStreet, address.numberStreet) &&
                Objects.equals(postcode, address.postcode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, numberStreet, postcode, city);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "addressA")
    public Collection<Journeys> getJourneysA() {
        return journeysA;
    }

    public void setJourneysA(Collection<Journeys> journeysA) {
        this.journeysA = journeysA;
    }

    @OneToMany(mappedBy = "addressB")
    public Collection<Journeys> getJourneysB() {
        return journeysB;
    }

    public void setJourneysB(Collection<Journeys> journeysB) {
        this.journeysB = journeysB;
    }

    public Address() {
    }

    public Address(String numberStreet, String postcode, String city) {
        this.numberStreet = numberStreet;
        this.postcode = postcode;
        this.city = city;
    }
}
