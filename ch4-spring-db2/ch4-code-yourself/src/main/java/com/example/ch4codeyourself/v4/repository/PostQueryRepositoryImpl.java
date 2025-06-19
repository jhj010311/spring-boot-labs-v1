package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.dto.post.PostSearchRequest;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.domain.QPost;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
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

    @Override
    public Page<PostResponse> search(PostSearchRequest request) {
        QPost post= QPost.post;

//        factory.selectFrom(post)
//                .where()  /// 이 안을 채운다
//                .fetch();

        BooleanBuilder builder = new BooleanBuilder();

        // /api/v1/posts?keyword=...&author=...&page=2&size=5

        // 검색어 포함
        if(StringUtils.hasText(request.getKeyword())) {
            builder.and(
                    post.title.contains(request.getKeyword())
                            .or(post.body.contains(request.getKeyword()))
            );
        }

        // 작성자
        if(StringUtils.hasText(request.getAuthor())) {
            builder.and(post.author.eq(request.getAuthor()));
        }

        if(request.getCreatedAt() != null) {
            builder.and(post.createdAt.goe(request.getCreatedAt()));
        }



        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Post> posts = factory.selectFrom(post)
                .where(builder)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        Long total = factory.select(post.count())
                .from(post).where(builder)
                .fetchOne();

        List<PostResponse> content = posts.stream().map(PostResponse::from).toList();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }
}
