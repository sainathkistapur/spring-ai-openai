# spring-ai-openai - RAG - Vector Store as pgVector

## Application to demonstrate the Spring AI - RAG Context using pgVector to store the vector embeddings. 

## How the application works
- As the cutoff of the model that we are using is October 2023, it will not have the knowledge of UK Spring Statement 2025.
- A PDF version of the UK Spring Statement 2025 `HMT_Spring_Statement_2025.pdf` is stored in the resources folder.
- When the application start it reads the PDF file from the resources folded to generate the vector embedding and persist into pgVector DB.
- When we send a basic prompt to the OpenAI requesting about UK Spring Statement 2025 it will reply back with information not found.
- When we send a prompt along with the RAG context (i.e. context from the vector store) OpenAI will respond back with proper information that is in the PDF file.
    - In this scenario the application will first query the local pdVector DB to get the contextual information based on the user query.
    - Then the application will pass that information as a context to the OpenAI to augment the response based on the context information.

### Type of prompts used
- Basic prompt - to show how the OpenAI responds if it can't find the latest information.
- Prompt Template - to show how a Prompt Template can be used to achieved RAG
- QuestionAndAnswerAdvisor - to show the QuestionAndAnswerAdvisor internally queries the vector store and attached context to the prompt.
- RetrievalAugmentationAdvisor - to show purpose build RAG advisor can be used for RAG

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
curl --location --request GET 'http://localhost:8080/springai/openai/rag/pgvector/without-rag-context' \
--header 'Content-Type: text/plain' \
--data-raw 'summaries Economic & Fiscal Outlook the SPRING STATEMENT 2025 UK'
```
Response: 
- Similar response like: I'm sorry, but I can't provide a summary...

##### cURL - Scenario 2:  Prompt Template with providing the RAG context.
```
curl --location --request GET 'http://localhost:8080/springai/openai/rag/pgvector/with-rag-prompt-template' \
--header 'Content-Type: text/plain' \
--data-raw 'what is the Public sector net financial liabilities1 in 2024 from SPRING STATEMENT 2025 UK'
```
**Note:** You can open the company profile PDF file in the resources file and ask question based on the that.
Response:
- You will now start getting the information based on the PDF file.

##### cURL - Scenario 3: QuestionAndAnswer Advisor with providing the RAG context.
```
curl --location --request GET 'http://localhost:8080/springai/openai/rag/pgvector/with-rag-qa-advisor' \
--header 'Content-Type: text/plain' \
--data-raw 'what is the Public sector net financial liabilities1 in 2024 from SPRING STATEMENT 2025 UK'
```
Response:
- You will now start getting the information based on the PDF file.
- 
##### cURL - Scenario 3: RetrievalAugmentation Advisor with providing the RAG context.
```
curl --location --request GET 'http://localhost:8080/springai/openai/rag/pgvector/with-rag-rag-advisor' \
--header 'Content-Type: text/plain' \
--data-raw 'what is the Public sector net financial liabilities1 in 2024 from SPRING STATEMENT 2025 UK'
```
Response:
- You will now start getting the information based on the PDF file.

