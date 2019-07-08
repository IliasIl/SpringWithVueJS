package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.ilias.SpringWithVueJS.domain.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
