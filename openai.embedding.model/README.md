# spring-ai-openai - Embedding Model

Application to demonstrate the Spring AI Embedding using a Foundation Model.

Embeddings are numerical representations of text, images, or videos that capture relationships between inputs.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage 

cURL - Simple embedding with default model
```
curl --location --request GET 'http://localhost:8080/springai/openai/embedding/simple' \
--header 'Content-Type: text/plain' \
--data-raw 'Tell me a joke'
```

Response: Will be an embedded vector. 

cURL - Simple embedding with custom model
```
curl --location --request GET 'http://localhost:8080/springai/openai/embedding/custom' \
--header 'Content-Type: text/plain' \
--data-raw 'Tell me a joke'
```

Response: Will be an embedded vector but this time generated using custom OpenAI model. 
