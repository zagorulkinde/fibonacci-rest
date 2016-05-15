package com.emc;


import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Arrays.asList;

@Path("/{number}")
public class Main {

  @GET()
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public Response getIt(@PathParam("number") long number) {
    return Response.status(200).entity(new ObjectMapper(number, asList(1L, 2L, 3L))).build();
  }

  @SuppressWarnings("unused")
  public static class ObjectMapper implements Serializable {

    @JsonProperty
    private long number;
    @JsonProperty
    private List<Long> results;

    public ObjectMapper(long number, List<Long> results) {
      this.number = number;
      this.results = results;
    }

    public long getNumber() {
      return number;
    }

    public void setNumber(long number) {
      this.number = number;
    }

    public List<Long> getResults() {
      return results;
    }

    public void setResults(List<Long> results) {
      this.results = results;
    }

    /*@Override
    public String toString() {
      return "{number:" +
          number +
          "}:" +
          "[" +
          results +
          "]";
    }*/

  }


}