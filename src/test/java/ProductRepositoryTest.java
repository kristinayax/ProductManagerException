import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;


public class ProductRepositoryTest {

    Product book1 = new Book(01, "Книга1", 400, "Автор1");
    Product book2 = new Book(02, "Книга2", 500, "Автор2");
    Product smartphone1 = new Smartphone(03, "Смартфон1", 3000, "Nokia");
    Product smartphone2 = new Smartphone(04, "Смартфон2", 10_000, "iPhone");


    @Test
    public void shouldSaveProducts() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);
        repo.removeById(smartphone2.getId());

        Product[] expected = {book1, book2, smartphone1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdNotFoundException() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(-10);
        });
    }

    @Test
    public void shouldFindAll() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual;
        actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


}
