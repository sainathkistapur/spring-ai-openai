# spring-ai-openai - Model Context Protocol (MCP) - Client using File System server

Application to demonstrate the Spring AI integration with OpenAI and perform act as an MCP client accessing the local File System server.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account
- Change the `<absolute-path-to-the-application-folder>` in the `mcp-servers.json` file to point to the location where you are running this application.

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```

Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

### Usage

cURL
```
curl --location --request GET 'http://localhost:8080/springai/openai/mcp/client/filesystem/basic-chat' \
--header 'Content-Type: text/plain' \
--data-raw 'what files are in the mcp-filesystem-folder.'
```


#### Other Prompts to try:

- `List all the firstnames in json format from the account.`
- `How many records are in the first file in the mcp-filesystem-folder`
- `List all the records from the first file in the mcp-filesystem-folder`
- `What is the last name of Ben from the employee.json file in the mcp-filesystem-folder`

### References
-  [Spring AI](https://docs.spring.io/spring-ai/reference/1.0/api/chat/openai-chat.html)


