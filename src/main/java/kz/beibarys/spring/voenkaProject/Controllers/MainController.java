package kz.beibarys.spring.voenkaProject.Controllers;

import kz.beibarys.spring.voenkaProject.Entities.Roles;
import kz.beibarys.spring.voenkaProject.Entities.Users;
import kz.beibarys.spring.voenkaProject.Repositories.RolesRepository;
import kz.beibarys.spring.voenkaProject.Repositories.UsersRepository;
import kz.beibarys.spring.voenkaProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "main")
public class MainController extends BaseController {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserService userService;
    @Autowired
    RolesRepository rolesRepository;

    @GetMapping(path = "/")
    public String index(Model model){
        return "index";
    }

    @GetMapping(value = "/about")
    public String about(Model model){return "about";}

    @GetMapping(value = "/login/{soz}")
    public String login(Model model, @PathVariable String soz){
        model.addAttribute("soz",soz);
        return "annonymous/login";
    }
//----------------------------------------------Admin-------------------------------------------------------
    @GetMapping(value = "/admin_profile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminProfile(Model model){return "admin/profile";}
//---------------------------------------------User----------------------------------------------------------
    @GetMapping(value = "/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userProfile(Model model){
        model.addAttribute("user",getUserData());
        return "user/profile";}



}
