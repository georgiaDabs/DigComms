import java.io.*;
import java.util.*;
import java.nio.file.Files;

public class InText
{
    ArrayList<Byte> slidingWindow;
    ArrayList<Byte> output;
    byte[] byteArray;
    private final long timeToEncode;
    private int startBytes;
    private int endBytes;
    public InText(String filename, int swSize, int bSize,String outFile){
        long startTime=0;
        long endTime=0;
        startBytes=0;
        endBytes=0;
        try{
            File f=new File(filename);
            byte[] byteArray=Files.readAllBytes(f.toPath());
            startBytes=byteArray.length;
            if(byteArray.length>swSize){
                //writeBytes(byteArray);
                startTime = System.currentTimeMillis();
                encode(swSize,bSize,byteArray);
                /*for(byte b:output){
                    System.out.println(b);
                }*/
                endTime = System.currentTimeMillis();
                
                printBytes(outFile);
            }else{
                System.out.println("sliding window size bigger than input");
            }
        }catch(IOException e){
            System.out.println("file not found");
        }
        timeToEncode=endTime-startTime;
    }
    public void writeBytes(){
        try{
        writeBytes(byteArray);
    }catch(IOException e){}
    }
    public long getTime(){
        return timeToEncode;
    }
    public void encode(int swSize, int bSize,byte[] bArray){
        output=new ArrayList<Byte>();
        int current=0;
        while(current<bArray.length){

            int greatestL=0;
            int greatestD=0;
            for(int d=1;d<swSize;d++){
                boolean match=true;
                int l=0;
                while((match)&&((current-d)>0)&&((current+l)<bArray.length)&&(l<d)){
                    if(l<bSize){
                        if(bArray[current-d+l]==bArray[current+l]){
                            //System.out.println("success at d="+d);
                            l++;
                        }else{
                            match=false;
                        }
                    }else{
                        match=false;
                    }
                }
                if(l>greatestL){
                    greatestL=l;
                    greatestD=d;
                    //System.out.println("top l at "+current+" gives D="+greatestD+" L="+greatestL);
                }
            }
           // System.out.println("int value---->"+greatestD+"byte value------->"+(byte)greatestD);
            output.add((byte)greatestD);
            //System.out.print((byte)greatestD);
           // System.out.println("int value---->"+greatestL+"byte value------->"+(byte)greatestL);
            output.add((byte)greatestL);
            //System.out.print((byte)greatestL);
            if((current)>=bArray.length){
                //System.out.println("end");
                char c='G';
                output.add((byte)c);
            }else{
                try{
                    if(greatestD!=0){
                        //System.out.println("greatestD is"+greatestD+"greatestL"+greatestL);
                        output.add(bArray[current+greatestL]);
                    }else{
                    //System.out.print(bArray[current+greatestL]+"\n");
                    output.add(bArray[current]);
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    //System.out.println(current);
                    //System.out.println(greatestD);
                    //System.out.println(bArray.length);
                }
            } 
            
            current+=greatestL+1;
        }
        endBytes=output.size();
    }

    public void printBytes(String outFile) throws IOException{
        BufferedWriter writer=new BufferedWriter(new FileWriter(outFile));
        File file=new File(outFile);
        FileOutputStream outStream=new FileOutputStream(file);
        for(Byte b: output){
            outStream.write(b);
        }
        outStream.flush();
        outStream.close();
    }
    public int getStartBytes(){
        return startBytes;
    }
    public int getEndBytes(){
        return endBytes;
    }
    public void writeBytes(byte[] array) throws IOException{
        BufferedWriter writer=new BufferedWriter(new FileWriter("C://Users//GIIIIIIINGEEEEEEE//Digital Communications//sampleBytes.txt"));
        for(byte b:array){
            System.out.print(b);
            writer.write(""+b);
        }
        writer.close();
       
    }
}
