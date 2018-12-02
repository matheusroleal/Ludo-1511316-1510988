package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class TextAreaLog {
	
	private static volatile TextAreaLog instance;
	
	JTextArea textArea;
	private int count = 0;
	
	private PrintStream standardOut;
	
	public TextAreaLog() throws FileNotFoundException {
		
		textArea = new JTextArea();
		textArea.setEditable(true);
		
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream("teste");
	
		// keeps reference of standard output stream
		standardOut = System.out;
	
		// re-assigns standard output stream and error output stream
		System.setOut(printStream);
		System.setErr(printStream);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	
	public void printLog(String str) throws BadLocationException {
		if(count > 10) {
			removeLog();
		}
		count ++;
		textArea.append(str+"\n");
	}
	
	public void removeLog() throws BadLocationException {
		count = 0;
		textArea.getDocument().remove(0,textArea.getDocument().getLength());
	}
	
	public static TextAreaLog getTextAreaLog() throws FileNotFoundException {
		if (instance == null) {
		    instance = new TextAreaLog();
		}
		return instance;
	}
		
}
