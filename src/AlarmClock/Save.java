/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlarmClock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 *
 * @author Александр
 */
public class Save {
  
    File file = new File("dataBase");
    
    void saveFile(ArrayList<Task> taskStorage ) throws InterruptedException
    {
       synchronized(file){
       if (!file.canWrite())file.wait();
       try
       {
          ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
          out.writeObject(taskStorage);
          out.close();out.flush();
           
       }
       catch(IOException e1)
       {
           System.out.println("Проблемы с схоранением");
       }
       file.notifyAll();
    }}
    
    ArrayList<Task> loadFile() throws InterruptedException
    {
        ArrayList<Task> taskStorage = new ArrayList<>();
        synchronized(file){
        if (!file.canRead()) file.wait();
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
           try{ taskStorage = (ArrayList<Task>)in.readObject();}
           catch(ClassNotFoundException e ){System.out.println("class not found");}
           in.close();
        }
        catch(IOException e2)
        {
            System.out.println("Проблемы с загрузкой");
        }
        
        file.notifyAll();
    }
        
        return taskStorage;
    }
    
    void createFile() 
    {
       synchronized(file){
       file.delete();
       try { file.createNewFile();}
       catch(IOException e ){e.printStackTrace();}
       file.notifyAll();
    }
    }
}
