package com.riq.mvc.view;

/**
 * Created by 锐 on 2017/3/18.
 */
//View
public class CaInputView {
    public interface InputHappened {
        public void operandIn(String operand);

        public void operateIn(String operate);
    }
}
