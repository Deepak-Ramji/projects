import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Arrays;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);   
    Color customDarkGray = new Color(80, 80, 80);

    String[] buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    String A = "0";
    String B = "0";
    String operator = "+";
    boolean flag = true;

    Calculator() {
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5, 4, 1, 1));
        buttonPanel.setBackground(customBlack);
        frame.add(buttonPanel, BorderLayout.CENTER);

        for(int i= 0; i<buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setText(buttonValue);
            button.setBorder(new LineBorder(customBlack));
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            
            if(Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            } else if(Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.WHITE);
            }
            
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonValue = button.getText();

                if(Arrays.asList(rightSymbols).contains(buttonValue)){
                    if(buttonValue == "="){
                        A = displayLabel.getText();
                        operation(B, A, operator);
                        double finalValue = Double.parseDouble(B);
                        displayLabel.setText(removeZeroDecimal(finalValue));
                    }else {
                        if(flag){
                            A = displayLabel.getText();
                            displayLabel.setText("0");
                            flag = false;
                            operation(B, A, operator);
                        }
                       operator = buttonValue;
                    }
                }else if(Arrays.asList(topSymbols).contains(buttonValue)){
                    if(buttonValue == "AC") {
                        clearAll();
                        displayLabel.setText(A);
                    }else if(buttonValue == "+/-") {
                        double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay *= -1;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                    }else{
                        double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay /= 100;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                    
                    }
                }else{
                     if(buttonValue == "."){
                        if (!displayLabel.getText().contains(buttonValue)) {
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                     }else if("0123456789".contains(buttonValue)){
                        if(displayLabel.getText() == "0"){
                            displayLabel.setText(buttonValue);
                        }else{
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                        flag=true;
                     }
                }
               }
            });
        }

        frame.setVisible(true);
    }

    void clearAll() {
        A = "0";
        B = "0";
        operator = "+"; 
    }

    String removeZeroDecimal(double number){
        if (number % 1 == 0){
            return Integer.toString((int) number);
        }else return Double.toString(number);
    }

    void operation (String A, String B, String operator){
        if (A != null){
            double numA = Double.parseDouble(A);
            double numB = Double.parseDouble(B);

            if(operator.equals("+")){
                this.B = Double.toString(numA+numB);
            }else if(operator.equals("-")){
                this.B = Double.toString(numA-numB);
            }else if(operator.equals("×")){
                this.B = Double.toString(numA*numB);
            }else{
                this.B = Double.toString(numA/numB);
            }
        }
    }
}
