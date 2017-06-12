package com.spotippos.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spotippos.SpotipposApiBoot;
import com.spotippos.exception.PropertyNotFound;
import com.spotippos.model.Boundaries;
import com.spotippos.model.Properties;
import com.spotippos.model.Property;
import com.spotippos.service.PropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpotipposApiBoot.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class PropertiesControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private PropertiesController controller;

    @Mock
    private PropertyService service;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void salvaPropriedade() throws Exception {

        when(service.create(any(Property.class))).thenReturn(10);

        mockMvc.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"x\": 222, \"y\": 444, \"title\": \"titulo\", \"price\": 1250000, \"description\": \"descricao\", \"beds\": 4, \"baths\": 3, \"squareMeters\": 210 }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/properties/10"));
    }

    @Test
    public void erroPropriedadeSemTitulo() throws Exception {

        when(service.create(any(Property.class))).thenReturn(10);

        mockMvc.perform(MockMvcRequestBuilders.post("/properties")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"x\": 222, \"y\": 444, \"price\": 1250000, \"description\": \"descricao\", \"beds\": 4, \"baths\": 3, \"squareMeters\": 210 }"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void consultaPropriedadePorId() throws Exception {
        Property property = new Property();

        property.setId(10);
        property.setBaths(3);
        property.setBeds(4);
        property.setDescription("descricao");
        property.setPrice(140000.0);
        property.setProvinces(Arrays.asList("A", "B"));
        property.setSquareMeters(180);
        property.setTitle("titulo");
        property.setX(900);
        property.setY(1000);

        when(service.findBy(10)).thenReturn(property);

        MockHttpServletRequestBuilder call = MockMvcRequestBuilders.get("/properties/10");

        mockMvc.perform(call).andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.baths", Matchers.is(3)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.beds", Matchers.is(4)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("descricao")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(140000.0)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.provinces", Matchers.hasSize(2)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.provinces[0]", Matchers.is("A")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.provinces[1]", Matchers.is("B")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.squareMeters", Matchers.is(180)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("titulo")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.x", Matchers.is(900)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.y", Matchers.is(1000)));

    }

    @Test
    public void erroAoConsultaPropriedadePorId() throws Exception {

        doThrow(PropertyNotFound.class).when(service).findBy(10);

        MockHttpServletRequestBuilder call = MockMvcRequestBuilders.get("/properties/10");

        mockMvc.perform(call).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void consultaPropriedadePorArea() throws Exception {
        Property property = new Property();

        property.setId(10);
        property.setBaths(3);
        property.setBeds(4);
        property.setDescription("descricao");
        property.setPrice(140000.0);
        property.setProvinces(Arrays.asList("A", "B"));
        property.setSquareMeters(180);
        property.setTitle("titulo");
        property.setX(900);
        property.setY(1000);

        List<Property> propertiesList = Arrays.asList(property, property);

        Properties properties = new Properties(propertiesList);

        when(service.findBy(any(Boundaries.class))).thenReturn(properties);

        MockHttpServletRequestBuilder call = MockMvcRequestBuilders.get("/properties")
                                                                   .param("ax", "10")
                                                                   .param("ay", "50")
                                                                   .param("bx", "30")
                                                                   .param("by", "10");

        mockMvc.perform(call).andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.totalProperties", Matchers.is(2)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties", Matchers.hasSize(2)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].id", Matchers.is(10)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].baths", Matchers.is(3)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].beds", Matchers.is(4)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].description", Matchers.is("descricao")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].price", Matchers.is(140000.0)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].provinces", Matchers.hasSize(2)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].provinces[0]", Matchers.is("A")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].provinces[1]", Matchers.is("B")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].squareMeters", Matchers.is(180)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].title", Matchers.is("titulo")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].x", Matchers.is(900)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].y", Matchers.is(1000)));

    }

    @Test
    public void erroAoConsultaPropriedadePorArea() throws Exception {

        doThrow(PropertyNotFound.class).when(service).findBy(any(Boundaries.class));

        MockHttpServletRequestBuilder call = MockMvcRequestBuilders.get("/properties")
                .param("ax", "10")
                .param("ay", "50")
                .param("bx", "30")
                .param("by", "10");


        mockMvc.perform(call).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
