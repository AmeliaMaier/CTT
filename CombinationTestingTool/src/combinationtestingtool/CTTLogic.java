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
public class CTTLogic
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
    }

    public String[][] Solve(String userInput)
    {
        String[][] systemOutput;
        List<CTTTestCaseObject> testCasesFullList = new ArrayList<>();
        String[] rawUserInputArray = userInput.split("\\n");
        CTTVariableObject[] cttVariableObjectsArray = new CTTVariableObject[rawUserInputArray.length];
        for (int x = 0; x < rawUserInputArray.length; x++)
        {
            cttVariableObjectsArray[x] = new CTTVariableObject(rawUserInputArray[x]);
        }
        SortArray(cttVariableObjectsArray);
        FillVariableNames(cttVariableObjectsArray, testCasesFullList);
        FillFirstTwoColumns(testCasesFullList, cttVariableObjectsArray);
        return systemOutput;
    }

    private void SortArray(CTTVariableObject[] cttVariableObjectsArray)
    {
        List<CTTVariableObject> sorting = new ArrayList<>();
        for (int x = 0; x < cttVariableObjectsArray.length; x++)
        {
            sorting.add(cttVariableObjectsArray[x]);
        }
        int location, size;
        for (int toSortLocation = 0; toSortLocation < cttVariableObjectsArray.length; toSortLocation++)
        {
            location = 0;
            size = 0;
            for (int x = 0; x < sorting.size(); x++)
            {
                if (sorting.get(x).GetNumberOfValues() >= size)
                {
                    location = x;
                    size = sorting.get(x).GetNumberOfValues();
                }
            }
            cttVariableObjectsArray[toSortLocation] = sorting.remove(location);
        }
    }

    private void FillVariableNames(CTTVariableObject[] cttVariableObjectsArray, List<CTTTestCaseObject> testCasesFullList)
    {
        testCasesFullList.add(new CTTTestCaseObject(cttVariableObjectsArray.length));
        for (int x = 0; x < cttVariableObjectsArray.length; x++)
        {
            testCasesFullList.get(0).SetValue(x, cttVariableObjectsArray[x].GetName());
        }
    }

    /* will need to be edited to work for nth value sorting*/
    private void FillFirstTwoColumns(List<CTTTestCaseObject> testCasesFullList, CTTVariableObject[] cttVariableObjectsArray)
    {
        for (int firstVariable = 0; firstVariable < cttVariableObjectsArray[0].GetNumberOfValues(); firstVariable++)
        {
            for (int secondVariable = 0; secondVariable < cttVariableObjectsArray[1].GetNumberOfValues(); secondVariable++)
            {
                testCasesFullList.add(new CTTTestCaseObject(cttVariableObjectsArray.length));
                testCasesFullList.get(testCasesFullList.size()-1).SetValue(0, cttVariableObjectsArray[0].GetValue(firstVariable));
                testCasesFullList.get(testCasesFullList.size()-1).SetValue(1, cttVariableObjectsArray[1].GetValue(secondVariable));

            }
        }
    }

}
