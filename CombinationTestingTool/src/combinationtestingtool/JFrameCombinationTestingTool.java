package combinationtestingtool;

import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lia
 */
public class JFrameCombinationTestingTool extends javax.swing.JFrame
{

    /**
     * Creates new form JFrameCombinationTestingTool
     */
    public JFrameCombinationTestingTool()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTextInterface = new javax.swing.JTextArea();
        jLabelTextInterface = new javax.swing.JLabel();
        jButtonImport = new javax.swing.JButton();
        jButtonRun = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaTextInterface.setColumns(20);
        jTextAreaTextInterface.setRows(5);
        jScrollPane1.setViewportView(jTextAreaTextInterface);

        jLabelTextInterface.setText("Text Interface");

        jButtonImport.setText("Import .txt File");
        jButtonImport.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonImportActionPerformed(evt);
            }
        });

        jButtonRun.setText("Run");
        jButtonRun.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonRunActionPerformed(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitle.setText("Variable Combination Testing Tool");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTextInterface)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonImport)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRun)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addGap(18, 18, 18)
                .addComponent(jLabelTextInterface)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonImport)
                    .addComponent(jButtonRun))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonImportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonImportActionPerformed
    {//GEN-HEADEREND:event_jButtonImportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImportActionPerformed

    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonRunActionPerformed
    {//GEN-HEADEREND:event_jButtonRunActionPerformed
        // TODO add your handling code here:
        //String lines[] = String.split("\\r?\\n");

        String userInput = this.jTextAreaTextInterface.getText();
        String[] rawUserInputArray = userInput.split("\\n");
        ArrayList<String[]> variableList = new ArrayList<>();
        String[] variableName = new String[1];
        String[] singleVariableInfoArray;
        String[] singleVariableValueArray;
        String[] singleVariableOrganizedArray;
        for (String variable : rawUserInputArray)
        {
            singleVariableInfoArray = variable.split(":");
            variableName[0] = singleVariableInfoArray[0];
            singleVariableValueArray = singleVariableInfoArray[1].split(",");
            singleVariableOrganizedArray = Arrays.copyOf(variableName, variableName.length + singleVariableValueArray.length);
            System.arraycopy(singleVariableValueArray, 0, singleVariableOrganizedArray, variableName.length, singleVariableValueArray.length);

            for (int x = 0; x < singleVariableOrganizedArray.length; x++)
            {
                singleVariableOrganizedArray[x] = singleVariableOrganizedArray[x].trim();
            }
            variableList.add(singleVariableOrganizedArray);
        }
        //sort
        Collections.sort(variableList, new Comparator<String[]>()
        {

            @Override
            public int compare(String[] strings, String[] otherStrings)
            {

                if (strings.length > otherStrings.length)
                {
                    return 1;
                } else if (strings.length < otherStrings.length)
                {
                    return -1;
                }

                return 0;
            }
        });

        //list tests to run
         /*
        Create a list of arrays 
        each array has length = #VariableNames
        first array stores names in order
        each other array holds variable values for one test
        */
        /*
        remove the variable names from the first position of each list in variableList
        Place variable names in the first array in the testArrayList
        list 1 goes into position 1, list 2 goes into position 2... ect
        */
        /*
        Fill the first and second position of each array with the values for the first and second variable
        needs to be all-pairs
        List each value of variable 1 as many times as there are values for variable 2
        List out the values for variable 2 in order as many times as there are values for variable 1
        */
        /*
        loop:
        if more variables
        fill out a list with all the pairs needed for next variable with each variable that came before it
        format: variable value 1,variable value 2
        set position1 variable to 0
        set position2 variable to 0
        set matchRequired to # of variables before this variable
        //loop:
        //if list not empty
        //for value of variable 2 at position1 in list, 
          if matches found = matchRequired for position2 in testArrayList (note empty space  = match)
            add value of variable 2 at position1 in list to testArrayList
            remove matches from listOfAllPairs
          else if position1 < listOfAllPairs.length-1
            position1++
          else if position2 < testArrayList.length-1
            position1 = 0
            position2 ++
          else if matchRequired >1
            matchRequired --
          else
            add a new row
            position1 = 0
            position2 ++
        //       
        */           
        
        //output

    }//GEN-LAST:event_jButtonRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JFrameCombinationTestingTool.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JFrameCombinationTestingTool.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JFrameCombinationTestingTool.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JFrameCombinationTestingTool.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new JFrameCombinationTestingTool().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImport;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JLabel jLabelTextInterface;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaTextInterface;
    // End of variables declaration//GEN-END:variables
}
