import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductManager;
import ru.netology.repository.ProductRepository;


public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(01, "Книга1", 400, "Автор1");
    Product book2 = new Book(02, "Книга2", 500, "Автор2");
    Product smartphone1 = new Smartphone(03, "Смартфон1", 3000, "Nokia");
    Product smartphone2 = new Smartphone(04, "Смартфон2", 10_000, "iPhone");


    @BeforeEach
    public void setup() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
    }

    @Test
    public void shouldFindSmartphoneByName() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("Смартфон1");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBookByName() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = {book2};
        Product[] actual = manager.searchBy("Книга2");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMatches() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Книга5");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindEmptyRequest() {
        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = manager.searchBy("");

        Assertions.assertArrayEquals(expected, actual);
    }

}
