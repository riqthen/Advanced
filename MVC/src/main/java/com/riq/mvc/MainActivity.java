package com.riq.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.riq.mvc.model.CalModel;
import com.riq.mvc.view.CaInputView;
import com.riq.mvc.view.CaOutputView;

/**
 * 使用MVC模式创建计算器
 */
public class MainActivity extends AppCompatActivity implements CaInputView.InputHappened {
    //    List<Button> buttonNums = new ArrayList<>();
//    List<Button> buttonOps = new ArrayList<>();
    CaInputView civ;
    CaOutputView cov;
    CalModel calModel;
    String number = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Resources res = getResources();
//        for (int i = 0; i < 9; i++) {
//            if (i <= 5) {
//                int idOp = res.getIdentifier("btno" + i, "id", getPackageName());
//                Button btnOp = (Button) findViewById(idOp);
//                buttonOps.add(btnOp);
//            }
//            int id = res.getIdentifier("btn" + i, "id", getPackageName());
//            Button button = (Button) findViewById(id);
//            buttonNums.add(button);
//        }
        civ = new CaInputView();
        cov = new CaOutputView(this);
        calModel = new CalModel();
    }

    @Override
    public void operandIn(String operand) {
        number = number.equals("0") ? operand : number + operand;
        cov.outputData(number);
    }

    @Override
    public void operateIn(String operate) {
        if (operate.equalsIgnoreCase("c")) {
            calModel.reset();
            number = "0";
            cov.outputData(number);
            return;
        }
        calModel.pushOperand(number);
        double result = calModel.pushOperate(operate);
        if (result % 1d == 0d) {
            int tmp = Double.valueOf(result).intValue();
            cov.outputData(String.valueOf(tmp));
        } else {
            cov.outputData(String.valueOf(result));
        }
        number = "0";
    }
}
