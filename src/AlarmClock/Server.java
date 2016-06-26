/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlarmClock;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Server extends Thread{
    
    ServerSocket serverSocket;
    
    private int port;
    
    
    
    @Override
    public void run() {
        
        
        port = UserInterface.getPort();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(serverSocket.toString());        
            
            while(true)
            {
                Socket socket = serverSocket.accept();
                new ServerOne(socket);
                System.out.println(socket);
            }
         }
         catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connection accepted - " + serverSocket);
        
        
    }
    
    
}

class ServerOne extends Thread
{
    Socket socket;
    ObjectOutputStream out;
    String command;
    ObjectInputStream in;
    boolean theEnd = false;
    
    
    public ServerOne(Socket socket) {
        
        
            this.socket = socket;
            
            start();
        
        
    }

    public ServerOne() {
    }
    
    
    
    @Override
    public void  run ()
    {   
        
        while(!theEnd)
        {
            try {
                in = new ObjectInputStream(socket.getInputStream());
                
                command = (String) in.readObject();
                switch (command)
                {
                    case "download" : upload();
                    System.out.println(command);
                    break;
                        
                    case "upload" : download();
                    System.out.println(command);
                    break;
                        
                    case "END" :
                        theEnd = true;
                        System.out.println(command);
                        //in.close();out.close();
                        socket.close();
                        break;
                        
                    case "delete" :
                        System.out.println("delete");
                        delete();
                        break;
                        
                }
                
                
                
                
                
                
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerOne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        

    }

    private synchronized void upload() throws IOException 
    {
        out = new ObjectOutputStream(socket.getOutputStream());
        
        out.writeObject(UserInterface.taskStorage.taskStorage);
        out.flush();
    }

    private synchronized void download() throws IOException, ClassNotFoundException 
    {
       
        in = new ObjectInputStream(socket.getInputStream());
        UserInterface.taskStorage.taskStorage = (ArrayList<Task>) in.readObject();
        
    }

    private synchronized void delete() throws IOException, ClassNotFoundException 
    {
        String deleteTaskName;
        in = new ObjectInputStream(socket.getInputStream());
        
        deleteTaskName = (String) in.readObject();
        
        for (int i = 0; i < UserInterface.taskStorage.taskStorage.size(); i++) {
            
            if(UserInterface.taskStorage.taskStorage.get(i).getName().equals(deleteTaskName))
            {
                UserInterface.taskStorage.taskStorage.remove(i);
            }
            
        }
    }
    
    
    
    

}