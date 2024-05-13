package org.example.resource;

import com.codahale.metrics.annotation.Timed;
import org.example.entity.TestEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private TestEntity testEntity;

    public HelloResource(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    // example for non-stateless resource class
    public static int staticInt = 1;
    public int number = 1;

    @GET
    @Timed
    public List<String> sayHello() {
        return testEntity.getRowFromTest();
    }
}
