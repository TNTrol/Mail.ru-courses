import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public
class Position {
    private int idPosition;
    private int idWaybill;
    private int idNomenclature;
    private int count;
    private double cost;
}
