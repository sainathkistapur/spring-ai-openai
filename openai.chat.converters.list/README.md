# spring-ai-openai - Structured Output Converter

Application to demonstrate the Spring AI integration with OpenAI and perform a simple prompt operation that converts the response to a simple list using `ListOutputConverter`.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage

**Step 1:** First check how the application responds without the list converter
cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/prompt/simple' \
--header 'Content-Type: text/plain' \
--data-raw 'List top 5 happiest countries in the world'
```

**Step 2:** Now check how the application responds with the list converter
cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/prompt/simple/with-list-converter' \
--header 'Content-Type: text/plain' \
--data-raw 'List top 5 happiest countries in the world'
```


