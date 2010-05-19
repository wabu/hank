package de.javauni.jarcade.control;

import java.io.FileNotFoundException;

public class CouldNotSaveExeption extends FileNotFoundException{
	public CouldNotSaveExeption(String s) {
		super(s);
	}
}
