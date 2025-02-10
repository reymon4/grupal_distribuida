package com.computacion.distribuida.clients;

import com.computacion.distribuida.dto.AuthorDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/authors")
public interface AuthorRestClient {
    @GetExchange("/{id}")
    AuthorDTO findById(@PathVariable("id") Integer id);
}
