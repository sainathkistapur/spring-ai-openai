# spring-ai-openai - Chat Memory - InMemory

Application to demonstrate the Spring AI integration with OpenAI and perform a conversational chat using InMemory storage.

**Important**: `"conversationId"` Request Header  in each request is used to identify and isolate individual conversation from other conversations. 

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage 

#### Scenario 1:  Simple chat with no memory usage
cURL - Request 1
```
curl --location --request GET 'http://localhost:8080/springai/openai/chat/in-memory/prompt-with-no-memory' \
--header 'conversationId: CID-1' \
--header 'Content-Type: text/plain' \
--data-raw 'My name is John'
```
Response: Will be a greeting message from the chat model

cURL - Request 2
```
curl --location --request GET 'http://localhost:8080/springai/openai/chat/in-memory/prompt-with-no-memory' \
--header 'conversationId: CID-1' \
--header 'Content-Type: text/plain' \
--data-raw 'What is my name?'
```
Response: Something like "I don't have access to personal data"


#### Scenario 2:  Chat with InMemory usage
cURL - Request 1 - Note: `conversationId` must be unique for each conversation.
```
curl --location --request GET 'http://localhost:8080/springai/openai/chat/in-memory/prompt-with-in-memory' \
--header 'conversationId: CID-2' \
--header 'Content-Type: text/plain' \
--data-raw 'My name is John'
```
Response: Will be a greeting message from the chat model

cURL - Request 2 - Note: `conversationId` must be unique for each conversation.
```
curl --location --request GET 'http://localhost:8080/springai/openai/chat/in-memory/prompt-with-in-memory' \
--header 'conversationId: CID-2' \
--header 'Content-Type: text/plain' \
--data-raw 'What is my name?'
```
Response: Your name is John.

