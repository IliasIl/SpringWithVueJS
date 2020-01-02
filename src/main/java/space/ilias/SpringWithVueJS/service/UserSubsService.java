package space.ilias.SpringWithVueJS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.UserSubs;
import space.ilias.SpringWithVueJS.repo.UserSubsRepo;

import java.util.List;

@Service
public class UserSubsService {
    private final UserSubsRepo userSubsRepo;

    @Autowired
    public UserSubsService(UserSubsRepo userSubsRepo) {
        this.userSubsRepo = userSubsRepo;
    }

    public List<UserSubs> findBySubscriberId(User subscriberId) {
        return userSubsRepo.findBySubscriberId(subscriberId);
    }
}
