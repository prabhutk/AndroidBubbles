package com.example.techkilla.bubbles.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class Word {

	private long typedCount;
	private long copiedCount;
	private TreeMap<Character, Word> children;
	
	private static final int SUGGESTIONS_SIZE = 10;

	private static interface IWordComparator extends Comparator<Word> {}

	public static final IWordComparator typedCountComparator;
	public static final IWordComparator copiedCountComparator;

	static {
		typedCountComparator = new TypedCountWordComparator();
		copiedCountComparator = new CopiedCountWordComparator();
	}

	private static final class TypedCountWordComparator implements
			IWordComparator {
		@Override
		public int compare(Word aWord, Word anotherWord) {

			if (aWord.typedCount == anotherWord.typedCount) {
				return 0;
			} else if (aWord.typedCount > anotherWord.typedCount) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	private static final class CopiedCountWordComparator implements
			IWordComparator {
		@Override
		public int compare(Word aWord, Word anotherWord) {

			if (aWord.copiedCount == anotherWord.copiedCount) {
				return 0;
			} else if (aWord.copiedCount > anotherWord.copiedCount) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public Word() {
		this.children = new TreeMap<Character, Word>();
	}

	public Word getChild(Character symbol) {
		return children.get(symbol);
	}

	public void addChild(Character symbol) {
		children.put(symbol, new Word());
	}
	
	public List<Word> getSuggestions() {
		List<Word> suggestions = new ArrayList<Word>(children.values()); 
		Collections.sort(suggestions, typedCountComparator);
		
		return suggestions.subList(0, SUGGESTIONS_SIZE);
	}

	public void reset(boolean dropChildren) {
		this.typedCount = 0L;
		this.copiedCount = 0L;

		if (dropChildren) {
			this.children = new TreeMap<Character, Word>();
		}
	}
}