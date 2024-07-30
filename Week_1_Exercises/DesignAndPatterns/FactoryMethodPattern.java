interface Document {
    void open();
    void close();
}

class WordDoc implements Document {
    public void open() {
        System.out.println("Opening Word document...");
    }
    public void close() {
        System.out.println("Closing Word document...");
    }
}

class PdfDoc implements Document {
    public void open() {
        System.out.println("Opening PDF document...");
    }
    public void close() {
        System.out.println("Closing PDF document...");
    }
}

class ExcelDoc implements Document {
    public void open() {
        System.out.println("Opening Excel document...");
    }
    public void close() {
        System.out.println("Closing Excel document...");
    }
}

abstract class DocFactory {
    public abstract Document createDocument();
}

class WordDocFactory extends DocFactory {
    public Document createDocument() {
        return new WordDoc();
    }
}

class PdfDocFactory extends DocFactory {
    public Document createDocument() {
        return new PdfDoc();
    }
}

class ExcelDocFactory extends DocFactory {
    public Document createDocument() {
        return new ExcelDoc();
    }
}

public class FactoryMethodPattern {
    public static void main(String[] args) {
        DocFactory wordFactory = new WordDocFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.close();

        DocFactory pdfFactory = new PdfDocFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();

        DocFactory excelFactory = new ExcelDocFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.close();
    }
}
