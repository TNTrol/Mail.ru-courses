import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Book {

    private @Getter String name;
    private @Getter Author author;
}
