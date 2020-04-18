package rcm;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

public abstract class TimeStamp {
    @Id
    protected LocalDateTime timestamp;
    
    protected TimeStamp() {


    }
    /**
     * 
     * Time stamp constructor
     * 
     * 
     * 
     * @param timestamp LocalDateTime with at least minute precision denoting the
     * 
     *                  time at which the measurement took place
     */

    public TimeStamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;

    }

    /**
     * 
     * Getter for the time stamp
     * 
     * @return LocalDateTime of the time stamp
     * 
     */

    public LocalDateTime getTimestamp() {

        return timestamp;

    }

}
