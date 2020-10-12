import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class Author {
    private @NotNull
    @Getter
    String name;
}
