package com.always.learner.springDataDemo.repository;
import com.always.learner.springDataDemo.dto.Users;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepositorys extends CrudRepository<Users, Long> {

     @Transactional
     @Modifying
     @Query("update Users set address=:address where id=:id")
     public void updateAddress(@Param("id") Long id, @Param("address") String address);


     //Indexed Query
     @Query("select u from Users u where u.name=?1")
     List<Users> getAllUsersByNames(String name);

     //Native Query
     @Query(value = "select u from users u where u.address=:address",nativeQuery=true)
     List<Users> getAllUsersByAddress(@Param("address") String address);

     //Named Query
     @Query("select u from Users u where u.address=:address")
     List<Users> getUsersByAddress(@Param("address") String address);

     @Transactional
     @Modifying
     @Query("update Users set address=?2 where id=?1")
     @Lock(LockModeType.NONE)
     public void changeAddress(Long id, String address);
}