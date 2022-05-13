package mk.ukim.finki.wp.eLibrary.service;

import mk.ukim.finki.wp.eLibrary.model.User;

public interface AuthService {

    User login(String username, String password);

}
