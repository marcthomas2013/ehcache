package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

/**
 * Created by marc.thomas on 26/08/2015.
 *
 * Blog article http://mtdevuk.com/2015/08/27/cachable-usage-and-gotchas/
 */
@Controller
public class MyController implements CommandLineRunner {

    @Autowired
    private MyService myService;

    @Override
    public void run(String... args) throws Exception {
        callServiceDirect();

        callFromObjectWithDependencyPassedIn();

        callFromObjectCreatedByTheService();

        callingPrivateCacheMethodFromWithinService();

        callingCacheMethodFromWithinService();
    }

    private void callServiceDirect() {
        System.out.println("callServiceDirect - Calling the service directly");
        System.out.println(myService.getURLResponse("http://test.local/"));
        System.out.println(myService.getURLResponse("http://test.local/"));
    }

    private void callFromObjectWithDependencyPassedIn() {
        System.out.println("callFromObjectWithDependencyPassedIn - Calling the service from another object with the dependency passed in.");
        ObjectThatCallsService objectThatCallsService = new ObjectThatCallsService(myService);
        objectThatCallsService.testService();
        objectThatCallsService.testService();
    }

    private void callFromObjectCreatedByTheService() {
        System.out.println("callFromObjectCreatedByTheService - Calling the service from another object, with the dependency passed in as this, demonstrating that this doesn't work.");
        ObjectThatCallsService finCalcFga = myService.createObjectThatCallsService();
        finCalcFga.testService();
        finCalcFga.testService();
    }

    private void callingCacheMethodFromWithinService() {
        System.out.println("callingCacheMethodFromWithinService - Demo where calling a public @Cachable method within the class it is defined won't use the cache");
        System.out.println(myService.callCachedMethodInternally());
        System.out.println(myService.callCachedMethodInternally());
    }

    private void callingPrivateCacheMethodFromWithinService() {
        System.out.println("callingPrivateCacheMethodFromWithinService - Demo where calling a private @Cachable method within the class it is defined won't use the cache");
        System.out.println(myService.callPrivateCachedMethod());
        System.out.println(myService.callPrivateCachedMethod());
    }
}
