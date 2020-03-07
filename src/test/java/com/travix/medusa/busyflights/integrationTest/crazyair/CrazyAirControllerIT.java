package com.travix.medusa.busyflights.integrationTest.crazyair;

import com.travix.medusa.busyflights.controller.CrazyAirController;
import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.service.CrazyAirServ;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CrazyAirControllerIT {

    @Autowired
    MockMvc mockMvc;


    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;

    List<CrazyAirResponse> list;
    int num;


    @Test
    public void getFlights() {

        try {
            mockMvc.perform(
                    get("/crazy-air-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "DA1")
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("passengerCount", "3")

                            .with(httpBasic(username, password)))
                    .andExpect(status().isOk())
//                    .andDo(print())
                    .andExpect(jsonPath("$", hasSize(10)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getFlightsBadParam() {

        try {
            mockMvc.perform(
                    get("/crazy-air-management/flight")
                            .param("origin", "DP331")//wrong length
                            .param("destination", "DA1")
                            .param("returnDate", "2019-03-05")
                            .param("departureDate", "2019-03-04")
                            .param("passengerCount", "3")
                            .with(httpBasic(username, password)))
                    .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getFlightsNoAuth() {

        try {
            mockMvc.perform(
                    get("/crazy-air-management/flight")
                            .param("from", "DP1")
                            .param("to", "DA1")
                            .param("inboundDate", "2019-03-05")
                            .param("outboundDate", "2019-03-04")
                            .param("passengerCount", "3")
                        )
                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}