package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.ilias.SpringWithVueJS.domain.User;

import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {"subscription", "subscribers"})
    Optional<User> findById(String id);
}
