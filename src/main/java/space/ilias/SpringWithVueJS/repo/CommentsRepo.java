package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.ilias.SpringWithVueJS.domain.Comments;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Long> {
}
