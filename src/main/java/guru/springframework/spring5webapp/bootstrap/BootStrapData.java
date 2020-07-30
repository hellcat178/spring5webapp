package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap, add publisher");

        Publisher pub = new Publisher();
        pub.setName("SFG Publishing");
        pub.setAddressLine1("1900 pub dr");
        pub.setCity("College Station");
        pub.setState("TX");
        pub.setZip("77040");

        publisherRepository.save(pub);
        System.out.println("Number of publisher: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Desigh", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(pub);
        pub.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pub);

        Author jk = new Author("J.K.", "Rowling");
        Book hp = new Book("Harry Potter", "21212112");
        jk.getBooks().add(hp);
        hp.getAuthors().add(jk);
        hp.setPublisher(pub);
        pub.getBooks().add(hp);

        authorRepository.save(jk);
        bookRepository.save(hp);
        publisherRepository.save(pub);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Books published: " + pub.getBooks().size());

    }
}
