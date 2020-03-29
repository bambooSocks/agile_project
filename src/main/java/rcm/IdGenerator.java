package rcm;

import java.util.HashMap;
import java.util.Map;

enum GroupIdType {
    USER,
    JOURNEY,
    CONTAINER
}

public class IdGenerator {
    private static IdGenerator instance;
    private Map<GroupIdType, Integer> lastIdByGroup = new HashMap<GroupIdType, Integer>();
    
    private IdGenerator() {}
    
    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }
    
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
