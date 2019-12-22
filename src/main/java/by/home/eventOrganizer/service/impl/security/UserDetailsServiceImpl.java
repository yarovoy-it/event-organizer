package by.home.eventOrganizer.service.impl.security;

import by.home.eventOrganizer.model.security.User;
import by.home.eventOrganizer.security.model.AuthenticationUserDetails;
import by.home.eventOrganizer.service.security.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    //TODO will not work because fetch type lazy and cant reach roles from users
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByName(username);
        final Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new AuthenticationUserDetails(user.getId(), user.getLogin(), user.getPassword(), authorities);
    }
}
