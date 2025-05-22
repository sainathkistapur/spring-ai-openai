# spring-ai-openai - Model Context Protocol (MCP) - Server - WebMVC

Application to demonstrate the Spring AI integration with OpenAI act as an Simple MCP Server to provide flight information.
The application loads the data from a json file and provides the flight information based on the user query.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage

The server will running on port 8088.

The local MCP client can access this server using the following URL: http://localhost:8088

### References
-  [Spring AI](https://docs.spring.io/spring-ai/reference/1.0/api/chat/openai-chat.html)


