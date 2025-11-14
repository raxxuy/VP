package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {

    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public Author save(Author author) {
        DataHolder.authors.removeIf(a -> a.getId().equals(author.getId()));
        DataHolder.authors.add(author);
        return author;
    }

    @Override
    public Author edit(Long id, Author author) {
        int index = DataHolder.authors.indexOf(findById(id).orElseThrow());
        DataHolder.authors.removeIf(a -> a.getId().equals(id));
        DataHolder.authors.add(index, author);
        return author;
    }

    @Override
    public void delete(Long id) {
        DataHolder.authors.removeIf(author -> author.getId().equals(id));
    }
}
