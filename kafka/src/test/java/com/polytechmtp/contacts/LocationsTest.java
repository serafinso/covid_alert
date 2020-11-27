package com.polytechmtp.contacts;

import com.polytechmtp.kafka.controllers.LocationsController;
import com.polytechmtp.kafka.models.Location;
import com.polytechmtp.kafka.repositories.LocationRepository;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
class LocationsTests {

    private MockMvc mockMvc;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationsController locationsController;



    /*@BeforeEach
    public void setup() {
        System.out.println("Passe dans le setup");
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(locationsController).build();
    }


    @Test
    public void testList() throws Exception {
        List<Location> locations = new ArrayList<Location>();
        locations.add(new Location());
        locations.add(new Location());

        when(locationRepository.findAll()).thenReturn((List) locations);
        mockMvc.perform(get("/locations")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    /*@Test
    public void testGetOne() throws Exception {
        long id = 1;
        Location location = new Location(
                (long)1,
                (long)1,
                (long)1,
                new Timestamp(Long.getLong("28/08/1998"))
        );

        when(locationRepository.getOne(id)).thenReturn(location);
        when(locationRepository.findById(id)).thenReturn(java.util.Optional.of(location));
        mockMvc.perform(get("/locations/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))

                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(jsonPath("$.*", isA(JSONArray.class)))
                .andExpect(jsonPath("$.location_id", is(1)))
                .andExpect(jsonPath("$.latitude", is(1)))
                .andExpect(jsonPath("$.longitude", is(1)));
    }


    @Test
    public void createlocation() throws Exception {


        Location location = new Location(
                (long)1,
                (long)1,
                (long)1,
                new Timestamp(Long.getLong("28/08/1998"))
        );

        String locationString = "{ " +
                "\"latitude\":1, " +
                "\"longitude\":1, " +
                "\"location_date\":904255200000 " +
                "}";

        System.out.println(locationString);
        mockMvc.perform(post("/locations")
                .content(locationString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }




    @Test
    public void deleteLocation() throws Exception {
        long id = 1;
        Location location = new Location(
                (long)1,
                (long)1,
                (long)1,
                new Timestamp(Long.getLong("28/08/1998"))        );

        when(locationRepository.findById(id)).thenReturn(java.util.Optional.of(location));

        mockMvc.perform(delete("/locations/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void updateOne() throws Exception {
        long id = 1;
        Location location = new Location(
                (long)1,
                (long)1,
                (long)1,
                new Timestamp(Long.getLong("28/08/1998"))        );

        String locationString = "{ " +
                "\"latitude\":1, " +
                "\"longitude\":1, " +
                "\"location_date\":904255200000 " +
                "}";

        System.out.println(locationString);
        when(locationRepository.findById(id)).thenReturn(java.util.Optional.of(location));
        when(locationRepository.getOne(id)).thenReturn(location);
        mockMvc.perform(put("/locations/1")
                .content(locationString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        )
                .andDo(print())
                .andExpect(status().isOk());
    }*/

}
