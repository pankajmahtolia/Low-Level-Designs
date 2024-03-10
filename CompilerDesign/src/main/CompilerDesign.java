package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class CompilerDesign {

    private static class Token {
        String token;
        int precedence;

        Token(String token, int precedence) {
            this.token = token;
            this.precedence = precedence;
        }
    }

    private static final Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2);

    private final Scanner scanner;
    private String currentToken;
    private String returnedToken;
    private int lineNr;
    private final StringBuilder output;
    private final Map<String, Integer> vars;
    private final Stack<Token> stack;

    public CompilerDesign(String code) {
        this.scanner = new Scanner(code);
        this.lineNr = 0;
        this.currentToken = null;
        this.returnedToken = null;
        this.output = new StringBuilder();
        this.vars = new HashMap<>();
        this.stack = new Stack<>();
        tokenize();
    }

    private void raiseError(String message) {
        throw new RuntimeException(lineNr + ": " + message);
    }

    private void tokenize() {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lineNr++;

            for (String token : line.trim().split("\\s+")) {
                if (token.matches("print|=|\\+|-|\\*|/")) {
                    System.out.print(token + " ");
                } else if (token.matches("\\d+")) {
                    System.out.print("number " + token + " ");
                } else if (Character.isAlphabetic(token.charAt(0))) {
                    System.out.print("identifier " + token + " ");
                } else {
                    raiseError("Invalid token " + token);
                }
            }
            System.out.println();
        }
    }

    private String nextToken() {
        if (returnedToken != null) {
            String token = returnedToken;
            returnedToken = null;
            return token;
        } else {
            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }
        }
    }

    private void returnToken(String token) {
        if (returnedToken != null) {
            throw new RuntimeException("Cannot return more than one token at a time");
        }
        returnedToken = token;
    }

    private boolean parseProgram() {
        if (!parseStatement()) {
            raiseError("Expected: statement");
        }
        String token = nextToken();
        while (token != null) {
            returnToken(token);
            if (!parseStatement()) {
                raiseError("Expected: statement");
            }
            token = nextToken();
        }
        return true;
    }

    private boolean parseStatement() {
        if (!parsePrintStatement() && !parseAssignment()) {
            raiseError("Expected: print statement or assignment");
        }
        String token = nextToken();
        if (!token.equals("\n")) {
            raiseError("Expected: end of line");
        }
        return true;
    }

    private boolean parsePrintStatement() {
        String token = nextToken();
        if (token == null || !token.equals("print")) {
            returnToken(token);
            return false;
        }
        if (!parseExpression()) {
            raiseError("Expected: expression");
        }

        int value = stackCollapse();
        System.out.println(value);
        return true;
    }


    private boolean parseAssignment() {
        String token = nextToken();
        if (!token.equals("identifier")) {
            returnToken(token);
            return false;
        }
        String identifier = nextToken();
        token = nextToken();
        if (!token.equals("=")) {
            raiseError("Expected =");
        }
        if (!parseExpression()) {
            raiseError("Expected expression");
        }

        vars.put(identifier, stackCollapse());
        return true;
    }

    private boolean parseExpression() {
        if (!parseValue()) {
            return false;
        }
        while (parseOperator()) {
            if (!parseValue()) {
                raiseError("Expected: value after operator");
            }
        }
        return true;
    }


    private boolean parseValue() {
        String token = nextToken();
        if (token == null) {
            return false;
        }

        if (token.equals("identifier")) {
            String identifier = nextToken();
            if (!vars.containsKey(identifier)) {
                raiseError("Syntax Error: Unknown variable " + identifier);
            } else {
                stackPush(vars.get(identifier));
            }
        } else if (token.equals("number")) {
            String number = nextToken();
            stackPush(Integer.parseInt(number));
        } else {
            returnToken(token);
            return false;
        }
        return true;
    }



    private boolean parseOperator() {
        String token = nextToken();
        if (token == null || !token.matches("\\+|-|\\*|/")) {
            returnToken(token);
            return false;
        }

        Token currentOperator = new Token(token, precedence.get(token));

        // Check for consecutive operators
        while (!stack.isEmpty() && isOperator(stack.peek())) {
            Token topOperator = stack.peek();
            if (currentOperator.precedence <= topOperator.precedence) {
                stackPopAndEvaluate();
            } else {
                break;
            }
        }

        stackPush(currentOperator);
        return true;
    }




    private void stackPopAndEvaluate() {
        Token operator = stackPop();
        int operand2 = Integer.parseInt(stackPop().token);
        int operand1 = Integer.parseInt(stackPop().token);
        int result = performOperation(operand1, operand2, operator.token);
        stackPush(result);
    }




    private void stackPush(int arg) {
        stack.push(new Token(String.valueOf(arg), 0)); // Convert int to Token
    }

    private void stackPush(Token arg) {
        stack.push(arg);
    }

    private Token stackPop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            return null;
        }
    }


    private int stackCollapse() {
        while (!stack.isEmpty()) {
            Token token = stackPop();
            if (isOperator(token)) {
                int operand2 = Integer.parseInt(stackPop().token);
                int operand1 = Integer.parseInt(stackPop().token);
                int result = performOperation(operand1, operand2, token.token);
                stackPush(result);
            } else {
                return Integer.parseInt(token.token);
            }
        }
        return 0;  // Replace with the actual value or logic for stack collapse
    }

    private boolean isOperator(Token token) {
        return token.token.matches("\\+|-|\\*|/");
    }

    private int performOperation(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new RuntimeException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new RuntimeException("Unexpected operator: " + operator);
        }
    }






    public void run() {
        try {
            parseProgram();
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void main(String[] args) {
        String code = "a = 10\nb = 20\nc = 2\nd = a + b * c\n";
        CompilerDesign program = new CompilerDesign(code);
        program.run();
    }
}
