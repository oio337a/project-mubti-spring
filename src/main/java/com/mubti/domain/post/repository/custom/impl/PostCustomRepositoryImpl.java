package com.mubti.domain.post.repository.custom.impl;

import com.mubti.domain.post.entity.Post;
import static com.mubti.domain.post.entity.QPost.post;
import static com.mubti.domain.user.entity.QUser.user;

import com.mubti.domain.post.repository.custom.PostCustomRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> findAllByPostTitle(Pageable pageable, String categoryType, String keyword) {
        List<Post> postList =  jpaQueryFactory
                .selectFrom(post)
                .where(post.categoryType.contains(categoryType), post.postTitle.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = jpaQueryFactory
                .selectFrom(post)
                .where(post.categoryType.contains(categoryType), post.postTitle.contains(keyword));

        return PageableExecutionUtils.getPage(postList, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<Post> findAllByPostContent(Pageable pageable, String categoryType, String keyword) {
        List<Post> postList =  jpaQueryFactory
                .selectFrom(post)
                .where(post.categoryType.contains(categoryType), post.postContent.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = jpaQueryFactory
                .selectFrom(post)
                .where(post.categoryType.contains(categoryType), post.postContent.contains(keyword));

        return PageableExecutionUtils.getPage(postList, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<Post> findAllByUserAlias(Pageable pageable, String categoryType, String keyword) {
        List<Post> postList =  jpaQueryFactory
                .selectFrom(post)
                .join(post.user, user)
                .where(post.categoryType.contains(categoryType), user.userAlias.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = jpaQueryFactory
                .selectFrom(post)
                .join(post.user, user)
                .where(post.categoryType.contains(categoryType), user.userAlias.contains(keyword));

        return PageableExecutionUtils.getPage(postList, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<Post> findAllByPostTitleOrPostContent(Pageable pageable, String categoryType, String keyword) {
        List<Post> postList =  jpaQueryFactory
                .selectFrom(post)
                .where(post.categoryType.contains(categoryType), post.postTitle.contains(keyword).or(post.postContent.contains(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Post> countQuery = jpaQueryFactory
                .selectFrom(post)
                .where(post.categoryType.contains(categoryType), post.postTitle.contains(keyword).or(post.postContent.contains(keyword)));

        return PageableExecutionUtils.getPage(postList, pageable, countQuery::fetchCount);
    }
}
