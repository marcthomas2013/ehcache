package demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by marc.thomas on 26/08/2015.
 */
@Service
public class MyService {
    @Cacheable(value="demoCache", key="#url")
    public String getURLResponse(String url) {
        System.out.println("This should only happen once!");
        return "Success";
    }

    public ObjectThatCallsService createFga() {
        return new ObjectThatCallsService(this);
    }

    /**
     * This method demostrates that because we are using SpringAOP in this application the
     * @Cachable annotation isn't picked
     * @return
     */
    public String callCachedMethodInternally() {
        System.out.println("This won't work because Spring AOP requires the @Cachable method to be called externally for the annotation to be picked up");
        return getURLResponse("http://test.local/");
    }
}
