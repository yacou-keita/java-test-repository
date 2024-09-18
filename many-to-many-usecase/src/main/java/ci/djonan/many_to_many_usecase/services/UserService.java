package ci.djonan.many_to_many_usecase.services;

import ci.djonan.many_to_many_usecase.entities.Role;
import ci.djonan.many_to_many_usecase.entities.User;
import ci.djonan.many_to_many_usecase.repositories.IRoleRepository;
import ci.djonan.many_to_many_usecase.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;



    public User addNewUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }


    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }


    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }


    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }


    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        role.getUsers().add(user);
    }


    public User authenticate(String name, String password) {
        User user = userRepository.findByName(name);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
