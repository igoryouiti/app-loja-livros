package br.com.isato.applojalivros.controller;

import br.com.isato.applojalivros.DTO.tradeCouponDTO.CreateTradeCouponDTO;
import br.com.isato.applojalivros.DTO.tradeCouponDTO.TradeCouponDTO;
import br.com.isato.applojalivros.model.BillingAddress;
import br.com.isato.applojalivros.model.PromoCoupon;
import br.com.isato.applojalivros.model.TradeCoupon;
import br.com.isato.applojalivros.service.PromoCouponService;
import br.com.isato.applojalivros.service.TradeCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon-management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CouponManagementController {

    @Autowired
    private TradeCouponService tradeCouponService;

    @Autowired
    private PromoCouponService promoCouponService;

    @GetMapping("/trade-coupon")
    public HttpEntity<List<TradeCoupon>> getAllTradeCoupons(){
      return ResponseEntity.ok(tradeCouponService.findAll());
    };

    @GetMapping("/trade-coupon/{id}")
    public ResponseEntity<TradeCoupon> getTradeCouponById(@PathVariable Long id){
        return tradeCouponService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/trade-coupon/customer/{id}")
    public ResponseEntity<List<TradeCouponDTO>> getAllTradeCouponByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(tradeCouponService.searchAllByCustomer(id));
    }

    @PostMapping("/trade-coupon")
    public ResponseEntity<TradeCoupon> createTradeCoupon(@RequestBody CreateTradeCouponDTO createTradeCouponDTO){
        return tradeCouponService.create(createTradeCouponDTO)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/trade-coupon")
    public ResponseEntity<TradeCoupon> updateTradeCoupon(@RequestBody TradeCoupon tradeCoupon){
        return tradeCouponService.update(tradeCoupon)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/trade-coupon/active-status-change/{id}")
    public ResponseEntity<TradeCoupon> updateTradeCouponActive(@PathVariable Long id){
        return tradeCouponService.changeActiveStatus(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/trade-coupon/{id}")
    public ResponseEntity deleteTradeCoupon(@PathVariable Long id) {
        tradeCouponService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/promo-coupon")
    public HttpEntity<List<PromoCoupon>> getAllPromoCoupons(){
        return ResponseEntity.ok(promoCouponService.findAll());
    };

    @GetMapping("/promo-coupon/{id}")
    public ResponseEntity<PromoCoupon> getPromoCouponById(@PathVariable Long id){
        return promoCouponService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/promo-coupon")
    public ResponseEntity<PromoCoupon> createPromoCoupon(@RequestBody PromoCoupon promoCoupon){
        return promoCouponService.create(promoCoupon)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/promo-coupon")
    public ResponseEntity<PromoCoupon> updatePromoCoupon(@RequestBody PromoCoupon promoCoupon){
        return promoCouponService.update(promoCoupon)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/promo-coupon/active-status-change/{id}")
    public ResponseEntity<PromoCoupon> updatePromoCouponActive(@PathVariable Long id){
        return promoCouponService.changeActiveStatus(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping("/promo-coupon/{id}")
    public ResponseEntity deletePromoCoupon(@PathVariable Long id) {
        promoCouponService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
