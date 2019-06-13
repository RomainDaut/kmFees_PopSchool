package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="Users.findUserByEmail&Password",
                query = "select u from Users u where lower(u.email)=:email and u.password=:password "),
        @NamedQuery(name = "Users.findByEmail", query = "select u from Users u where u.email=:email"),
        @NamedQuery(name = "Users.findAllUsers", query = "select u from Users u")
})


public class Users implements Serializable {
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int admin;
    private Collection<Address> addresses;
    private Collection<Vehicules> vehicules;

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "admin")
    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return idUser == users.idUser &&
                admin == users.admin &&
                Objects.equals(name, users.name) &&
                Objects.equals(surname, users.surname) &&
                Objects.equals(email, users.email) &&
                Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, surname, email, password, admin);
    }

    @OneToMany(mappedBy = "users")
    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(mappedBy = "users")
    public Collection<Vehicules> getVehicules() {
        return vehicules;
    }

    public void setVehicules(Collection<Vehicules> vehicules) {
        this.vehicules = vehicules;
    }
}
