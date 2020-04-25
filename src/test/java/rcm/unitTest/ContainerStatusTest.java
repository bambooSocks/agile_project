package rcm.unitTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import rcm.model.ContainerStatus;

public class ContainerStatusTest {

    @Test
    public void testHashCode() {
        LocalDateTime timestamp = LocalDateTime.of(2020, 3, 13, 4, 20);
        ContainerStatus s1 = new ContainerStatus(timestamp, 13.5, 75.0, 1.01,"New York");
        ContainerStatus s2 = new ContainerStatus(timestamp, 13.5, 75.0, 1.01,"New York");

        ContainerStatus sn1 = new ContainerStatus(null, 13.5, 75.0, 1.01,"New York");
        ContainerStatus sn2 = new ContainerStatus(null, 13.5, 75.0, 1.01,"New York");

        assertTrue(s1.hashCode() == s2.hashCode());
        assertTrue(s1.equals(s2) && s2.equals(s1));

        assertTrue(sn1.hashCode() == sn2.hashCode());
        assertTrue(sn1.equals(sn2) && sn2.equals(sn1));
    }

    @Test
    public void testEquals() {
        LocalDateTime timestamp = LocalDateTime.of(2020, 3, 13, 4, 20);
        LocalDateTime timestamp2 = LocalDateTime.of(2020, 3, 12, 4, 20);

        ContainerStatus s = new ContainerStatus(timestamp, 13.5, 75.0, 1.01, "New York");
        assertFalse(s.equals(null));
        assertFalse(s.equals(timestamp));
        assertFalse(s.equals(new ContainerStatus(timestamp, 14.0, 75.0, 1.01, "New York")));
        assertFalse(s.equals(new ContainerStatus(timestamp, 13.5, 80.0, 1.01, "New York")));
        assertFalse(s.equals(new ContainerStatus(timestamp, 13.5, 75.0, 1.05, "New York")));

        ContainerStatus sn = new ContainerStatus(null, 13.5, 75.0, 1.01, "New York");
        assertFalse(sn.equals(s));
        assertTrue(sn.equals(new ContainerStatus(null, 13.5, 75.0, 1.01, "New York")));
        ContainerStatus s2 = new ContainerStatus(timestamp2, 13.5, 75.0, 1.01, "New York");
        assertFalse(s.equals(s2));
    }

}
