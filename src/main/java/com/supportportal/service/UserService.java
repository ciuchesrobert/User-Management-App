package com.supportportal.service;

import com.supportportal.domain.User;
import com.supportportal.exceptions.domain.EmailNotFoundException;
import com.supportportal.exceptions.domain.NotAnImageFileException;
import com.supportportal.exceptions.domain.UsernameExistsException;
import com.supportportal.exceptions.domain.UsernameNotFountException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UsernameExistsException, UsernameNotFountException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email,
                    String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UsernameExistsException, UsernameNotFountException, IOException, NotAnImageFileException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail,
                    String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UsernameExistsException, UsernameNotFountException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UsernameExistsException, UsernameNotFountException, IOException, NotAnImageFileException;
}
