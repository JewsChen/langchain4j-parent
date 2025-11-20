package com.test.langchain.models;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleModelTest {
    @Resource
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testSimpleModel() {
//        String answer = openAiChatModel.chat("Say 'Hello World'");
//        System.out.println(answer);

        UserMessage userMessage = UserMessage.from(
                TextContent.from("Hello!"),
                TextContent.from("How are you?")
        );
        userMessage.attributes().put("name", "张三");
        ChatResponse chatResponse = openAiChatModel.chat(userMessage);
        System.out.println(chatResponse.aiMessage().text());
        System.out.println(chatResponse.aiMessage().attributes());
    }
}
