package Hackerrank.codingapi.mapper;


import Hackerrank.codingapi.payloads.stocksdtos.StockTradeDTO;
import Hackerrank.codingapi.entities.StockTrade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface StockMapper {

    StockTradeDTO toDTO(StockTrade stockTrade);
    StockTrade   toEntity(StockTradeDTO stockTradeDTO);
    List<StockTradeDTO> DTO_LIST(List<StockTrade> stockTrades);

}
