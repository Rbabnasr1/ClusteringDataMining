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
public class Cluster {

    int k;
    int numberOfAtt;
    ArrayList<Record> records = new ArrayList<>();
    Record centroid;

    public Cluster(int k, Record centroid) {
        this.k = k;
        this.centroid = centroid;
        numberOfAtt = centroid.att.size();
    }

    ArrayList<Double> getMean() {
        double sum[] = new double[numberOfAtt];
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < numberOfAtt; ++i) {
            for (Record r : records) {
                sum[i] += r.att.get(i);
            }
            result.add(sum[i] / records.size());
        }
        return result;
    }

}
