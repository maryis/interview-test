package com.travix.medusa.busyflights.unitTests.toughjet;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.repository.ToughJetRepo;
import com.travix.medusa.busyflights.service.ToughJetServ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ToughJetServTest {

    @Autowired
    ToughJetServ toughJetServ;

    @MockBean
    ToughJetRepo toughJetRepo;

    @Autowired
    Request request;

    List<ToughJetResponse> list;
    int num;

    @Before
    public void initialize(){

        num=4;

        request.setNumberOfPassengers(num);
        request.setDepartureDate("2019-03-04");
        request.setReturnDate("2019-03-05");
        request.setOrigin("DP1");
        request.setDestination("DA1");

        list=new ArrayList<>();
        ToughJetResponse entity=new ToughJetResponse();
        entity.setTax(2);
        entity.setDiscount(5);
        entity.setBasePrice(20);
        entity.setDepartureAirportName("GGG");
        entity.setArrivalAirportName("LLL");
        list.add(entity);

    }

    @Test
    public void testfindParam(){

        Mockito.when(toughJetRepo.findByParam(any())).thenReturn(list);
        List<ToughJetResponse> result= toughJetServ.findByParam(request);

        Assert.assertEquals(1,result.size());
        Assert.assertEquals(list.get(0).getBasePrice(), result.get(0).getBasePrice(),0);
        Assert.assertEquals("not equal arrival",list.get(0).getArrivalAirportName(), result.get(0).getArrivalAirportName());
//
//        for (int i=0;i<list.size();i++){
//            System.out.println("list:"+ list.get(i).getArrivalAirportName()+":"+list.get(i).getCarrier());
//            System.out.println("result:"+ result.get(i).getArrivalAirportName()+":"+result.get(i).getCarrier());
//        }


    }
}