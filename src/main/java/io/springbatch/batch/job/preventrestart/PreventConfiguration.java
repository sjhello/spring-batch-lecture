package io.springbatch.batch.job.preventrestart;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PreventConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job preventRestartJob() {
        return jobBuilderFactory.get("preventRestartJob")
                .start(preventRestartStep1())
                .preventRestart()       // JobRestartException : JobInstance already exists and is not restartable
                .build();
    }

    @Bean
    public Step preventRestartStep1() {
        return stepBuilderFactory.get("preventRestartStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("preventRestartStep1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step preventRestartStep2() {
        return stepBuilderFactory.get("preventRestartStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("preventRestartStep2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
