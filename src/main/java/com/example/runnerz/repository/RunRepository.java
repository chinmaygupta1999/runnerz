package com.example.runnerz.repository;

import com.example.runnerz.run.Run;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

    List<Run> findAllByLocation(String Location);

}
