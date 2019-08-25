package space.ilias.SpringWithVueJS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.UserSubs;
import space.ilias.SpringWithVueJS.repo.UserDetailsRepo;

import java.util.List;
import java.util.stream.Collectors;

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
        List<UserSubs> listSubs = channelId
                .getSubscribers()
                .stream()
                .filter(subscribers -> subscribers.getSubscriberId().equals(subscriber))
                .collect(Collectors.toList());
        if (listSubs.isEmpty()) {
            UserSubs userAdd = new UserSubs(channelId, subscriber);
            channelId.getSubscribers().add(userAdd);
        } else {
            channelId.getSubscribers().removeAll(listSubs);
        }
        return userDetailsRepo.save(channelId);
    }
}
