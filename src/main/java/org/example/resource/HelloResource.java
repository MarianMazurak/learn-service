package org.example.resource;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloResource {

    private String subject;

    public HelloResource(String subject) {
        this.subject = subject;
    }

    // example for non-stateless resource class
    public static int staticInt = 1;
    public int number = 1;

    @GET
    @Timed
    public String sayHello() {
        int sum = staticInt + number;
        number++;
        staticInt++;
        System.out.println("hello");
        return subject;
    }
}
