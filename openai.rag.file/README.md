# spring-ai-openai - RAG - Vector Store as File
 
Application to demonstrate the Spring AI - RAG - Using vector store as file (`SimpleVectorStore`).

## How the application works
- A PDF Company Profile file that was created purely based on a dummy company name whose information doesn't exit in the OpenAI
- When the application start it reads the PDF file from the resources folded to generate the vector embedding and persist the vector store to a file in the application folder `vector-store.json`
- When we send a basic prompt to the OpenAI requesting about the dummy company it will reply back with information found.
- When we send a prompt along with the RAG context (i.e. the vector store) OpenAI will respond back with proper information that is in the PDF file.

## Running the Application 
- Make sure you've registered with the OpenAI and topped up you account with some credit.
- Obtain the API key from your OpenAI account

### Run Command
```
mvn spring-boot:run -Dspring-boot.run.arguments="--OPENAI_API_KEY=<your_openai_api_key>"
```
Replace the "<your_openai_api_key>" placeholder with your actual Open AI API Key.

**Note:** when the application starts up successfully, you should see a file name `vector-store.json` created in this application root directory. This happens only once. If the file already exits the application will NOT recreate the vector store.

### Usage

#### Functional API's
##### cURL - Scenario 1: Basic prompt without providing the RAG context.
```
curl --location --request GET 'http://localhost:8080/springai/openai/rag/file/without-rag' \
--header 'Content-Type: text/plain' \
--data-raw 'What is CrystalComABC?'
```
Response: 
- As of my last update in October 2023, there is no widely recognized entity... etc. 

##### cURL - Scenario 2: Basic prompt with providing the RAG context.
```
curl --location --request GET 'http://localhost:8080/springai/openai/rag/file/with-rag' \
--header 'Content-Type: text/plain' \
--data-raw 'What is CrystalComABC?'
```
**Note:** You can open the company profile PDF file in the resources file and ask question based on the that.
Response:
- You will now start getting the information about the dummy company. 

##### cURL - Scenario 3: Basic prompt with providing the RAG context. More detailed question
```
curl --location --request GET 'http://localhost:8080/springai/openai/rag/file/with-rag' \
--header 'Content-Type: text/plain' \
--data-raw 'What is revene of CrystalComABC in 2009?'
```
Response:
- This will give information from a Financial Overview table in the PDF. 
