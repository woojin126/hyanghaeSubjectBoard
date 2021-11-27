package com.sparta.securityoauthlogin.repository;

import com.sparta.securityoauthlogin.model.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Boards,Long> {
    List<Boards> findAllByOrderByModifiedAtDesc();
}
