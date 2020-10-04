import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Author {
    private @Getter String Name;
    private @Getter String DataOfBorn;
}
