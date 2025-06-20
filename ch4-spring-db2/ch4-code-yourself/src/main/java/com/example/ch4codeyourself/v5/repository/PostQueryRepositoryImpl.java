package com.example.ch4codeyourself.v5.repository;

import com.example.ch4codeyourself.v5.domain.QPost;
import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.post.PostResponse;
import com.example.ch4codeyourself.v5.dto.post.PostSearchRequest;
import com.example.ch4codeyourself.v5.repository.PostQueryRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable) {

        QPost post = QPost.post;

        // page, size
        //        SELECT * FROM posts
        //        WHERE
        //        LIMIT 10 offset ....
        List<Post> content = queryFactory
                .selectFrom(post)
                .where(post.createdAt.goe(createdAt))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(post.count())
                .from(post)
                .where(post.createdAt.goe(createdAt))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null ? count : 0);
    }

    // /api/v1/posts?keyword=스프링&author=yun&page=2&size=5
    // ...
    @Override
    public Page<PostResponse> search(PostSearchRequest request) {
        // QueryDSL로 동적 쿼리 만들기
        QPost post = QPost.post;

        // BooleanBuilder
        BooleanBuilder builder = new BooleanBuilder();

        // SELECT * FROM posts;
        //        queryFactory.selectFrom(post)
        //                .where(??? = builder)
        //                .fetch();

        // 검색어 포함 (제목이나 본문)
        if (StringUtils.hasText(request.getKeyword())) {
            builder.and(
                    post.title.contains(request.getKeyword())
                            .or(post.body.contains(request.getKeyword()))
            );
        }

        // 작성자 일치 여부 WHERE author = request.getAuthor()
        if (StringUtils.hasText(request.getAuthor())) {
            builder.and(post.author.eq(request.getAuthor()));
        }

        // 작성일 이후
        if (request.getCreatedAt() != null) {
            builder.and(post.createdAt.goe(request.getCreatedAt()));
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Post> posts = queryFactory.selectFrom(post)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        long total = queryFactory
                .select(post.count())
                .from(post)
                .where(builder)
                .fetchOne();

        List<PostResponse> content = posts.stream().map(PostResponse::from).toList();
        return new PageImpl<>(content, pageable, total);
    }
}
