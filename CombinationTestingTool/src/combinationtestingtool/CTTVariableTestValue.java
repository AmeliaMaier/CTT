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
public class CTTVariableTestValue
{
    boolean set;
    String value;
    
    CTTVariableTestValue()
    {
        this.set = false;
        this.value = "";
    }
    
    public void SetValue(String value)
    {
        this.value = value;
        this.set = true;
    }
    
    public String GetValue()
    {
        return this.value;
    }
    
    public boolean IsSet()
    {
        return this.set;
    }
}
