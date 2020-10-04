import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Book {
    private @Getter String Name;
    private @Getter String Text;
    private @Getter Author Author;
}
