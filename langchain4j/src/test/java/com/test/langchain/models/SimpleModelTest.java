package com.test.langchain.models;

import dev.langchain4j.data.message.ImageContent;
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
        ChatResponse response = openAiChatModel.chat(userMessage);
        System.out.println(response.aiMessage().text());
        System.out.println(response.aiMessage().attributes());

//        UserMessage userMessage = UserMessage.from(
//                TextContent.from("Describe the following image"),
//                ImageContent.from("http://51wm-pic1.oss-cn-hangzhou.aliyuncs.com/spas/ssc/EBkXYrnX-fjwDaEbx-EtkhMctz.png")
//        );
//        ChatResponse response = openAiChatModel.chat(userMessage);
//        System.out.println(response.aiMessage().text());
    }
}
