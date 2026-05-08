# API Orchestrator (Java)

## 1. 优化后的需求（V1）

### 1.1 核心能力
1. **HAR 批量导入接口**：支持从 HAR 文件中解析并导入多个接口（URL、Method、Header、Body、Query 参数）。
2. **接口调试**：单接口可独立运行，查看请求/响应详情。
3. **接口过滤**：支持按域名、路径关键词、Method、状态码等条件筛选可导入接口。
4. **流程编排**：支持定义接口执行顺序（顺序、分支、汇聚）。
5. **条件控制器**：支持 if/else、switch、循环上限、重试策略。
6. **变量传递**：支持从上游响应中提取变量（JSONPath）并传递给下游接口。
7. **参数化**：支持环境变量、全局变量、测试数据集（CSV/JSON）注入。
8. **断言能力**：支持状态码断言、响应字段断言、耗时断言。
9. **AI 编排建议**：基于当前流程给出建议（如合并公共 Header、增加重试、增加断言）。
10. **采纳建议**：可一键采纳 AI 建议并持久化流程变更。
11. **数据持久化**：流程、接口定义、运行记录、AI 建议与采纳记录全部入库。

### 1.2 非功能需求
- 可观测性：记录执行日志、节点耗时、失败原因。
- 可扩展性：新增控制器节点时不影响已有流程。
- 安全性：敏感信息（token/password）脱敏存储。

## 2. 技术架构（V1）

- **后端框架**：Spring Boot 3 + Java 17
- **持久层**：Spring Data JPA + H2（可替换 MySQL/PostgreSQL）
- **核心模块**：
    - `HarImportService`：HAR 解析与接口落库
    - `FlowService`：流程定义、执行入口
    - `FlowExecutionEngine`：流程节点执行引擎（后续可扩展 DAG）
    - `AiSuggestionService`：AI 建议生成、采纳与记录
- **API 层**：
    - `/api/har/import`：导入 HAR
    - `/api/flows`：创建流程
    - `/api/flows/{flowId}/execute`：执行流程
    - `/api/flows/{flowId}/ai-suggestions`：生成/查看建议
    - `/api/flows/ai-suggestions/{suggestionId}/accept`：采纳建议

## 3. 快速启动

```bash
mvn spring-boot:run
```
