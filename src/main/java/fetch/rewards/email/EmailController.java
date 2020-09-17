package fetch.rewards.email;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Inject
    private EmailService emailService;

    @PostMapping("/filter")
    public Long unique(@RequestBody(required = false) Optional<Set<String>> emails){
        return emailService.unique(emails);
    }
}
