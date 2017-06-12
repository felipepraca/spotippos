package com.spotippos.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spotippos.SpotipposApiBoot;
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

}
