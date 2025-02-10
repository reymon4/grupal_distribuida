package com.computacion.distribuida.repo;

import com.computacion.distribuida.db.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    private EntityManager manager;

    public void save(Book book) {
        manager.persist(book);
    }
    public Book findById(Integer id) {
        return manager.find(Book.class, id);
    }
    public void update(Book book) {
        manager.merge(book);
    }
    public void delete(Book book) {
        manager.remove(book);
    }
    public List<Book> findAll() {
        return manager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
