# spring-ai-openai - Multimodel - Image-To-Text

Application to demonstrate the Spring AI integration with OpenAI to generate a image description.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage 

cURL - Simple Text to Image Generation - The image is stored in the `resources/images` folder
```
curl --location --request GET 'http://localhost:8080/springai/openai/multimodel/image-to-text'
```

Response: you will get a description of the image that is sent in the prompt.