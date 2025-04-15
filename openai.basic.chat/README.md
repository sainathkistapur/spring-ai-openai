# spring-ai-openai - Basic Chat

Application to demonstrate the Spring AI integration with OpenAI and perform a simple chat operation.

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
curl --location --request GET 'http://localhost:8080/springai/openai/basic-chat' \
--header 'Content-Type: text/plain' \
--data-raw 'What is chatGPT?'
```


