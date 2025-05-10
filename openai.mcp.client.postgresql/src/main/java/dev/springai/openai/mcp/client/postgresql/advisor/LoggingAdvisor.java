package dev.springai.openai.mcp.client.postgresql.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;

@Slf4j
public class LoggingAdvisor implements CallAroundAdvisor {


    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        log.info("BEFORE: {}", advisedRequest);

        AdvisedResponse advisedResponse = chain.nextAroundCall(advisedRequest);

        log.info("AFTER: {}", advisedResponse);

        return advisedResponse;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
