import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @JsonProperty("name")
    public String name;
    @JsonProperty("count")
    public int count;
    @JsonProperty("cost")
    public double cost;
    @JsonProperty("producer")
    public String producer;

    @JsonCreator
    public Product(@JsonProperty("name") String name, @JsonProperty("count") int count, @JsonProperty("cost") double cost,@JsonProperty("producer") String producer)
    {
        this.name = name;
        this.count = count;
        this.cost = cost;
        this.producer = producer;
    }
}
