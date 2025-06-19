package com.example.ch4labs.repository;

import com.example.ch4labs.domain.QReview;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.ReviewResponse;
import com.example.ch4labs.dto.ReviewSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory factory;

    @Override
    public Page<ReviewResponse> search(ReviewSearchRequest request) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());


        if(StringUtils.hasText(request.getAuthor())) {
            builder.and(review.author.eq(request.getAuthor()));
        }

        if(StringUtils.hasText(request.getBookTitle())) {
            builder.and(review.bookTitle.eq(request.getBookTitle()));
        }

        if(StringUtils.hasText(request.getBookTitleContains())) {
            builder.and(review.bookTitle.contains(request.getBookTitleContains()));
        }

        if(StringUtils.hasText(request.getBookAuthor())) {
            builder.and(review.bookAuthor.eq(request.getBookAuthor()));
        }

        if(StringUtils.hasText(request.getTitleContains())) {
            builder.and(review.title.contains(request.getTitleContains()));
        }

        if(StringUtils.hasText(request.getContentContains())) {
            builder.and(review.content.contains(request.getContentContains()));
        }

        if(request.getRating() != null) {
            builder.and(review.rating.eq(request.getRating()));
        }

        if(request.getMinRating() != null) {
            builder.and(review.rating.goe(request.getMinRating()));
        }

        if(request.getMaxRating() != null) {
            builder.and(review.rating.loe(request.getMaxRating()));
        }


        Long total = factory.select(review.count()).from(review)
                .where(builder)
                .fetchOne();



        // 정렬법?
        if(StringUtils.hasText(request.getSort())) {
            List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
            String[] sorting = request.getSort().split(",");
            Boolean ascending = null;

            if(sorting[1].equals("asc")) {
                ascending = true;
            } else if (sorting[1].equals("desc")) {
                ascending = false;
            }

            if(ascending != null) {
                if (sorting[0].equals("id")) {
                    orderSpecifiers.add(ascending ? review.id.asc() : review.id.desc());
                } else if (sorting[0].equals("rating")) {
                    orderSpecifiers.add(ascending ? review.rating.asc() : review.rating.desc());
                }
            }

            List<Review> reviews = factory.selectFrom(review)
                    .where(builder)
                    .limit(pageable.getPageSize()).offset(pageable.getOffset())
                    .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
                    .fetch();

            List<ReviewResponse> content = reviews.stream().map(ReviewResponse::from).toList();

            return new PageImpl<>(content, pageable, total != null ? total : 0);

        }





        List<Review> reviews = factory.selectFrom(review)
                .where(builder)
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        List<ReviewResponse> content = reviews.stream().map(ReviewResponse::from).toList();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }
}
