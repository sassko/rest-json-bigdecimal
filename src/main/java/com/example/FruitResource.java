package com.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    @RestClient
    FruitClient fruitClient;

    @GET
    public Fruit get() {
        Fruit fruit = new Fruit(BigDecimal.TEN);
        return fruit;
    }

    @POST
    public void add(Fruit fruit) {
        fruitClient.delegate(fruit);
    }
}
