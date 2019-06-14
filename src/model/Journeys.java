package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Journeys implements Serializable {
    private int idJourney;
    private String reasonForTravel;
    private double cost;
    private Double distance;
    private Date journeyDate;
    private Vehicules vehicules;
    private Address addressA;
    private Address addressB;
    public Journeys() {
    }
    public Journeys(String reasonForTravel, Date journeyDate, Vehicules vehicules, Address addressA, Address addressB) {
        this.reasonForTravel = reasonForTravel;
        this.journeyDate = journeyDate;
        this.vehicules = vehicules;
        this.addressA = addressA;
        this.addressB = addressB;
    }

    @Id
    @Column(name = "idJourney")
    public int getIdJourney() {
        return idJourney;
    }

    public void setIdJourney(int idJourney) {
        this.idJourney = idJourney;
    }

    @Basic
    @Column(name = "reasonForTravel")
    public String getReasonForTravel() {
        return reasonForTravel;
    }

    public void setReasonForTravel(String reasonForTravel) {
        this.reasonForTravel = reasonForTravel;
    }

    @Basic
    @Column(name = "cost", nullable = true)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "distance")
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setRandomDistance(){
        this.distance = Math.random()*240;
    }

    @Basic
    @Column(name = "JourneyDate")
    public Date getJourneyDate() {
        return journeyDate;
    }
    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journeys journeys = (Journeys) o;
        return idJourney == journeys.idJourney &&
                Double.compare(journeys.cost, cost) == 0 &&
                distance == journeys.distance &&
                Objects.equals(reasonForTravel, journeys.reasonForTravel) &&
                Objects.equals(journeyDate, journeys.journeyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJourney, reasonForTravel, cost, distance, journeyDate);
    }

    @ManyToOne
    @JoinColumn(name = "idVehicule", referencedColumnName = "idVehicule", nullable = false)
    public Vehicules getVehicules() {
        return vehicules;
    }

    public void setVehicules(Vehicules vehicules) {
        this.vehicules = vehicules;
    }

    @ManyToOne
    @JoinColumn(name = "idAddressA", referencedColumnName = "idAddress", nullable = false)
    public Address getAddressA() {
        return addressA;
    }

    public void setAddressA(Address addressA) {
        this.addressA = addressA;
    }

    @ManyToOne
    @JoinColumn(name = "idAddressB", referencedColumnName = "idAddress", nullable = false)
    public Address getAddressB() {
        return addressB;
    }

    public void setAddressB(Address addressB) {
        this.addressB = addressB;
    }

    public void costCalc(){
        double c[] = {0.451,0.518,0.543,0.568,0.595};
        double nb = 0.00;
        if(vehicules.getVehiType().equals("Car")){

            int pow = Integer.parseInt(vehicules.getPower());
             nb = c[pow-3] * this.getDistance();

        }else if(vehicules.getVehiType().equals("Motorcycle")){
            if(vehicules.getPower().equals("1,2")){
                nb = 0.338 * distance;
            }else if(vehicules.getPower().equals("3,5")){
                nb = 0.338 * distance;
            }else if(vehicules.getPower().equals("5+")){
                nb = 0.400 * distance;
            }
        }else if(vehicules.getVehiType().equals("Bicycle")){
            nb = 0.269 * distance;
        }
        this.cost = (double) Math.round(nb * 100) / 100;
        /*this.isPayed = false;*/
    }

}
