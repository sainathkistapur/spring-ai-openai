# spring-ai-openai - Multimodel - Text-to-Speech

Application to demonstrate the Spring AI integration with OpenAI and perform a simple Text to Speech Operation.

We demonstrate both 
- Simple Text to Speech  
- Text to Speech using streams. For long text like audiobooks. 

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage 

cURL - Simple Text to Speech
```
curl --location --request GET 'http://localhost:8080/springai/openai/multimodel/text-to-speech' \
--header 'Content-Type: text/plain' \
--data-raw 'That'\''s one small step for man, one giant leap for mankind.' -o audio.mp3
```

cURL - Simple Text to Speech With Streams 
```
curl --location --request GET 'http://localhost:8080/springai/openai/multimodel/text-to-speech-stream' \
--header 'Content-Type: text/plain' \
--data-raw 'That'\''s one small step for man, one giant leap for mankind.' -o audio.mp3
```

