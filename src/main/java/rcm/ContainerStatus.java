package rcm;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ContainerStatus {

    @Id
    private LocalDateTime timestamp;
    @Column
    private double temperature;
    @Column
    private double humidity;
    @Column
    private double atmPressure;
    @ManyToOne
    private Journey journey;
    @Column
    private String location;

    private ContainerStatus() {

    }

    /**
     * 
     * Container status constructor
     * 
     * 
     * 
     * @param timestamp   LocalDateTime with at least minute precision denoting the
     * 
     *                    time at which the measurement took place
     * 
     * @param temperature Double of the temperature in the container at the given
     * 
     *                    time
     * 
     * @param humidity    Double of the humidity in the container at the given time
     * 
     * @param atmPressure Double of the air pressure in the container at the given
     * 
     *                    time
     * 
     */
    public ContainerStatus(LocalDateTime timestamp, double temperature, double humidity, double atmPressure,
            String location) {

        this.timestamp = timestamp;

        this.temperature = temperature;

        this.humidity = humidity;

        this.atmPressure = atmPressure;

        this.location = location;

    }

    /**
     * Override of hashCode method to check all fields
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(atmPressure);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(humidity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        temp = Double.doubleToLongBits(temperature);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        return result;
    }

    /**
     * Override of equals method to check all fields
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContainerStatus other = (ContainerStatus) obj;
        if (Double.doubleToLongBits(atmPressure) != Double.doubleToLongBits(other.atmPressure))
            return false;
        if (Double.doubleToLongBits(humidity) != Double.doubleToLongBits(other.humidity))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (Double.doubleToLongBits(temperature) != Double.doubleToLongBits(other.temperature))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

    /**
     * Getter for the time stamp
     * 
     * @return LocalDateTime of the time stamp of the Container Status
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getAtmPressure() {
        return atmPressure;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public String getLocation() {
        return location;
    }

}