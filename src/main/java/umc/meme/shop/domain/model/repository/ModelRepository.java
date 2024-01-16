package umc.meme.shop.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.meme.shop.domain.model.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
