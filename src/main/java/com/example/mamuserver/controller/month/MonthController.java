package com.example.mamuserver.controller.month;

import com.example.mamuserver.controller.month.dto.AddReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class MonthController {

    // Month calculate Method
    @GetMapping("/month")
    public String[] monthMethod(@RequestParam String yyyyMM) {
        LocalDate date = LocalDate.parse(yyyyMM + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate today = LocalDate.now();
        LocalDate firstDay = date.withDayOfMonth(1);
        LocalDate lastDay = date.withDayOfMonth(date.lengthOfMonth());

        String[] result = new String[2];

        if (date.equals(today.withDayOfMonth(1))) { // 현재 월인 경우
            result[0] = firstDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            result[1] = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } else { // 다른 월인 경우
            result[0] = firstDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            result[1] = lastDay.plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }

        return result;
    }

    // Body exam
    @GetMapping("/add")
    public int addMethod(@RequestBody AddReqDTO addReqDTO) {
        return addReqDTO.getNum1() + addReqDTO.getNum2();
    }

    // Variable exam
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "당신이 입력한 유저 아니디와 주문 아이디는 " + userId + ", " + orderId + " 입니다.";
    }

}

