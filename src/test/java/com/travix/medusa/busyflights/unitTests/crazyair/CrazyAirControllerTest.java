package com.travix.medusa.busyflights.unitTests.crazyair;

import com.travix.medusa.busyflights.controller.CrazyAirController;
import com.travix.medusa.busyflights.controller.ToughJetController;
import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.service.CrazyAirServ;
import com.travix.medusa.busyflights.service.ToughJetServ;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CrazyAirController.class)
public class CrazyAirControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;


    @MockBean
    CrazyAirServ crazyAirServ;

    List<CrazyAirResponse> list;
    int num;

    @Before
    public void initialize() {
        list=new ArrayList<>();
        CrazyAirResponse entity=new CrazyAirResponse();
        entity.setPrice(33);
        entity.setAirline("Crazy");
        entity.setDepartureAirportCode("DFG");
        entity.setDestinationAirportCode("GGG");
        list.add(entity);

    }

    @Test
    public void testGetFlights() {

        Mockito.when(crazyAirServ.findByParam(any())).thenReturn(list);

        try {
            mockMvc.perform(
                    get("/crazy-air-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "DA1")
                            .param("returnDate", "2020-02-02")
                            .param("departureDate", "2020-01-02")
                            .param("passengerCount", "3")

                            .with(httpBasic(username, password)))
                    .andExpect(status().isOk())
//                    .andDo(print())
                    .andExpect(jsonPath("$", hasSize(1)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetFlightsBadParam() {


        Mockito.when(crazyAirServ.findByParam(any())).thenReturn(list);
        try {
            mockMvc.perform(
                    get("/crazy-air-management/flight")
                            .param("origin", "tyrr")//wrong
                            .param("destination", "DA1")
                            .param("returnDate", "2020-02-02")
                            .param("departureDate", "2020-01-02")
                            .param("passengerCount", "3")
                            .with(httpBasic(username, password)))
                    .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetFlightsNoAuth() {


        Mockito.when(crazyAirServ.findByParam(any())).thenReturn(list);
        try {
            mockMvc.perform(
                    get("/crazy-air-management/flight")
                            .param("origin", "DP1")
                            .param("destination", "DA1")
                            .param("returnDate", "2020-02-02")
                            .param("departureDate", "2020-01-02")
                            .param("passengerCount", "3")
            )
                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}