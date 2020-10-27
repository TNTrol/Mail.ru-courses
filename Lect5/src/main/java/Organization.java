import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Organization {
    private int idOrganization;
    private int inn;
    private int checkingAccount;
}
