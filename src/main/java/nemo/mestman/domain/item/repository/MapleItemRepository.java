package nemo.mestman.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nemo.mestman.domain.item.entity.MapleItem;

public interface MapleItemRepository extends JpaRepository<MapleItem, Long> {
}
