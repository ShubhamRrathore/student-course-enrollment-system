package Hackerrank.codingapi.payloads.stocksdtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockTradeDTO {

    private Integer id;
    private  String type;
    private  String symbol;
    private  Integer share;
    private  Long timestamp;
    private  Integer userId;


}
