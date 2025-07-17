/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package csc310;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class wordgame extends javax.swing.JFrame {

    String[][] word = {
        {"Abundant", "Existing or available in large quantities"},
        {"Benevolent", "Kind and generous"},
        {"Catalyst", "Something that causes change or action"},
        {"Divergent", "Moving or extending in different directions"},
        {"Ephemeral", "Lasting for a very short time"},
        {"Facetious", "Treating serious issues with inappropriate humor"},
        {"Gargantuan", "Enormous or gigantic in size"},
        {"Hapless", "Unlucky or unfortunate"},
        {"Iconoclast", "A person who attacks cherished beliefs or institutions"},
        {"Juxtapose", "To place side by side for comparison"},
        {"Kindle", "To ignite or inspire"},
        {"Labyrinth", "A complex and confusing network of passages"},
        {"Meticulous", "Showing great attention to detail"},
        {"Nebulous", "Unclear, vague, or ill-defined"},
        {"Ostracize", "To exclude or banish from a group or society"},
        {"Pernicious", "Causing harm or ruin; deadly"},
        {"Quixotic", "Unrealistic and impractical"},
        {"Resilient", "Able to recover quickly from difficult conditions"},
        {"Serendipity", "The occurrence of events by chance in a happy or beneficial way"},
        {"Tenacious", "Holding fast, persistent, stubborn"},
        {"Ubiquitous", "Present, appearing, or found everywhere"},
        {"Vicarious", "Experienced in the imagination through the feelings or actions of another person"},
        {"Whimsical", "Playfully quaint or fanciful in an appealing way"},
        {"Xenophobia", "Fear or hatred of foreigners or strangers"},
        {"Yearn", "To have an intense longing or desire"},
        {"Zenith", "The highest point or peak"},
        {"Altruistic", "Showing a selfless concern for the well-being of others"},
        {"Belligerent", "Hostile and aggressive"},
        {"Conundrum", "A confusing and difficult problem or question"},
        {"Deleterious", "Causing harm or damage"},
        {"Enigma", "A person or thing that is mysterious, puzzling, or difficult to understand"},
        {"Fluctuate", "To vary or change irregularly"},
        {"Garrulous", "Excessively talkative, especially on trivial matters"},
        {"Hackneyed", "Lacking significance due to overuse or repetition"},
        {"Ineffable", "Too great or extreme to be expressed or described in words"},
        {"Juxtaposition", "The fact of two things being seen or placed close together with contrasting effect"},
        {"Kindle", "To light or set on fire"},
        {"Languid", "Displaying or having a disinclination for physical exertion or effort"},
        {"Mellifluous", "Sweet or musical; pleasant to hear"},
        {"Nonchalant", "Feeling or appearing casually calm and relaxed"},
        {"Obfuscate", "To deliberately make something unclear or confusing"},
        {"Pernicious", "Having a harmful effect, especially in a gradual or subtle way"},
        {"Querulous", "Complaining in a petulant or whining manner"},
        {"Reticent", "Not revealing one's thoughts or feelings readily"},
        {"Salient", "Most noticeable or important"},
        {"Taciturn", "Reserved or uncommunicative in speech; saying little"},
        {"Unanimous", "Fully in agreement or showing complete agreement"},
        {"Vacillate", "To waver in decision or opinion"},
        {"Wistful", "Having or showing a feeling of vague or regretful longing"},
        {"Xenial", "Friendly, relating to hospitality"},
        {"Yearning", "A feeling of intense longing for something"},
        {"Zen", "A state of calm attentiveness in which one's actions are guided by intuition rather than by conscious effort"},
        {"Abate", "To lessen or reduce in intensity"},
        {"Benevolent", "Showing kindness and goodwill"},
        {"Cacophony", "A harsh, discordant mixture of sounds"},
        {"Debilitate", "To weaken or enfeeble"},
        {"Eclectic", "Deriving ideas or style from a broad range of sources"},
        {"Facade", "The front of a building, or a superficial appearance"},
        {"Garrulous", "Excessively talkative, especially on trivial matters"},
        {"Halcyon", "Denoting a period of time in the past that was idyllically happy"},
        {"Idiosyncrasy", "A mode of behavior or way of thought peculiar to an individual"},
        {"Juxtapose", "To place side by side for contrast"},
        {"Kinetic", "Relating to or resulting from motion"},
        {"Laconic", "Using very few words"},
        {"Mellifluous", "Sweet or musical; pleasant to hear"},
        {"Nefarious", "Wicked or criminal"},
        {"Ostentatious", "Showy; designed to impress"},
        {"Paradigm", "A typical example or pattern of something"},
        {"Quixotic", "Exceedingly idealistic; unrealistic and impractical"},
        {"Recalcitrant", "Having an obstinately uncooperative attitude"},
        {"Sanguine", "Optimistic or positive, especially in a bad situation"},
        {"Taciturn", "Reserved or uncommunicative in speech"},
        {"Ubiquitous", "Present, appearing, or found everywhere"},
        {"Vicarious", "Experienced in the imagination through the feelings of another"},
        {"Wizened", "Shriveled or wrinkled with age"},
        {"Xenophobia", "Dislike of or prejudice against people from other countries"},
        {"Yoke", "To join together; a wooden crosspiece for oxen"},
        {"Zephyr", "A gentle, mild breeze"},
        {"Aesthetic", "Concerned with beauty or the appreciation of beauty"},
        {"Belligerent", "Hostile and aggressive"},
        {"Capricious", "Given to sudden changes of mood or behavior"},
        {"Deleterious", "Causing harm or damage"},
        {"Ephemeral", "Lasting for a very short time"},
        {"Furtive", "Attempting to avoid notice or attention; secretive"}
    };

    int index = 0;
    int score = 0;
    int remainingAttempts = 3;
    int getLetterAttempts = 5;
    int revealedLetters = 0;
    String currentWord = word[index][0].toLowerCase();
    String scrambledWord = scramble(currentWord);
    char[] revealedWord;
    


    // TODO add your handling code here:
// TODO add your handling code here:
    // TODO add your handling code here:
    // TODO add your handling code here:
    // TODO add your handling code here:
    /**
     * Creates new form wordgame
     */
    public wordgame() {
        initComponents();
        jLabel5.setText(scramble(word[index][0].toLowerCase()));
        jLabel7.setText("Score: " + score);
        jLabel8.setText("Get Letter Attempts Remaining: " + getLetterAttempts);

        timer n = new timer();
        Thread t = new Thread(n);
        t.start();

        blink bn = new blink();
        Thread t1 = new Thread(bn);
        t1.start();
    }
    
    private void setNewWord() {
        currentWord = word[index][0].toLowerCase();
        scrambledWord = scramble(currentWord);
        revealedWord = new char[currentWord.length()];
        for (int i = 0; i < revealedWord.length; i++) {
            revealedWord[i] = '_'; // Initialize with underscores
        }
        revealedLetters = 0;
        remainingAttempts = 3;

        jLabel5.setText(scrambledWord);
        jLabel7.setText("Score: " + score);
        jLabel8.setText("Get Letter Attempts Remaining: " + getLetterAttempts);
        jLabel4.setText(new String(revealedWord));
        jLabel6.setText("Time Left: " + remainingAttempts + " sec");
    }


    public String scramble(String text) {
        char[] letters = text.toCharArray();
        Random random = new Random();
        for (int i = 0; i < letters.length; i++) {
            int j = random.nextInt(letters.length);
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }
        return new String(letters);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WORD GAME");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setText("Word");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setText("Your guess");

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel4.setText("jLabel4 to display the hint");

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton1.setText("Guess");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton3.setText("Hint");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jButton4.setText("Reshuffle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel7.setText("Score");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                    .addComponent(jButton4)
                                    .addGap(17, 17, 17))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jTextField2)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String guess = jTextField2.getText().toLowerCase();
        if (guess.equalsIgnoreCase(word[index][0].toLowerCase())) {
            JOptionPane.showMessageDialog(rootPane, "Good Job!!");
            score++;
            jLabel7.setText("Score: " + score);
            jLabel8.setText("Get Letter Attempts Remaining: " + getLetterAttempts);
            getLetterAttempts++;
            nextWord();
        } else {
            remainingAttempts--;
            JOptionPane.showMessageDialog(rootPane, "Wrong! Please try Again");
            if (remainingAttempts == 0) {
                JOptionPane.showMessageDialog(rootPane, "No more attempts! The correct word was: " + currentWord, "Game Over", JOptionPane.WARNING_MESSAGE);
                scoreZeroForWord(); 
                nextWord();
            }
        }
    } 
    
    private void scoreZeroForWord() {
        score += 0;
        jLabel7.setText("Score: " +score);
    }
    
    
    public void nextWord() {
        if (index < word.length - 1) {
            index++;
            setNewWord();
        }else {
            JOptionPane.showMessageDialog(rootPane, "Game over! You have completed all words.");
            index = 0;
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (word.length == index + 1) {
            JOptionPane.showMessageDialog(rootPane, "This is the last word");
        } else {
            index++;
            jLabel4.setText("");
            jLabel5.setText(scramble(word[index][0]).toLowerCase());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (getLetterAttempts > 0 && revealedLetters < currentWord.length()) {
            JOptionPane.showMessageDialog(rootPane, "Next correct letter: " + currentWord.charAt(revealedLetters));
            revealedLetters++;
            getLetterAttempts--;
            jLabel4.setText(word[index][1]);
            jLabel8.setText("Get Letter Attempts Remaining: " + getLetterAttempts);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No more letter hints available!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        jPanel1.setBackground(Color.red);       // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        jPanel1.setBackground(Color.green);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jLabel5.setText(scramble(word[index][0]).toLowerCase());        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(wordgame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(wordgame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(wordgame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(wordgame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new wordgame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
