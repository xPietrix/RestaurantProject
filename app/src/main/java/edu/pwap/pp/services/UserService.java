package edu.pwap.pp.services;

import edu.pwap.pp.clients.UserApi;
import edu.pwap.pp.models.User;
import edu.pwap.pp.repositories.UserRepository;

public class UserService
{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void login(User user)
    {
        this.userRepository.login(user);
    }

    public void addUser(UserApi api, String role, User user)
    {
        this.userRepository.addUser(api, role, user);
    }
}
