# spring-ai-openai - Multimodel - Text-to-Image

Application to demonstrate the Spring AI integration with OpenAI and perform a simple Text to Image generation.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage 

cURL - Simple Text to Image Generation
```
curl --location --request GET 'http://localhost:8080/springai/openai/multimodel/text-to-image' \
--header 'Content-Type: text/plain' \
--data-raw 'Generate an image of apple playing cricket'
```

Response: you will receive a link to the image which you can click/paste in the browser to view the image.
