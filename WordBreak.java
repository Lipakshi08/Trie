package TRIE;

public class WordBreak {
    static class Node{
        Node[] children;
        boolean eow;
        
        public Node(){
            children = new Node[26];
            for(int i=0; i<26; i++){
                children[i] = null;
            }
            eow = false;
        }
    }
    static Node root = new Node();

    public static void insert(String words){
        Node curr = root;
        for(int i=0; i<words.length(); i++){
            int idx = words.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            if (i == words.length()-1) {
                curr.children[idx].eow = true;
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }
    public static boolean search(String key){
        Node curr = root;
        for(int i=0; i<key.length(); i++){
            int idx = key.charAt(i) - 'a';
            Node node = curr.children[idx];
            if (node == null) {
                return false;
            }
            if (i == key.length()-1 && root.eow == false) {
                return true;
            }
            curr = curr.children[idx];
        }  
        return true; 
    }
    public static boolean wordBreak(String key){
        if (key.length() == 0) {
            return true;
        }
        for(int i=1; i<=key.length(); i++){
            String firstPart = key.substring(0, i);
            String secPart = key.substring(i);
            if (search(firstPart) && wordBreak(secPart)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String words[] = {"I", "like", "Sam", "Samsung", "mobile"};
        String key = "IlikeSamsung";

        for(int i=0; i<words.length; i++){
            insert(words[i]);
        }
        System.out.println(wordBreak(key));
    }
}