package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/users")
  public ResponseEntity<User> createNewUser(@RequestBody User postManUser) {
    User apiUser = userService.handleCreateUser(postManUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(apiUser);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
    this.userService.handleDeleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body("Delete user with id: " + id);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    User fetchUser = this.userService.getUserById(id);
    return ResponseEntity.status(HttpStatus.OK).body(fetchUser);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User requestUser) {
    User currentUser = userService.handleUpdateUser(requestUser);
    return ResponseEntity.status(HttpStatus.OK).body(currentUser);
  }

}
