package ci.djonan.many_to_many_usecase.services;

import ci.djonan.many_to_many_usecase.entities.Role;
import ci.djonan.many_to_many_usecase.entities.User;

public interface IUserService {
    User addNewUser(User user);
    User findUserByName(String name);
    Role addNewRole(Role role);
    Role findRoleByName(String name);
    User authenticate(String name, String password);
    void addRoleToUser(String userName,String roleName);
}
