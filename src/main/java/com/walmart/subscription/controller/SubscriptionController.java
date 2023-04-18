package com.walmart.subscription.controller;

import com.sun.istack.internal.NotNull;
import com.walmart.subscription.common.SubscriptionConstants;
import com.walmart.subscription.dto.BadDataErrors;
import com.walmart.subscription.dto.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SubscriptionController {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SubscriptionConstants.SUBSCRIPTIONS_ENDPOINT_V1)
    public @ResponseBody
    ResponseEntity<?> echoSubscription(@Validated @RequestBody @NotNull Subscription subscription) {
        log.info("echoSubscription.subscription ="+subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    return new HashMap<String, String>(){{
                        put(error.getField(), error.getDefaultMessage());
                    }};
                })
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(BadDataErrors.builder().errors(errors).build());
    }
}
