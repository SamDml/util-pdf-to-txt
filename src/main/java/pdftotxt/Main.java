package pdftotxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import eu.adm.solutions.utils4adms.util.PDFUtilities;

public class Main {

	public static void main(String[] args) {
		File inputFolder = new File("C:\\Users\\SamDemeulemeester\\PdfToTxt");
		String fileExtension = "";

		for (File inputFile : inputFolder.listFiles()) {
			
			fileExtension = readFileExtension(inputFile);
			
			if (fileExtension.equals(".pdf")) {
				try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(inputFile.getAbsolutePath().replace(".pdf", ".txt")))) {
					
					String textPDF = PDFUtilities.getTextFromAllPages(inputFile).replace("\"", "");
					writer.write(textPDF);
					
					System.out.println("Input file: " + Paths.get(inputFile.getAbsolutePath()));
					System.out.println("Output file: " + Paths.get(inputFile.getAbsolutePath().replace(".pdf", ".txt")));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}

		}

	}
	
	private static String readFileExtension(File inputFile) {
		String fileExtension = "";
		
		int indexOfFileExtension = inputFile.getAbsoluteFile().toString().lastIndexOf(".");
		if (indexOfFileExtension != -1) {
			fileExtension = inputFile.getAbsoluteFile().toString().substring(indexOfFileExtension);				
		}
		
		return fileExtension;
	}

}
