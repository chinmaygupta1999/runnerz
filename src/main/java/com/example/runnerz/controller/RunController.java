package com.example.runnerz.controller;

import com.example.runnerz.run.Run;
import com.example.runnerz.exception.RunNotFoundException;
import com.example.runnerz.repository.RunRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

//    public RunController(RunRepository runRepository) {
//        this.runRepository = runRepository;
//    }

    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.GONE)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(runRepository.findById(id).get());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location);
    }

//    @ResponseStatus(HttpStatus.FOUND)
//    @GetMapping("/count")
//    int count() {
//        return runRepository.count();
//    }
//
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @PostMapping("/saveAll")
//    void saveAll(@Valid @RequestBody List<Run> runs) {
//        runRepository.saveAll(runs);
//    }

}
