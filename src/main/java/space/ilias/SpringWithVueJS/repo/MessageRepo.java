package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.User;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    Page<Message> findByAuthorIn(List<User> author, Pageable pageable);
}
