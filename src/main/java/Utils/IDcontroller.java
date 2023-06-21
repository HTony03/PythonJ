package Utils;

import java.util.HashSet;
import java.util.Random;

public class IDcontroller {
    HashSet<Long> IDs = new HashSet<>();

    Random random;

    public IDcontroller(){
        random = new Random();
    }

    public long getNewID(){
        long id = random.nextLong();
        while(IDs.contains(id)){
            id = random.nextLong();
        }
        IDs.add(id);
        return id;
    }

    public void removeID(long id){
        IDs.remove(id);
    }
}
