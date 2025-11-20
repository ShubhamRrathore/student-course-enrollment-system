package Hackerrank.codingapi.Service.services;

import Hackerrank.codingapi.payloads.stocksdtos.StockTradeDTO;

import java.util.List;

public interface StockService {

    StockTradeDTO createNewStock(StockTradeDTO stockTradeDto);
    List<StockTradeDTO> getStock();
    List<StockTradeDTO> filterBy(String type, Integer UserId);
    StockTradeDTO GetStockById(Integer id);

}
