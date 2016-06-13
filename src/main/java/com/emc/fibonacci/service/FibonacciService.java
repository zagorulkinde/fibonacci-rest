package com.emc.fibonacci.service;


import com.emc.fibonacci.FibonacciSequenceGenerator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Path("/{number}")
public class FibonacciService {
    private final Logger logger = LoggerFactory.getLogger(FibonacciService.class);
    private final static int ERROR_STATUS_CODE = 400;
    private final static int OK_STATUS_CODE = 200;
    private final List<BigInteger> accumulator = new LinkedList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response computeFibonacciNumber(@PathParam("number") long number) {

        if (number < 1) {
            logger.error("Received invalid number {}", number);
            return Response
                    .status(ERROR_STATUS_CODE)
                    .entity(ERROR_STATUS_CODE + "ERROR number must be >= 1")
                    .build();
        }

        Collection<BigInteger> results = new FibonacciSequenceGenerator(accumulator, lock).compute(number);
        logger.info("Generated results: {}", results);

        return Response.status(OK_STATUS_CODE).entity(new ObjectMapper(number, results)).build();
    }

    @SuppressWarnings("unused")
    private static class ObjectMapper implements Serializable {

        @JsonProperty
        private long number;
        @JsonProperty
        private Collection<BigInteger> results;

        ObjectMapper(long number, Collection<BigInteger> results) {
            this.number = number;
            this.results = results;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }

        public Collection<BigInteger> getResults() {
            return results;
        }

        public void setResults(List<BigInteger> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            return "{number:" +
                    number +
                    "}:" +
                    "[" +
                    results +
                    "]";
        }

    }


}