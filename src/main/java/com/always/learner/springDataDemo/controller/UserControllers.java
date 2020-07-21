package com.always.learner.springDataDemo.controller;
import com.always.learner.springDataDemo.dto.Users;
import com.always.learner.springDataDemo.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
public class UserControllers
{
    Logger logger= LoggerFactory.getLogger(UserControllers.class);
    @Autowired
    UserServices userService;

    @GetMapping("/users_data")
    public List<Users> getAllUser() throws InterruptedException {
       logger.info("access all user");
       Thread.sleep(1000);
       return userService.getAllUser();
    }

    @GetMapping("/userdata/{id}")
    public Users getUser(@PathVariable("id") Long id)
    {
        logger.info("access user based on id");
        return userService.getUser(id);
    }

    @PostMapping("/user_data")
    public ResponseEntity<Object> addUser(@RequestBody Users user)
    {
        userService.addUser(user);
        URI path=ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(path).build();
    }

    @PutMapping("/user_data/{id}")
    public String updateUser(@PathVariable Long id,@RequestParam String address)
    {
        userService.updateUser(id,address);
        return "address updated for "+id;
    }

    @DeleteMapping("/user_remove_data/{id}")
    public String deleteUser(@PathVariable Long id)
    {
         userService.deleteUser(id);
         return "User with Id: "+id+" Deleted !!";
    }

    @GetMapping("/users/{name}")
    public List<Users> getAllUsersByNames(@PathVariable String name)
    {
        return userService.getAllUsersByNames(name);
    }

    @GetMapping("/user/{address}")
    public List<Users> getUsersByAddress(@PathVariable String address)
    {
        return userService.getUsersByAddress(address);
    }

    @PutMapping("/user_data/{id}/{address}")
    public void changeAddress(@PathVariable Long id,@PathVariable String address)
    {
        userService.changeAddress(id,address);
    }

    @GetMapping("/sort_user/{sortedParam}")
    public List<Users> findAllSortedUsers(@PathVariable String sortedParam)
    {
    return  userService.findAllSortedUsers(sortedParam);
    }

    @GetMapping("/user_per_page/{pagenumber}/{numberOfElementsPerPage}")
    public Page<Users> getAllUserByPages(@PathVariable String pagenumber, @PathVariable String numberOfElementsPerPage)
    {
        return userService.getAllUserByPages(Integer.parseInt(pagenumber),Integer.parseInt(numberOfElementsPerPage));

    }

    @GetMapping("/user_per_slice/{pagenumber}/{numberOfElementsPerSlice}")
    public Slice<Users> getAllUserBySlice(@PathVariable String pagenumber, @PathVariable String numberOfElementsPerSlice) {
        return userService.getAllUserBySlice(Integer.parseInt(pagenumber),Integer.parseInt(numberOfElementsPerSlice));
    }

    @GetMapping("/native/{address}")
    public List<Users> getAllUsersById(@PathVariable String address)
    {
        return userService.getAllUsersByAddress(address);
    }

    @GetMapping("/logs_data")
    public String logs()
    {
        logger.trace("A TRACE MESSAGE");
        logger.info("An INFO Message");
        logger.debug("A DEBUG Message");
        logger.error("An ERROR Message");
        logger.warn("A WARN Message");
        return "Hey buddy see the console for different level of logs";
    }
}
