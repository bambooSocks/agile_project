package rcm;

import java.time.LocalDateTime;

public abstract class TimeStamp {

    protected LocalDateTime timestamp;

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
