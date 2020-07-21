package com.always.learner.springDataDemo.service;
import com.always.learner.springDataDemo.dto.Users;
import com.always.learner.springDataDemo.repository.UserRepository;
import com.always.learner.springDataDemo.repository.UserRepositorys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpls implements UserServices {

    Logger logger= LoggerFactory.getLogger(UserServiceImpls.class);
    @Autowired
    UserRepositorys userRepository;

    @Autowired
    UserRepository userRepo;

    @Override
    public List<Users> getAllUser() {
        List<Users> userList= (List<Users>) userRepository.findAll();
        logger.info("UserList from the DataBase:- {}",userList);
        return userList;
    }

    @Override
    public Users getUser(Long id) {
      Optional<Users> userOptional=userRepository.findById(id);
      Users user=userOptional.get();
      logger.info("User - {} find by Id - {}",user,id);
      return user;
    }

    @Override
    public void addUser(Users user) {
       userRepository.save(user);
       logger.info("User added- {}",user);
    }

    @Override
    public String updateUser(Long id,String address) {
        userRepository.updateAddress(id,address);
        logger.info("New Address for user-id {} is - {}",id,address);
        return "New Address for user-id"+id+" is - "+address;
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
       logger.info("Delete {} of User",id);
    }

    @Override
    public List<Users> getAllUsersByNames(String name)
    {
        List<Users> usersList=userRepository.getAllUsersByNames(name);
        logger.info("get All Users By Names - {}",usersList);
        return usersList;
    }

    @Override
    public List<Users> getUsersByAddress(String address)
    {
         return userRepository.getUsersByAddress(address);
    }

    @Override
    public String changeAddress(Long id,String address) {
        userRepository.changeAddress(id,address);
        logger.info("New Address for user-id {} is - {}",id,address);
        return "New Address for user-id"+id+" is - "+address;
    }

    @Override
    public List<Users> findAllSortedUsers(String sortedParam)
    {
        return (List<Users>) userRepo.findAll(Sort.by(sortedParam));
    }

    @Override
    public Page<Users> getAllUserByPages(int pagenumber, int numberOfElementsPerPage) {
        return userRepo.findAll(PageRequest.of(pagenumber,numberOfElementsPerPage));
    }

    @Override
    public Slice<Users> getAllUserBySlice(int pagenumber, int numberOfElementsPerPage) {
        return userRepo.findAll(PageRequest.of(pagenumber,numberOfElementsPerPage));
    }

    @Override
    public List<Users> getAllUsersByAddress(String address) {
        return userRepository.getAllUsersByAddress(address);
    }

}
