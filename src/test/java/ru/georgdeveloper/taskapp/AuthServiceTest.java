package ru.georgdeveloper.taskapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.georgdeveloper.taskapp.enums.Role;
import ru.georgdeveloper.taskapp.models.User;
import ru.georgdeveloper.taskapp.repositpry.UserRepository;
import ru.georgdeveloper.taskapp.services.UserService;
import java.security.Principal;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void createUserTest() {
        UserService userService = new UserService(userRepository, passwordEncoder);
        User user = new User();
        user.setName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.USER));
        userService.createUser(user);
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void loginTest() {
        UserService userService = new UserService(userRepository, passwordEncoder);
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        Principal principal = mock(Principal.class);
        when(authentication.getPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn("test@example.com");
        User user = new User();
        user.setName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.USER));
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User loggedInUser = userService.getUserByPrincipal(principal);

        assertNotNull(loggedInUser);
        assertEquals(user.getEmail(), loggedInUser.getEmail());
    }

    @Test
    void logoutTest() {
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);

        SecurityContextHolder.clearContext();

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}