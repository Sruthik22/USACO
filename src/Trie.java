import java.util.HashMap;
import java.util.Map;

class Trie {

    TrieNode node;

    /** Initialize your data structure here. */
    public Trie() {
        node = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void addWord(String word) {

        TrieNode curNode = node;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!curNode.children.containsKey(c)) {
                curNode.children.put(c, new TrieNode());
            }

            curNode = curNode.children.get(c);
        }

        curNode.endOfWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(node, word, 0);
    }

    public boolean dfs(TrieNode curTrieNode, String word, int index) {
        if (index == word.length()) {
            return curTrieNode.endOfWord;
        }

        if (word.charAt(index) == '.') {
            boolean works = false;

            for (Map.Entry<Character, TrieNode> entry: curTrieNode.children.entrySet()) {
                if (dfs(entry.getValue(), word, index+1)) {
                    works = true;
                    break;
                }
            }

            return works;
        }

        else {
            if (curTrieNode.children.containsKey(word.charAt(index))) {
                return dfs(curTrieNode.children.get(word.charAt(index)), word, index+1);
            }

            return false;
        }
    }

    static class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Trie wordDictionary = new Trie();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // -> false
        System.out.println(wordDictionary.search("bad")); // -> true
        System.out.println(wordDictionary.search(".ad")); //  -> true
        System.out.println(wordDictionary.search("b..")); //  -> true
    }
}