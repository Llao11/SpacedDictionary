package org.PoC;


// Java Program to demonstrate
// Simple JscrollPane
import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;

// Driver Class
public class testTime {
    // main function
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now : "+now);
        LocalDateTime min = LocalDateTime.MIN;
        System.out.println("MIN : "+min);
        LocalDateTime max = LocalDateTime.MAX;
        System.out.println("max : "+max);
        Duration duration = Duration.between(min,now);
        System.out.println("min-now "+duration.toMinutes());
        System.out.println("lll = "+ Duration.between(LocalDateTime.MIN,LocalDateTime.now()).toMinutes());
    }
}
