import java.io.File;
import java.util.Scanner;
import java.util.Stack;

// Brea Zeller
// CPW 245 Winter 2019
// Programming Assignment #2: DirectorySize.java
// Description: The starter code, DirectorySize.java, gives a recursive method for finding a directory size.
// I need to add a method that accomplishes the same task without using recursion,
// using a Stack to store the subdirectories under a directory. 

public class DirectorySize {
    public static void main(String[] args) {
        System.out.print("Enter a directory or file: ");
        Scanner input = new Scanner(System.in);
        File directory = new File(input.nextLine());

        System.out.println(getSizeRecursive(directory) +
            " bytes");
        System.out.println(getSizeStack(directory) +
            " bytes");
        System.out.println(getSizeQueue(directory) +
            " bytes");
    }

    public static long getSizeRecursive(File file) {
        long size = 0;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < files.length; i++) {
                size += getSizeRecursive(files[i]);
            }
        } else {
            size += file.length();
        }

        return size;
    }

    public static long getSizeStack(File file) {


        Stack < File > stack = new Stack < > ();
        long size = 0;
        stack.push(file);

        while (!stack.isEmpty()) {
            File temp = stack.pop();
            if (temp.isFile()) {
                size += temp.length();
            } else {
                File[] files = temp.listFiles();
                for (int i = 0; files != null && i < files.length; i++) {
                    stack.push(files[i]);
                }
            }
        }
        return size;
    }

    public static long getSizeQueue(File file) {

        Queue < File > queue = new LinkedList < > ();
        long size = 0;
        queue.offer(file);

        while (!queue.isEmpty()) {
            File temp = queue.poll();
            if (temp.isFile()) {
                size += temp.length();
            } else {
                File[] files = temp.listFiles();
                for (int i = 0; files != null && i < files.length; i++) {
                    queue.add(files[i]);

                }
            }
        }
        return size;
    }
}
