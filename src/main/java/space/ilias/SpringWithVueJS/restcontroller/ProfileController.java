package space.ilias.SpringWithVueJS.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.UserSubs;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    @JsonView(Views.FullProfile.class)
    public User profileUser(@PathVariable("id") User profile) {
        return profile;
    }

    @PostMapping("subscribe-user/{channelId}")
    @JsonView(Views.FullProfile.class)
    public User changeSubscribe(@AuthenticationPrincipal User subscriber,
                                @PathVariable User channelId) {

        if (subscriber.equals(channelId)) {
            return channelId;
        } else {
            return profileService.changeSubscribe(subscriber, channelId);
        }
    }

    @JsonView(Views.IdName.class)
    @GetMapping("/get-subscribers/{channelId}")
    public List<UserSubs> listOfSubscribers(@PathVariable("channelId") User user) {

        return profileService.listOfSubscribers(user);
    }

    @JsonView(Views.IdName.class)
    @PostMapping("/change-status/{subscriberId}")
    public UserSubs changeStatus(@AuthenticationPrincipal User user,
                                 @PathVariable("subscriberId") User subscriber) {

        return profileService.changeStatus(user, subscriber);
    }
}
