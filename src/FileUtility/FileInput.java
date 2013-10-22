package FileUtility;

import java.io.*;

//**************************************************************************
// Class FileInput controls reading from a text file by opening the file,
// reading one line at a time, keeping track of the end of file, and handling
// I/O errors.
//**************************************************************************
public class FileInput {
	BufferedReader fileInput;
	private String fileName;
	boolean moreData;

	// **************************************************************************
	// This constructor opens the file given by the name passed in the
	// parameter.
	// **************************************************************************
	public FileInput() {
		fileName = null;
		moreData = false;
	}// end FileInput constructor

	public void openFile(String name) {
		if (!name.equals(null)) {
			fileName = name;
			try {
				File inFile = new File(name);
				FileReader fileReader = new FileReader(inFile);
				fileInput = new BufferedReader(fileReader);
				moreData = true;
			}// end try
			catch (IOException error) {
				System.err.println("An error occurred reading from file "
						+ fileName + "!\n");
				System.exit(1);
			}// end catch

		}// end if
	}// end openFile constructor

	// **************************************************************************
	// This method reads and returns one line of the text file. If the file
	// is empty, an empty string is returned.
	// **************************************************************************
	public String readLine() {
		String inputLine;

		if (fileName.equals(null)) {
			System.err
					.println("There is no file opened. Please check the file name or open the file");
			System.exit(1);
		}// end if

		try {
			inputLine = fileInput.readLine();
			if (inputLine == null)
				moreData = false;
			return inputLine;
		}// end try
		catch (IOException error) {
			System.err.println("An error occurred reading from file "
					+ fileName + "!\n");
			System.exit(1);
		}// end catch
		return null;
	}// end readLine method

	public boolean hasMoreData() {
		return moreData;
	}// end hasMoreData method

} // end FileInput class

