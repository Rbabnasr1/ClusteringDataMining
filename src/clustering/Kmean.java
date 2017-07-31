/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rabab
 */
public class Kmean {

    public ArrayList<Record> getRandomK(int k, ArrayList<Record> records) {
        ArrayList<Record> finalK = new ArrayList<>();
        boolean taken[] = new boolean[records.size()];
        for (int i = 0; i < k; ++i) {
            int indexOfK = (int) (Math.random() * records.size());
            while (taken[indexOfK]) {
                indexOfK = (int) (Math.random() * records.size());
            }
            taken[indexOfK] = true;
            finalK.add(records.get(indexOfK));
        }
        return finalK;
    }

    public Double Manhaten(Record centroid, Record row) {
        double Distance = 0;
        for (int i = 0; i < centroid.att.size(); ++i) {
            Distance += Math.abs(centroid.att.get(i) - row.att.get(i));
        }
        return Distance;
    }

    public boolean clusterGroup(ArrayList<Cluster> clusters, ArrayList<Record> data) {
        boolean ismoved = false;
        for (int i = 0; i < data.size(); ++i) {
            double minCluster = Double.MAX_VALUE;
            Cluster newCluster = null;
            for (Cluster cluster : clusters) {
                if (Manhaten(cluster.centroid, data.get(i)) < minCluster) {
                    minCluster = Manhaten(cluster.centroid, data.get(i));
                    newCluster = cluster;
                }
            }
            if (newCluster != data.get(i).current) {
                ismoved = true;
            }
            data.get(i).current = newCluster;
            newCluster.records.add(data.get(i));
        }
        return ismoved;
    }

    public ArrayList<Record> Read() throws IOException {

        ArrayList<Record> data = new ArrayList<>();

        RandomAccessFile file = new RandomAccessFile("dataSet2.txt", "rw");
        String text = file.readLine();
        int count=0;
       // while (text != null) {
        while(count<10000){
            ArrayList<Double> row = new ArrayList<>();
            String split[] = text.split(", ");
            for (int i = 0; i < split.length; i++) {
                row.add(Double.parseDouble(split[i]));
            }
            Record r = new Record(row);
            data.add(r);
            text = file.readLine();
            count++;
        }
        System.out.println("data Size : " +data.size());
        return data;

    }

    public void write(ArrayList<Cluster> clusters) throws FileNotFoundException, IOException {

        for (int i = 0; i < clusters.size(); ++i) {
            RandomAccessFile output = new RandomAccessFile("Cluster" + (i + 1) + ".txt", "rw");
            output.write(("Mean: " + clusters.get(i).centroid.att.toString() + "\r\n").getBytes());
            System.out.println("*********************************************************************************");
            System.out.println((i + 1) + (") Mean: " + clusters.get(i).centroid.att));
            System.out.println("----------------------------------------------------");
            for (int j = 0; j < clusters.get(i).records.size(); ++j) {
                output.write((clusters.get(i).records.get(j).att.toString() + "\r\n").getBytes());
            }
        }

    }

    public void algorthim(int k, int d) throws FileNotFoundException, IOException {
        ArrayList<Record> data = Read();
        ArrayList<Cluster> clusters = new ArrayList<>();
        System.out.println("********************* Type Of Data Set *******************************************");
        String finaltype = "(";
        for (int i = 0; i < data.get(i).att.size(); ++i) {
            ArrayList<Double> distinctOfData = new ArrayList<>();
            for (int j = 0; j < data.size(); ++j) {
                if (!distinctOfData.contains(data.get(j).att.get(i))) {
                    distinctOfData.add(data.get(j).att.get(i));
                }
            }
            if (distinctOfData.size() > d) {
                finaltype = finaltype + " continuous  ";
            } else {
                finaltype = finaltype + " discrete  ";
            }

        }
        finaltype = finaltype + ")";

        System.out.println(finaltype);

        System.out.println("****************************************************************");

        ArrayList<Record> centroids = getRandomK(k, data);

        for (int i = 0; i < centroids.size(); ++i) {
            clusters.add(new Cluster(k, centroids.get(i)));
        }

        Long startTimeOfRun = System.currentTimeMillis();

        while (clusterGroup(clusters, data)) {
            for (int i = 0; i < clusters.size(); ++i) {
                clusters.get(i).centroid = new Record(clusters.get(i).getMean());
            }
        }

        Long endTimeOfRun = System.currentTimeMillis();
        System.out.println("*********************************************************************************");
        System.out.println("time to run the program :   " + (endTimeOfRun - startTimeOfRun));
        System.out.println("*********************************************************************************");
        write(clusters);

    }
}
