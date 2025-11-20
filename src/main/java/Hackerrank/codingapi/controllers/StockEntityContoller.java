package Hackerrank.codingapi.controllers;

import Hackerrank.codingapi.payloads.stocksdtos.StockTradeDTO;
import Hackerrank.codingapi.Service.Impl.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/Stocktradings")
@RequiredArgsConstructor
public class StockEntityContoller {
    private  final StockServiceImpl stockService;

    @PostMapping
    public ResponseEntity<StockTradeDTO> createStock (@RequestBody StockTradeDTO stockTradeDTO){
        log.info("please print something");
       StockTradeDTO stockTradeDTO1 =  this.stockService.createNewStock(stockTradeDTO);
       return  new ResponseEntity<StockTradeDTO>(stockTradeDTO1 , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StockTradeDTO>> getStock(){
        return new ResponseEntity<List<StockTradeDTO>>(this.stockService.getStock(), HttpStatus.OK);
    }
//    @GetMapping("/")
//    public String greet(){
//        return "Hello shubham";
//    }

    @GetMapping("/")
    public ResponseEntity<List<StockTradeDTO>> filterStock(@RequestParam String type , @RequestParam Integer userID){

        if (type != null || userID != null){
            return new ResponseEntity<>(this.stockService.filterBy(type,userID), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<StockTradeDTO>>(this.stockService.getStock() ,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public  ResponseEntity<StockTradeDTO> getById(@PathVariable  Integer id){
//        return new ResponseEntity<StockTradeDTO>(this.stockService.GetStockById(userId),HttpStatus.OK);
        return  ResponseEntity.ok(this.stockService.GetStockById(id));


    }

}
