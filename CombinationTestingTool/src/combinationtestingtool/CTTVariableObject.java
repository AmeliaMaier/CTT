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
public class CTTVariableObject
{

    String variableName;
    String[] variableValues;
    
   /*
     Separate by :
     Before : is variable name
     After : is all possible values
     Separate second section by ,
     Each value is a variable value*/
    CTTVariableObject(String rawInput)
    {
        String[] singleVariableInfoArray = rawInput.split(":");
        variableName = singleVariableInfoArray[0].trim();
        variableValues = singleVariableInfoArray[1].split(",");

        for (int x = 0; x < variableValues.length; x++)
        {
            variableValues[x] = variableValues[x].trim();
        }

    }
    
    public String GetName()
    {
        return variableName;
    }
    
    public int GetNumberOfValues()
    {
        return variableValues.length;
    }
    
    public String GetValue(int location)
    {
        return variableValues[location];
    }

}
