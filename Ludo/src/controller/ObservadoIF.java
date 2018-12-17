package controller;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.text.BadLocationException;

public interface ObservadoIF {
	public void add(ObservadorIF o);
	public void remove(ObservadorIF o);
	public void notifyObservador(ActionEvent o) throws InterruptedException, FileNotFoundException, BadLocationException;
}
