import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Waybill {
    private int idWaybill;
    private Date date;
    private  int idOrganization;
}
