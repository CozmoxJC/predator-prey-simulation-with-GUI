import java.io.FileWriter;
import java.io.IOException;

public class GenerateCsv{
    private FieldStats fieldstats;
    private Field field;
    public static void main(String [] args){
        generateCsvFile("C:\\Users\\user\\Desktop\\test.csv"); 
    }

    private static void generateCsvFile(String sFileName){
        try{
            FileWriter writer = new FileWriter(sFileName);
            
            writer.append("Rabbits");
            writer.append(',');
            writer.append("Foxes");
            writer.append(',');
            writer.append("Wolves");
            writer.append(',');
            writer.append("Bears");
            writer.append(',');
            writer.append("Hunters");
            writer.append('\n');
            
            

            writer.flush();
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } 
    }
}
