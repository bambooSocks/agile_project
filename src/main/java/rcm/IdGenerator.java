package rcm;

import java.util.HashMap;
import java.util.Map;

enum GroupIdType {
    COMPANY, CLIENT, JOURNEY, CONTAINER
}

public class IdGenerator {
    private static IdGenerator instance;
    private Map<GroupIdType, Integer> lastIdByGroup = new HashMap<GroupIdType, Integer>();

    /**
     * Private constructor
     */
    private IdGenerator() {
    }

    /**
     * Static method used to get an instance of the singleton object
     * 
     * @return reference to the singleton object of IdGenerator
     */
    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    /**
     * Generates a unique ID for given ID group
     * 
     * @param type Type of desired ID group
     * @return New unique ID for given ID group
     */
    public int getId(GroupIdType type) {
        // read the old ID
        int oldId = lastIdByGroup.getOrDefault(type, -1);

        // generate a new ID
        int newId = oldId + 1;

        // update the ID in the map
        lastIdByGroup.put(type, newId);

        return newId;
    }

}
