package org.stuntbum.bowlscore.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;
import org.stuntbum.bowlscore.config.AppConfig;
import org.stuntbum.bowlscore.config.MvcConfig;
import org.stuntbum.bowlscore.config.TestConfig;
import org.stuntbum.bowlscore.domain.Score;
import org.stuntbum.bowlscore.repository.ScoreRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by mikko on 23/01/16.
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class, MvcConfig.class})
public class ScoreControllerDocumentation {

    @Autowired
    private ScoreRepository mockRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    //@Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    //@Test
    public void getScoresByName() throws Exception {
        when(mockRepository.findByName("Aku")).thenReturn(new ArrayList<Score>());
        this.mockMvc.perform(get("/scores/Aku").accept("application/json"))
                .andExpect(status().isOk());
    }

}
