//        INPUTS
//        SeachAutoComplete seachAutoComplete = new SeachAutoComplete();
//        seachAutoComplete.insert("pankaj");
//        seachAutoComplete.insert("pancake");
//        seachAutoComplete.insert("parking");
//        seachAutoComplete.insert("pagination");
//        System.out.println(seachAutoComplete.suggest("pa"));
//        System.out.println(seachAutoComplete.suggest("pan"));
//        System.out.println(seachAutoComplete.suggest("pag"));
//        System.out.println(seachAutoComplete.suggest("parking"));
//        System.out.println(seachAutoComplete.suggest("pang"));
//
//        OUTPUTS
//        [parking, pagination, pancake, pankaj]
//        [pancake, pankaj]
//        [pagination]
//        [parking]
//        []
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TrieNode {
    HashMap<Character, TrieNode> childs;
    boolean isWord;

    public TrieNode() {
        childs = new HashMap<>();
        isWord = false;
    }
}

public class SeachAutoComplete {
    TrieNode root;

    public SeachAutoComplete() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode temp = root;
        if (word == null || word.isEmpty())
            return;
        for (int i = 0; i < word.length(); i++) {
            if (!temp.childs.containsKey(word.charAt(i))) {
                temp.childs.put(word.charAt(i), new TrieNode());
            }

            temp = temp.childs.get(word.charAt(i));

            if (i == word.length() - 1)
                temp.isWord = true;
        }
    }


    List<String> suggest(String word) {
        TrieNode temp = root;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (!temp.childs.containsKey(word.charAt(i))) {
                return result;
            }
            temp = temp.childs.get(word.charAt(i));
        }
        StringBuilder formedWord = new StringBuilder(word);
        suggestRelatedItems(temp, formedWord, result);
        return result;
    }

    private void suggestRelatedItems(TrieNode temp, StringBuilder formedWord, List<String> result) {
        if (temp.isWord) {
            result.add(formedWord.toString());
        }

        if (temp.childs == null || temp.childs.isEmpty())
            return;

        for (char it : temp.childs.keySet()) {
            TrieNode localTemp = temp;
            temp = temp.childs.get(it);
            formedWord.append(it);
            suggestRelatedItems(temp, formedWord, result);
            formedWord.deleteCharAt(formedWord.length() - 1);
            temp = localTemp;
        }
    }

    public static void main(String[] args) {
        SeachAutoComplete seachAutoComplete = new SeachAutoComplete();
        seachAutoComplete.insert("pankaj");
        seachAutoComplete.insert("pancake");
        seachAutoComplete.insert("parking");
        seachAutoComplete.insert("pagination");
        System.out.println(seachAutoComplete.suggest("pa"));
        System.out.println(seachAutoComplete.suggest("pan"));
        System.out.println(seachAutoComplete.suggest("pag"));
        System.out.println(seachAutoComplete.suggest("parking"));
        System.out.println(seachAutoComplete.suggest("pang"));
    }
}
