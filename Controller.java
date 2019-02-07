import java.io.*;
import java.io.IOException;
public class Controller
{
    public static void main(String[] args){
        try{
            FileOutputStream fos = new FileOutputStream("C://Users//GIIIIIIINGEEEEEEE//Digital Communications/bwresults.txt");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String results="";
            String inFile="C://Users//GIIIIIIINGEEEEEEE//Digital Communications//blackAndWhite.jpg";
            String outFile="C://Users//GIIIIIIINGEEEEEEE//Digital Communications//encodedSample.txt";
            String decodedFile="C://Users//GIIIIIIINGEEEEEEE//Digital Communications//decodedBW.jpg";
            for(int i=10;i<130;i+=10){
                for(int j=10;j<130;j+=10){
                    bw.write("=========================================================================================");
                    bw.newLine();
                    bw.write("SLIDING WINDOW:"+i+" BUFFER:"+j+"\n");
                    bw.newLine();
                    System.out.println("SLIDING WINDOW:"+i+" BUFFER:"+j);
                    InText t =new InText(inFile,i,j,outFile);
                    OutText o=new OutText(outFile,decodedFile);
                    bw.write("time to encode:"+t.getTime()+" time to decode:"+o.getTime());
                    bw.newLine();
                    System.out.println("time to encode:"+t.getTime()+" time to decode:"+o.getTime());
                    bw.write("start Bytes:"+t.getStartBytes()+" endBytes:"+t.getEndBytes()+" ratio:"+(float)(t.getStartBytes())/(t.getEndBytes()));
                    bw.newLine();
                    System.out.println("start Bytes:"+t.getStartBytes()+" endBytes:"+t.getEndBytes()+" ratio:"+(float)(t.getEndBytes())/(t.getStartBytes()));
                    bw.write("============================================================================");
                    bw.newLine();
                }
            }

            
            bw.close();
        }catch(IOException e){
            System.out.println("problem with file");
        }
    }
}
