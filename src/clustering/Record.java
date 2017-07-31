/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering;

import java.util.ArrayList;

/**
 *
 * @author rabab
 */
public class Record {
     ArrayList<Double> att;
    Cluster current = null;
    
    public Record(ArrayList<Double> row) {
        this.att = row;
    }
}