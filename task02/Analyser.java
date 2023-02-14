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
                //System.out.println(Arrays.toString(textArray));

                /*
                 * - initial: lastWord = ""; currentWord = words[i];
                - if lastWord is empty then assign lastWord = currentWord and continue to next loop
                - how to add/update the map: check if lastWord is already in the map.
                a. if yes, check if currentWord is in the submap
                - if yes, increment the integer tagged to currentWord using replace. 
                - if no, put currentWord, 1 into the submap
                b. if no, put lastWord, (currentWord, 1)) into the map (google nested maps for more details on this)
                 */

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
                        }
                        else{
                            firstMap.put(currentWord, secondMap);
                        }
                        
                    }
        
                System.out.println(secondMap);
                //System.out.println(firstMap);



             
                for(int j = 0; j < firstMap.size(); j++) {
                    for(int k = 0; j < secondMap.size(); k++) {
                  //do actions here 
                    } 
                 }
                

                    /*
                    for(int i = 0; i < firstMap.size(); i++) {
                        int secondMap = firstMap.get(i);
                    for(int j = 0; j < secondMap.size(); j++) {

                        String currentWord = textArray[i];
                        if(prevWord == ""){
                            prevWord = currentWord;
                        }
                        else{
                            prevWord = textArray[i-1];
                        }
                        //check if prevWord is in firstMap
                        if(firstMap.containsKey(prevWord)){
                            if(secondMap.containsKey(currentWord)){
                                int initialCount = firstMap.get(prevWord).get(currentWord);
                                secondMap.replace(currentWord, initialCount + 1);
                            }
                            else{
                                secondMap.put(currentWord, 1);
                            }
                        }
                        else{
                            firstMap.put(prevWord, secondMap);
                        }
                 } 
                }
                
                }
                System.out.print(firstMap);
                 */
  

                //OriginalFrequency = Outermap.get(word).get(word2)

                //Outermap.get(word).replace(word2, originalFrequency + 1)    

            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

     
    
}
