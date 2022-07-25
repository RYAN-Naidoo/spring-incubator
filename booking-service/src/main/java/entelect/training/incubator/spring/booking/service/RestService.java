package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.flight.controller.FlightsController;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.net.http.HttpHeaders;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(FlightsController.class);

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSOdN() {
        String url = "http://localhost:8202/flights";
        try{
            return this.restTemplate.getForObject(url, String.class);
        }catch (Exception e) {
            return "null";
        }
    }

    public String validateFlight(int id) {
        String url = "http://localhost:8202/flights/" + id;
        try{
            return this.restTemplate.getForObject(url, String.class);
        }catch (Exception e) {
            return "null";
        }
    }

/*    public String validateCustomer(int id) {
        String url = "http://localhost:8201/customers/" + id;
        try{
            this.restTemplate.
            return this.restTemplate.getForObject(url, String.class, );
        }catch (Exception e) {
            return "null";
        }
    }*/

    public String validateCustomer(int id) {

        try {
            String url = "http://localhost:8201/customers/" + id;

            // setup request header
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("admin", "is_a_lie");

            // build requests
            HttpEntity request = new HttpEntity(headers);

            // make request
            ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class, 1);

            String json = response.getBody();
            return  json;
        }catch (Exception e){
            return "null";
        }

    }

}
