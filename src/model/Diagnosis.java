/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author aerobe
 */
public class Diagnosis {
    private String[] Nursing_diagnosis;
    private String[] Medical_diagnosis;
    private String[] Collaborative_diagnosis;

    public Diagnosis(String[] Nursing_diagnosis, String[] Medical_diagnosis, String[] Collaborative_diagnosis){
        this.Nursing_diagnosis = Nursing_diagnosis;
        this.Medical_diagnosis = Medical_diagnosis;
        this.Collaborative_diagnosis = Collaborative_diagnosis;
    }

    public String[] getND(){
        return Nursing_diagnosis;
    }

    public void setND(String[] Nursing_diagnosis){
        this.Nursing_diagnosis = Nursing_diagnosis;
    }

    public String[] getMD(){
        return Medical_diagnosis;
    }

    public void setMD(String[] Medical_diagnosis){
        this.Medical_diagnosis = Medical_diagnosis;
    }

    public String[] getCD(){
        return Collaborative_diagnosis;
    }

    public void setCD(String[] Collaborative_diagnosis){
        this.Collaborative_diagnosis = Collaborative_diagnosis;
    }
}
