import java.io.*;
import java.util.Scanner;

public class TreeTraversal {
	public static void main(String args[]) {

		String[] filesList = {"in-order.txt", "in-order-reverse.txt", "random.txt"};
		String[] outFileList = {"in-order-output.txt", "in-order-reverse-output.txt", "random-output.txt"};
		for(int file = 0; file < filesList.length; file++) {
		BinaryTree tree = new BinaryTree();

		Scanner line = null;
		Scanner word = null;
		String fileName = filesList[file];

		try {
			line = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();  
		}

		//reading in and adding all words
		while(line.hasNextLine()){
			word = new Scanner(line.nextLine());
			while(word.hasNext()) {
				tree.insert(word.next());
			}
		}
		line.close();
		
		//Get all depth first search results and store in an array
		String[] depthFirst = tree.depthFirst();
		
		//Create a new output file
		File output = new File(outFileList[file]);
		FileWriter writer;
		try {
			writer = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("File: ");
			bw.write(filesList[file]);
			bw.newLine();
			bw.write("Depth First:");
			bw.newLine();
			
			// Write all depth first search results to file
			for(String s : depthFirst) {
				bw.write(s);
				bw.newLine();
				bw.newLine();
			}
			
			// Write breadth first results to file
			bw.write("Breadth First:");
			bw.newLine();
			bw.write(tree.breadthFirst());
			bw.newLine();
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
}

