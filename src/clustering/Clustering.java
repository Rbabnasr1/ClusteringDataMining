/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author rabab
 */
public class Clustering {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         Scanner input = new Scanner(System.in);
         
        System.out.println("========================== Cluster (K-mean) ===========================================");
         
        System.out.println("=======================================================================================");
         System.out.println("Enter K nubmer :");
        int k = input.nextInt();
        System.out.println("=====================================================================");
        System.out.println("enter d distict :");
              int d = input.nextInt(); 
                System.out.println("=====================================================================");
       
        Kmean kmean=new Kmean();
        kmean.algorthim(k,d);
    }
    
}
