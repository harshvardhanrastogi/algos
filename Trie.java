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
			while (count < word.length()) {
				root = root.children.get(word.charAt(count));
				if(root == null) return false;
				count++;
			}
			return root.endOfWord;
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
		Mutable b = new Mutable(false);
		delete(this.root, word, 0, b);
	}

	private void delete(TrieNode root, String word, int index, Mutable del) {
		if (index < word.length()) {
			delete(root.children.get(word.charAt(index)), word, ++index, del);//Depth first traversal
		}

		if (del.abort == 1) {
			return;
		}
		
		if (!del.b && index == word.length() && !root.children.isEmpty() && root.endOfWord) {
			root.endOfWord = false;//remove the end flag 
			del.abort = 1;// stop all the method stack calls if this word is a substring of another word
		}

		if (root.children.isEmpty()) {
			del.b = true;//last empty node reached start removing keys
		}

		if (del.b) {
			if (root.children.size() > 1) {// node has more than 1 key
				del.b = false;
				//remove key if it does not appear in another word or it is the last character
				if(!root.children.get(word.charAt(index - 1)).endOfWord || (index - 1) == word.length() - 1) {
					root.children.remove(word.charAt(index - 1));				
				}
			} else {
				root.children.clear();
			}
		}
	}

	static class Mutable {
		public boolean b; 
		public int abort;
		public Mutable(boolean b, int abort) {
			this.b = b;
			this.abort = abort;
		}
		public Mutable(boolean b) {
			this.b = b;
		}
		public Mutable(int abort) {
			this.abort = abort;
		}
		public String toString() {
			return String.valueOf(b);
		}
	}

	public void printInOrder(TrieNode root) {
		if (root.children.isEmpty()) return;

		Queue<TrieNode> q = new LinkedList<>();
		q.add(root);

		System.out.println();
		TrieNode n;
		while((n = q.poll()) != null) {
			System.out.println(n.children.keySet() +" "+n.endOfWord);
			Iterator<Character> it = n.children.keySet().iterator();
			while(it.hasNext()) {
				q.add(n.children.get(it.next()));
			}
		}
	}


	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abc");
		trie.insert("abgl");
		trie.insert("cdf");
		trie.insert("abcd");
		trie.insert("lmo");
		trie.insert("lmn");

		trie.delete("abcd");
		trie.delete("abc");
		
		
		trie.printInOrder(trie.getRoot());
	}
}