package com.always.learner.springDataDemo.service;
import com.always.learner.springDataDemo.dto.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserServices
{
    public List<Users> getAllUser();

    public Users getUser(Long id);

    public void addUser(Users user);

    public String updateUser(Long id, String address);

    public void deleteUser(Long id);

    public List<Users> getAllUsersByNames(String name);

    public List<Users> getUsersByAddress(String address);

    public String changeAddress(Long id, String address);

    public List<Users> findAllSortedUsers(String sortedParam);

    public Page<Users> getAllUserByPages(int pagenumber, int numberOfElementsPerPage);

    public Slice<Users> getAllUserBySlice(int pagenumber, int numberOfElementsPerPage);

    public List<Users> getAllUsersByAddress(String address);




}
