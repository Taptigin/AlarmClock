/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlarmClock;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Александр
 */
public class Task implements Serializable{
 
    public String name;
    public String description;
    public Date date;
    
    SimpleDateFormat formatDate = new SimpleDateFormat();
    
    public Date taskDate;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
    
    public Task()
    {}
    
    public Task(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }
    
    public Date dateWorking(String buildDate) throws ParseException
    {
        formatDate.applyPattern("dd.MM.yyyy.HH:mm");
        taskDate = formatDate.parse(buildDate);
        return taskDate;
    }

}
