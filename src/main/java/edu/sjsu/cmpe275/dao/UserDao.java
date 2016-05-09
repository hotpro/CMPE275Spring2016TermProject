package edu.sjsu.cmpe275.dao;

import edu.sjsu.cmpe275.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yutao on 5/5/16.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    public List<User> findByEmail(String email);

}
