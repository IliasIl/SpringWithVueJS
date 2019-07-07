package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import space.ilias.SpringWithVueJS.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
