package br.com.mat.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

import com.itextpdf.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import io.cucumber.java.Status;

public class Pdf extends Setup {
	
	public static void generate(String caminhoPastas, String nomeScenarioData, Status status) throws DocumentException, IOException, java.io.IOException  {
		
		float pagex = 800, pagey = 600, imagex = 670, imagey = 400;
		Document doc = new Document(new Rectangle(pagex, pagey));
		OutputStream os = new FileOutputStream(caminhoPastas + "\\" + nomeScenarioData + ".pdf");;
		File fileImage = new File(caminhoPastas);
		Image image = null;
		Paragraph paragraph = null;
		Rectangle rectInferior = null, rectSuperior = null;;
		Image logoImg = Image.getInstance(".\\img\\yourCompanyLogo.PNG");
		System.out.println(logoImg);
		PdfWriter.getInstance(doc, os);
		
		doc.open();
		
		File[] filesImagensOrdenadoData = fileImage.listFiles();
		Arrays.sort(filesImagensOrdenadoData, Comparator.comparingLong(File::lastModified));
	    
		for (File f : filesImagensOrdenadoData) {
	    	String fileName = f.getName();
	    	if (fileName.contains(".pdf")) {
	    		continue;
	    	}
	    	if (fileName.contains(".png")) {
	    		image  = Image.getInstance(f.getPath());
	    		doc.newPage();
	    		
	    		
	    		paragraph = new Paragraph(fileName.replace(".png", " "));
	    		fileName = fileName + " - Status do Cenário: " + status.name();
	    		paragraph.setAlignment( Paragraph.ALIGN_CENTER );
	    		paragraph.setSpacingAfter(20);
	    		doc.add( paragraph );
	    		
	    		logoImg.scaleAbsolute(75,75);
	    		logoImg.setAbsolutePosition(25, pagey - 100);
	    		doc.add(logoImg);
	    		
	    		image.scaleAbsolute(imagex, imagey);
	    		image.setAbsolutePosition( (pagex - imagex) / 2 , (pagey - imagey) / 2);
	    		doc.add(image);
	    		
	    		if( status.name().equals("FAILED") ) {
	    			rectInferior = new Rectangle(pagex, 20); 
		    		rectInferior.setBackgroundColor(BaseColor.RED);
		    		doc.add(rectInferior);
		    		
		    		rectSuperior = new Rectangle(0, pagey-20, pagex, pagey); 
		    		rectSuperior.setBackgroundColor(BaseColor.RED);
		    		doc.add(rectSuperior);
	    		}
	    		
	    		if( status.name().equals("PASSED") ) {
	    			rectInferior = new Rectangle(pagex, 20); 
		    		rectInferior.setBackgroundColor(BaseColor.GREEN);
		    		doc.add(rectInferior);
		    		
		    		rectSuperior = new Rectangle(0, pagey-20, pagex, pagey); 
		    		rectSuperior.setBackgroundColor(BaseColor.GREEN);
		    		doc.add(rectSuperior);
	    		}
	    		
	    	}
	    }
		if ( doc.isOpen() ) {
            try {
            	doc.close();
            } catch (Exception e) {
            	System.out.println("Sem imagens para gerar pdf! " + e);
            }
			
        }
        if (os != null) {
        	os.close();
        }
	}
		
}
	
