package nemo.mestman.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nemo.mestman.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
