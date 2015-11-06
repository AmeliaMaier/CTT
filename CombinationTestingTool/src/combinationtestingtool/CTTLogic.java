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

    int caseLocation;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
    }

    public String Solve(String userInput)
    {
        int nth = 2; //temp hard code since no other option at the moment
        int numMatchesRequired;
        int[] matchLocationsArray = new int[1];
        String systemOutput = "";
        List<CTTVariableValuesToMatch> matchesNeededList = new ArrayList<>();
        List<CTTTestCaseObject> testCasesFullList = new ArrayList<>();
        String[] rawUserInputArray = userInput.split("\\n");
        CTTVariableObject[] cttVariableObjectsArray = new CTTVariableObject[rawUserInputArray.length];
        for (int x = 0; x < rawUserInputArray.length; x++)
        {
            cttVariableObjectsArray[x] = new CTTVariableObject(rawUserInputArray[x]);
        }
        SortArray(cttVariableObjectsArray);
        FillVariableNames(cttVariableObjectsArray, testCasesFullList);
        FillNthColumns(nth, testCasesFullList, cttVariableObjectsArray);
        for (int variableLocation = nth; variableLocation < cttVariableObjectsArray.length; variableLocation++)
        {
            for (int valueLocation = 0; valueLocation < cttVariableObjectsArray[variableLocation].GetNumberOfValues(); valueLocation++)
            {
                matchesNeededList = FillMatchesNeeded(matchesNeededList, cttVariableObjectsArray, variableLocation);
                numMatchesRequired = variableLocation;
                this.caseLocation = 1;
                //CleanMatchLocationsArray(variableLocation, matchLocationsArray);
                while (!matchesNeededList.isEmpty())
                {
                    if (this.caseLocation == 1)
                    {
                        while (!AnswerFound(variableLocation, matchLocationsArray, matchesNeededList, numMatchesRequired, testCasesFullList))
                        {
                            numMatchesRequired = MakeEasier(numMatchesRequired, testCasesFullList, variableLocation, cttVariableObjectsArray.length);
                        }
                    } else if (!AnswerFound(variableLocation, matchLocationsArray, matchesNeededList, numMatchesRequired, testCasesFullList))
                    {
                        while (!AnswerFound(variableLocation, matchLocationsArray, matchesNeededList, numMatchesRequired, testCasesFullList))
                        {
                            numMatchesRequired = MakeEasier(numMatchesRequired, testCasesFullList, variableLocation, cttVariableObjectsArray.length);
                        }
                    }
                    AddTestValue(matchesNeededList, testCasesFullList, matchLocationsArray, cttVariableObjectsArray[variableLocation].GetValue(valueLocation));
                    CleanMatchesNeeded(matchesNeededList);
                }
            }
        }

        for (int testCaseNumber = 0; testCaseNumber < testCasesFullList.size(); testCaseNumber++)
        {
            for (int testValue = 0; testValue < cttVariableObjectsArray.length; testValue++)
            {
                systemOutput = systemOutput + testCasesFullList.get(testCaseNumber).GetValue(testValue) + ", ";
            }
            systemOutput = systemOutput + "\n";
        }
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

    private void FillNthColumns(int nth, List<CTTTestCaseObject> testCasesFullList, CTTVariableObject[] cttVariableObjectsArray)
    {
        int size = 0;
        int testCaseLocation = 0;
        for (int variableLocation = nth - 1; variableLocation >= 0; variableLocation--)
        {
            testCaseLocation = 1;
            if (testCasesFullList.size() == 1)
            {
                for (int valueLocation = 0; valueLocation < cttVariableObjectsArray[variableLocation].GetNumberOfValues(); valueLocation++)
                {
                    testCasesFullList.add(new CTTTestCaseObject(cttVariableObjectsArray.length));
                    testCasesFullList.get(valueLocation + 1).SetValue(variableLocation, cttVariableObjectsArray[variableLocation].GetValue(valueLocation));
                }
            } else
            {
                for (int valueLocation = 0; valueLocation < cttVariableObjectsArray[variableLocation].GetNumberOfValues(); valueLocation++)
                {
                    size = testCasesFullList.size();
                    while (testCaseLocation < size)
                    {
                        if (valueLocation != cttVariableObjectsArray[variableLocation].GetNumberOfValues() - 1)
                        {
                            DuplicateRow(testCaseLocation, testCasesFullList);
                        }
                        testCasesFullList.get(testCaseLocation).SetValue(variableLocation, cttVariableObjectsArray[variableLocation].GetValue(valueLocation));
                        testCaseLocation++;
                    }

                }
            }
        }
    }

    private void DuplicateRow(int location, List<CTTTestCaseObject> testCasesFullList)
    {
        testCasesFullList.add(new CTTTestCaseObject(testCasesFullList.get(location).GetNumberOfValues()));
        for (int valueLocation = 0; valueLocation < testCasesFullList.get(location).GetNumberOfValues(); valueLocation++)
        {
            if (testCasesFullList.get(location).IsSet(valueLocation))
            {
                testCasesFullList.get(testCasesFullList.size() - 1).SetValue(valueLocation, testCasesFullList.get(location).GetValue(valueLocation));
            }
        }
    }

    private List<CTTVariableValuesToMatch> FillMatchesNeeded(List<CTTVariableValuesToMatch> matchesNeededList, CTTVariableObject[] cttVariableObjectsArray, int maxVariableLocation)
    {
        matchesNeededList = new ArrayList<>();
        for (int variableLocation = 0; variableLocation < maxVariableLocation; variableLocation++)
        {
            matchesNeededList.add(new CTTVariableValuesToMatch());
            for (int valueLocation = 0; valueLocation < cttVariableObjectsArray[variableLocation].GetNumberOfValues(); valueLocation++)
            {
                matchesNeededList.get(variableLocation).AddValue(cttVariableObjectsArray[variableLocation].GetValue(valueLocation));
            }
        }
        return matchesNeededList;
    }

    private boolean AnswerFound(int variableLocation, int[] matchLocationsArray, List<CTTVariableValuesToMatch> matchesNeededList, int numMatchesRequired, List<CTTTestCaseObject> testCasesFullList)
    {
        CleanMatchLocationsArray(variableLocation, matchLocationsArray);
        System.out.println(variableLocation);
        System.out.println(matchLocationsArray.length);
        while (caseLocation < testCasesFullList.size() && GetCount(matchLocationsArray) < numMatchesRequired)
        {
            if (testCasesFullList.get(caseLocation).IsNull(matchesNeededList.size()))
            {
                if (testCasesFullList.get(this.caseLocation).IsNull(matchesNeededList.size() - 1)
                        || matchesNeededList.get(matchesNeededList.size() - 1).GetValue(0).equals(testCasesFullList.get(this.caseLocation).GetValue(matchesNeededList.size() - 1)))
                {
                    for (int x = matchesNeededList.size() - 2; x >= 0; x--)
                    {
                        if (matchesNeededList.get(x).ContainsValue(testCasesFullList.get(this.caseLocation).GetValue(x)))
                        {
                            matchLocationsArray[x] = matchesNeededList.get(x).GetLocation(testCasesFullList.get(this.caseLocation).GetValue(x));
                        } else
                        {
                            matchLocationsArray[x] = -1;
                        }
                    }
                }
            }
        }
        return GetCount(matchLocationsArray) >= numMatchesRequired;
    }

    private void CleanMatchLocationsArray(int size, int[] matchLocationsArray)
    {
        matchLocationsArray = new int[size];
        for (int x = 0; x < size; x++)
        {
            matchLocationsArray[x] = -1;
        }
    }

    private int GetCount(int[] matchLocationsArray)
    {
        int count = 0;
        for (int x = 0; x < matchLocationsArray.length; x++)
        {
            if (matchLocationsArray[x] != -1)
            {
                count = count + 1;
            }
        }
        return count;
    }

    private int MakeEasier(int numMatchesRequired, List<CTTTestCaseObject> testCasesFullList, int maxMatchesRequired, int length)
    {
        if (numMatchesRequired != 1)
        {
            numMatchesRequired = numMatchesRequired - 1;
        } else
        {
            testCasesFullList.add(new CTTTestCaseObject(length));
            numMatchesRequired = maxMatchesRequired;
        }
        return numMatchesRequired;
    }

    private void AddTestValue(List<CTTVariableValuesToMatch> matchesNeededList, List<CTTTestCaseObject> testCasesFullList, int[] matchLocationsArray, String currentVariableValue)
    {
        testCasesFullList.get(this.caseLocation).SetValue(matchLocationsArray.length, currentVariableValue);
        for (int x = matchLocationsArray.length - 1; x >= 0; x--)
        {
            if (testCasesFullList.get(this.caseLocation).IsNull(x) && matchLocationsArray[x] != -1)
            {
                testCasesFullList.get(this.caseLocation).SetValue(x, matchesNeededList.get(x).GetValue(matchLocationsArray[x]));
                matchesNeededList.get(x).RemoveValue(matchLocationsArray[x]);
            }
        }
    }

    private void CleanMatchesNeeded(List<CTTVariableValuesToMatch> matchesNeededList)
    {
        for (int x = matchesNeededList.size() - 1; x >= 0; x--)
        {
            if (matchesNeededList.get(x).IsEmpty())
            {
                matchesNeededList.remove(x);
            } else
            {
                break;
            }
        }
    }
}
