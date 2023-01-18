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
        System.out.println("Started in Bootstrap");

        //Guardar publisher
        Publisher publi = new Publisher("John", "Address", "city", "state", "zip");
        publisherRepository.save(publi);


        //Autores
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publi);
        publi.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publi);


        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE development without EJB", "423442424");

        //Meter libros a la lista de libros de Eric
        rod.getBooks().add(noEJB);
        //Meter en la lista de autores del libro ddd a eric
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publi);
        publi.getBooks().add(noEJB);

        //Guardar ambos en repositorios

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publi);




        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publi.getBooks().size());



    }
}
