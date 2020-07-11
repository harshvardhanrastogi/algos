import java.util.*;
public class Trie {

	static class TrieNode {
		public Map<Character, TrieNode> children;
		public boolean endOfWord;
		public TrieNode() {
			children = new HashMap<>();
		}
		
	}

	private TrieNode root;
	private boolean prefixFound;
	public TrieNode getRoot() {
		TrieNode node = new TrieNode();
		node.children = new HashMap<>(this.root.children);
		node.endOfWord = this.root.endOfWord;
		return node;
	}

	public Trie() {
		root = new TrieNode();
	}


	public void insert(String word) {
		int count = 0;
		char[] chars = word.toCharArray();
		TrieNode root = this.root; 
		while (count < word.length()) {
			char c = chars[count];
			if (!root.children.containsKey(c)) {
				root.children.put(c, new TrieNode());
			}  
		
			root = root.children.get(c);
			if (count == word.length() - 1) {
				root.endOfWord = true;	
			}
			count++;
		}
	}

	public boolean search(String word, boolean matchWord) {
		if (matchWord) {
			TrieNode root = this.root;
			if (root == null) return false;
			int count = 0;
			char[] chars = word.toCharArray();
			while (count <= word.length()) {
				if (root.endOfWord) return true;
				char c = chars[count];
				if (!root.children.containsKey(c)) return false;
				root = root.children.get(c);
				count++;
			}
			return false;
		} else {
			return prefixSearch(word);
		}
	}

	public boolean prefixSearch(String word) {
		int count = 0;
		int match = 0;
		TrieNode root = this.root;
		while (count <= word.length()) {
			char c = word.charAt(count);
			if (!root.children.containsKey(c)) {
				return false;
			} 

			match++;
			root = root.children.get(c);
			if (match == word.length() - 1) return true;
			count++;
		}
		return false;
	}


	public void delete(String word) {
		delete(this.root, word, 0);
	}

	private void delete(TrieNode root, String word, int index) {

		if (index < word.length() - 1) {
			try {
				System.out.println(word.charAt(index));
				index++;
				delete(root.children.get(word.charAt(index)), word, index);
			} catch(NullPointerException e) {
				e.printStackTrace();
				Iterator<Map.Entry<Character, TrieNode>> it = root.children.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<Character, TrieNode> entry = it.next();
					System.out.println(entry.getKey() +" "+entry.getValue());
				}
			}
		}

		TrieNode nodeToDelete = root.children.get(word.charAt(index)).children.get(word.charAt(index + 1));
		if (index == word.length() - 2 && !nodeToDelete.children.isEmpty()) {
			nodeToDelete.endOfWord = false;
			return;
		} 

		// if (nodeToDelete.children.isEmpty()) {
		// 	root.children.remove(word.charAt(index));
		// }
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abc");
		trie.insert("abgl");
		trie.insert("cdf");
		trie.insert("abcd");
		trie.insert("lmn");
		trie.delete("abc");
		System.out.println("Has abc "+trie.search("abc", true));
	}
}