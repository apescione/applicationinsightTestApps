package com.example.sse.messages.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api")
public class MessagesController {


    @RequestMapping(value = "/messages",
            produces = {"text/event-stream", "application/stream+json"},
            method = RequestMethod.GET)
    public Mono<ResponseEntity<Flux<ServerSentEvent<String>>>> getSseMessages() {
        Flux<ServerSentEvent<String>> sseFlux = Flux.interval(Duration.ofSeconds(5))
                .map(i -> ServerSentEvent.<String>builder().event("ping")
                        .data(i.toString()).id(RandomStringUtils.randomAlphanumeric(8)).build());
        return Mono.just(ResponseEntity.ok().body(sseFlux));
    }


}
