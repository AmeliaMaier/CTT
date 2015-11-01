/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinationtestingtool;

/**
 *
 * @author Lia
 */
public class CTTTestCaseObject
{
    String[] testCaseValues;
    CTTTestCaseObject(int length)
    {
        this.testCaseValues = new String[length];
    }
    
    public void SetValue(int location, String value)
    {
        this.testCaseValues[location] = value;
    }
    
    public String GetValue(int location)
    {
        return this.testCaseValues[location];
    }
    
    public int GetNumberOfValues()
    {
        return this.testCaseValues.length;
    }
    
}
