public class Kmp {

	private final char[] text;
	private final char[] pattern;
	private final int[] keys;
	public Kmp(String text, String pattern) {
		this.text = text.toCharArray();
		this.pattern = pattern.toCharArray();
		keys = preprocessPattern(this.pattern);
	}
	/*** Knuthh Morris Pratt
		preprocess
			if text[i] && pattern[j] matches then 
				key[i] = j + 1
				increment i & j

			else if j is 0
				then increment i
			else 
				j = keys[j - 1]		

		search
			if text[i] && pattern[j] matches then 
				increment i & j
				if j has reached pattern's last element 
					then match found
			else if j is 0
				then increment i
			else 
				j = keys[j - 1]		

	*/

	private int[] preprocessPattern(char[] pattern) {
		int i = 1, j = 0;
		int[] keys = new int[pattern.length];
		while (i < pattern.length) {
			if (pattern[i] == pattern[j]) {
				keys[i] = ++j;
			} else if (j != 0) {
				j = keys[j - 1];
				continue;
			} 
			i++;
		}
		return keys;
	}

	public void search(int i, int j) {
		while (i < text.length) {
			if (text[i] == pattern[j]) {
				j++;
				if (j == pattern.length) {
					System.out.println("Match found at "+(i - j + 1));
					j = 0;
				}
			} else if (j != 0) {
				j = keys[j - 1];
				continue;
			} 
			i++;
		}
	}

	public void printKeys() {
		for (Integer i : keys) {
			System.out.print(i + " ");
		}
	}
	
	public static void main(String[] args) {
		Kmp kmp = new Kmp("abracadabra", "abra");
		kmp.search(0, 0);
	}

}