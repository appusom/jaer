/*
Copyright June 13, 2011 Andreas Steiner, Inst. of Neuroinformatics, UNI-ETH Zurich
*/

package ch.unizh.ini.jaer.projects.opticalflow.usbinterface;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * a very simple GUI window that can be used to directly send commands over a
 * serial line and display the text answers
 * 
 * @see dsPIC33F_COM_ConfigurationPanel
 * @see dsPIC33F_COM_OpticalFlowHardwareInterface
 * 
 * @author andstein
 */

public class dsPIC33F_COM_CommandLineWindow extends javax.swing.JFrame {

    dsPIC33F_COM_OpticalFlowHardwareInterface hardwareInterface;
    ArrayList<String> commandHistory;
    int historyI;
    
    /** Creates new form dsPIC33F_COM_CommandLineWindow */
    public dsPIC33F_COM_CommandLineWindow(dsPIC33F_COM_OpticalFlowHardwareInterface hi) {
        initComponents();
        hardwareInterface= hi;
        commandHistory=new ArrayList<String>();
        historyI= 0;
        
        // after disappearing, the window can simply be re-opened by enabling
        // its visibility upon pressing the "cmds" button in the config panel
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public void logString(String str)
    {
        textArea.append( str + "\n");
        textArea.scrollRectToVisible(new Rectangle(0,textArea.getHeight()-2,1,1));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commandLine = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("dsPIC33F_COM_CommandLine");

        commandLine.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        commandLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commandLineActionPerformed(evt);
            }
        });
        commandLine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                commandLineKeyPressed(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        textArea.setLineWrap(true);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(commandLine, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(commandLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void commandLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commandLineActionPerformed
        String command= commandLine.getText();
        commandHistory.add(command);
        historyI= commandHistory.size();
        hardwareInterface.sendCommand(command);
        commandLine.setText("");
    }//GEN-LAST:event_commandLineActionPerformed

    private void commandLineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commandLineKeyPressed
        // implement some minimalistic command history
        if (evt.getKeyCode() == KeyEvent.VK_UP && historyI > 0)
            historyI--;
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN && historyI < commandHistory.size() -1)
            historyI++;
        else return;
        
        commandLine.setText(commandHistory.get(historyI));
        commandLine.selectAll();
    }//GEN-LAST:event_commandLineKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commandLine;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
