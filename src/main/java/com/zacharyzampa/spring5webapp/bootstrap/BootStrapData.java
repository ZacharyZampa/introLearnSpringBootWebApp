package com.zacharyzampa.spring5webapp.bootstrap;

import com.zacharyzampa.spring5webapp.domain.Author;
import com.zacharyzampa.spring5webapp.domain.Book;
import com.zacharyzampa.spring5webapp.domain.Publisher;
import com.zacharyzampa.spring5webapp.repositories.AuthorRepository;
import com.zacharyzampa.spring5webapp.repositories.BookRepository;
import com.zacharyzampa.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  // tell Spring to use this as a Spring Managed Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setName("St Petersburg");
        publisher.setName("FL");

        publisherRepository.save(publisher);
        System.out.println("Nuber of Publishers " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        publisher.getBooks().add(ddd);

        // Save to repository
        // This will save these into the H2 Database
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "#939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of books by publisher: " + publisher.getBooks().size());


    }
}
