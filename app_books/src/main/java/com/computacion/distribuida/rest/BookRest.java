package com.computacion.distribuida.rest;

import com.computacion.distribuida.db.Book;
import com.computacion.distribuida.dto.AuthorDTO;
import com.computacion.distribuida.dto.BookDTO;
import com.computacion.distribuida.repo.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.computacion.distribuida.clients.AuthorRestClient;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@Transactional
public class BookRest {
    @Autowired
    BookRepository repository;

    @Autowired
    private RestClient.Builder restClientBuilder;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findAll() {

        var restClient = restClientBuilder.baseUrl("http://app-authors")
                .build();

        var adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        AuthorRestClient service = factory.createClient(AuthorRestClient.class);

        return repository.findAll()
                .stream()
                .map(book -> {
                    System.out.println("Buscando author con id= " + book.getAuthorId());
                    AuthorDTO author;
                    try {
                        author = service.findById(book.getAuthorId());
                        System.out.println("Author encontrado: " + author.toString());
                    } catch (Exception e) {
                        author = service.findByIdFallback(book.getAuthorId(), e);
                        System.out.println("Fallback author: " + author.toString());
                    }
                    var dto = new BookDTO( );

                    dto.setId(book.getId());
                    dto.setIsbn(book.getIsbn());
                    dto.setTitle(book.getTitle());
                    dto.setPrice(book.getPrice());
                    dto.setAuthorName(author.getFirstName() + " " + author.getLastName());

                    return dto;
                })
                .toList();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> findById(@PathVariable Integer id) {
        var book = repository.findById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> update(@PathVariable Integer id,@RequestBody Book book) {
        var bookObj = repository.findById(id);
        if (bookObj == null) {
            return ResponseEntity.notFound().build();
        }
        book.setId(bookObj.getId());
        repository.update(book);
        return ResponseEntity.ok(book);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        var book = repository.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(book);
        return ResponseEntity.ok("Deleted book with id: " + id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@RequestBody Book book) {
        repository.save(book);
        return ResponseEntity.ok(book);
    }



}
