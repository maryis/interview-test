package com.travix.medusa.busyflights.unitTests;

import com.travix.medusa.busyflights.utility.TimeUtiltiy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest


public class TimeUtiltiyTest {

    @Test
    public void getTime() {

        LocalDateTime t=TimeUtiltiy.getTime("2013-12-12T00:45:34Z", "ISO_INSTANT");
        Assert.assertEquals(t,LocalDateTime.parse("2013-12-12T00:45:34"));

        LocalDateTime t1=TimeUtiltiy.getTime("2013-12-12T00:45:34", "ISO_LOCAL_DATE_TIME");
        Assert.assertEquals(t1,LocalDateTime.parse("2013-12-12T00:45:34"));
    }


}