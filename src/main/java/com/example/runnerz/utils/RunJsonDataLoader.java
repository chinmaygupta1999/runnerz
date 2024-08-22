package com.example.runnerz.utils;

import com.example.runnerz.run.JdbcClientRunRepository;
import com.example.runnerz.run.Runs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from data and saving it to Database.", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data.", e);
            }
        }
        else {
            log.info("Not loading runs from JSON because container already contains data.");
        }
    }

}
