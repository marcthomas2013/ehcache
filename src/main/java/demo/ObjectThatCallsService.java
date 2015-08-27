package demo;

/**
 * Created by marc.thomas on 26/08/2015.
 */
public class ObjectThatCallsService {
    private final MyService myService;

    public ObjectThatCallsService(MyService myService) {
        this.myService = myService;
    }

    public void testService() {
        System.out.println(myService.getURLResponse("test"));
    }
}
