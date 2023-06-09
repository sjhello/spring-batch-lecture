package io.springbatch.batch.job.validator;

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
public class ValidatorConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job validatorJob() {
        return jobBuilderFactory.get("validatorJob")
                .start(validatorStep1())
                .next(validatorStep2())
                .next(validatorStep3())
                .validator(new CustomJobParametersValidator())
                .build();
    }

    @Bean
    public Step validatorStep1() {
        return stepBuilderFactory.get("validatorStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("validatorStep1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step validatorStep2() {
        return stepBuilderFactory.get("validatorStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("validatorStep2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step validatorStep3() {
        return stepBuilderFactory.get("validatorStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("validatorStep3");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
