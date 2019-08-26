package java11webflux.control;

import java11webflux.data.CourseRepository;
import java11webflux.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.security.Principal;

@Controller
public class WebControl {

    @Value("${app.name}")
    String appName;

    @Autowired
    CourseRepository courseRepository;

    @PostConstruct
    public void setup() {
        courseRepository.count().blockOptional().filter(count -> count == 0).ifPresent(it ->
                {
                    var courseFlux = Flux.just(
                            new Course("Beginning Java"),
                            new Course("Advanced Java"),
                            new Course("Reactive Streams in Java"));
                    courseFlux
                        .doOnNext(c -> System.out.println(c.toString()))
                        .subscribeOn(Schedulers.elastic())
                        .flatMap(courseRepository::save)
                        .subscribe(); // need to actually execute save*/
                });
    }

    @GetMapping("/")
    public Mono<String> index(Model model, Principal principal) {
        model.addAttribute("name", principal == null ? "" : principal.getName());
        model.addAttribute("applicationName", appName);
        return Mono.just("index");
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("applicationName", appName);
        model.addAttribute("error", "Login failed.");
        return "login";
    }

    @GetMapping("/user/account")
    public String userAccount(Model model, Principal principal) {
        model.addAttribute("applicationName", appName);
        model.addAttribute("username", principal.getName());
        return "account";
    }
}
