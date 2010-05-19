package de.javauni.jarcade.control;

import java.io.FileNotFoundException;
public class CouldNotLoadExeption extends FileNotFoundException {
	public CouldNotLoadExeption(String s) {
		super(s);
	}
}
