package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model,
                        @ModelAttribute("login") LoginDto loginDto) {
        //return "forward:/WEB-INF/jsp/user/login";
        return "redirect:/login";
    }
}
