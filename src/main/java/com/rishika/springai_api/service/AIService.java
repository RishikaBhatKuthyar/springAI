package com.rishika.springai_api.service;

import com.rishika.springai_api.dto.bookDetails;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AIService {
    @Autowired
    ChatModel chatModel;
    @Value("${spring.ai.openai.base-url}")
    private String openAiBaseUrl;

    @PostConstruct
    public void logBaseUrl() {
        System.out.println("OpenAI Base URL: " + openAiBaseUrl);
    }

    public String getJoke(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                    Please act as a funny person and create a joke on the given {topic}?
                    Please be mindful and sensitive about the content though.
                """);
        promptTemplate.add("topic", topic);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

    public String getBooks(String category, String year) {
        PromptTemplate promptTemplate = new PromptTemplate(""" 
                Please provide me best book for the given {category} and the {year}.
                Please do provide a summary of the book as well, the information should be
                limited and not much in depth. Please provide the details in the JSON format
                containing this information: category, year, review, author, summary
                """);
        promptTemplate.add("category", category);
        promptTemplate.add("year", year);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
    public bookDetails getBooksInJSON(String category, String year) {
        BeanOutputConverter<bookDetails> converter = new BeanOutputConverter<>(bookDetails.class);
        PromptTemplate promptTemplate = new PromptTemplate("""
                Please provide me best book for the given {category} and the {year}.
                Please do provide a summary of the book as well, the information should be
                limited and not much in depth. The response should be containing this information :
                category, book, year, review author, summary
                {format}
                """);
        promptTemplate.add("category", category);
        promptTemplate.add("year", year);
        promptTemplate.add("format", converter.getFormat());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        return converter.convert(response.getResult().getOutput().getText());
    }


}