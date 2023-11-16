//        Design an in-memory file system to simulate the following functions:
//
//        ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.
//
//        mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
//
//        addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.
//
//        readContentFromFile: Given a file path, return its content in string format.
//
//        deleteDirOrFile: Delete the given dir/folder or a file as a path.
//
//        Example:
//
//        Input:
//        FileSystem fileSystem = new FileSystem();
//        System.out.println(fileSystem.ls("/"));
//        fileSystem.mkdir("/a/b/c");
//        System.out.println(fileSystem.ls("/"));
//        fileSystem.addContentToFile("/a/b/c/file.txt", "Hello World");
//        System.out.println(fileSystem.readContentFromFile("/a/b/c/file.txt"));
//        fileSystem.mkdir("/a/e");
//        System.out.println(fileSystem.ls("/a"));
//        fileSystem.deleteDirOrFile("/a/e");
//        System.out.println(fileSystem.ls("/a"));

//        Output:
//        []
//        [a]
//        Hello World
//        [b, e]
//        [b]


package main.java;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

class Dir {
    boolean isFile;
    HashMap<String, Dir> dirMap = new HashMap<>();
    String content = "";
}

public class FileSystem {

    Dir root;

    public FileSystem() {
        root = new Dir();
    }

    List<String> ls(String path) {
        Dir temp = root;
        List<String> result = new ArrayList<>();
        if (!path.equals("/")) {
            String[] steps = path.split("/");
            for (int i = 1; i < steps.length - 1; i++) {
                temp = temp.dirMap.get(steps[i]);
            }
            if (temp.isFile) {
                result.add(steps[steps.length - 1]);
                return result;
            } else {
                temp = temp.dirMap.get(steps[steps.length - 1]);
            }
        }

        result = new ArrayList<>(temp.dirMap.keySet());
        Collections.sort(result);
        return result;
    }

    void mkdir(String path) {
        Dir temp = root;
        String[] steps = path.split("/");
        for (int i = 1; i < steps.length; i++) {
            if (!temp.dirMap.containsKey(steps[i])) {
                temp.dirMap.put(steps[i], new Dir());
            }
            temp = temp.dirMap.get(steps[i]);
        }
    }

    String readContentFromFile(String path) {
        Dir temp = root;
        String[] steps = path.split("/");

        for (int i = 1; i < steps.length - 1; i++) {
            temp = temp.dirMap.get(steps[i]);
        }

        return temp.dirMap.get(steps[steps.length - 1]).content;
    }

    void addContentToFile(String path, String data) {
        Dir temp = root;
        String[] steps = path.split("/");

        for (int i = 1; i < steps.length - 1; i++) {
            temp = temp.dirMap.get(steps[i]);
        }
        if (!temp.dirMap.containsKey(steps[steps.length - 1])) {
            temp.dirMap.put(steps[steps.length - 1], new Dir());
        }
        temp = temp.dirMap.get(steps[steps.length - 1]);
        temp.isFile = true;
        temp.content += data;
    }

    void deleteDirOrFile(String path) {
        Dir temp = root;
        String[] steps = path.split("/");

        for (int i = 1; i < steps.length - 1; i++) {
            temp = temp.dirMap.get(steps[i]);
        }
        //temp = temp.dirMap.get(steps[steps.length-1]);
        temp.dirMap.remove(steps[steps.length - 1]);
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        System.out.println(fileSystem.ls("/"));
        fileSystem.mkdir("/a/b/c");
        System.out.println(fileSystem.ls("/"));
        fileSystem.addContentToFile("/a/b/c/file.txt", "Hello World");
        System.out.println(fileSystem.readContentFromFile("/a/b/c/file.txt"));
        fileSystem.mkdir("/a/e");
        System.out.println(fileSystem.ls("/a"));
        fileSystem.deleteDirOrFile("/a/e");
        System.out.println(fileSystem.ls("/a"));
    }
}
