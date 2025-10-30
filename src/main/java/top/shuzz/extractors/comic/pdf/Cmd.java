package top.shuzz.extractors.comic.pdf;

import java.util.HashMap;
import java.util.Map;

public class Cmd {

    /**
     * Program argument: PDF comic file path
     */
    final public static String ARG_PDF_PATH = "pdf-path";
    /**
     * Program argument: The comic name
     */
    final public static String ARG_COMIC_NAME = "comic-name";
    /**
     * Program argument: JPEG image DPI
     * 
     */
    final public static String ARG_DPI = "dpi";

    public static Map<String, String> parseArgs(final String[] args) {

        final var resultMap = new HashMap<String, String>();

        if (args == null || args.length == 0) {

            return resultMap;
        }

        for (final var arg : args) {
            final var split = arg.split("=");
            if (split.length != 2) {
                continue;
            }

            final var paramName = split[0].replaceAll("--", "").toLowerCase();
            resultMap.put(paramName, split[1]);
        }

        return resultMap;
    }
}
