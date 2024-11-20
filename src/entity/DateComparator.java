/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Comparator;

/**
 * @author Hafiz Chew Hoe Leong
 */
public class DateComparator implements Comparator<Programme> {

    @Override
    public int compare(Programme programme1, Programme programme2) {

        return programme1.getDateIntroduce().compareTo(programme2.getDateIntroduce());
    }
}
