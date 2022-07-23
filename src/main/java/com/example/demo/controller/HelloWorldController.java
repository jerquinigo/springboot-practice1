package com.example.demo.controller;

import com.example.demo.dao.ImplDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class HelloWorldController {

    @Autowired
    ImplDao dao;

    @GetMapping(value="/hello")
    public String getGreeting(){
        return dao.displayGreeting();
    }
    @GetMapping(value="/second")
    public String method2() {
        return "this is a string 2 ";
    }

    @GetMapping(value="/get")
    //this will display as api on screen
    public ResponseEntity<JsonNode> method3() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet("https://dog.ceo/api/breeds/image/random");
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine());

            HttpEntity entity = response.getEntity();
            System.out.println(entity);
            if(entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);

                JsonNode mainNode = null;
                ObjectMapper objMapper = new ObjectMapper();

                mainNode = objMapper.readTree(result);
                System.out.println(mainNode.path("message"));
                //this will show the api that is avaiable and then you can key into the values if needed
                return ResponseEntity.ok(mainNode);
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
