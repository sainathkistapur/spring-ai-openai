# spring-ai-openai - Observability

Application to demonstrate the Spring AI - Observability to get insights into AI-related operations.

We will be exposing the metrics using
- Actuator 
- Zipkin (using Springboot docker compose support)

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage
cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/basic-chat/observability' \
--header 'Content-Type: text/plain' \
--data-raw 'What is a prompt '
```

##### Check the actuator endpoints
List of available metrics: 
`http://localhost:8080/actuator/metrics`

You are particularly interested in these:

```
"gen_ai.client.operation",
"gen_ai.client.operation.active",
"gen_ai.client.token.usage",
"spring.ai.advisor",
"spring.ai.advisor.active",
"spring.ai.chat.client",
"spring.ai.chat.client.active"
```

##### Check the tracing on Zipkin 

`http://localhost:9411/zipkin`


