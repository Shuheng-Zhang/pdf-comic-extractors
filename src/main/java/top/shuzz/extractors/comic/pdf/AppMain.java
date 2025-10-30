package top.shuzz.extractors.comic.pdf;

import java.io.IOException;
import java.util.Optional;

import cn.hutool.core.util.SystemPropsUtil;
import cn.hutool.system.SystemPropsKeys;
import top.shuzz.extractors.comic.pdf.service.ExtractService;

/**
 * @author heng
 * @since 2025/10/28
 */
public class AppMain {
    public static void main(String[] args) throws IOException {

        // 解析输入参数
        final var argsMap = Cmd.parseArgs(args);

        // 文件路径
        final var pdfPath = argsMap.get(Cmd.ARG_PDF_PATH);
        // 漫画名称
        final var comicName = argsMap.get(Cmd.ARG_COMIC_NAME);
        // DPI
        final var dpiStr = argsMap.get(Cmd.ARG_DPI);
        final var dpi = Optional.ofNullable(dpiStr)
                .map(s -> {
                    try {
                        return Float.parseFloat(s);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .orElse(null);

        // 自动获取输出根目录
        final var outputDir = SystemPropsUtil.get(SystemPropsKeys.USER_DIR);

        // 加载PDF文件
        final var pdf = ExtractService.loadPdfFile(pdfPath);

        // 提取图片并存储到文件系统
        ExtractService.extractComicPages(pdf, comicName, dpi, outputDir);

        System.out.println("Extract comic done");
        System.out.println("Comic stored in: " + outputDir + "/" + comicName);
        System.out.println();
    }
}
