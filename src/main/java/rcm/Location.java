package rcm;

import java.time.LocalDateTime;

public class Location extends TimeStamp {

    private String location;

    public Location(LocalDateTime timestamp, String location) {
        super(timestamp);
        this.location = location;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        return true;
    }

    public String getLocation() {
        return location;
    }

}
