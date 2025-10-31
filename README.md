# pdf-comic-extractor

A simple tool for extracting images from PDF files, typically used for comic books in PDF format.

## Dependencies

This tool is written in Java. It requires **JDK 21** or later for development, or **JRE 21** or later to run.

## Usage

Open a terminal and run the following command:

```shell
java -jar pdf-comic-extractor-<version>.jar --pdf-path=<path/to/pdf/file> --comic-name=<comic_name> --dpi=300
```

By default, the dpi value is set to **300** if not specified.

The `comic-name` argument is used to create a folder to store the image files when specified.

## Roadmap

- [x] Core functions
- [ ] Support batch files processing
- [ ] Support files under directory processing
- [ ] Add a GUI
