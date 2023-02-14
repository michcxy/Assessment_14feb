package task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyser {

    public static void main(String[] args) {

        String directoryName = args[0];
        
        try {
    
            // Create a file object
            File f = new File(directoryName);

            //list of files
            File[] files = f.listFiles();

            // list that holds strings of a file
            List<String> listOfStrings
                = new ArrayList<String>();

            for(int i = 0; i < files.length; i ++){
                // load data from file
                BufferedReader br = new BufferedReader(
                new FileReader(files[i]));
        
                // read entire line as string
                String line = br.readLine();
        
                // checking for end of file
                while (line != null) {
                    listOfStrings.add(line);
                    line = br.readLine();
                }
        
                // closing bufferreader object
                br.close();
                // storing the data in arraylist to array
                //String[] array
                //= listOfStrings.toArray(new String[0]);
                }

                String strippedText = String.join(", ", listOfStrings);
                strippedText = strippedText.replaceAll("[\\p{P}&&[^\u0027]]","").toLowerCase();
                strippedText = strippedText.replaceAll("'", "");
                //System.out.println(strippedText);

                String[] textArray = strippedText.split(" ");

                Map<String, Map<String, Integer>> firstMap = new HashMap<>();
                Map<String, Integer> secondMap = new HashMap<>();

                    for(int i = 0; i < textArray.length-1; i++){
                        String currentWord = textArray[i];
                        String nextWord = textArray[i+1];
                
                        if(firstMap.containsKey(currentWord)){
                            if(!secondMap.containsKey(nextWord)){
                                secondMap.put(nextWord, 1);
                    
                            }
                            else{
                                int initialCount = secondMap.get(nextWord);
                                secondMap.replace(nextWord, initialCount+1);
                                
                            }
                            firstMap.replace(currentWord, secondMap);
                        }
                        else{
                            
                            firstMap.put(currentWord, secondMap);
                        }
                        
                    }
        
                //System.out.println(secondMap);
                System.out.println(firstMap);

                //iterate nested map
                for(int i = 0; i < firstMap.size(); i++) {
                    for(int j = 0; j < secondMap.size(); j++) {
                  //probability calculation.......
                    } 
                 }
                
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    
}
