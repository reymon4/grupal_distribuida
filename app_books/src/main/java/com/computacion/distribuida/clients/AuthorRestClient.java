package com.computacion.distribuida.clients;

import com.computacion.distribuida.dto.AuthorDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/authors")
public interface AuthorRestClient {
    @GetExchange("/{id}")
    @CircuitBreaker(name = "authorService", fallbackMethod = "findByIdFallback")
    AuthorDTO findById(@PathVariable("id") Integer id);

    default AuthorDTO findByIdFallback(Integer id, Throwable t) {
        var dto = new AuthorDTO();
        dto.setId(-1);
        dto.setFirstName("No Available");
        dto.setLastName("No Available");
        return dto;
    }
}
