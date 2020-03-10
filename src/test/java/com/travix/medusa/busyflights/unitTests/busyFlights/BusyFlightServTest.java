package com.travix.medusa.busyflights.unitTests.busyFlights;

import com.travix.medusa.busyflights.BusyFlightsApplication;
import com.travix.medusa.busyflights.domain.*;
import com.travix.medusa.busyflights.service.BusyFlightServ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
//to test it, we should start the real app, because it gather data from two API
//and here there is no mvc up
public class BusyFlightServTest {

    @Autowired
    BusyFlightServ busyFlightServ;

    @Autowired
    Request request;

    @Before
    public void initialize() {


        System.out.println("make sure the main app is up");

        request.setNumberOfPassengers(3);
        request.setDepartureDate("2019-03-04");
        request.setReturnDate("2019-03-05");
        request.setOrigin("DP1");
        request.setDestination("DA1");
    }

    @Test
    public void testFindAllByParam() {

        List<BusyFlightsResponse> list=busyFlightServ.findByParam(request);
        Assert.assertEquals(list.size(), 20);

//        list.stream().forEach(i->
//                System.out.println(i.toString()));

    }

    @Test
    public void findToughtByParam() {

        List<ToughJetResponse> list=busyFlightServ.findToughByParam(request);
        Assert.assertEquals(list.size(), 10);

//        list.stream().forEach(i->
//                System.out.println(i.toString()));

    }

    @Test
    public void findCrazyByParam() {

        List<CrazyAirResponse> list=busyFlightServ.findCrazyByParam(request);
        Assert.assertEquals(list.size(), 10);

//        list.stream().forEach(i->
//                System.out.println(i.toString()));

    }
}