package com.rabiloo.sharedocument.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabiloo.sharedocument.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
