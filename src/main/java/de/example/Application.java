package de.example;

import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
@RequiredArgsConstructor
@Slf4j
class Scheduler {

    final Tracer tracer;

    @Scheduled(fixedRate = 10_000)
    public void scheduledWithTracer() {
        log.info("scheduled with trace-id: {}", tracer.currentTraceContext().context().traceId());
        throw new IllegalStateException("intended exception");
    }

}
