package space.ilias.SpringWithVueJS.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.service.ProfileService;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    public User profileUser(@PathVariable("id") User profile) {
        return profile;
    }

    @PostMapping("subscribe-user/{channelId}")
    public User changeSubscribe(@AuthenticationPrincipal User subscriber,
                                @PathVariable User channelId) {

        if (subscriber.equals(channelId)) {
            return channelId;
        } else {
            return profileService.changeSubscribe(subscriber, channelId);
        }
    }
}
