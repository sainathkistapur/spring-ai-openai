# spring-ai-openai - Model Context Protocol (MCP) - Client using PostgreSQL DB as server

Application to demonstrate the Spring AI integration with OpenAI and perform act as an MCP client accessing the PostgreSQL DB in the read only mode.

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
curl --location --request GET 'http://localhost:8080/springai/openai/mcp/client/postgresql/basic-chat' \
--header 'Content-Type: text/plain' \
--data-raw 'Is there anyone with firstname John in account? If so what is date of birth or dob?'
```

### Other Prompts to try:

- `List all the firstnames in json format from the account.`
- `How many records are in the account table?`
- `How many people have dob year between 1980 and 1990 in account? List there firtnames.`
  (Note: In this prompt we intentionally created a typo error in the 'firstname' word but the AI clever enough to interpret and give us the correct response)

### References
-  [Spring AI](https://docs.spring.io/spring-ai/reference/1.0/api/chat/openai-chat.html)


