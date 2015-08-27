package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

/**
 * Created by marc.thomas on 26/08/2015.
 */
@Controller
public class MyController implements CommandLineRunner {

    @Autowired
    private MyService myService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Calling the service directly");
        System.out.println(myService.getURLResponse("http://test.local/"));
        System.out.println(myService.getURLResponse("http://test.local/"));

        System.out.println("Calling the service from another object with the dependency passed in.");
        ObjectThatCallsService objectThatCallsService = new ObjectThatCallsService(myService);
        objectThatCallsService.testService();
        objectThatCallsService.testService();

        System.out.println("Calling the service from another object, with the dependency passed in as this, demonstrating that this doesn't work.");
        ObjectThatCallsService finCalcFga = myService.createObjectThatCallsService();
        finCalcFga.testService();
        finCalcFga.testService();

        System.out.println("Demo where calling a public @Cachable method within the class it is defined won't use the cache");
        System.out.println(myService.callCachedMethodInternally());
        System.out.println(myService.callCachedMethodInternally());
    }
}
