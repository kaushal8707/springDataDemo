package com.always.learner.springDataDemo.repository;

import com.always.learner.springDataDemo.dto.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<Users,Long>
{

}
