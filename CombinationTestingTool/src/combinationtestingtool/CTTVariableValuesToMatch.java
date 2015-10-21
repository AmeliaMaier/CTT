/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinationtestingtool;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lia
 */
public class CTTVariableValuesToMatch
{
    List<String> values;
    CTTVariableValuesToMatch()
    {
       this.values = new ArrayList<>(); 
    }
    
    public void AddValue(String value)
    {
        this.values.add(value);
    }
    
    public void RemoveValue(int location)
    {
        this.values.remove(location);
    }
    
    public boolean ContainsValue(String value)
    {
        return this.values.contains(value);
    }
    
    public int GetLocation (String value)
    {
        return this.values.indexOf(value);
    }
    
    public String GetValue(int location)
    {
        return this.values.get(location);
    }
    
    public boolean IsEmpty()
    {
        return values.isEmpty();
    }
}
