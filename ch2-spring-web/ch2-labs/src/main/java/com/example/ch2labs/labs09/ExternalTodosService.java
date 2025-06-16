package com.example.ch2labs.labs09;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalTodosService {

    private final WebClient webClient;

    public ExternalTodosService() {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    public ExternalTodosResponse getAllTodos(Long id) {
        ExternalTodosResponse todo = webClient.get()
                .uri("/todos/" + id)
                .retrieve()
                .bodyToMono(ExternalTodosResponse.class) // Mono<Post[]>로 값을 변환
                .block(); // 비동기식으로 작동하던 코드를 이곳에서 동기식으로 변환

        return todo;
    }


}
