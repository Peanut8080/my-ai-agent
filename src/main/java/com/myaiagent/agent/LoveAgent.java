package com.myaiagent.agent;

import com.myaiagent.advisor.MyLoggerAdvisor;
import com.myaiagent.chatmemmory.FileBasedChatMemory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 恋爱助手智能体
 *
 * @author tanghua
 * @date: 2026/02/23/ 21:57
 */
@Component
@Slf4j
public class LoveAgent {

    private final ChatClient chatClient;


    private final String SYSTEM_PROMPT = """
            扮演深耕恋爱心理领域的专家-林薇。开场向用户表明身份，告知用户可倾诉恋爱难题。围绕单身、恋爱、已婚三种状态提问：
            单身状态询问社交圈拓展及追求心仪对象的困扰；恋爱状态询问沟通、习惯差异引发的矛盾；已婚状态询问家庭责任与亲属关系处理的问题。
            引导用户详述事情经过、对方反应及自身想法，以便给出专属解决方案。
            """;

    /**
     * 构造智能体
     *
     * @param dashScopeChatModel 阿里另积对话大模型
     */
    public LoveAgent(ChatModel dashScopeChatModel) {

//        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        String chatMemoryDir = String.format("%s/temp/chat-memory", System.getProperty("user.dir"));
        ChatMemory chatMemory = new FileBasedChatMemory(chatMemoryDir);

        chatClient = ChatClient.builder(dashScopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        new MyLoggerAdvisor()
                )
                .build();
    }

    /**
     * 多轮会话
     *
     * @param userMsg 用户信息
     * @param chatId  聊天室id
     * @return AI返回结果
     */
    public String doChat(String userMsg, String chatId) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(userMsg)
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .chatResponse();
        assert chatResponse != null;
        String output = chatResponse.getResult().getOutput().getText();
        log.info("outPut:{}", output);
        return output;
    }


    record LoverReport(String title, List<String> suggestions) {

    }

    /**
     * 结构化输出
     *
     * @param userMsg 用户信息
     * @param chatId  聊天室id
     * @return AI返回结果
     */
    public LoverReport doChatWithResponse(String userMsg, String chatId) {
        LoverReport loverReport = chatClient.prompt()
                .system(SYSTEM_PROMPT + "每次对话后都要生成恋爱报，标题为：{用户名}的恋爱报告，内容为建议列表")
                .user(userMsg)
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(LoverReport.class);
        assert loverReport != null;
        log.info("loverReport:{}", loverReport);
        return loverReport;
    }
}


