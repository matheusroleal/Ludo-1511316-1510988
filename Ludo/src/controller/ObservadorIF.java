package controller;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.text.BadLocationException;

public interface ObservadorIF {
	public void update(ActionEvent e) throws InterruptedException, FileNotFoundException, BadLocationException;
}
