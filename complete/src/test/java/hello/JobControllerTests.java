/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.Job;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JobControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private List<Job> listOfJobs = new ArrayList<Job>();
    
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
/*

    @Test
    public void noParamJobShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/jobs")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void paramJobShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/jobs").param("name", "Spring Community"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    }
*/
    
    @Test
    public void add_Jobs1() throws Exception {
       	Job job = new Job("Verificar pressão do poço", "medição do fluído jogado no poço", "* * * * * ?");
       	this.mockMvc.perform(post("/jobs")
                .contentType(JobControllerTests.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(job))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(JobControllerTests.APPLICATION_JSON_UTF8));
    }

    @Test
    public void add_Jobs2() throws Exception {
    	Job job = new Job("Verificar peso suspenso", "medição do peso da coluna satisfatório", "* * * * * ?");
    	this.mockMvc.perform(post("/jobs")
                .contentType(JobControllerTests.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(job))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(JobControllerTests.APPLICATION_JSON_UTF8));
    }

    @Test
    public void add_Jobs3() throws Exception {
    	Job job = new Job("Verificar chave hidráulica", "medição do tubos apertados e calibrados", "* * * * * ?");
    	this.mockMvc.perform(post("/jobs")
                .contentType(JobControllerTests.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(job))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(JobControllerTests.APPLICATION_JSON_UTF8));
       	
    }
    
    @Test
    public void add_Jobs4() throws Exception {
    	Job job = new Job("Verificar chave flutuante", "medição do tubos apertados e calibrados", "* * * * * ?");
    	this.mockMvc.perform(post("/jobs")
                .contentType(JobControllerTests.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(job))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(JobControllerTests.APPLICATION_JSON_UTF8));
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    
    public List<Job> mockJobs() {
    	Job job = new Job("Verificar pressão do poço", "medição do fluído jogado no poço", "* * * * *");
      	listOfJobs.add(job);
    	job = new Job("Verificar peso suspenso", "medição do peso da coluna satisfatório", "* * * * *");
      	listOfJobs.add(job);
    	job = new Job("Verificar chave hidráulica", "medição do tubos apertados e calibrados", "* * * * *");
      	listOfJobs.add(job);
    	job = new Job("Verificar chave flutuante", "medição do tubos apertados e calibrados", "* * * * *");
      	listOfJobs.add(job);

      	return listOfJobs;
    }

}
