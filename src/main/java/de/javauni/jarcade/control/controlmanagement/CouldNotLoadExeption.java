package de.javauni.jarcade.control.controlmanagement;

import java.io.FileNotFoundException;
public class CouldNotLoadExeption extends FileNotFoundException {
	public CouldNotLoadExeption(String s) {
		super(s);
	}
}
