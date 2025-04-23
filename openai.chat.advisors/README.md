# spring-ai-openai - Advisors

Application to demonstrate the Spring AI - Advisors to intercept, modify, and enhance AI-driven interactions.

We will be using three kinds of implementation here 
- Logging (custom) Advisor
- SafeGuardAdvisor (built-in) Advisor (Sensitive words: "datasets", "human", "developed")
- Mixed advisors Logging + SafeGuardAdvisor

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage 1 - Logging Advisor
cURL - Logging Advisor
```
curl --location --request GET 'http://localhost:8080/springai/openai/advisors/logging' \
--header 'Content-Type: text/plain' \
--data-raw 'What is chatGPT? Limit to 50 words'
```
You will see the logs of prompt Before and After in the logs. 

### Usage 2 - SafeGuard Advisor
cURL - SafeGuard Advisor - Stopping scenario
```
curl --location --request GET 'http://localhost:8080/springai/openai/advisors/safeguard' \
--header 'Content-Type: text/plain' \
--data-raw 'Is chatgpt a human'
```
You will get a response 
```
I'm unable to respond to that due to sensitive content. Could we rephrase or discuss something else?
```

cURL - SafeGuard Advisor - Passing scenario
```
curl --location --request GET 'http://localhost:8080/springai/openai/advisors/safeguard' \
--header 'Content-Type: text/plain' \
--data-raw 'What is the capital of UK'
```
You will get a response from the model

### Usage 2 - Mixed Advisors

cURL - SafeGuard + Logging Advisor - Stopping scenario
```
curl --location --request GET 'http://localhost:8080/springai/openai/advisors/mixed-advisors' \
--header 'Content-Type: text/plain' \
--data-raw 'Is chatgpt a human'
```
You will see BEFORE and AFTER logs and also get a response
```
I'm unable to respond to that due to sensitive content. Could we rephrase or discuss something else?
```

cURL - SafeGuard Advisor - Passing scenario
```
curl --location --request GET 'http://localhost:8080/springai/openai/advisors/safeguard' \
--header 'Content-Type: text/plain' \
--data-raw 'What is the capital of UK'
```
You will see BEFORE and AFTER logs and also a response from the model