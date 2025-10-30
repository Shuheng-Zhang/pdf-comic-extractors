package top.shuzz.extractors.comic.pdf.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import cn.hutool.core.io.FileUtil;

public class ExtractService {

    /**
     * Load PDF file
     * 
     * @param filePath PDF file path
     * @return A {@code PDDocument} PDF object
     * @throws IOException
     */
    public static PDDocument loadPdfFile(final String filePath) throws IOException {

        if (!FileUtil.exist(filePath)) {
            System.err.println("File not found: " + filePath);
            System.exit(-1);
        }
        if (!"pdf".equals(FileUtil.extName(filePath))) {
            System.err.println("File not supported");
            System.exit(-1);
        }

        final var pdfFile = new RandomAccessReadBufferedFile(filePath);
        final var pdfParser = new PDFParser(pdfFile);
        return pdfParser.parse();
    }

    /**
     * Extract comic pages as JPEG images from PDF file object
     *
     * @param pdfObj    The loaded PDF file object
     * @param comicName Comic name, the output target files directory name
     * @param dpi       DPI
     * @param outputDir Root output directory
     */
    public static void extractComicPages(
            final PDDocument pdfObj,
            final String comicName,
            final Float dpi,
            final String outputDir) throws IOException {

        if (pdfObj == null) {
            System.err.println("File cannot be read");
            System.exit(-1);
        }

        final var pages = pdfObj.getNumberOfPages();
        if (pages <= 0) {
            System.out.println("No comic image found");
            System.exit(-1);
        }

        final var targetOutputDir = outputDir + "/" + comicName;
        FileUtil.mkdir(targetOutputDir);

        final var targetDpi = Optional.ofNullable(dpi).orElse(300f);

        final var pdfRenderer = new PDFRenderer(pdfObj);
        for (int i = 0; i < pages; i++) {
            final var bufferImage = pdfRenderer.renderImageWithDPI(i, targetDpi, ImageType.RGB);
            final var imgName = String.format("IMG_%03d.jpeg", (i + 1));
            ImageIO.write(bufferImage, "jpeg", new File(targetOutputDir + "/" + imgName));
        }

        pdfObj.close();
    }

}
