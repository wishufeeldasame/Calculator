package middleexam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * iOS 사칙연산 계산기 프로그램
 * 
 * @author Suh Jang Ho
 * @version 0.1
 * 
 * @created 2024.10.23
 * @updated 2024.10.23
 * 
 * @changelog
 */

 public class Calculator extends JFrame {
    private JTextField display; // 계산 결과와 입력을 표시해줄 텍스트 필드
    private String op; // 연산자를 저장할 변수
    private double num1, num2, result; // 계산에 사용할 변수

    public Calculator(){
        // 프로그램의 창 크기와, 종료, 레이아웃 설정
        setTitle("계산기");
        setSize(300,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 계산 결과와 입력을 표시해줄 텍스트 필드 정의
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        // 버튼 정의 및 패널에 추가
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,4,5,5));
        ButtonListener ButtonListener = new ButtonListener();

        String[] buttons ={
            "C","±","%","÷",
            "7","8","9","x",
            "4","5","6","-",
            "1","2","3","+",
            "0",".","="
        };

        for(String label : buttons){
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.addActionListener(ButtonListener);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // 버튼 클릭 이벤트 처리
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.charAt(0)>='0' && command.charAt(0)<='9' || command.equals(".")){
                // 값이 입력되면 디스플레이에 추가
                display.setText(display.getText() + command);
            } else if (command.equals("C")){
                // C 버튼 클릭 시 디스플레이 초기화
                display.setText("");
                num1 = num2 = result = 0;
            } else if (command.equals("±")){
                // ± 버튼 클릭 시 디스플레이의 값이 양수면 음수로, 음수면 양수로 변경
                double currentValue = Double.parseDouble(display.getText());
                currentValue *= -1;
                display.setText(String.valueOf(currentValue));
            } else if(command.equals("=")){
                // = 버튼 클릭 시 계산 수행
                num2 = Double.parseDouble(display.getText());
                switch(op){
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "x":
                        result = num1 * num2;
                        break;
                    case "÷":
                        result = num1 / num2;
                        break;
                    case "%":
                        result = num1 % num2;
                        break;
                }
                display.setText(String.valueOf(result));
                num1 = result;
            } else {
                // 연산자 버튼 클릭 시 연산자 저장
                num1 = Double.parseDouble(display.getText());
                op = command;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}