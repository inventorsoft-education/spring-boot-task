package demohometask.demohometask;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FileOperation {

    public static void writeObject(Team object){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\IdeaProjects\\InventorSoftProjects\\demo-home-task\\src\\main\\java\\demohometask\\demohometask\\team.txt")))
        {
            oos.writeObject(object);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
