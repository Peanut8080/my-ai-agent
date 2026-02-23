package com.myaiagent.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.advisor.api.BaseChatMemoryAdvisor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/23/ 22:59
 */
@SpringBootTest
class LoveAgentTest {

    @Resource
    private LoveAgent loveAgent;

    @Test
    void doChat() {
        String uuid = UUID.randomUUID().toString();
        String result = loveAgent.doChat("我是汤姆猫", uuid);

        result = loveAgent.doChat("我喜欢迪丽热巴", uuid);

        result = loveAgent.doChat("我叫什么？", uuid);


    }
}