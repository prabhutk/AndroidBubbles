package com.example.techkilla.bubbles.util;


public class Dictionary {

	private Word rootWord;

	public void addWord(String wordString) {

		if (wordString.length() > 0) {
			this.addWord(wordString.substring(0, wordString.length() - 1));

			Word parentWord = rootWord;

			for (int i = 0; i < wordString.length() - 1; i++) {
				
				if(parentWord == null) {
					break;
				}
				
				parentWord = parentWord.getChild(wordString.charAt(i));
			}

			if(parentWord == null) {
				// TODO - log and throw exception
				
			} else {
				parentWord.addChild(wordString.charAt(wordString.length() - 1));
			}
		}
	}
	
	public Word getWord(String wordString) {

		Word aWord = rootWord;

		for (int i = 0; i < wordString.length(); i++) {
			
			if(aWord == null) {
				break;
			}
			
			aWord = aWord.getChild(wordString.charAt(i));
		}
		
		return aWord;
	}
}
