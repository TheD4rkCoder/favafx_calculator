package com.bx.fallmerayer.javafxtest.favafx_test;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;


public class HelloController{
    @FXML
    private Label numLabel;
    @FXML
    private Button enter;
    private boolean resDisplay;
    static char []allowed = new char[]{'+', '-', '*', '/', '.', '^'};


    private double calc (double a, double b, String sign){
        switch(sign.charAt(0)){
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                return a/b;
            }
            case '^' -> {
                return Math.pow(a, b);
            }
            case '√' -> {
                return Math.sqrt(a);
            }
        }
        return 0;
    }
    private double calculateRec (Node node){
        if(node.getLeft() == null && node.getRight() == null){
            return Double.parseDouble(node.getComponent());
        }else if(node.getLeft() == null){
            return calc(calculateRec(node.getRight()), 0, node.getComponent());
        }
        return calc(calculateRec(node.getLeft()), calculateRec(node.getRight()), node.getComponent());
    }

    private Node setTree(Node root, String part){
        int index = part.length();
        int ign = 0;
        int skip = 0;
        if(part.charAt(0) == '-'){
            skip = 1;
        }
        for(int i = skip; i < part.length(); ++i){
            if(part.charAt(i) == ')'){
                ign -= 1;
                continue;
            }
            if(ign > 0){
                continue;
            }
            if(part.charAt(i) == '('){
                ign += 1;
                continue;
            }
            if(part.charAt(i) == '+' || part.charAt(i) == '-'){
                index = i;
            }
        }
        ign = 0;
        if(index == part.length()) {
            for(int i = skip; i < part.length(); ++i){
                if(part.charAt(i) == ')'){
                    ign -= 1;
                    continue;
                }
                if(ign > 0){
                    continue;
                }
                if(part.charAt(i) == '('){
                    ign += 1;
                    continue;
                }
                if(part.charAt(i) == '*' || part.charAt(i) == '/'){
                    index = i;
                }
            }
            if(index == part.length()){
                for(int i = skip; i < part.length(); ++i){
                    if(part.charAt(i) == ')'){
                        ign -= 1;
                        continue;
                    }
                    if(ign > 0){
                        continue;
                    }
                    if(part.charAt(i) == '('){
                        ign += 1;
                        continue;
                    }
                    if(part.charAt(i) == '^' || part.charAt(i) == '√'){
                        index = i;
                    }
                }
            }
        }
        if(index == part.length()){
            if(part.charAt(0) == '(' && part.charAt(part.length()-1) == ')'){
                return setTree(root, part.substring(1, part.length() - 1));
            }else{
                if(part.charAt(0) == '(' && part.charAt(2) == ')'){
                    return setTree(root, "-" + part.substring(3));
                }
            }
            root.setComponent(part);
            root.setLeft(null);
            root.setRight(null);
            return root;
        }
        root.setComponent(String.valueOf(part.charAt(index)));
        if(part.charAt(index) == '√'){
            root.setRight(new Node());
            setTree(root.getRight(), part.substring(index + 1));
            return root;
        }
        root.setLeft(new Node());
        root.setRight(new Node());
        setTree(root.getLeft(), part.substring(0, index));
        setTree(root.getRight(), part.substring(index + 1));
        return root;
    }

    @FXML
    public void addInNumText(ActionEvent actionEvent) {
        if (actionEvent.getSource().getClass().equals(Button.class)) {
            Button temp = (Button) actionEvent.getSource();
            if(temp.getText().equals("=")){
                callCalc(null);
                /*
                resDisplay = true;
                String toCalc = numLabel.getText();
                //System.out.println(toCalc);
                Node root = new Node();
                root = setTree(root, toCalc);
                double res = calculateRec(root);
                //System.out.println(res);
                numLabel.setText("");
                if(res == Double.MAX_VALUE || (int) res == Integer.MAX_VALUE || Double.isNaN(res)){
                    numLabel.setText("Error!");
                    return;
                }
                if(res - (int) res == 0){
                    numLabel.setText(String.format("%s%s", numLabel.getText(), "=" + (int)res));
                }else{
                    numLabel.setText(String.format("%s%s", numLabel.getText(), "=" + res));
                }



                /*
                resDisplay = true;
                String textS = numLabel.getText();

                System.out.println(textS);
                int power = 0;
                ArrayList<Integer> numbers = new ArrayList<>(2);
                ArrayList<Character> signs = new ArrayList<>();
                int spot = 0;
                int num = 0;
                for(int i = textS.length() - 1; i >= 0; --i){
                    if(textS.charAt(i) > 47 && textS.charAt(i) < 58){
                        num += Math.pow(10, power)*Integer.parseInt(String.valueOf(textS.charAt(i)));
                        ++power;
                    }else{
                        spot++;
                        numbers.add(num);
                        signs.add(textS.charAt(i));
                        num = 0;
                        power = 0;
                    }
                }
                if(num != 0){
                    numbers.add(num);
                }

                int res = numbers.get(numbers.size() - 1);
                System.out.println(res);
                System.out.println(numbers.get(numbers.size() - 2));
                int sign = signs.size() - 1;
                for(int i = numbers.size() - 2; i >= 0; --i){
                    System.out.println("oha" + res);
                    switch(signs.get(sign)){
                        case '+' -> res += numbers.get(i);
                        case '-' -> res -= numbers.get(i);
                    }
                    System.out.println("oha" + res);
                    --sign;
                }
                numLabel.setText("");
                numLabel.setText(String.format("%s%s", numLabel.getText(), "=" + res));
                //numLabel.setText(numLabel.getText() + "ststst");
                */

            }else{
                if(resDisplay) {
                    resDisplay = false;
                    if(temp.getText().equals("+") || temp.getText().equals("-") || temp.getText().equals("*") || temp.getText().equals("/")) {
                        numLabel.setText(String.format("%s%s", numLabel.getText(), temp.getText()));
                    }else if(temp.getText().equals("x^(")){
                        numLabel.setText(String.format("%s%s", numLabel.getText(), temp.getText().substring(1)));
                    }else if(temp.getText().equals("√")){
                        numLabel.setText("√(");
                    }else{
                        numLabel.setText("");
                        numLabel.setText(temp.getText());
                    }
                }else{
                    if(temp.getText().equals("x^(")){
                        numLabel.setText(String.format("%s%s", numLabel.getText(), temp.getText().substring(1)));
                    }else if(temp.getText().equals("√")){
                        numLabel.setText(String.format("%s%s(", numLabel.getText(), temp.getText()));
                    }else {
                        numLabel.setText(String.format("%s%s", numLabel.getText(), temp.getText()));
                    }
                }
            }
        }
    }


    @FXML
    private void callCalc(KeyEvent evt){
        if(evt != null && !evt.getCharacter().equals("\r")){
            if(evt.getCharacter().equals("^")){
                numLabel.setText(numLabel.getText() + "^(");
            }
            return;
        }
        enter.requestFocus();
        resDisplay = true;
        String toCalc = numLabel.getText();
        Node root = new Node();
        root = setTree(root, toCalc);
        double res = calculateRec(root);
        numLabel.setText("");
        if(res == Double.MAX_VALUE || (int) res == Integer.MAX_VALUE || Double.isNaN(res)){
            numLabel.setText("Error!");
            return;
        }
        if(res - (int) res == 0){
            numLabel.setText("" + (int)res);
        }else{
            numLabel.setText("" + res);
        }
    }

    private boolean testString(String s){
        if(s != null){
            if(!s.equals("")){
                return false;
            }
        }
        return true;
    }

    @FXML
    public void keyHandling(KeyEvent evt){
        if (evt.getCode().getCode() == 8) {
            clearLastSign(new ActionEvent());
            return;
        }
        int b = evt.getCode().getCode();

        if(b > 47 && b < 58) {
            numLabel.setText(String.format("%s%s", numLabel.getText(), evt.getCode().getChar()));
            return;
        }
        if(testString(evt.getText())){
            return;
        }
        char c = evt.getText().charAt(0);
        if(c >= '0' && c <= '9'){
            numLabel.setText(numLabel.getText() + c);
        }else{
            if(c == '^'){
                numLabel.setText(numLabel.getText() + "^(");
                return;
            }
            for (char value : allowed) {
                if (value == c) {
                    numLabel.setText(numLabel.getText() + c);
                }
            }
        }
    }
    public void clearNumText(ActionEvent actionEvent) {
        resDisplay = false;
        numLabel.setText("");
    }
    public void clearLastSign (ActionEvent actionEvent){
        resDisplay = false;
        if(!numLabel.getText().equals(""))
            numLabel.setText(numLabel.getText().substring(0, numLabel.getText().length() - 1));
    }
}