package Hackerrank.codingapi.Service.Impl;


import Hackerrank.codingapi.exception.ResourceNotFoundException;
import Hackerrank.codingapi.mapper.StockMapper;
import Hackerrank.codingapi.payloads.stocksdtos.StockTradeDTO;
import Hackerrank.codingapi.Service.services.StockService;
import Hackerrank.codingapi.entities.StockTrade;
import Hackerrank.codingapi.repositories.StockTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private  final StockTradeRepository stockTradeRepository;
    private  final StockMapper stockMapper;

    @Override
    public StockTradeDTO createNewStock(StockTradeDTO stockTradeDto) {
      StockTrade stockTrade =  stockMapper.toEntity(stockTradeDto);
       stockTrade = stockTradeRepository.save(stockTrade);
        return stockMapper.toDTO(stockTrade);
    }

    @Override
    public List<StockTradeDTO> getStock() {
      List<StockTrade> stockTrade = stockTradeRepository.findAll();
        return stockMapper.DTO_LIST(stockTrade);
    }

    @Override
    public List<StockTradeDTO> filterBy(String type, Integer userId) {

        List<StockTrade>  stockTrades =  stockTradeRepository.findAll();
        Predicate<StockTrade>  conditon = e -> false;

        if(type != null){
            conditon = conditon.or(e-> e.getType().equals(type));
        }

        if (userId != null){
            conditon = conditon.or(e -> e.getUserId().equals(userId));
        }
      stockTrades =  stockTrades.stream().filter(conditon).toList();
//
//      List<StockTrade>  stockTrades =  stockTradeRepository.findAll();
//      if(type != null && UserId != null){
//      stockTrades = stockTrades.stream().filter(e -> e.getType().equals(type) && e.getUserId().equals(UserId)).collect(Collectors.toList());
//        }
//
//     else if(type != null){
//          stockTrades = stockTrades.stream().filter(e -> e.getType().equals(type)).collect(Collectors.toList());
//      }
//
//     else if (UserId != null){
//         stockTrades = stockTrades.stream().filter(e -> e.getUserId().equals(UserId)).collect(Collectors.toList());
//      }
     return  stockMapper.DTO_LIST(stockTrades);
    }

    @Override
    public StockTradeDTO GetStockById(Integer id) {
     StockTrade stockTrade = stockTradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stcok", " Id ", (long)id));
        return stockMapper.toDTO(stockTrade);
    }


}
