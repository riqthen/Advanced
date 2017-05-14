package com.riq.mvc.interfaces;

/**
 * Created by 锐 on 2017/3/19.
 */

public interface ICalculator {
    public void pushOperand(String operand);

    public double pushOperate(String operate);

    public void reset();
}
