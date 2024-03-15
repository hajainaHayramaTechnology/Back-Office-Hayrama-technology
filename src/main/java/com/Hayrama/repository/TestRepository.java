package com.Hayrama.repository;

import com.Hayrama.models.Test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
    @Query(value = "SELECT * from test ",nativeQuery = true)
    List<Test> getAll();
}
