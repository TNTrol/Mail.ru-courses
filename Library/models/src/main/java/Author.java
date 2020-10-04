import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Author {
    private @Getter String Name;
    private @Getter String DataOfBorn;
}
