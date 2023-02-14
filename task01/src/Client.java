import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException{

        if(args.length <= 0) {
            System.out.println("Please connect to a server using <host>:<port>\n");
            return;
        } 

        String[] arrSplit = args[0].split(":");
        //System.out.println(arrSplit[0]);
        //System.out.println(arrSplit[1]);

        try{

            Socket sock = new Socket(arrSplit[0]
                    , Integer.parseInt(arrSplit[1]));
            
            InputStream is = sock.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            OutputStream os = sock.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeUTF("Michelle Cheong");
            oos.writeUTF("mich.cxy@gmail.com");

            //split response
            String[] response = ois.readUTF().split(",");;
            

            //get Mean of numbers
            float totalSum = 0;
            for(int i = 0; i <  response.length; i++){
            totalSum += Float.parseFloat(response[i]);
            }
            Float mean = totalSum/response.length;
            System.out.println(mean);
            oos.writeFloat(mean);;
            
            //get Standard Deviation
            Float[] floatArr = Arrays.stream(response).map(Float::valueOf).toArray(Float[]::new);

            float standardDeviation = 0.0f;
            for(float num: floatArr) {
            standardDeviation += Math.pow(num - mean, 2);
            }
            float result = (float) Math.sqrt(standardDeviation/floatArr.length);
            oos.writeFloat(result);;

            oos.flush();
            oos.close();
            ois.close();
            is.close();
            os.close();
            sock.close();
        

        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
