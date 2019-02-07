import java.io.*;
import java.nio.file.Files;
import java.util.*;
public class OutText
{
    ArrayList<Byte> bytes;
    private final long timeToDecode;
    public OutText(String file, String outFile){
        long startTime=0;
        long endTime=0;
        try{
            File f=new File(file);
            byte[] byteArray=Files.readAllBytes(f.toPath());
            startTime= System.currentTimeMillis();
            decode(byteArray);
            endTime= System.currentTimeMillis();
            printBytes(outFile);
        }catch(IOException e){
            System.out.println("exception with file");
        }
        timeToDecode=endTime-startTime;
    }
    public long getTime(){return timeToDecode;}
    public void decode(byte[] byteArray){
        bytes=new ArrayList<Byte>();
        for(int i=0;i<byteArray.length-2;i=i+3){
            //System.out.println("treble is"+byteArray[i]+","+byteArray[i+1]+","+byteArray[i+2]);
            if(byteArray[i]==(byte)0){
                bytes.add(byteArray[i+2]);

            }else{
                //System.out.println("section to be added is from"+byteArray[i]+" to "+(byteArray[i]-byteArray[i+1])+"away");
                ArrayList<Byte> currentBytes=new ArrayList<Byte>();
                
                    for(int j=byteArray[i];j>(byteArray[i]-byteArray[i+1]);j--){

                        currentBytes.add(bytes.get(bytes.size()-j));
                    }
                
                bytes.addAll(currentBytes);
                bytes.add(byteArray[i+2]);
            }
        }

    }

    public void printBytes(String outFile) throws IOException{
        BufferedWriter writer=new BufferedWriter(new FileWriter(outFile));
        File file=new File(outFile);
        FileOutputStream outStream=new FileOutputStream(file);
        for(Byte b: bytes){
            outStream.write(b);
        }
        outStream.flush();
        outStream.close();
    }
}
