package com.cv.dao;

import com.cv.entity.Token;
import com.cv.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    Token findByUser(user user);
    List<Token> findTokensByTokenStr(String tokenStr);
}
