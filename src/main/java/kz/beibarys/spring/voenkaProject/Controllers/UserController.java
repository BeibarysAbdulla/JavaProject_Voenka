package kz.beibarys.spring.voenkaProject.Controllers;

import kz.beibarys.spring.voenkaProject.Entities.Roles;
import kz.beibarys.spring.voenkaProject.Entities.Users;
import kz.beibarys.spring.voenkaProject.Repositories.RolesRepository;
import kz.beibarys.spring.voenkaProject.Repositories.UsersRepository;
import kz.beibarys.spring.voenkaProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "userCon")//
public class UserController extends BaseController implements WebMvcConfigurer {
    @Autowired
    UserService userService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("annonymous/registr").setViewName("annonymous/registr");
    }

    @GetMapping(value = "/registrPage/{soz}")
    public String registrPage(Model model,@PathVariable String soz){
        model.addAttribute("soz",soz);
        return "annonymous/registr";
    }

    @PostMapping(value = "/registr")
    public String  register(@ModelAttribute("userForm") Users userForm , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/registr";
        }

        Users user = usersRepository.findByDeletedAtNullAndEmail(userForm.getEmail());

        if (user!=null)
            return "redirect:/userCon/registrPage/This nick name already exist!";
        if(userForm.getPassword().length()<6 && userForm.getPassword()!=userForm.getPasswordConfirm()){
            return "redirect:/userCon/registrPage/Password is too short<br>Passwords do not match";
        }else {
            if (userForm.getPassword().length() < 6) {
                return "redirect:/userCon/registrPage/Password is too short";
            }

            if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
                return "redirect:/userCon/registrPage/Passwords do not match";
            }
        }

        String fileName = StringUtils.substringBefore(userForm.getEmail(),"@");
        File folder = new File("/uploads/users/"+fileName);
        if(!folder.exists()){
            boolean jai = folder.mkdir();
            if(jai){
                userService.registerUser(userForm);

                return "redirect:/main/login/You was registrate successfully! <br> Now you can enter the our website:)";
            }else
                return "redirect:/main/login/papka ashylmady";


        }
        else
            return "redirect:/main/login/ondai adam bar eken bkakbakabnk";

    }

}
