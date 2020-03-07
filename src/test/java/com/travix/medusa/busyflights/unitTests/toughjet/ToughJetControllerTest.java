package com.travix.medusa.busyflights.unitTests.toughjet;

import com.travix.medusa.busyflights.controller.ToughJetController;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ToughJetController.class)
public class ToughJetControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;


    @MockBean
    ToughJetServ toughJetServ;

    List<ToughJetResponse> list;
    int num;

    @Before
    public void initialize() {
        list = new ArrayList<>();
        ToughJetResponse entity = new ToughJetResponse();
        entity.setTax(2);
        entity.setDiscount(5);
        entity.setBasePrice(20);
        entity.setDepartureAirportName("GGG");
        entity.setArrivalAirportName("LLL");
        list.add(entity);

    }

    @Test
    public void testGetFlights() {

        Mockito.when(toughJetServ.findByParam(any())).thenReturn(list);

        try {
            mockMvc.perform(
                    get("/tough-jet-management/flight")
                            .param("from", "DP1")
                            .param("to", "DA1")
                            .param("inboundDate", "2019-03-05")
                            .param("outboundDate", "2019-03-04")
                            .param("numberOfAdults", "3")

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


        Mockito.when(toughJetServ.findByParam(any())).thenReturn(list);
        try {
            mockMvc.perform(
                    get("/tough-jet-management/flight")
                            .param("from", "DP1")
                            .param("to", "DA1")
                            .param("inboundDate", "2019-3-05")
                            .param("outboundDate", "2019-3-04")
                            .param("numberOfAdults", "3")
                            .with(httpBasic(username, password)))
                    .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetFlightsNoAuth() {


        Mockito.when(toughJetServ.findByParam(any())).thenReturn(list);
        try {
            mockMvc.perform(
                    get("/tough-jet-management/flight")
                            .param("from", "DP1")
                            .param("to", "DA1")
                            .param("inbounddate", "2012-3-04")
                            .param("outbounddate", "2012-03-05")
                            .param("num", "3")
            )
                    .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
//                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}