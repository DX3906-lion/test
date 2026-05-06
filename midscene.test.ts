import { chromium } from 'playwright';
import { MidsceneAgent } from '@midscene/web';

/**
 * Midscene E2E 示例脚本（TypeScript）
 * 环境变量在代码里显式声明，避免依赖外部 shell 配置。
 */
async function main() {
  // 1) 显式声明运行环境配置
  process.env.MIDSCENE_OPENAI_API_KEY = 'your-openai-api-key';
  process.env.MIDSCENE_OPENAI_BASE_URL = 'https://api.openai.com/v1';
  process.env.MIDSCENE_MODEL = 'gpt-4.1-mini';

  // 2) 启动浏览器并创建 Midscene Agent
  const browser = await chromium.launch({ headless: true });
  const page = await browser.newPage();
  const agent = new MidsceneAgent(page, {
    aiActionContext: {
      // 与上面的环境变量保持一致，这里再显式声明一份
      model: process.env.MIDSCENE_MODEL,
      apiKey: process.env.MIDSCENE_OPENAI_API_KEY,
      baseURL: process.env.MIDSCENE_OPENAI_BASE_URL,
    },
  });

  try {
    // 3) 打开页面
    await page.goto('https://example.com', { waitUntil: 'domcontentloaded' });

    // 4) 用自然语言驱动测试
    await agent.ai('检查页面主标题是否包含 Example Domain');
    await agent.ai('点击 More information 链接');
    await agent.ai('确认当前页面包含 IANA 文本');

    // 5) 断言（可选）
    const url = page.url();
    if (!url.includes('iana.org')) {
      throw new Error(`断言失败：预期跳转到 iana.org，实际 URL 为 ${url}`);
    }

    console.log('✅ Midscene 测试通过');
  } finally {
    await browser.close();
  }
}

main().catch((error) => {
  console.error('❌ Midscene 测试失败:', error);
  process.exit(1);
});
