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
    CTTVariableTestValue[] testCaseValues;
    CTTTestCaseObject(int length)
    {
        this.testCaseValues = new CTTVariableTestValue[length];
        for(int x = 0; x < length; x++)
        {
            this.testCaseValues[x] = new CTTVariableTestValue();
        }
    }
    
    public void SetValue(int location, String value)
    {
        this.testCaseValues[location].SetValue(value);
    }
    
    public String GetValue(int location)
    {
        return this.testCaseValues[location].GetValue();
    }
    
    public int GetNumberOfValues()
    {
        return this.testCaseValues.length;
    }
    
    public boolean IsNull(int location)
    {
        return !this.testCaseValues[location].IsSet();
    }  
    
    public boolean IsSet(int location)
    {
        return this.testCaseValues[location].IsSet();
    }
            
}
