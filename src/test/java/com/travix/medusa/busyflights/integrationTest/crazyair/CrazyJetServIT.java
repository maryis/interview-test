package com.travix.medusa.busyflights.integrationTest.crazyair;

import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.service.CrazyAirServ;
import com.travix.medusa.busyflights.service.ToughJetServ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CrazyJetServIT {

    @Autowired
    CrazyAirServ crazyAirServ;

    @Autowired
    Request request;

    @Before
    public void initialize(){


        request.setNumberOfPassengers(3);
        request.setDepartureDate("2019-03-04");
        request.setReturnDate("2019-03-05");
        request.setOrigin("DP1");
        request.setDestination("DA1");

    }

    @Test
    public void testFindParam(){

        List<Response> result= crazyAirServ.findByParam(request);

        Assert.assertEquals(10,result.size());



    }
}