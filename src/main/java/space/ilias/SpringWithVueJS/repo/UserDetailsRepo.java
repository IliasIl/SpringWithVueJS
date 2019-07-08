package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.ilias.SpringWithVueJS.domain.User;

@Repository
public interface UserDetailsRepo extends JpaRepository<User, String> {
}
