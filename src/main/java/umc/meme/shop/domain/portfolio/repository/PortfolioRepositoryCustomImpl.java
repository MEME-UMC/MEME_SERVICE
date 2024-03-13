package umc.meme.shop.domain.portfolio.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import umc.meme.shop.domain.artist.entity.Artist;
import umc.meme.shop.domain.portfolio.entity.Portfolio;
import umc.meme.shop.global.enums.Category;

import java.util.List;

import static umc.meme.shop.domain.portfolio.entity.QPortfolio.portfolio;

public class PortfolioRepositoryCustomImpl implements PortfolioRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PortfolioRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Portfolio> findByArtist(Artist artist, Pageable pageable) {
        List<Portfolio> content = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.artist.eq(artist),
                        portfolio.isBlock.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.artist.eq(artist),
                        portfolio.isBlock.eq(false))
                .fetchCount();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Portfolio> findByCategory(Category category, Pageable pageable) {
        List<Portfolio> content = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.category.eq(category),
                        portfolio.isBlock.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.category.eq(category),
                        portfolio.isBlock.eq(false))
                .fetchCount();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Portfolio> search(String query, Pageable pageable) {
        List<Portfolio> content = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.makeupName.like(query),
                        portfolio.info.like(query),
                        portfolio.isBlock.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.makeupName.like(query),
                        portfolio.info.like(query),
                        portfolio.isBlock.eq(false))
                .fetchCount();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Portfolio> findAllNotBlocked(Pageable pageable) {
        List<Portfolio> content = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.isBlock.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .selectFrom(portfolio)
                .where(portfolio.isBlock.eq(false))
                .fetchCount();
        return new PageImpl<>(content, pageable, total);
    }

}
