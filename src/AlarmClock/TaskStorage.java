/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlarmClock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Александр
 */
public class TaskStorage implements Serializable{
    
    ArrayList<Task> taskStorage = new ArrayList<>();
    
    public TaskStorage() {
    }
    
    public void addTask(Task task)
    {
        taskStorage.add(0,task);
        
        
    }
    
    public void taskSort()
    {
        Collections.sort(taskStorage, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.date.compareTo(o2.date);
            }
        });
    }
}
