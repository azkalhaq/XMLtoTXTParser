/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltotxt;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Azka
 */
public class bacaxml {
    public String content="";
    String[] Paragraf = new String[5];
    String title = "";
    public void hasil(String dir){
        try {
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
        //Referensi : http://www.mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/
        //http://stackoverflow.com/questions/9596468/open-only-xml-file-in-jfilechooser
        //http://www.programcreek.com/java-api-examples/index.php?api=javax.swing.JFileChooser
	DefaultHandler handler = new DefaultHandler() {
        boolean judul = false;
        boolean tempat = false;
        boolean tanggal = false;
        boolean paragraf = false;
        //String content = "";
        String tmpt;
        int countpar = 1;
        
        
	boolean bfname = false;
	boolean blname = false;
	boolean bnname = false;
	boolean bsalary = false;
        boolean bp = false;
        String filelocation = null;
        
 
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		//System.out.println("Start Element :" + qName);
 
		if (qName.equalsIgnoreCase("HEADLINE")) {
			judul = true;
		}

                if (qName.equalsIgnoreCase("P")) {
			paragraf = true;
		}
                
                if (qName.equalsIgnoreCase("DATELINE")) {
			tanggal = true;
                        tempat = true;
		}
 
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
		//System.out.println("End Element :" + qName);
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
 
		if (judul) {
                        content = content + "Judul : " + new String(ch, start, length);
                        title = content;
			System.out.println("Judul : " + new String(ch, start, length));
                        judul = false;
		}
                
                if (tempat){
                        tmpt = new String(ch,start,length-10);
                        content = content + "\nTempat: " + tmpt;
                        System.out.println("Tempat: " + tmpt);
			tempat = false;
                }
                
                if (tanggal) {
                        String tgl = new String(ch,start,length);
                        content = content+"\nTanggal: " + tgl.substring(length-10, length)+"\n";
			System.out.println("Tanggal: " + tgl.substring(length-10, length)+"\n");
			tanggal = false;
                        content = content+"\nIsi berita:";
                        System.out.println("Isi berita");
		}
                
                if (paragraf) {
                        String check=new String(ch,start,length);
                        if (check.contains("--")){
                            paragraf = false;
                        } else {
                            content=content+"\n\nParagraf "+countpar+" : "+check;
                            //System.out.println("Paragraf "+countpar+" : " + check);
                            Paragraf[countpar-1]=check;
                            paragraf = false;
                            countpar++;
                            
                        }
		}
	}
        
        };
        
        saxParser.parse(dir, handler);
       
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}