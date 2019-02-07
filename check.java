import java.io.*;
import java.nio.file.Files;
public class check{
    public static void main(String[] args){
        try{
            File f=new File("C://Users//GIIIIIIINGEEEEEEE//Digital Communications//encodedSample.txt");
            byte[] byteArray=Files.readAllBytes(f.toPath());
            for(int b=0;b<byteArray.length;b++){
                if(b%3==2){
                    System.out.print((char)byteArray[b]+" \n");
                }else{
                System.out.print(byteArray[b]+" ");}
            }
        }catch(IOException e){System.out.println("file not found");}
    }
}
