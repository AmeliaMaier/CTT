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
    List<Boolean> status;

    CTTVariableValuesToMatch()
    {
        this.values = new ArrayList<>();
        this.status = new ArrayList<>();
    }

    public void AddValue(String value)
    {
        this.values.add(value);
        this.status.add(true);
    }

    public void RemoveValue(int location)
    {
        this.status.set(location, false);
    }

    public boolean ContainsValue(String value)
    {
        if (this.values.contains(value))
        {
            return this.status.get(values.indexOf(value));
        } else
        {
            return false;
        }
    }

    public int GetLocation(String value)
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
