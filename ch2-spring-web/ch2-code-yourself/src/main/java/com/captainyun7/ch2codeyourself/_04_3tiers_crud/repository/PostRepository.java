package com.captainyun7.ch2codeyourself._04_3tiers_crud.repository;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {
    // db용 클래스

    // 맵은 키/밸류로 저장
    // 키는 중복 불가 >> 고유한 값이 필요
    private final Map<Long, Post> store = new HashMap<>();
    private Long sequence = 0L;
    
    // 여기는 다른 곳에서 만든 bean을 쓰지 않으니 Autowired 해선 안 된다



    // 게시글 CRUD 구현

    // 생성(create)
    // 서버가 관리하는 키를 제외한 밸류를 클라이언트에서 받고,
    // 반환값으로 서버키를 합친 완전체 객체를 넘긴다
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);

        return post;
    }
    // 설계에 따라선 update도 겸할 수 있음




    // 읽기(read)
    //

    /*
    public Post read(Long id) {
        return store.get(id);
    }
    
     */

    // Optional 객체
    // null 처리에 강점을 가짐
    // Optional.ofNullable(store.get(id))
    // 메서드 안쪽이 null이어도 예외 발생 없이 안전하게 전달
    // try-catch 일일이 적을 필요 없음!
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
