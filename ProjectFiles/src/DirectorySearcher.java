import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;

public class DirectorySearcher{

    public DirectorySearcher(){
	}

	/***
	 * Converts the given size in bytes provided by fileSize into a new size in the lowest unit of storage (using base-10 units up to TB,
	 * in which 1 KB = 1000 B, 1 MB = 1000 KB, etc) in which the number is less than 1000, and has no more than 2 decimal places
	 * which the number is less than 1000, and has no more than 2 decimal places (through truncation of other decimal places). Returns the new size as a String.
	 * 
	 * @param fileSize a long that represents the size in bytes which was given
	 * @return a String that contains the new number given by the conversion, followed by the unit of storage it is represented in
	 */
	public String abbreviateFileSize(long fileSize){
		String fileType = "B";
		double fileSizeDecimal = (double) fileSize;
		while (fileSizeDecimal / 1000 >= 1){
			if (fileType == "B"){
				fileType = "KB";
			}
			else if (fileType == "KB"){
				fileType = "MB";
			}
			else if (fileType == "MB"){
				fileType = "GB";
			}
			else if (fileType == "GB"){
				fileType = "TB";
			}
			else {
				break;
			}
			fileSizeDecimal /= 1000;
		}
		//truncate to a maximum of 2 decimal places
		long storage = (long) (fileSizeDecimal * 100);
		double newDecimal = (double) (storage) / 100;
		String toReturn = "" + String.valueOf(newDecimal) + " " + fileType;
		return toReturn;
	}
	
	/***
	 * Searches the base directory, and find its size, as well as its 5 largest subdirectories and its 5 largest
	 * files.
	 * @param pathStr the String of the base directory's path
	 * @return an ArrayList with 11 elements, with the 1st element being a String of the base directory's
	 * size, elements 1-6 being a String of the 5 largest subfolders' sizes and paths, and the last 5 elements
	 * being a String of the 5 largest files' sizes and paths.
	 */
    public ArrayList<String> search(String pathStr){
		//store the size of the base directory
		long totalFileSize = 0;
		//create an ArrayList that will store the 5 largest files
		ArrayList<Long> largestFileSizeList = new ArrayList<Long>();
		ArrayList<String> largestFilePathList = new ArrayList<String>();
		for (int i = 0; i < 5; i++){
			long temp = (long) 0;
			largestFileSizeList.add(temp);
			largestFilePathList.add("");
		}
		//create an ArrayList that will store the 5 largest subfolders
		ArrayList<Long> largestFileFolderSizeList = new ArrayList<Long>();
		ArrayList<String> largestFileFolderPathList = new ArrayList<String>();
		for (int i = 0; i < 5; i++){
			long temp = (long) 0;
			largestFileFolderSizeList.add(temp);
			largestFileFolderPathList.add("");
		}
		//create an ArrayList to store all of the subfolders that still need to be explored
		ArrayList<Path> dirsToExplore = new ArrayList<Path>();
		Path firstDir = Paths.get(pathStr);
		//add the base directory to the 'subfolders to be explored' list
		dirsToExplore.add(firstDir);
		//if the 'subfolders to be explored' list contains subfolders, continue exploring them
		while (!dirsToExplore.isEmpty()){
			//explore the first subfolder in the 'subfolders to be explored' list, and remove it from the list
			Path currentDir = dirsToExplore.get(0);
			dirsToExplore.remove(0);
			//keep track of the size of the currently explored subfolder
			long currentFolderSize = 0;
			//look at each file/directory in the current subfolder
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir)) {
				for (Path filePath : stream) {
					String fileFullName = currentDir + "\\" + filePath.getFileName().toString();
					File file = new File(fileFullName);
					//check whether this is a file or a directory
					if (!Files.isDirectory(filePath)){
						//calculate file size (in bytes)
						long fileSize = file.length();
						/*check if file is larger than any file in the ArrayList of the 5 largest files. If so, add
						the file to the list*/
						totalFileSize += fileSize;
						currentFolderSize += fileSize;
						if (fileSize > largestFileSizeList.get(4)){
							largestFileSizeList.remove(4);
							largestFilePathList.remove(4);
							largestFileSizeList.add(4,fileSize);
							largestFilePathList.add(4,fileFullName);
							if (fileSize > largestFileSizeList.get(3)){
								largestFileSizeList.remove(4);
								largestFilePathList.remove(4);
								largestFileSizeList.add(3,fileSize);
								largestFilePathList.add(3,fileFullName);
								if (fileSize > largestFileSizeList.get(2)){
									largestFileSizeList.remove(3);
									largestFilePathList.remove(3);
									largestFileSizeList.add(2,fileSize);
									largestFilePathList.add(2,fileFullName);
									if (fileSize > largestFileSizeList.get(1)){
										largestFileSizeList.remove(2);
										largestFilePathList.remove(2);
										largestFileSizeList.add(1,fileSize);
										largestFilePathList.add(1,fileFullName);
										if (fileSize > largestFileSizeList.get(0)){
											largestFileSizeList.remove(1);
											largestFilePathList.remove(1);
											largestFileSizeList.add(0,fileSize);
											largestFilePathList.add(0,fileFullName);
										}
									}
								}
							}
						}
					}
					else {
						//since this is a directory, add it to the 'subfolders to be explored' list
						dirsToExplore.add(0,filePath);
					}
				}
				/*subfolder has been completely explored, so we check if its size is larger than any subfolder in
				the ArrayList of the 5 largest subfolders. If so, add the subfolder to the list*/
				if (currentFolderSize > largestFileFolderSizeList.get(4)){ 
					largestFileFolderSizeList.remove(4);
					largestFileFolderPathList.remove(4);
					largestFileFolderSizeList.add(4,currentFolderSize);
					largestFileFolderPathList.add(4,currentDir.toString());
					if (currentFolderSize > largestFileFolderSizeList.get(3)){
						largestFileFolderSizeList.remove(4);
						largestFileFolderPathList.remove(4);
						largestFileFolderSizeList.add(3,currentFolderSize);
						largestFileFolderPathList.add(3,currentDir.toString());
						if (currentFolderSize > largestFileFolderSizeList.get(2)){
							largestFileFolderSizeList.remove(3);
							largestFileFolderPathList.remove(3);
							largestFileFolderSizeList.add(2,currentFolderSize);
							largestFileFolderPathList.add(2,currentDir.toString());
							if (currentFolderSize > largestFileFolderSizeList.get(1)){
								largestFileFolderSizeList.remove(2);
								largestFileFolderPathList.remove(2);
								largestFileFolderSizeList.add(1,currentFolderSize);
								largestFileFolderPathList.add(1,currentDir.toString());
								if (currentFolderSize > largestFileFolderSizeList.get(0)){
									largestFileFolderSizeList.remove(1);
									largestFileFolderPathList.remove(1);
									largestFileFolderSizeList.add(0,currentFolderSize);
									largestFileFolderPathList.add(0,currentDir.toString());
								}
							}
						}
					}
				}
			} catch (IOException | DirectoryIteratorException x) {
				System.err.println("A caught exception: "+x);
			}
		}
		//exploration of the folders is complete
		/*Create an ArrayList containing the total size of the base folder, the 5 biggest subfolders,
		and the 5 biggest files, and return the ArrayList. Each size to be added to the ArrayList, originally in
		bytes, will be converted into a more compact String via abbreviateFileSize before being added to the list*/
		ArrayList<String> returnString = new ArrayList<String>();
		System.out.println("The size of "+firstDir.toString()+"\\ is "+abbreviateFileSize(totalFileSize));
		returnString.add(""+abbreviateFileSize(totalFileSize));
		for (int i = 0; i<5; i++){
			int k = i+1;
			if (largestFileFolderSizeList.get(i) <= 0){
				returnString.add(""+k+") ---");
			}
			else {
				System.out.println("#"+k+" largest folder (not including files in its subfolders) is "+largestFileFolderPathList.get(i)+" with "+abbreviateFileSize(largestFileFolderSizeList.get(i)));
				String folderString = "";
				folderString += ""+k+") "+largestFileFolderPathList.get(i)+"\\ with "+abbreviateFileSize(largestFileFolderSizeList.get(i));
				returnString.add(folderString);
			}
		}
		for (int i = 0; i<5; i++){
			int k = i+1;
			if (largestFileSizeList.get(i) <= 0){
				returnString.add(""+k+") ---");
			}
			else {
				System.out.println("#"+k+" largest file is "+largestFilePathList.get(i)+" with "+abbreviateFileSize(largestFileSizeList.get(i)));
				String fileString = "";
				fileString+=""+k+") "+largestFilePathList.get(i)+" with "+abbreviateFileSize(largestFileSizeList.get(i));
				returnString.add(fileString);	
			}
		}
        return returnString;
    }
}