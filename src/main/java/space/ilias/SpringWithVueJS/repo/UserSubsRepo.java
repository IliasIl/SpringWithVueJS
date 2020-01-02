package space.ilias.SpringWithVueJS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.UserSubs;
import space.ilias.SpringWithVueJS.domain.UserSubsId;

import java.util.List;

@Repository
public interface UserSubsRepo extends JpaRepository<UserSubs, UserSubsId> {

    List<UserSubs> findByChannelId(User user);

    List<UserSubs> findBySubscriberId(User subscriberId);

    UserSubs findByChannelIdAndSubscriberId(User channel, User subscriber);

}
