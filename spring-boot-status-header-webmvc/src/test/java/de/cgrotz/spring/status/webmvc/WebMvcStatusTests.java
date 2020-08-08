package de.cgrotz.spring.status.webmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class WebMvcStatusTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testControllers() throws Exception {
        mvc.perform(get("/no_status"))
                .andExpect(status().isOk())
                .andExpect(header().doesNotExist("Status"));

        mvc.perform(get("/deprecated"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "DEPRECATED"));

        mvc.perform(get("/alpha"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "ALPHA"));

        mvc.perform(get("/beta"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "BETA"));

        mvc.perform(get("/alphaWithAdditionalInfo"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "ALPHA"))
                .andExpect(header().string("Status-Info", "Beta on 01.01.2021"));
    }

    @Test
    public void testControllersClass() throws Exception {
        mvc.perform(get("/controller2/hello"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "DEPRECATED"));

        mvc.perform(get("/controller3/hello"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "ALPHA"));

        mvc.perform(get("/controller4/hello"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "BETA"));
    }

    @Test
    public void testControllersMixed() throws Exception {
        mvc.perform(get("/controller5/no_status"))
                .andExpect(status().isOk())
                .andExpect(header().string("Status", "DEPRECATED"));

        mvc.perform(get("/controller5/alpha"))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Status",  "ALPHA","DEPRECATED"));

        mvc.perform(get("/controller5/beta"))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Status", "BETA","DEPRECATED"));

        mvc.perform(get("/controller5/alphaWithAdditionalInfo"))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Status","ALPHA", "DEPRECATED"))
                .andExpect(header().string("Status-Info", "Beta on 01.01.2021"));
    }
}
