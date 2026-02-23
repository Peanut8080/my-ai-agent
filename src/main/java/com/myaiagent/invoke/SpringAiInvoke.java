package com.myaiagent.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * spring ai 框架调用大模型
 *
 * @author tanghua
 * @date: 2026/02/23/ 17:38
 */
@Component
public class SpringAiInvoke implements CommandLineRunner {

    @Resource
    private ChatModel dashscpeChatModel;

    @Override
    public void run(String... args) throws Exception {
//        AssistantMessage result = dashscpeChatModel.call(new Prompt("你好")).getResult().getOutput();
//        System.out.println(result.getText());
    }
}
