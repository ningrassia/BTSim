import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Importer designed to parse .csv file for the BitTorrent Simulator project.
 * The file data follows format agreed upon by the group member.<br><br>
 * Data imported will be stored into the following five ArrayList objects:<br>
 * 1. {@link #peerIDList}<br>
 * 2. {@link #upSpdList}<br>
 * 3. {@link #downSpdList}<br>
 * 4. {@link #startTimeList}<br>
 * 5. {@link #endTimeList}<br>
 * @author Hieu
 */
public class BTSimImporter {
    
    /**
     * List of errors detected while parsing an input file.
     */
    public static ArrayList<String> errorList = new ArrayList<String>();
    
    /**
     * List of IDs of the peers from input file, in the same order as in the file.
     */
    public static ArrayList<Integer> peerIDList = new ArrayList<Integer>();
    
    /**
     * List of upload speeds of the peers parsed from input file, in the same 
     * order as in the file.
     */
    public static ArrayList<Integer> upSpdList = new ArrayList<Integer>();
    
    /**
     * List of download speeds of the peers parsed from input file, in the same 
     * order as in the file.
     */
    public static ArrayList<Integer> downSpdList = new ArrayList<Integer>();
    
    /**
     * List of start times of the peers parsed from input file, in the same 
     * order as in the file.
     */
    public static ArrayList<Integer> startTimeList = new ArrayList<Integer>();
    
    /**
     * List of end times of the peers parsed from input file, in the same 
     * order as in the file.
     */
    public static ArrayList<Integer> endTimeList = new ArrayList<Integer>();
    
    /**
     * An ArrayList object containing five other ArrayList objects.
     * Each of which contain data parsed from input file. The lists are stored
     * in the following order.<br>
     * 1. {@link #peerIDList}<br>
     * 2. {@link #upSpdList}<br>
     * 3. {@link #downSpdList}<br>
     * 4. {@link #startTimeList}<br>
     * 5. {@link #endTimeList}<br>
     */
    
    //final fields to implement warning feature for potentially bad inputs
    //public static final int maxUpSpd;
    //public static final int maxDownSpd;
    //public static final int simDuration;
    
    /**
     * A boolean field to keep track of whether the file the importer last read
     * has error in it. True if there is at least one error. False otherwise.
     */
    public static boolean badFile = false;
    
    public static String torrentName;
    public static int torrentSize;
    
    
    public BTSimImporter() {
        
    }
    
    public BTSimImporter(String fileLoc) {
        readFile(fileLoc);
    }
        
    //////////////HELPER METHODS////////////////////////////////////////////////
    public static void readFile(String fileLoc) {
        
        // Whenever this method is called, clear the error list and imported
        // peer list so they could be updated with new data. The file by default
        // is assume to be good.
        errorList.clear();
        peerIDList.clear();
        upSpdList.clear();
        downSpdList.clear();
        startTimeList.clear();
        endTimeList.clear();
        badFile = false;
        
        BufferedReader br = null;
        String line = "";
        int lineNum = 0;
        
        try {
            br = new BufferedReader (new FileReader(fileLoc));
            
            // Parse first line for torrent name and torrent size.
            line = br.readLine();
            lineNum++;
            parseTorrentLine(line);
            
            while ((line = br.readLine()) != null) {
                lineNum++;
                parsePeerLine(lineNum, line);
            }
            
            if (lineNum == 1) {
                // This happen when the loop to parse peer information never
                // ran.
                errorList.add("No peer information found in file");
                peerIDList.add(-1);
                upSpdList.add(-1);
                downSpdList.add(-1);
                startTimeList.add(-1);
                endTimeList.add(-1);
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("File not found");
            errorList.add("File not found");
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("IOException");
            errorList.add("IOException");
        } finally {
            // Close the reader
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        if (!errorList.isEmpty()) {
            badFile = true;
        }
    }
    
    
    private static void parseTorrentLine(String line) {
        if (line == null) {
            errorList.add("Line 1 is empty");
            return;
        }
        
        String[] cell = line.split(",");
        if (cell.length != 2) {
            errorList.add("Wrong format at line 1");
            return;
        }
        
        torrentName = cell[0];
        
        try {
            torrentSize = Integer.parseInt(cell[1]);
        } catch (Exception e) {
            errorList.add("Wrong format at cell (1, 2)");
            torrentSize = -1;
        }
    }
    
    
    /**
     * Helper method to parse the peer info from the line it was passed.
     * @param lineNum The number of the line in the file. Passed to use for 
     * error reporting purpose.
     * @param line The line to be parsed
     * @return An ImportedPeer object including the data extracted from the line.
     */
    private static void parsePeerLine(int lineNum, String line) {
        String[] cell = line.split(",");
        
        if (cell.length != 5) {
            // If the line is of wrong format, throw exception and end the parsing process.
            errorList.add("Wrong format at line " + lineNum);
            peerIDList.add(-1);
            upSpdList.add(-1);
            downSpdList.add(-1);
            startTimeList.add(-1);
            endTimeList.add(-1);
            return;
        }
        
        peerIDList.add(Integer.parseInt(cell[0]));
        
        int addCase = 0;
        while (addCase != 4) {
            int temp = 0;
            addCase++;
            try {
                temp = Integer.parseInt(cell[addCase]);
            } catch (Exception e) {
                errorList.add
                ("Wrong format at cell (" + lineNum + ", " + addCase + ")");
                temp = -1;
            }
            switch (addCase) {
                case 1:
                    // Column 2: upload speed
                    upSpdList.add(temp);
                    break;
                case 2:
                    // Column 3: download speed
                    downSpdList.add(temp);
                    break;
                case 3:
                    // Column 4: start time
                    startTimeList.add(temp);
                    break;
                case 4:
                    // Column 5: end time
                    endTimeList.add(temp);
                    break;
                default:
                    break;
            }
        }        
    }
    
}
