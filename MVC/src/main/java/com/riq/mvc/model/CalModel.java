package com.riq.mvc.model;

import com.riq.mvc.interfaces.ICalculator;

import java.util.Stack;

/**
 * Created by 锐 on 2017/3/19.
 */

public class CalModel implements ICalculator {
    Stack<String> dataStack = new Stack<>();
    boolean isOperate = false;

    @Override
    public void pushOperand(String operand) {
        dataStack.add(operand);
        isOperate = false;
    }

    @Override
    public double pushOperate(String operate) {
        double result = 0;
        if (isOperate) {
            dataStack.pop();
        }
        if (operate.equals("=")) {
            result = CalModel.popOpOffStack(dataStack);
        } else {
            Stack<String> tmpStack = (Stack<String>) dataStack.clone();
            dataStack.add(operate);
            isOperate = true;
        }
        return result;
    }

    @Override
    public void reset() {
        dataStack.removeAllElements();
        isOperate = false;

    }

    public static double popOpOffStack(Stack<String> stack) {
        double result = 0;
        double operand = Double.valueOf(stack.pop());
        if (stack.isEmpty()) {
            return operand;
        }
        String operate = stack.pop();
        if (operate.equals("+")) {
            result = CalModel.popOpOffStack(stack) + operand;
        } else if (operate.equals("-")) {
            result = CalModel.popOpOffStack(stack) - operand;
        } else if (operate.equals("*")) {
            result = CalModel.popOpOffStack(stack) * operand;
        } else if (operate.equals("/")) {
            result = CalModel.popOpOffStack(stack) / operand;
        }
        return result;
    }

}
