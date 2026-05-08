package com.example.apiorchestrator.ai;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AiSuggestionService {
    public List<String> suggest(String flowDefinition) {
        return Arrays.asList(
                "将登录接口前置，并将 token 提取为全局变量",
                "对高风险接口添加状态码与响应体字段断言",
                "在依赖订单ID的步骤前增加条件控制器检查变量是否为空"
        );
    }
}
