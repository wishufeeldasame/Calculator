package middleexam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * iOS 사칙연산 계산기 프로그램
 * 
 * @author Suh Jang Ho
 * @version 0
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
        
    }

    public static void main(String[] args) {
        new Calculator();
    }
 }