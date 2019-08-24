package space.ilias.SpringWithVueJS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.repo.UserDetailsRepo;

import java.util.Set;

@Service
public class ProfileService {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public ProfileService(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    public User findById(String id) {
        return userDetailsRepo.findById(id).get();
    }

    public User changeSubscribe(User subscriber, User channelId) {
        Set<User> subscribers = channelId.getSubscribers();
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }
        return userDetailsRepo.save(channelId);
    }
}
