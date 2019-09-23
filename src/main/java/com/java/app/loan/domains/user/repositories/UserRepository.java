package com.java.app.loan.domains.user.repositories;

import com.java.app.loan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, BigInteger> {
}
