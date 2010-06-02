package de.javauni.jarcade.control.controlmanagement;

import java.io.FileNotFoundException;

public class CouldNotSaveExeption extends FileNotFoundException{
	public CouldNotSaveExeption(String s) {
		super(s);
	}
}
