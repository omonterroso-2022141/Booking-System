package controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health") int
    public String checkAPI() {
        return "<h1>The API is working ok!</h1>";
    }
}