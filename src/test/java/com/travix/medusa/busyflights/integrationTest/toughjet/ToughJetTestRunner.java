package com.travix.medusa.busyflights.integrationTest.toughjet;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ToughJetTestRunner {

    public static void main(String[] args) {

        Result res= JUnitCore.runClasses(ToughJetControllerIT.class, ToughJetServIT.class);

        for(Failure f:res.getFailures()){
            System.out.println("Failure in: " + f.getTestHeader());
        }

        System.out.println("\nis all test successful?:" + res.wasSuccessful());
        System.out.println("no of tests:" +res.getRunCount());
        System.out.println("no of fails:" + res.getFailureCount());
    }
}
