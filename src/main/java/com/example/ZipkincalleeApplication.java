package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class ZipkincalleeApplication {

	private static final Logger LOG = Logger.getLogger(ZipkincalleeApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ZipkincalleeApplication.class, args);
	}

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/")
    public String callHome(){
        LOG.log(Level.INFO, "Bottom will be called in Callee!");
        return restTemplate.getForObject("http://localhost:8040", String.class)+"|CALLEE";
    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

}
