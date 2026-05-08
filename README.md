# API 编排平台（Java8 + 本地 CSV）

## 1) 需求优化（V1）
- HAR 批量导入、调试、过滤、流程编排、条件控制器、变量传递、参数化、断言、AI 建议与采纳。

## 2) 技术架构（V1）
- 后端：Spring Boot 2.7（Java8）
- 持久化：本地 CSV 文件（`data/api_cases.csv`）
- 模块：har-parser / flow-designer / runner-engine / ai-advisor / case-manager

## 3) 已编码
- `api_case` 基础创建/查询接口。
- CSV 仓储实现（替代 MySQL）。
- AI 建议原型服务。
