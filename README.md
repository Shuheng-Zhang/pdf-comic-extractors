# pdf-comic-extractor

A simple tool for extracting images from PDF file.

This tool usually use for PDF format Comic books.

# Dependencies

This tool is written with Java. It requires JDK21 or above for development, or JRE21 or above for using.

# Usage

Open a terminal, type command below:

```shell
java -jar pdf-comic-extractor-<version>.jar --pdf-path=<path/to/pdf/file> --comic-name=<comic_name> --dpi=300
```

By default, `dpi` is **300** if it is not specified.

The argument `comic-name` is used for creating a folder to storee the image files if is specified.

# Roadmap

- [x] Core functions
- [ ] Support batch files processing
- [ ] Support files under directory processing
- [ ] Add a GUI
