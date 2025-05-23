package kg.alatoo.broke.repositories;

import kg.alatoo.broke.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepository extends JpaRepository<Tip, Long> {
}
