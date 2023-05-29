package com.mycompany.project1.repository;

import com.mycompany.project1.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository <Message,Long>{
   List<Message> findByTextContains(String tag);
   List<Message> findAllByProductId(Long id);
}
