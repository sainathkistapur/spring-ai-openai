# spring-ai-openai - Chat Memory - JDBC Memory

Application to demonstrate the Spring AI integration with OpenAI and perform a conversational chat using JdbcMemory storage.

**Important**: `"conversationId"` Request Header  in each request is used to identify and isolate individual conversation from other conversations.

We will be using
- PostgreSQL DB for storing the chat memory (As of now only PostgreSQL and MariaDB are supported by the Spring AI.)
- Springboot Docker Compose support to build and run the DB scripts at run time. 

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


#### Scenario 2:  Chat with Jdbc Memory usage
cURL - Request 1 - Note: `conversationId` must be unique for each conversation.
```
curl --location --request GET 'http://localhost:8080/springai/openai/chat/in-memory/prompt-with-in-memory' \
--header 'conversationId: CID-2' \
--header 'Content-Type: text/plain' \
--data-raw 'My name is John'
```
Response: Will be a greeting message from the chat model

Now you can check the DB table `ai_chat_memory` and see entries for both input and output messages.

cURL - Request 2 - Note: `conversationId` must be unique for each conversation.
```
curl --location --request GET 'http://localhost:8080/springai/openai/chat/in-memory/prompt-with-in-memory' \
--header 'conversationId: CID-2' \
--header 'Content-Type: text/plain' \
--data-raw 'What is my name?'
```
Response: Your name is John.

