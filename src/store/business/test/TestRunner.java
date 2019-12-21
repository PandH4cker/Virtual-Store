package store.business.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(VirtualStoreTestSuite.class);
        for(Failure f : result.getFailures())
            System.out.println(f.toString());

        if(result.wasSuccessful())
            System.out.println("The tests were successful and terminated in " + result.getRunTime() + " millisecondes");
    }
}
