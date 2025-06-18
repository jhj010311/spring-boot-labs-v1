package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import com.example.ch4codeyourself.v3.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory factory;

    @Override
    public Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable) {

        QPost post = QPost.post;

        // goe : greater or equal
        List<Post> content = factory.selectFrom(post).where(post.createdAt.goe(createdAt))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long count = factory.select(post.count()).from(post)
                .where(post.createdAt.goe(createdAt))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null ? count : 0);
    }
}
