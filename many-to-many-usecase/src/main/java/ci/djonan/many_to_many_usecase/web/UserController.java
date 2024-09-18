package ci.djonan.many_to_many_usecase.web;

import ci.djonan.many_to_many_usecase.entities.User;
import ci.djonan.many_to_many_usecase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
private UserService userService;

    @GetMapping("/user/{name}")
    public User findUserByName(@PathVariable String name){
        return userService.findUserByName(name);
    }
}
