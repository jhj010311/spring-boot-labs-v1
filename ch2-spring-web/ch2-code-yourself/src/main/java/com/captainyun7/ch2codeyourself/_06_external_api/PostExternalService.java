package com.captainyun7.ch2codeyourself._06_external_api;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PostExternalService {

    private final WebClient webClient;

    public PostExternalService() {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    public Post[] getAllPosts() {
        Post[] posts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(Post[].class) // Mono<Post[]>로 값을 변환
                .block(); // 비동기식으로 작동하던 코드를 이곳에서 동기식으로 변환

        return posts;
    }
}
