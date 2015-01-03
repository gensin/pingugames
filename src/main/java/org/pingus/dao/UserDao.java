package org.pingus.dao;

import javax.transaction.Transactional;

import org.pingus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserDao extends JpaRepository<User, Long>{

}
