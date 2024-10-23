package middleexam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * iOS 사칙연산 계산기 프로그램
 * 
 * @author Suh Jang Ho
 * @version 0.6
 * 
 * @created 2024.10.23
 * @updated 2024.10.24
 * 
 * @changelog
 * [0.1] 계산기 프로그램 구현
 * [0.2] 계산을 했을때 식이 표시되도록 수정, 코드를 북쪽과 중앙으로 나누어 메소드로 분리
 * [0.3] 계산 결과가 정수일 경우 int로 출력, 소수일 경우 double로 출력
 * [0.4] 계산 결과값을 소수점 이하 10자리까지 반올림하여 부동소수점 오차를 해결
 * [0.5] 버튼 배열을 위해 빈 버튼 추가, Readme.md 파일 작성
*  [0.6] 버튼 배경색 및 글자색 수정
 * 
 * @see ChatGPT 부동소수점 오차 해결 참고 
 */

public class Calculator extends JFrame {
    private JTextField display; // 계산 결과와 입력을 표시해줄 텍스트 필드
    private JTextField equation; // 계산식을 표시해줄 텍스트 필드
    private String op; // 연산자를 저장할 변수
    private double num1, num2, result; // 계산에 사용할 변수
    private String previousInput; // 이전 입력을 저장할 변수
    String[] button = {
        "C","±","%","÷",
        "7","8","9","x",
       "4","5","6","-",
       "1","2","3","+",
       ":)","0",".","="
   }; // 버튼 이름 배열
    JButton[] buttons = new JButton[button.length]; // 버튼 배열

    public Calculator(){
        // 프로그램의 창 크기와, 종료, 레이아웃 설정
        setTitle("계산기");
        setSize(250,350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0,0));
        setBackground(Color.DARK_GRAY);


        showNorth(); // 텍스트필드를 북쪽에 배치
        showCenter(); // 버튼을 중앙에 배치
        setVisible(true);
    }

    // 버튼 클릭 이벤트 처리
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.charAt(0)>='0' && command.charAt(0)<='9' || command.equals(".")) {
                // 값이 입력되면 디스플레이에 추가
                display.setText(display.getText() + command);
            } else if (command.equals("C")) {
                // C 버튼 클릭 시 디스플레이 초기화
                display.setText("");
                equation.setText("");
                num1 = num2 = result = 0;
                previousInput = "";
            }else if (command.equals("±")) {
                // ± 버튼 클릭 시 디스플레이의 값이 양수면 음수로, 음수면 양수로 변경
                double currentValue = Double.parseDouble(display.getText());
                currentValue *= -1;
                display.setText(String.valueOf(currentValue));
            }else if(command.equals("=")) {
                // = 버튼 클릭 시 계산 수행
                previousInput = display.getText();
                equation.setText(previousInput);
                num2 = Double.parseDouble(display.getText().split(" ")[2]);
                switch(op){
                    case "+":
                        result=num1+num2;
                        break;
                    case "-":
                        result=num1-num2;
                        break;
                    case "x":
                        result=num1*num2;
                        break;
                    case "÷":
                        result=num1/num2;
                        break;
                    case "%":
                        result=num1%num2;
                        break;
                }

                // 결과값을 소수점 이하 10자리까지 반올림
                result=Math.round(result * 1e10)/1e10;

                // 결과값이 정수인지 확인하여 출력
                if (result == (int) result) {
                    display.setText(String.valueOf((int) result)); // 정수일 경우 int로 출력
                } else {
                    display.setText(String.valueOf(result)); // 소수일 경우 double로 출력
                }
                num1=result;
            } else if(command.equals(":)")){
                // 어떤 이벤트도 발생하지 않음 버튼 배열을 위해 추가
            } else {
                // 연산자 버튼 클릭 시 연산자 저장 및 디스플레이에 표시
                num1=Double.parseDouble(display.getText());
                op=command;
                display.setText(display.getText() + " " + op + " ");
                equation.setText("");
            }
        }
    }
    // 텍스트필드 2개를 북쪽에 배치하는 클래스
    void showNorth(){
        // 텍스트필드를 북쪽에 배치
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new GridLayout(2,0,0,0));
        
        
        // 텍스트필드 생성 및 설정
        equation = new JTextField();
        equation.setEditable(false);
        equation.setBorder(null);
        equation.setBackground(new Color(80,82,85));
        equation.setFont(new Font("Dialog", Font.PLAIN,15));
        equation.setForeground(Color.GRAY);
        //equation.setEnabled(false); // display와 구분되게 하기 위해 비활성화
        equation.setCaretColor(new Color(0, 0, 0, 0)); // 커서 색상을 투명으로 설정
        equation.setHorizontalAlignment(SwingConstants.RIGHT);

        display = new JTextField();
        display.setEditable(false);
        display.setBorder(null);
        display.setBackground(new Color(80,82,85));
        display.setFont(new Font("Dialog", Font.BOLD, 20));
        display.setForeground(Color.WHITE);
        display.setCaretColor(new Color(0, 0, 0, 0)); // 커서 색상을 투명으로 설정
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(equation); panel.add(display);
        add(panel, BorderLayout.NORTH);

    }

    void showCenter(){
        // 버튼 정의 및 패널에 추가
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new GridLayout(5,4,1,1));
        ButtonListener ButtonListener = new ButtonListener();
        
        for(int i=0; i<buttons.length; i++){ {
            buttons[i] = new JButton(button[i]);
            //buttons[i].setFocusable(false);
            buttons[i].addActionListener(ButtonListener);
            buttons[i].setFont(new Font("Dialog", Font.BOLD, 20));
            buttons[i].setForeground(Color.white);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            
            if(button[i].matches("[÷+=x-]")){
                buttons[i].setBackground(new Color(255,159,9));
            } else if(button[i].matches("[C±%]")){
                buttons[i].setBackground(new Color(97,99,102));
            } else if(button[i].equals(":)")){
                buttons[i].setBackground(Color.DARK_GRAY);
                buttons[i].setForeground(Color.darkGray);
                buttons[i].setEnabled(false);
            } else {
                buttons[i].setBackground(new Color(123,125,127));
            }
     
        panel.add(buttons[i]);
        }
        
        add(panel, BorderLayout.CENTER);
        }
                
    }
    public static void main(String[] args) {
        new Calculator();
    }
}